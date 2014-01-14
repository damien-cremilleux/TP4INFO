%% TP5
%% Damien Crémilleux, Lauriane Holy
%% Année 2013/2014

%%%%%%%%%%%%%%
%% Partie 1 %%
%%%%%%%%%%%%%%

%%%%%%%%%%%%%%%%%%%%%% SECTION 1.1 (premiers) %%%%%%%%%%%%%%%%%%%%%%
entier(zero).				
entier(s(X)) :-
	entier(X).

%% add(?X,?Y,?Z) : calcul la somme de deux entiers de Peano
add(zero,Y,Y).
add(s(X),Y,s(Z)) :-
	add(X,Y,Z).

%% sub(?X,?Y,?Z) : calcul la différence de deux entiers de Peano
sub(X,zero,X).
sub(s(X),s(Y),Z) :-
	sub(X,Y,Z).

%% prod(+X,,+Y,-Z) : calcul le produit de deux entiers de Peano
prod(zero,_,zero).
prod(s(X),Y, Z) :-
	prod(X,Y,Temp),
	add(Temp,Y,Z).

	
%% factorial(+N,-Y) : calcul la factoriel d'un entier de Peano
factorial(zero,s(zero)).
factorial(s(N),Y) :-
	factorial(N,Temp),
	prod(s(N),Temp,Y).

%%%%%%%%%%%%%%%%%%%%%% SECTION 1.2 (binaire) %%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%% Binary representation
add_bit(0, 0, 0, 0, 0).
add_bit(0, 0, 1, 1, 0).
add_bit(0, 1, 0, 1, 0).
add_bit(0, 1, 1, 0, 1).
add_bit(1, 0, 0, 1, 0).
add_bit(1, 0, 1, 0, 1).
add_bit(1, 1, 0, 0, 1).
add_bit(1, 1, 1, 1, 1).


%% add_bin(?X,?Y,?Z) : somme de deux entiers en représentation binaire
add_bin(X,Y,Z) :-
	add_bin(X,Y,Z,0). % on met la retenu à zero
add_bin([],Y,Y,0).
add_bin(X,[],X,0) :-
	\==(X,[]).

add_bin([],[],Res,1) :-
	add_bin([1],[0],Res,0).

add_bin([],Y,Res,1) :-
	\==(Y,[]),
	add_bin([1],Y,Res,0).

add_bin(X,[],Res,1) :-
	\==(X,[]),
	add_bin(X,[1],Res,0).

add_bin([Elem1|Rest1],[Elem2|Rest2],[Res|Suite],CarryIn):-
	add_bit(Elem1,Elem2,CarryIn,Res,CarryOut),
	add_bin(Rest1,Rest2,Suite,CarryOut).

%% sub_bin(?X,Y,?Z) : différence de deux entiers en représentation binaire
sub_bin(X,Y,Z) :- 
	add_bin(Z,Y,X).


%% prod_bin(+X,+Y,-Z) : produit de deux entiers en représentation binaire
prod_bit(0,_,[]).
prod_bit(1,Res,Res).

prod_bin([],_,[]).

prod_bin([Elem|Rest],Y,Z) :-
	prod_bit(Elem,Y,Res),
	prod_bin(Rest,Y,Tmp),
	add_bin(Res,[0|Tmp],Z).

%% factorial_bin(+N,-Fact) : factoriel d'un entier en représentation binaire
factorial_bin([],[1]). 				%On ne sait pas comment matcher de façon générale avec [0],[0,0], etc.

factorial_bin(N,Fact) :-
	sub_bin(N,[1],Res),
	factorial_bin(Res,FactInt),
	prod_bin(N,FactInt,Fact).

%%%%%%%%%%%%%%%%%%%%%% SECTION 1.3 (entiers) %%%%%%%%%%%%%%%%%%%%%%

%% factorialIs(+N,-Fact) : factorielle d'un entier avec utilisation du prédicat is

factorialIs(0,1).
factorialIs(N,Fact) :-
	N>0,
	S is N-1,
	factorialIs(S,Temp),
	Fact is N*Temp.



%%%%%%%%%%% Optional part
evaluate_numbers(N1, M1, N2, M2) :-
        evaluate(N1, N2),
        evaluate(M1, M2),
        number(N2),
        number(M2).        

evaluate(N, N) :- number(N).

evaluate(add(N1, M1), N) :-
        evaluate_numbers(N1, M1, N2, M2),
        N is N2 + M2.

evaluate(sub(N1, M1), N) :-
        evaluate_numbers(N1, M1, N2, M2),
        N is N2 - M2.

evaluate(prod(N1, M1), N) :-
        evaluate_numbers(N1, M1, N2, M2),
        N is N2 * M2.

evaluate(eq(N1, M1), Res) :-
        evaluate_numbers(N1, M1, N2, M2),
        (
            N2 = M2, Res = t
        ;
            N2 \= M2, Res = f
        ).

evaluate(fun(X, Body), fun(X, Body)).


fresh_variables(Expr, Res) :-
       fresh_variables(Expr, [], Res).

fresh_variables(X, Assoc, Y) :-
        var(X),
        !,
        assoc(X, Assoc, Y).

fresh_variables(add(X1, Y1), Assoc, add(X2, Y2)) :-
        fresh_variables(X1, Assoc, X2),
        fresh_variables(Y1, Assoc, Y2).

fresh_variables(prod(X1, Y1), Assoc, prod(X2, Y2)) :-
        fresh_variables(X1, Assoc, X2),
        fresh_variables(Y1, Assoc, Y2).

fresh_variables(sub(X1, Y1), Assoc, sub(X2, Y2)) :-
        fresh_variables(X1, Assoc, X2),
        fresh_variables(Y1, Assoc, Y2).

fresh_variables(eq(X1, Y1), Assoc, eq(X2, Y2)) :-
        fresh_variables(X1, Assoc, X2),
        fresh_variables(Y1, Assoc, Y2).

fresh_variables(if(Cond1, X1, Y1), Assoc, if(Cond2, X2, Y2)) :-
        fresh_variables(Cond1, Assoc, Cond2),
        fresh_variables(X1, Assoc, X2),
        fresh_variables(Y1, Assoc, Y2).

fresh_variables(Number, _, Number) :- number(Number).

fresh_variables(fun(X, Body1), Assoc, fun(Y, Body2)) :-
        fresh_variables(Body1, [(X, Y) | Assoc], Body2).

fresh_variables(apply(Fun1, Param1), Assoc, apply(Fun2, Param2)) :-
        fresh_variables(Fun1, Assoc, Fun2),
        fresh_variables(Param1, Assoc, Param2).
        
%Fun = fun(N, fun(F, if(eq(N, 0), 1, prod(N, apply(apply(F, sub(N, 1)), F))))), Factorial = fun(N, apply(apply(Fun, N), Fun)), evaluate(apply(Factorial, 42), Res).


%%%%%%%%%%%%%%%%%%
%% Données tests
%%%%%%%%%%%%%%%%%%%

test(0,zero).
test(1,s(zero)).
test(2,s(s(zero))).
test(3,s(s(s(zero)))).
test(4,s(s(s(s(zero))))).
test(5,s(s(s(s(s(zero)))))).
test(6,s(s(s(s(s(s(zero))))))).


test_bin(0,[]).
test_bin(1,[1]).
test_bin(2,[0,1]).
test_bin(3,[1,1]).
test_bin(4,[0,0,1]).
test_bin(5,[1,0,1]).
test_bin(6,[0,1,1]).

%%%%%%%%%%%%%%%%%%
%% Tests
%%%%%%%%%%%%%%%%%%

/* Question 1.1

[eclipse 11]: test(0,X),test(2,Y),add(X,Y,Z).

X = zero
Y = s(s(zero))
Z = s(s(zero))
Yes (0.00s cpu)


[eclipse 12]: test(3,X),test(2,Y),test(5,Z),add(X,Y,Z).

X = s(s(s(zero)))
Y = s(s(zero))
Z = s(s(s(s(s(zero)))))
Yes (0.00s cpu)

[eclipse 13]: test(3,X),test(2,Y),test(6,Z),add(X,Y,Z).

No (0.00s cpu)


[eclipse 14]:  test(3,Z),add(X,Y,Z).

Z = s(s(s(zero)))
X = zero
Y = s(s(s(zero)))
Yes (0.00s cpu, solution 1, maybe more) ? ;

Z = s(s(s(zero)))
X = s(zero)
Y = s(s(zero))
Yes (0.00s cpu, solution 2, maybe more) ? ;

Z = s(s(s(zero)))
X = s(s(zero))
Y = s(zero)
Yes (0.00s cpu, solution 3, maybe more) ? ;

Z = s(s(s(zero)))
X = s(s(s(zero)))
Y = zero
Yes (0.00s cpu, solution 4)


*/

/* Question 1.2
[eclipse 12]: test(5,X),test(3,Y),sub(X,Y,Z).

X = s(s(s(s(s(zero)))))
Y = s(s(s(zero)))
Z = s(s(zero))
Yes (0.00s cpu)


[eclipse 13]: test(3,X),test(3,Y),test(3,Z),sub(X,Y,Z).

No (0.00s cpu)


[eclipse 14]: test(2,Z),sub(X,Y,Z).

Z = s(s(zero))
X = s(s(zero))
Y = zero
Yes (0.00s cpu, solution 1, maybe more) ? ;

Z = s(s(zero))
X = s(s(s(zero)))
Y = s(zero)
Yes (0.00s cpu, solution 2, maybe more) ? ;

Z = s(s(zero))
X = s(s(s(s(zero))))
Y = s(s(zero))
Yes (0.00s cpu, solution 3, maybe more) ? ;

Z = s(s(zero))
X = s(s(s(s(s(zero)))))
Y = s(s(s(zero)))
Yes (0.00s cpu, solution 4, maybe more) ? ;

Z = s(s(zero))
X = s(s(s(s(s(s(zero))))))
Y = s(s(s(s(zero))))
Yes (0.00s cpu, solution 5, maybe more) ? ;

...
*/

/* Question 1.2

[eclipse 15]: test(3,X),test(2,Y),prod(X,Y,Z).

X = s(s(s(zero)))
Y = s(s(zero))
Z = s(s(s(s(s(s(zero))))))
Yes (0.00s cpu)


[eclipse 16]: test(0,X),test(2,Y),prod(X,Y,Z).

X = zero
Y = s(s(zero))
Z = zero
Yes (0.00s cpu)


[eclipse 17]: test(4,X),test(0,Y),prod(X,Y,Z).

X = s(s(s(s(zero))))
Y = zero
Z = zero
Yes (0.00s cpu)
*/


/* Question 1.2

[eclipse 20]: test(3,N),factorial(N,Fact).

N = s(s(s(zero)))
Fact = s(s(s(s(s(s(zero))))))
Yes (0.00s cpu)


[eclipse 21]: test(0,N),factorial(N,Fact).

N = zero
Fact = s(zero)
 Yes (0.00s cpu)


[eclipse 24]: test(6,Six),add(Six,Six,N),factorial(N,Fact).
*** Overflow of the global/trail stack in spite of garbage collection!
You can use the "-g kBytes" (GLOBALSIZE) option to have a larger stack.
Peak sizes were: global stack 131068 kbytes, trail stack 4100 kbytes
Abort
*/

/* Question 1.5
[eclipse 2]: add_bin([0],[0],Z).

Z = [0]
Yes (0.00s cpu, solution 1, maybe more) ?
Type ; for more solutions, otherwise <return> ? ;

No (0.00s cpu)
[eclipse 3]: add_bin([1],[0],Z).

Z = [1]
Yes (0.00s cpu, solution 1, maybe more) ? ;

No (0.00s cpu)
[eclipse 4]: add_bin([1],[1],Z).

Z = [0, 1]
Yes (0.00s cpu, solution 1, maybe more) ? ;

No (0.00s cpu)

[eclipse 6]: add_bin([0,1],[0,1],Z).

Z = [0, 0, 1]
Type ; for more solutions, otherwise <return> ? ;

No (0.00s cpu)
[eclipse 7]: add_bin([0,0,1],[0,1],Z).

Z = [0, 1, 1]
Yes (0.00s cpu, solution 1, maybe more) ?
*/


/* Question 1.6 

[eclipse 10]: sub_bin([0],[0],Z).

Z = []
Yes (0.00s cpu, solution 1, maybe more) ?
Type ; for more solutions, otherwise <return> ? ;

Z = [0]
Yes (0.00s cpu, solution 2, maybe more) ? ;

No (0.00s cpu)
[eclipse 11]: sub_bin([1],[0],Z).

Z = [1]
Yes (0.00s cpu, solution 1, maybe more) ? ;

No (0.00s cpu)
[eclipse 12]: sub_bin([1],[1],Z).

Z = []
Yes (0.00s cpu, solution 1, maybe more) ? ;

Z = [0]
Yes (0.00s cpu, solution 2, maybe more) ? ;

No (0.00s cpu)
[eclipse 13]: sub_bin([1,1,1],[1,0,0],Z).

Z = [0, 1, 1]
Yes (0.00s cpu, solution 1, maybe more) ?
Type ; for more solutions, otherwise <return> ? ;

No (0.00s cpu)
[eclipse 14]: sub_bin([1,1,1],[1,1,1,1],Z).

No (0.00s cpu)
*/



/* Question 1.7

[eclipse 21]: prod_bin([0],[0],Z).

Z = [0]
Yes (0.00s cpu, solution 1, maybe more) ? ;

No (0.00s cpu)
[eclipse 22]: prod_bin([1],[0],Z).

Z = [0]
Yes (0.00s cpu, solution 1, maybe more) ? ;

No (0.00s cpu)

[eclipse 24]: prod_bin([1,1],[1],Z).

Z = [1, 1]
Yes (0.00s cpu, solution 1, maybe more) ?
Type ; for more solutions, otherwise <return> ? ;

No (0.01s cpu)
[eclipse 25]: prod_bin([1,1],[1,1,1],Z).

Z = [1, 0, 1, 0, 1]
Yes (0.00s cpu, solution 1, maybe more) ? ;

No (0.00s cpu)
*/

/* Question 1.8
// Le resultat est correct mais apparrait plusieurs fois
[eclipse 13]: factorial_bin([1,0,1],X).

X = [0, 0, 0, 1, 1, 1, 1]
Yes (0.00s cpu, solution 1, maybe more) ? ;

X = [0, 0, 0, 1, 1, 1, 1]
Yes (0.00s cpu, solution 2, maybe more) ? ;

X = [0, 0, 0, 1, 1, 1, 1]
Yes (0.00s cpu, solution 3, maybe more) ? ;

X = [0, 0, 0, 1, 1, 1, 1]
Yes (0.00s cpu, solution 4, maybe more) ? ;

No (0.00s cpu)


[eclipse 15]: factorial_bin([1,1],X).

X = [0, 1, 1]
Yes (0.00s cpu, solution 1, maybe more) ? ;

X = [0, 1, 1]
Yes (0.00s cpu, solution 2, maybe more) ? ;

No (0.00s cpu)


// Quand le bit de poid fort est à 0, le calcul échoue
[eclipse 16]: factorial_bin([1,1,0],X).

No (0.00s cpu)


/* Question 1.9

[eclipse 46]: factorialIs(2,Res).
Res = 2
Yes (0.00s cpu, solution 1, maybe more) ? ;

No (0.00s cpu)


[eclipse 47]: factorialIs(4,Res).
Res = 24
Yes (0.00s cpu, solution 1, maybe more) ? ;

No (0.00s cpu)

[eclipse 48]: factorialIs(5,Res).
Res = 120
Yes (0.00s cpu, solution 1, maybe more) ? ;

No (0.00s cpu)

[eclipse 50]: factorialIs(15,Res).
Res = 1307674368000
Yes (0.00s cpu, solution 1, maybe more) ? ;

No (0.00s cpu)

*/