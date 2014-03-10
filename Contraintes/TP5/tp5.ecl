%% TP 5
%% Damien Crémilleux - Lauriane Holy

:-lib(ic).
:-lib(branch_and_bound).

%% Question 5.1
%%%%%%%%%%%%%%%

getTechniciens([](5,7,2,6,9,3,7,5,3)).
getQuantite([](140,130,60,95,70,85,100,30,45)).
getBenefice([](4,5,8,5,6,4,7,10,11)).

getFabriquer(Fabriquer):-
	dim(Fabriquer,[9]),
	Fabriquer #:: 0..1.


%% Question 5.2
%%%%%%%%%%%%%%%
%% prodVecteur(+V1,+V2,-VRes) : renvoie [a*d, b*e, c*f] avec V1=[a,b,c] et V2=[d, e,f]
produitVecteur(V1, V2, VRes) :-
	dim(V1, Dim),
	dim(VRes,Dim),
	(foreacharg(X, V1), foreacharg(Y, V2), foreacharg(Z, VRes) do
	Z #=  X * Y).

%% sommeVecteur(+V,-VRes) : renvoie la somme des éléments du vecteur
sommeVecteur(V,Res) :-
	(foreacharg(X,V), fromto(0, In, Out, Res) do
    Out #= X + In
).

%% produit scalaire
produitScalaire(V1,V2,Res) :-
	produitVecteur(V1, V2, VRes),
	sommeVecteur(VRes,Res).
	
%% Transforme un tableau en liste
%%arrayToList(Array,List) :-
%%	Array =.. [_|List].

 %% nombreTotalOuvrierNecessaire(+VectQuantite,-Res) nombre Total d'Ouvrier Necessaire 
nombreTotalOuvrierNecessaire(VectFabriquer, Res) :- 
 	getTechniciens(VectTechniciens),
 	produitScalaire(VectTechniciens, VectFabriquer, Res).
		
%% beneficeParTelephone(VectRes) : le vecteur benefice oar sorte de téléphone
beneficeParTelephone(VectFabriquer, VectRes) :- 
 	getBenefice(VectBenefice), 
	getQuantite(VectQuantite),
	produitVecteur(VectBenefice,VectQuantite,VectTemp),
	produitVecteur(VectTemp, VectFabriquer, VectRes).
 
%% profitTotal(-Res) : Le profit total
profitTotal(VectFabriquer,Res) :-
	beneficeParTelephone(VectFabriquer, VectRes),
	sommeVecteur(VectRes,Res).
	
%% Question 5.3
%%%%%%%%%%%%%%%
%% pose_contraintes(?Fabriquer, ?NbTechniciensTotal, ?Profit) : pose les contraintes 

pose_contraintes(_Fabriquer, NbTechniciensTotal,Profit):-
	NbTechniciensTotal #=< 22,
	Profit #> 2600.

solve(Fabriquer,Profit):-
	getFabriquer(Fabriquer),
	nombreTotalOuvrierNecessaire(Fabriquer, NbTechniciensTotal),
	profitTotal(Fabriquer,Profit),
	pose_contraintes(Fabriquer, NbTechniciensTotal, Profit),
	labeling(Fabriquer).


%% Question 5.4
%%%%%%%%%%%%%%%
%% Exemple pour minimize
test(X) :-
	[X,Y,Z,W] #:: [0..10],
	X #= Z+Y+2*W,
	X #\= Z+Y+W,
	labeling([X]).


%% [eclipse 11]: minimize(test(X), X).
%% Found a solution with cost 1
%% Found no solution with cost -1.0Inf .. 0

%% X = 1
%% Yes (0.00s cpu)

%% [eclipse 8]: minimize(test(X), X).
%% Found a solution with cost 2
%% Found no solution with cost -1.0Inf .. 1

%% X = 2
%% Yes (0.00s cpu)

%% On constate qu'il faut labeler sur toutes les variables sinon le résultat donné par Eclipse est faux. En effet les delayed goal ne sont pas pris en compte.

%% Question 5.5
%%%%%%%%%%%%%%%
%% On obtient :

%% [eclipse 6]: Q #= -P ,minimize(solve(F,P), Q).
%% Found a solution with cost -2665
%% Found no solution with cost -1.0Inf .. -2666

%% Q = -2665
%% P = 2665
%% F = [](0, 1, 1, 0, 0, 1, 1, 0, 1)

%% La solution est donc 2665

%% Question 5.6
%%%%%%%%%%%%%%%

pose_contraintes2(_Fabriquer, NbTechniciensTotal,Profit):-
	NbTechniciensTotal #=< 22,
	Profit #>= 1000.

solve2(Fabriquer,Profit,NbTechniciensTotal):-
	getFabriquer(Fabriquer),
	nombreTotalOuvrierNecessaire(Fabriquer, NbTechniciensTotal),
	profitTotal(Fabriquer,Profit),
	pose_contraintes2(Fabriquer, NbTechniciensTotal, Profit),
	labeling(Fabriquer).



/*

%%%%%%%%%%%
%% TESTS %%
%%%%%%%%%%%

%% Question 5.1
%%%%%%%%%%%%%%%

[eclipse 19]: getFabriquer(Fabriquer).

Fabriquer = [](_280{[0, 1]}, _298{[0, 1]}, _316{[0, 1]}, _334{[0, 1]}, _352{[0, 1]}, _370{[0, 1]}, _388{[0, 1]}, _406{[0, 1]}, _424{[0, 1]})
Yes (0.00s cpu)


%% Question 5.2
%%%%%%%%%%%%%%%
[eclipse 25]:  nombreTotalOuvrierNecessaire(R).

R = R{0 .. 47}

There are 17 delayed goals. Do you want to see them? (y/n) 

Delayed goals:
	_771{0 .. 5} - 5 * _292{[0, 1]} #= 0
	_1041{0 .. 7} - 7 * _310{[0, 1]} #= 0
	_1311{0 .. 2} - 2 * _328{[0, 1]} #= 0
	_1581{0 .. 6} - 6 * _346{[0, 1]} #= 0
	_1851{0 .. 9} - 9 * _364{[0, 1]} #= 0
	_2121{0 .. 3} - 3 * _382{[0, 1]} #= 0
	_2391{0 .. 7} - 7 * _400{[0, 1]} #= 0
	_2661{0 .. 5} - 5 * _418{[0, 1]} #= 0
	_2931{0 .. 3} - 3 * _436{[0, 1]} #= 0
	_3158{0 .. 12} - _771{0 .. 5} - _1041{0 .. 7} #= 0
	_3277{0 .. 14} - _3158{0 .. 12} - _1311{0 .. 2} #= 0
	_3396{0 .. 20} - _3277{0 .. 14} - _1581{0 .. 6} #= 0
	_3515{0 .. 29} - _3396{0 .. 20} - _1851{0 .. 9} #= 0
	_3634{0 .. 32} - _3515{0 .. 29} - _2121{0 .. 3} #= 0
	_3753{0 .. 39} - _3634{0 .. 32} - _2391{0 .. 7} #= 0
	_3872{0 .. 44} - _3753{0 .. 39} - _2661{0 .. 5} #= 0
	R{0 .. 47} - _3872{0 .. 44} - _2931{0 .. 3} #= 0
Yes (0.00s cpu)


[eclipse 26]: beneficeParTelephone(VectRes).

VectRes = [](_770{0 .. 4}, _1040{0 .. 5}, _1310{0 .. 8}, _1580{0 .. 5}, _1850{0 .. 6}, _2120{0 .. 4}, _2390{0 .. 7}, _2660{0 .. 10}, _2930{0 .. 11})

There are 9 delayed goals. Do you want to see them? (y/n) 

Delayed goals:
	_770{0 .. 4} - 4 * _292{[0, 1]} #= 0
	_1040{0 .. 5} - 5 * _310{[0, 1]} #= 0
	_1310{0 .. 8} - 8 * _328{[0, 1]} #= 0
	_1580{0 .. 5} - 5 * _346{[0, 1]} #= 0
	_1850{0 .. 6} - 6 * _364{[0, 1]} #= 0
	_2120{0 .. 4} - 4 * _382{[0, 1]} #= 0
	_2390{0 .. 7} - 7 * _400{[0, 1]} #= 0
	_2660{0 .. 10} - 10 * _418{[0, 1]} #= 0
	_2930{0 .. 11} - 11 * _436{[0, 1]} #= 0
Yes (0.00s cpu)



Res = Res{0 .. 60}

There are 17 delayed goals. Do you want to see them? (y/n) 

Delayed goals:
	_771{0 .. 4} - 4 * _293{[0, 1]} #= 0
	_1041{0 .. 5} - 5 * _311{[0, 1]} #= 0
	_1311{0 .. 8} - 8 * _329{[0, 1]} #= 0
	_1581{0 .. 5} - 5 * _347{[0, 1]} #= 0
	_1851{0 .. 6} - 6 * _365{[0, 1]} #= 0
	_2121{0 .. 4} - 4 * _383{[0, 1]} #= 0
	_2391{0 .. 7} - 7 * _401{[0, 1]} #= 0
	_2661{0 .. 10} - 10 * _419{[0, 1]} #= 0
	_2931{0 .. 11} - 11 * _437{[0, 1]} #= 0
	_3158{0 .. 9} - _771{0 .. 4} - _1041{0 .. 5} #= 0
	_3277{0 .. 17} - _3158{0 .. 9} - _1311{0 .. 8} #= 0
	_3396{0 .. 22} - _3277{0 .. 17} - _1581{0 .. 5} #= 0
	_3515{0 .. 28} - _3396{0 .. 22} - _1851{0 .. 6} #= 0
	_3634{0 .. 32} - _3515{0 .. 28} - _2121{0 .. 4} #= 0
	_3753{0 .. 39} - _3634{0 .. 32} - _2391{0 .. 7} #= 0
	_3872{0 .. 49} - _3753{0 .. 39} - _2661{0 .. 10} #= 0
	Res{0 .. 60} - _3872{0 .. 49} - _2931{0 .. 11} #= 0
Yes (0.00s cpu)


%% Question 5.3
%%%%%%%%%%%%%%%
[eclipse 41]: solve(Fab,P).

Fab = [](0, 1, 1, 0, 0, 1, 1, 0, 1)
P = 2665
Yes (0.00s cpu, solution 1, maybe more) ? ;

No (0.00s cpu)


%% Question 5.6
%%%%%%%%%%%%%%%

[eclipse 19]: minimize(solve2(F,P,O), O).
Found a solution with cost 10
Found a solution with cost 9
Found a solution with cost 8
Found a solution with cost 7
Found no solution with cost -1.0Inf .. 6

F = [](1, 0, 1, 0, 0, 0, 0, 0, 0)
P = 1040
O = 7
Yes (0.00s cpu)

*/