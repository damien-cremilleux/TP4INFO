%% TP 10 MONDES POSSIBLES
%% Cremilleux Damien - Holy Lauriane



% dana likes cody
% bess does not like dana
% cody does not like abby
% nobody likes someone who does not like her
% abby likes everyone who likes bess
% dana likes everyone bess likes
% everybody likes somebody

people([abby, bess, cody, dana]).

%% Question 1.1
%% make_all_pairs(+,-) : 

make_all_pairs_elem([],_,[]).
make_all_pairs_elem([Elem|Rest],Elem2,[likes(Elem,Elem2)|ListeRes]) :-
	make_all_pairs_elem(Rest,Elem2,ListeRes).

make_all_pairs([],[]).
make_all_pairs(ListeElem,Res) :-
	make_all_pairs2(ListeElem,ListeElem,Res).

make_all_pairs2(_,[],[]).

make_all_pairs2(ListeElem,[Elem2|Reste2],Res) :-
	make_all_pairs_elem(ListeElem,Elem2,ResTmp),
	make_all_pairs2(ListeElem,Reste2,ResTmp2),
	append(ResTmp,ResTmp2,Res).

%% Question 1.2
%% sub_list(+,-): 'a list * 'a list
sub_list([],[]).

sub_list([Elem|Rest],[Elem|Res]):- % on prend le premier élément
	sub_list(Rest,Res).

sub_list([_|Rest],Res):- % on passe le premier élément
	sub_list(Rest,Res).


%% Question 1.3
%% proposition(+) : likes('a,'a) list
proposition1(ListeLikes) :-
	member(likes(dana,cody),ListeLikes).

proposition2(ListeLikes) :-
	not(member(likes(bess,dana),ListeLikes)).

proposition3(ListeLikes) :-
	not(member(likes(cody,abby),ListeLikes)).

proposition4(ListeLikes) :-
	prop4(ListeLikes,ListeLikes).

prop4([likes(X,Y)|Reste],ListeLikes) :-
	member(likes(Y,X),ListeLikes),
	prop4(Reste,ListeLikes).

prop4([],_).

proposition5(ListeLikes) :-
	prop5(ListeLikes,ListeLikes).

prop5([],_).

prop5([likes(X,bess)|Reste],ListeLikes) :-
	member(likes(abby,X),ListeLikes),
	prop5(Reste,ListeLikes).

prop5([likes(_,Y)|Reste],ListeLikes) :-
	\==(Y,bess),
	prop5(Reste,ListeLikes).


proposition6([likes(dana,X),likes(bess,X)]).
proposition7([likes(abby,X),likes(X,bess)]).


% Questions 1.6 and 1.7
test_possible_worlds :-
        possible_worlds(World),
        writeln(World),
        fail.

%% TESTS
/*
%% Question 1.1

[eclipse 10]: make_all_pairs([1,2],Res).	

Res = [likes(1, 1), likes(2, 1), likes(1, 2), likes(2, 2)]
Yes (0.00s cpu)


[eclipse 11]: people(X),make_all_pairs(X,Res).

X = [abby, bess, cody, dana]
Res = [likes(abby, abby), likes(bess, abby), likes(cody, abby), likes(dana, abby), likes(abby, bess), likes(bess, bess), likes(cody, bess), likes(dana, bess), likes(abby, cody), likes(bess, cody), likes(cody, cody), likes(dana, cody), likes(abby, dana), likes(bess, dana), likes(cody, dana


%% Question 1.2
[eclipse 84]: sub_list([1,2],Res).

Res = [1, 2]
Yes (0.00s cpu, solution 1, maybe more) ? ;

Res = [1]
Yes (0.00s cpu, solution 2, maybe more) ? ;

Res = [2]
Yes (0.00s cpu, solution 3, maybe more) ? ;

Res = []
Yes (0.00s cpu, solution 4)
[eclipse 85]: people(X),sub_list(X,Res).

X = [abby, bess, cody, dana]
Res = [abby, bess, cody, dana]
Yes (0.00s cpu, solution 1, maybe more) ? ;

X = [abby, bess, cody, dana]
Res = [abby, bess, cody]
Yes (0.00s cpu, solution 2, maybe more) ? ;

X = [abby, bess, cody, dana]
Res = [abby, bess, dana]
Yes (0.01s cpu, solution 3, maybe more) ? ;

X = [abby, bess, cody, dana]
Res = [abby, bess]
Yes (0.01s cpu, solution 4, maybe more) ? ;

X = [abby, bess, cody, dana]
Res = [abby, cody, dana]
Yes (0.01s cpu, solution 5, maybe more) ? ;

X = [abby, bess, cody, dana]
Res = [abby, cody]
Yes (0.01s cpu, solution 6, maybe more) ? ;

X = [abby, bess, cody, dana]
Res = [abby, dana]
Yes (0.01s cpu, solution 7, maybe more) ? ;

X = [abby, bess, cody, dana]
Res = [abby]
Yes (0.01s cpu, solution 8, maybe more) ? ;

X = [abby, bess, cody, dana]
Res = [bess, cody, dana]
Yes (0.01s cpu, solution 9, maybe more) ? ;

X = [abby, bess, cody, dana]
Res = [bess, cody]
Yes (0.01s cpu, solution 10, maybe more) ? ;

X = [abby, bess, cody, dana]
Res = [bess, dana]
Yes (0.01s cpu, solution 11, maybe more) ? ;

X = [abby, bess, cody, dana]
Res = [bess]
Yes (0.01s cpu, solution 12, maybe more) ? ;

X = [abby, bess, cody, dana]
Res = [cody, dana]
Yes (0.01s cpu, solution 13, maybe more) ? ;

X = [abby, bess, cody, dana]
Res = [cody]
Yes (0.01s cpu, solution 14, maybe more) ? ;

X = [abby, bess, cody, dana]
Res = [dana]
Yes (0.01s cpu, solution 15, maybe more) ? ;

X = [abby, bess, cody, dana]
Res = []
Yes (0.01s cpu, solution 16)


%% Question 1.3

[eclipse 55]: proposition1([likes(dana,cody),likes(dana,dana)]).

Yes (0.00s cpu, solution 1, maybe more) ? ;

No (0.00s cpu)
[eclipse 56]: proposition1([likes(bess,dana),likes(dana,dana)]).

No (0.00s cpu)

[eclipse 53]: proposition2([likes(dana,cody),likes(dana,dana)]).

Yes (0.00s cpu)
[eclipse 54]: proposition2([likes(bess,dana),likes(dana,dana)]).

No (0.00s cpu)

[eclipse 61]: proposition3([likes(bess,dana),likes(dana,dana)]).

Yes (0.00s cpu)
[eclipse 62]: proposition3([likes(bess,dana),likes(cody,abby)]).

No (0.00s cpu)

[eclipse 115]: proposition4([likes(bess,dana),likes(dana,bess)]). 

Yes (0.00s cpu, solution 1, maybe more) ? ;

No (0.00s cpu)
[eclipse 116]: proposition4([likes(bess,dana),likes(dana,bess),likes(dana,cody)]). 

No (0.00s cpu)
*/