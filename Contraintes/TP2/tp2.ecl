%% TP2
%% Damien Crémilleux - Lauriane Holy

:-lib(ic_symbolic).
:-lib(ic).

%% Question 2.1
%%%%%%%%%%%%%%%
%% Définition des domaines
:- local domain(pays(anglais, espagnol, ukrainien, norvegien, japonais)).
:- local domain(couleur(rouge,vert,blanc, jaune,bleu)).
:- local domain(boisson(cafe, the, lait, jusOrange, eau)).
:- local domain(voiture(bmw, ford, toyota, honda, datsun)).
:- local domain(animaux(chien, serpent, renard, cheval, zebre)).


%% Question 2.2
%%%%%%%%%%%%%%%
%% domaines_maison(m(?Pays,?Couleur,?Boisson,?Voiture,?Animaux,?Numero) : contraint le domaine des varaibles qui compose une maison

domaines_maison(m(Pays,Couleur,Boisson,Voiture,Animal,Numero)) :-
	Pays &:: pays,
	Couleur &:: couleur,
	Boisson &:: boisson,
	Voiture &:: voiture,
	Animal &:: animaux,
	Numero #:: 1..5.


%% Question 2.3
%%%%%%%%%%%%%%%
%% rue(?Rue) : unifie rue à la liste des maisons et pose les contraintes de domaine. Fixe également la valeur des variables Numero de chaque maison

rue([m(P1,C1,B1,V1,A1,1),m(P2,C2,B2,V2,A2,2),m(P3,C3,B3,V3,A3,3),m(P4,C4,B4,V4,A4,4),m(P5,C5,B5,V5,A5,5)]):-
	domaines_maison(m(P1,C1,B1,V1,A1,1)),
	domaines_maison(m(P2,C2,B2,V2,A2,2)),
	domaines_maison(m(P3,C3,B3,V3,A3,3)),
	domaines_maison(m(P4,C4,B4,V4,A4,4)),
	domaines_maison(m(P5,C5,B5,V5,A5,5)),
	ic_symbolic:alldifferent([P1,P2,P3,P4,P5]),
	ic_symbolic:alldifferent([C1,C2,C3,C4,C5]),
	ic_symbolic:alldifferent([B1,B2,B3,B4,B5]),
	ic_symbolic:alldifferent([V1,V2,V3,V4,V5]),
	ic_symbolic:alldifferent([A1,A2,A3,A4,A5]).
	

%% Question 2.4
%%%%%%%%%%%%%%%
%% ecrit_maison(?Rue) : récupère chaque élément de Rue et l'écrit
ecrit_maison(Rue) :-
	(foreach(X,Rue) do writeln(X)).


%% Question 2.5
%%%%%%%%%%%%%%%
%%getVarList(?Rue,?Liste) : recupère la liste des variables du problème
getVarList(Rue,Liste):-
	(foreach(m(P,C,B,V,A,_N),Rue),
	fromto([],In, Out, Liste)
    do
Out = [P,C,B,V,A| In]
).


%% labeling_symbolic(+Liste) : prédicat de labeling
labeling_symbolic([]).
labeling_symbolic([Elem|Rest]) :-
	ic_symbolic:indomain(Elem),
	labeling_symbolic(Rest).

% Question 2.6
%%%%%%%%%%%%%%%
%%resoude(?Rue) trouve une solution respectant les contraintes 
resoudre(Rue):-
	rue(Rue),
	getVarList(Rue,List),
	contraintesSimples(Rue),
	contrainteE(Rue),
	contrainteJ(Rue),
	contrainteK(Rue),
	contrainteN(Rue),
	labeling_symbolic(List),
	ecrit_maison(Rue).


% Question 2.7
%%%%%%%%%%%%%%%
%% On pose les contraintes de l'énoncé

contraintesSimples(Rue) :-
	(foreach(m(P,C,B,V,A,N),Rue) do 
    (
	(P &= anglais) #= (C &= rouge),		%Contrainte A
	(P &= espagnol) #=  (A &= chien),       %Contrainte B
	(C &= vert) #= (B &= cafe),		%Contrainte C
	(P &= ukrainien) #= (B &= the),		%Contrainte D
	(V &= bmw) #= (A &= serpent),		%Contrainte F
	(C &= jaune) #= (V &= toyota),		%Contrainte G
	(B &= lait) #= (N #= 3),		%Contrainte H
	(P &= norvegien) #=(N #= 1),		%Contrainte I
	(V &= honda) #=(B &= jusOrange),	%Contrainte L
	(V &= datsun) #=(P &= japonais)	        %Contrainte M
    )
).



contrainteE(Rue) :-
	(foreach(m(_P,C,_B,_V,_A,N),Rue), param(Rue) do
(
    (foreach(m(_P1,C1,_B1,_V1,_A1,N1),Rue),
	param(N,C) do
    (C1 &= vert) and (C &= blanc) => (N1 #= N+1)
    )
)
).

contrainteJ(Rue) :-
	(foreach(m(_P,_C,_B,V,_A,N),Rue), param(Rue) do
(
    (foreach(m(_P1,_C1,_B1,_V1,A1,N1),Rue),
	param(N,V) do
    (V &= ford) and (A1 &= renard) => ((N #= N1+1) or( N #= N1-1))
    )
)
).

contrainteK(Rue) :-
	(foreach(m(_P,_C,_B,V,_A,N),Rue), param(Rue) do
(
    (foreach(m(_P1,_C1,_B1,_V1,A1,N1),Rue),
	param(N,V) do 
    (V &= toyota) and (A1 &= cheval) => ((N #= N1+1) or( N #= N1-1))
)
)
).

contrainteN(Rue) :-
	(foreach(m(P,_C,_B,_V,_A,N),Rue), param(Rue) do
(
    (foreach(m(_P1,C1,_B1,_V1,_A1,N1),Rue),
	param(P,N) do
    
    (P &= norvegien) and (C1 &= bleu) => ((N #= N1+1) or( N #= N1-1))
)
)
).

% Question 2.8
%%%%%%%%%%%%%%%

%% Le japonais possède un zèbre, et le norvégien boit de l'eau


% Question de compréhension
%%%%%%%%%%%%%%%%%%%%%%%%%%%

%% Il aurait été possible de ne pas fixer le numéro de maison, mais cela nous aurait obligé à poser une contrainte supplémentaire sur les numeros et donc augmenter l'arbre de recherche. 

%% Exemple de maison
m1(m(anglais,rouge,cafe,bmw,chien,1)).
m2(m(espagnol, vert, the, ford, serpent, 2)).
m3(m(ukrainien, blanc, lait, toyota, renard, 3)).
m4(m(norvegien, jaune, jusOrange, hoda, cheval, 4)).
m5(m(japonais, bleu, eau, datsun, zebre, 5)).

/*

%%%%%%%%%%%
%% TESTS %%
%%%%%%%%%%%

%% Question 2.2
%%%%%%%%%%%%%%%

[eclipse 8]: domaines_maison(m(anglais,rouge, cafe,bmw, chien, 2)).

Yes (0.00s cpu)

[eclipse 9]: domaines_maison(m(anglais,rouge, cafe,citroen, chien, 2)).
Variable or domain value expected in citroen &:: voiture
Aborting execution ...
Abort


%% Question 2.3
%%%%%%%%%%%%%%%

[eclipse 13]: m1(A),m2(B),m3(C),m4(D),m5(E), rue([A,B,C,D,E]).
WARNING: module 'ic_global' does not exist, loading library...
queues.eco loaded in 0.00 seconds
ordset.eco loaded in 0.01 seconds
heap_array.eco loaded in 0.01 seconds
graph_algorithms.eco loaded in 0.04 seconds
max_flow.eco loaded in 0.00 seconds
flow_constraints_support.eco loaded in 0.00 seconds
ic_sequence.eco loaded in 0.01 seconds
ic_global.eco loaded in 0.06 seconds
lists.eco  loaded in 0.00 seconds

A = m(anglais, rouge, cafe, bmw, chien, 1)
B = m(espagnol, vert, the, ford, serpent, 2)
C = m(ukrainien, blanc, lait, toyota, renard, 3)
D = m(norvegien, jaune, jusOrange, hoda, cheval, 4)
E = m(japonais, bleu, eau, datsun, zebre, 5)
Yes (0.06s cpu)
[eclipse 14]: m1(A),m2(B),m3(C),m4(D), rue([A,B,C,D,D]).

No (0.00s cpu)



%% Question 2.4
%%%%%%%%%%%%%%%

[eclipse 17]: m1(A),m2(B),m3(C),m4(D),m4(E), ecrit_maison([A,B,C,D,E]).
m(anglais, rouge, cafe, bmw, chien, 1)
m(espagnol, vert, the, ford, serpent, 2)
m(ukrainien, blanc, lait, toyota, renard, 3)
m(norvegien, jaune, jusOrange, hoda, cheval, 4)
m(norvegien, jaune, jusOrange, hoda, cheval, 4)

A = m(anglais, rouge, cafe, bmw, chien, 1)
B = m(espagnol, vert, the, ford, serpent, 2)
C = m(ukrainien, blanc, lait, toyota, renard, 3)
D = m(norvegien, jaune, jusOrange, hoda, cheval, 4)
E = m(norvegien, jaune, jusOrange, hoda, cheval, 4)
Yes (0.00s cpu)


%% Question 2.5
%%%%%%%%%%%%%%%
[eclipse 3]: rue(A), getVarList(A,Liste).
WARNING: module 'ic_global' does not exist, loading library...
queues.eco loaded in 0.00 seconds
ordset.eco loaded in 0.00 seconds
heap_array.eco loaded in 0.01 seconds
graph_algorithms.eco loaded in 0.02 seconds
max_flow.eco loaded in 0.00 seconds
flow_constraints_support.eco loaded in 0.00 seconds
ic_sequence.eco loaded in 0.00 seconds
ic_global.eco loaded in 0.05 seconds
lists.eco  loaded in 0.01 seconds

A = [m(_377{[anglais, espagnol, ukrainien, norvegien, japonais]}, _481{[rouge, vert, blanc, jaune, bleu]}, _585{[cafe, the, lait, jusOrange, eau]}, _689{[bmw, ford, toyota, hoda, datsun]}, _793{[chien, serpent, renard, cheval, zebre]}, 1), m(_930{[anglais, espagnol, ukrainien, norvegien, japonais]}, _1034{[rouge, vert, blanc, jaune, bleu]}, _1138{[cafe, the, lait, jusOrange, eau]}, _1242{[bmw, ford, toyota, hoda, datsun]}, _1346{[chien, serpent, renard, cheval, zebre]}, 2), m(_1483{[anglais, espagnol, ukrainien, norvegien, japonais]}, _1587{[rouge, vert, blanc, jaune, bleu]}, _1691{[cafe, the, lait, jusOrange, eau]}, _1795{[bmw, ford, toyota, hoda, datsun]}, _1899{[chien, serpent, renard, cheval, zebre]}, 3), m(_2036{[anglais, espagnol, ukrainien, norvegien, japonais]}, _2140{[rouge, vert, blanc, jaune, bleu]}, _2244{[cafe, the, lait, jusOrange, eau]}, _2348{[bmw, ford, toyota, hoda, datsun]}, _2452{[chien, serpent, renard, cheval, zebre]}, 4), m(_2589{[anglais, espagnol, ukrainien, norvegien, japonais]}, _2693{[rouge, vert, blanc, jaune, bleu]}, _2797{[cafe, the, lait, jusOrange, eau]}, _2901{[bmw, ford, toyota, hoda, datsun]}, _3005{[chien, serpent, renard, cheval, zebre]}, 5)]
Liste = [_2589{[anglais, espagnol, ukrainien, norvegien, japonais]}, _2693{[rouge, vert, blanc, jaune, bleu]}, _2797{[cafe, the, lait, jusOrange, eau]}, _2901{[bmw, ford, toyota, hoda, datsun]}, _3005{[chien, serpent, renard, cheval, zebre]}, _2036{[anglais, espagnol, ukrainien, norvegien, japonais]}, _2140{[rouge, vert, blanc, jaune, bleu]}, _2244{[cafe, the, lait, jusOrange, eau]}, _2348{[bmw, ford, toyota, hoda, datsun]}, _2452{[chien, serpent, renard, cheval, zebre]}, _1483{[anglais, espagnol, ukrainien, norvegien, japonais]}, _1587{[rouge, vert, blanc, jaune, bleu]}, _1691{[cafe, the, lait, jusOrange, eau]}, _1795{[bmw, ford, toyota, hoda, ...]}, _1899{[chien, serpent, renard, ...]}, _930{[anglais, espagnol, ...]}, _1034{[rouge, ...]}, _1138{[...]}, _1242{...}, ...]

There are 30 delayed goals. Do you want to see them? (y/n) 
Yes (0.06s cpu)

%% Question 2.5 - suite
%%%%%%%%%%%%%%%
[eclipse 6]: rue(A), getVarList(A,Liste), labeling_symbolic(Liste).

A = [m(japonais, bleu, eau, datsun, zebre, 1), m(norvegien, jaune, jusOrange, hoda, cheval, 2), m(ukrainien, blanc, lait, toyota, renard, 3), m(espagnol, vert, the, ford, serpent, 4), m(anglais, rouge, cafe, bmw, chien, 5)]
Liste = [anglais, rouge, cafe, bmw, chien, espagnol, vert, the, ford, serpent, ukrainien, blanc, lait, toyota, renard, norvegien, jaune, jusOrange, hoda, ...]
Yes (0.00s cpu, solution 1, maybe more) ? ;

A = [m(japonais, bleu, eau, datsun, cheval, 1), m(norvegien, jaune, jusOrange, hoda, zebre, 2), m(ukrainien, blanc, lait, toyota, renard, 3), m(espagnol, vert, the, ford, serpent, 4), m(anglais, rouge, cafe, bmw, chien, 5)]
Liste = [anglais, rouge, cafe, bmw, chien, espagnol, vert, the, ford, serpent, ukrainien, blanc, lait, toyota, renard, norvegien, jaune, jusOrange, hoda, ...]
Yes (0.00s cpu, solution 2, maybe more) ? ;

A = [m(japonais, bleu, eau, hoda, zebre, 1), m(norvegien, jaune, jusOrange, datsun, cheval, 2), m(ukrainien, blanc, lait, toyota, renard, 3), m(espagnol, vert, the, ford, serpent, 4), m(anglais, rouge, cafe, bmw, chien, 5)]
Liste = [anglais, rouge, cafe, bmw, chien, espagnol, vert, the, ford, serpent, ukrainien, blanc, lait, toyota, renard, norvegien, jaune, jusOrange, datsun, ...]
Yes (0.00s cpu, solution 3, maybe more) ? ;

A = [m(japonais, bleu, eau, hoda, cheval, 1), m(norvegien, jaune, jusOrange, datsun, zebre, 2), m(ukrainien, blanc, lait, toyota, renard, 3), m(espagnol, vert, the, ford, serpent, 4), m(anglais, rouge, cafe, bmw, chien, 5)]
Liste = [anglais, rouge, cafe, bmw, chien, espagnol, vert, the, ford, serpent, ukrainien, blanc, lait, toyota, renard, norvegien, jaune, jusOrange, datsun, ...]
Yes (0.00s cpu, solution 4, maybe more) ? 

...


%% Question 2.6
%%%%%%%%%%%%%%%

[eclipse 10]: resoudre([A,B,C,D,E]).
m(japonais, bleu, eau, datsun, zebre, 1)
m(norvegien, jaune, jusOrange, hoda, cheval, 2)
m(ukrainien, blanc, lait, toyota, renard, 3)
m(espagnol, vert, the, ford, serpent, 4)
m(anglais, rouge, cafe, bmw, chien, 5)

A = m(japonais, bleu, eau, datsun, zebre, 1)
B = m(norvegien, jaune, jusOrange, hoda, cheval, 2)
C = m(ukrainien, blanc, lait, toyota, renard, 3)
D = m(espagnol, vert, the, ford, serpent, 4)
E = m(anglais, rouge, cafe, bmw, chien, 5)
Yes (0.00s cpu, solution 1, maybe more) ? ;
m(japonais, bleu, eau, datsun, cheval, 1)
m(norvegien, jaune, jusOrange, hoda, zebre, 2)
m(ukrainien, blanc, lait, toyota, renard, 3)
m(espagnol, vert, the, ford, serpent, 4)
m(anglais, rouge, cafe, bmw, chien, 5)

A = m(japonais, bleu, eau, datsun, cheval, 1)
B = m(norvegien, jaune, jusOrange, hoda, zebre, 2)
C = m(ukrainien, blanc, lait, toyota, renard, 3)
D = m(espagnol, vert, the, ford, serpent, 4)
E = m(anglais, rouge, cafe, bmw, chien, 5)
Yes (0.00s cpu, solution 2, maybe more) ? ;
m(japonais, bleu, eau, hoda, zebre, 1)
m(norvegien, jaune, jusOrange, datsun, cheval, 2)
m(ukrainien, blanc, lait, toyota, renard, 3)
m(espagnol, vert, the, ford, serpent, 4)
m(anglais, rouge, cafe, bmw, chien, 5)


%% Question 2.7
%%%%%%%%%%%%%%%
[eclipse 2]: resoudre([A,B,C,D,E]).
WARNING: module 'ic_global' does not exist, loading library...
queues.eco loaded in 0.00 seconds
ordset.eco loaded in 0.00 seconds
heap_array.eco loaded in 0.01 seconds
graph_algorithms.eco loaded in 0.01 seconds
max_flow.eco loaded in 0.00 seconds
flow_constraints_support.eco loaded in 0.00 seconds
ic_sequence.eco loaded in 0.01 seconds
ic_global.eco loaded in 0.05 seconds
lists.eco  loaded in 0.00 seconds
m(norvegien, jaune, eau, toyota, renard, 1)
m(ukrainien, bleu, the, ford, cheval, 2)
m(anglais, rouge, lait, bmw, serpent, 3)
m(espagnol, blanc, jusOrange, honda, chien, 4)
m(japonais, vert, cafe, datsun, zebre, 5)

A = m(norvegien, jaune, eau, toyota, renard, 1)
B = m(ukrainien, bleu, the, ford, cheval, 2)
C = m(anglais, rouge, lait, bmw, serpent, 3)
D = m(espagnol, blanc, jusOrange, honda, chien, 4)
E = m(japonais, vert, cafe, datsun, zebre, 5)
Yes (0.06s cpu, solution 1, maybe more) ? ;

No (0.07s cpu)


*/