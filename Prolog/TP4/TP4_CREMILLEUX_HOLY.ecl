/**
TP 4 Arbres binaires - Prolog

@author Damien CRÉMILLEUX
@author Lauriane HOLY
@version Annee scolaire 2013/2014_
*/


/*
-------------------------------------------------------------------------------
 Définition des prédicats
-------------------------------------------------------------------------------
*/

%% arbre_binaire(+B) : reussi si arbre binaire et si le noeud est un entier
arbre_binaire(vide).
arbre_binaire(arb_bin(R,G,D)) :-
	integer(R),
	arbre_binaire(G),
	arbre_binaire(D).

%% dans_arbre_binaire(+E,+B) : réussit si E et l'une des étiquettes de l'arbre Binaire B.
%%On utilise une coupure pour ne plus explorer les branches une fois qu'on a trouvé l'élement
%%On utilise une deuxième coupure lorsqu'on trouve l'élément dans l'arbre gauche afin de ne finir l'exploration.
dans_arbre_binaire(Elem,arb_bin(Elem,_,_)):-
	!.
dans_arbre_binaire(Elem,arb_bin(_,G,_)):-
	dans_arbre_binaire(Elem,G),
	!.
dans_arbre_binaire(Elem,arb_bin(_,_,D)):-
	dans_arbre_binaire(Elem,D).


%% sous_arbre_binaire(+S,+B) : réussit si S est un sous-arbre de B
sous_arbre_binaire(Arbre,Arbre) :-
	!.
sous_arbre_binaire(Sous_arbre,arb_bin(_,ArbreG2,_)) :-
	sous_arbre_binaire(Sous_arbre,ArbreG2),
	!. 					% on arrete l'exploration de l'arbre une fois le un sous-arbre trouvé
sous_arbre_binaire(Sous_arbre,arb_bin(_,_,ArbreD2)) :-
	sous_arbre_binaire(Sous_arbre,ArbreD2),
	!. 					%idem



%% remplacer(+SA1,+SA2,+B,-B1) : B1 est l'arbre B dans laquelle toute les occurences du sous arbre SA1 est remplacée par le sous-arbre SA2
remplacer(_,_,vide,vide).
remplacer(SA1,SA2,SA1,SA2).
remplacer(SA1,SA2,arb_bin(Elem,ArbreG1,ArbreD1),arb_bin(Elem,ArbreG2,ArbreD2)):-
	SA1\==arb_bin(Elem,ArbreG1,ArbreD1),
	remplacer(SA1,SA2,ArbreG1,ArbreG2),
	remplacer(SA1,SA2,ArbreD1,ArbreD2).

%%isomorphes(+B1,+B2) : réussit si B1 et B2 sont isomorphes
isomorphes(B,B).
isomorphes(arb_bin(Elem,ArbreG1,ArbreD1),arb_bin(Elem,ArbreG2,ArbreD2)) :-
	arb_bin(Elem,ArbreG1,ArbreD1)\==arb_bin(Elem,ArbreG2,ArbreD2),
	isomorphes(ArbreG1,ArbreG2),
	isomorphes(ArbreD1,ArbreD2).
isomorphes(arb_bin(Elem,ArbreG1,ArbreD1),arb_bin(Elem,ArbreG2,ArbreD2)) :-
	arb_bin(Elem,ArbreG1,ArbreD1)\==arb_bin(Elem,ArbreG2,ArbreD2),
	isomorphes(ArbreG1,ArbreD2),
	isomorphes(ArbreD1,ArbreG2).

%% infixe(+B,-L) : rend la liste L, parcours infixe de B
infixe(vide,[]).
infixe(arb_bin(Elem,ArbreG,ArbreD),L) :-
	infixe(ArbreG,L1),
	infixe(ArbreD,L2),
	append(L1,[Elem|L2],L).

%% nb_etiq_sup(+E,+B,?N) : N nombre d'étiquettes strictement supérieures à E, dans l'abre binaire d'entiers ordonnés B
nb_etiq_sup(_,vide,0).
nb_etiq_sup(E,arb_bin(Elem,_,ArbreD),N) :-
	>=(E,Elem),
        nb_etiq_sup(E,ArbreD,N).
nb_etiq_sup(E,arb_bin(Elem,ArbreG,ArbreD),N) :-
	<(E,Elem),
        nb_etiq_sup(E,ArbreD,N1),
        nb_etiq_sup(E,ArbreG,N2),
        N is N1+N2+1.
        
%% insertion_arbre_ordonne(+X,+B1,B2) : B2 est l'arbre ordonné obtenu par l'insertion de la valeur X dans B1
insertion_arbre_ordonne(X,vide,arb_bin(X,vide,vide)).
insertion_arbre_ordonne(X,arb_bin(X,ArbreG,ArbreD),arb_bin(X,ArbreG,ArbreD)).
insertion_arbre_ordonne(X,arb_bin(Elem,ArbreG,ArbreD1),arb_bin(Elem,ArbreG,ArbreD2)) :-
        >(X,Elem),
        insertion_arbre_ordonne(X,ArbreD1,ArbreD2).

insertion_arbre_ordonne(X,arb_bin(Elem,ArbreG1,ArbreD),arb_bin(Elem,ArbreG2,ArbreD)) :-
        <(X,Elem),
        insertion_arbre_ordonne(X,ArbreG1,ArbreG2).

%% insertion_arbre_ordonne(+X,+B) :  B est l'arbre ordonné dans lequel on a inséré X                         
insertion_arbre_ordonne1(X,B):-	
	free(B),
	B = arb_bin(X,_,_),
        !.                    % la coupure empeche de rappeller insertion_arbre_ordonne1 sur B.
insertion_arbre_ordonne1(X,arb_bin(X,_,_)).
insertion_arbre_ordonne1(X,arb_bin(Elem,_,ArbreD)):-
	>(X,Elem),
	insertion_arbre_ordonne1(X,ArbreD).

insertion_arbre_ordonne1(X,arb_bin(Elem,ArbreG,_)):-
        <(X,Elem),
	insertion_arbre_ordonne1(X,ArbreG).
	



/*
-------------------------------------------------------------------------------
 Tests
-------------------------------------------------------------------------------
*/

% Quelques arbres à copier coller pour vous faire gagner du temps, mais
% n'hésitez pas à en définir d'autres

test(arbre_vide,vide).

test(arbre1,arb_bin(1, arb_bin(2, arb_bin(6, vide, vide), vide), arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide)))).

test(arbre2,arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide))).

%%test(arbre3,arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, 7, vide))). on le commente car ce n'est pas un arbre binaire. On a testé cela avant de commenter.

test(arbre4,arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, arb_bin(6, vide, vide), arb_bin(7, vide, vide)))).

test(arbre5,arb_bin(3, arb_bin(5, arb_bin(6, vide, vide), arb_bin(7, vide, vide)), arb_bin(4, vide, vide))).

test(arbre6,arb_bin(3, arb_bin(6, vide, vide), arb_bin(5, arb_bin(4, vide, vide), arb_bin(7, vide, vide)))).

test(arbre7,arb_bin(8, arb_bin(4, arb_bin(2, vide, vide), arb_bin(6, vide, vide)), arb_bin(12, arb_bin(10, vide, vide), vide))).

test(arbre8,arb_bin(8, arb_bin(4, arb_bin(2, _, _), arb_bin(6, _, _)), arb_bin(12, arb_bin(10, _, _), _))).

test(arbre9,arb_bin(6,arb_bin(2,arb_bin(1,vide,vide),arb_bin(4,vide,vide)),arb_bin(8,vide,arb_bin(10,vide,vide)))).

test(arbre10,arb_bin(8,arb_bin(2,arb_bin(1,vide,vide),arb_bin(4,vide,vide)),arb_bin(6,vide,arb_bin(10,vide,vide)))).

test(arbre11,arb_bin(6,arb_bin(2,arb_bin(1,vide,vide),arb_bin(4,vide,vide)),arb_bin(8,arb_bin(2,arb_bin(1,vide,vide),arb_bin(4,vide,vide)),arb_bin(10,vide,vide)))).

test(arbre12, arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide))).

test(arbre13, arb_bin(5, arb_bin(6,vide,vide), arb_bin(7,vide,vide))).

test(arbre14, arb_bin(1,vide,vide)).




%%**************************************************

/* Question 2.1

[eclipse 9]: test(Nom,Arbre),arbre_binaire(Arbre).

Nom = arbre_vide
Arbre = vide
Yes (0.00s cpu, solution 1, maybe more) ? ;

Nom = arbre1
Arbre = arb_bin(1, arb_bin(2, arb_bin(6, vide, vide), vide), arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide)))
Yes (0.00s cpu, solution 2, maybe more) ? ;

Nom = arbre2
Arbre = arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide))
Yes (0.00s cpu, solution 3, maybe more) ? ;

Nom = arbre4
Arbre = arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, arb_bin(6, vide, vide), arb_bin(7, vide, vide)))
Yes (0.00s cpu, solution 4, maybe more) ? ;

Nom = arbre5
Arbre = arb_bin(3, arb_bin(5, arb_bin(6, vide, vide), arb_bin(7, vide, vide)), arb_bin(4, vide, vide))
Yes (0.00s cpu, solution 5, maybe more) ? ;

Nom = arbre6
Arbre = arb_bin(3, arb_bin(6, vide, vide), arb_bin(5, arb_bin(4, vide, vide), arb_bin(7, vide, vide)))
Yes (0.00s cpu, solution 6, maybe more) ? ;

Nom = arbre7
Arbre = arb_bin(8, arb_bin(4, arb_bin(2, vide, vide), arb_bin(6, vide, vide)), arb_bin(12, arb_bin(10, vide, vide), vide))
Yes (0.00s cpu, solution 7, maybe more) ? ;

Nom = arbre8
Arbre = arb_bin(8, arb_bin(4, arb_bin(2, vide, vide), arb_bin(6, vide, vide)), arb_bin(12, arb_bin(10, vide, vide), vide))
Yes (0.00s cpu, solution 8, maybe more) ? ;

Nom = arbre9
Arbre = arb_bin(6, arb_bin(2, arb_bin(1, vide, vide), arb_bin(4, vide, vide)), arb_bin(8, vide, arb_bin(10, vide, vide)))
Yes (0.00s cpu, solution 9, maybe more) ? ;

Nom = arbre10
Arbre = arb_bin(8, arb_bin(2, arb_bin(1, vide, vide), arb_bin(4, vide, vide)), arb_bin(6, vide, arb_bin(10, vide, vide)))
Yes (0.00s cpu, solution 10, maybe more) ? ;

Nom = arbre10
Arbre = arb_bin(6, arb_bin(2, arb_bin(1, vide, vide), arb_bin(4, vide, vide)), arb_bin(8, arb_bin(2, arb_bin(1, vide, vide), arb_bin(4, vide, vide)), arb_bin(10, vide, vide)))
Yes (0.00s cpu, solution 11)
*/

%%**************************************************

/* Question 2.2

[eclipse 14]: test(Nom,Arbre),dans_arbre_binaire(8,Arbre).

Nom = arbre7
Arbre = arb_bin(8, arb_bin(4, arb_bin(2, vide, vide), arb_bin(6, vide, vide)), arb_bin(12, arb_bin(10, vide, vide), vide))
Yes (0.00s cpu, solution 1, maybe more) ? ;

Nom = arbre8
Arbre = arb_bin(8, arb_bin(4, arb_bin(2, _270, _271), arb_bin(6, _274, _275)), arb_bin(12, arb_bin(10, _282, _283), _279))
Yes (0.00s cpu, solution 2, maybe more) ? ;

Nom = arbre9
Arbre = arb_bin(6, arb_bin(2, arb_bin(1, vide, vide), arb_bin(4, vide, vide)), arb_bin(8, vide, arb_bin(10, vide, vide)))
Yes (0.00s cpu, solution 3, maybe more) ? ;

Nom = arbre10
Arbre = arb_bin(8, arb_bin(2, arb_bin(1, vide, vide), arb_bin(4, vide, vide)), arb_bin(6, vide, arb_bin(10, vide, vide)))
Yes (0.00s cpu, solution 4, maybe more) ? ;

Nom = arbre11
Arbre = arb_bin(6, arb_bin(2, arb_bin(1, vide, vide), arb_bin(4, vide, vide)), arb_bin(8, arb_bin(2, arb_bin(1, vide, vide), arb_bin(4, vide, vide)), arb_bin(10, vide, vide)))
Yes (0.00s cpu, solution 5)
*/

%%**************************************************

/* Question 2.3
L'arbre binaire 8 a été commenté pour cette question

[eclipse 30]: test(Nom1,Arbre1),test(Nom2,Arbre2),sous_arbre_binaire(Arbre1,Arbre2).

Nom1 = arbre2
Arbre1 = arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide))
Nom2 = arbre1
Arbre2 = arb_bin(1, arb_bin(2, arb_bin(6, vide, vide), vide), arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide)))
Yes (0.00s cpu, solution 1, maybe more) ? ;

Nom1 = arbre2
Arbre1 = arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide))
Nom2 = arbre2
Arbre2 = arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide))
Yes (0.00s cpu, solution 2, maybe more) ? ;

Nom1 = arbre2
Arbre1 = arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide))
Nom2 = arbre12
Arbre2 = arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide))
Yes (0.01s cpu, solution 3, maybe more) ? ;

Nom1 = arbre4
Arbre1 = arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, arb_bin(6, vide, vide), arb_bin(7, vide, vide)))
Nom2 = arbre4
Arbre2 = arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, arb_bin(6, vide, vide), arb_bin(7, vide, vide)))
Yes (0.01s cpu, solution 4, maybe more) ? ;

Nom1 = arbre5
Arbre1 = arb_bin(3, arb_bin(5, arb_bin(6, vide, vide), arb_bin(7, vide, vide)), arb_bin(4, vide, vide))
Nom2 = arbre5
Arbre2 = arb_bin(3, arb_bin(5, arb_bin(6, vide, vide), arb_bin(7, vide, vide)), arb_bin(4, vide, vide))
Yes (0.01s cpu, solution 5, maybe more) ? ;

Nom1 = arbre6
Arbre1 = arb_bin(3, arb_bin(6, vide, vide), arb_bin(5, arb_bin(4, vide, vide), arb_bin(7, vide, vide)))
Nom2 = arbre6
Arbre2 = arb_bin(3, arb_bin(6, vide, vide), arb_bin(5, arb_bin(4, vide, vide), arb_bin(7, vide, vide)))
Yes (0.01s cpu, solution 6, maybe more) ? ;

Nom1 = arbre11
Arbre1 = arb_bin(6, arb_bin(2, arb_bin(1, vide, vide), arb_bin(4, vide, vide)), arb_bin(8, arb_bin(2, arb_bin(1, vide, vide), arb_bin(4, vide, vide)), arb_bin(10, vide, vide)))
Nom2 = arbre11
Arbre2 = arb_bin(6, arb_bin(2, arb_bin(1, vide, vide), arb_bin(4, vide, vide)), arb_bin(8, arb_bin(2, arb_bin(1, vide, vide), arb_bin(4, vide, vide)), arb_bin(10, vide, vide)))
Yes (0.01s cpu, solution 7, maybe more) ? ;

Nom1 = arbre12
Arbre1 = arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide))
Nom2 = arbre1
Arbre2 = arb_bin(1, arb_bin(2, arb_bin(6, vide, vide), vide), arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide)))
Yes (0.01s cpu, solution 8, maybe more) ? ;

Nom1 = arbre12
Arbre1 = arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide))
Nom2 = arbre2
Arbre2 = arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide))
Yes (0.01s cpu, solution 9, maybe more) ? ;

Nom1 = arbre12
Arbre1 = arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide))
Nom2 = arbre12
Arbre2 = arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide))
Yes (0.01s cpu, solution 10, maybe more) ? ;

No (0.01s cpu)
*/

%%**************************************************

/* Question 2.3

[eclipse 36]: test(Nom1,Arbre1),test(Nom2,Arbre2),sous_arbre_binaire(Arbre1,Arbre2).

Nom1 = arbre_vide
Arbre1 = vide
Nom2 = arbre_vide
Arbre2 = vide

Yes (0.00s cpu, solution 1, maybe more) ? ;

...

Nom1 = arbre1
Arbre1 = arb_bin(1, arb_bin(2, arb_bin(6, vide, vide), vide), arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide)))
Nom2 = arbre8
Arbre2 = arb_bin(8, arb_bin(4, arb_bin(2, arb_bin(1, arb_bin(2, arb_bin(6, vide, vide), vide), arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide))), _363), arb_bin(6, _366, _367)), arb_bin(12, arb_bin(10, _374, _375), _371))
Yes (0.00s cpu, solution 14, maybe more) ? ;

Nom1 = arbre2
Arbre1 = arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide))
Nom2 = arbre1
Arbre2 = arb_bin(1, arb_bin(2, arb_bin(6, vide, vide), vide), arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide)))
Yes (0.00s cpu, solution 15, maybe more) ? ;

Nom1 = arbre2
Arbre1 = arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide))
Nom2 = arbre2
Arbre2 = arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide))
Yes (0.00s cpu, solution 16, maybe more) ? ;

Nom1 = arbre2
Arbre1 = arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide))
Nom2 = arbre8
Arbre2 = arb_bin(8, arb_bin(4, arb_bin(2, arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide)), _351), arb_bin(6, _354, _355)), arb_bin(12, arb_bin(10, _362, _363), _359))
Yes (0.00s cpu, solution 17, maybe more) ? ;

Nom1 = arbre2
Arbre1 = arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide))
Nom2 = arbre12
Arbre2 = arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide))
Yes (0.00s cpu, solution 18, maybe more) ? ;
...

Nom1 = arbre12
Arbre1 = arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide))
Nom2 = arbre12
Arbre2 = arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide))
Yes (0.01s cpu, solution 38)
*/



%%**************************************************

/* Question 2.4

[eclipse 5]: test(arbre12,X),test(arbre14,Y),test(arbre1,Z),remplacer(X,Y,Z,B).

X = arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide))
Y = arb_bin(1, vide, vide)
Z = arb_bin(1, arb_bin(2, arb_bin(6, vide, vide), vide), arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide)))
B = arb_bin(1, arb_bin(2, arb_bin(6, vide, vide), vide), arb_bin(1,
 vide, vide))
Yes (0.00s cpu, solution 1, maybe more) ? ;
;

No (0.00s cpu)


[eclipse 6]: test(arbre12,X),test(arbre1,Y),test(arbre12,Z),remplacer(X,Y,Z,B).

X = arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide))
Y = arb_bin(1, arb_bin(2, arb_bin(6, vide, vide), vide), arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide)))
Z = arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide))
B = arb_bin(1, arb_bin(2, arb_bin(6, vide, vide), vide), arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide)))
Yes (0.00s cpu, solution 1, maybe more) ? ;
;

No (0.00s cpu)


[eclipse 8]: test(arbre12,X),test(arbre1,Y),test(arbre14,Z),remplacer(X,Y,Z,B).

X = arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide))
Y = arb_bin(1, arb_bin(2, arb_bin(6, vide, vide), vide), arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide)))
Z = arb_bin(1, vide, vide)
B = arb_bin(1, vide, vide)
Yes (0.00s cpu, solution 1, maybe more) ? ;
;

No (0.00s cpu)
*/


%%**************************************************

/* Question 2.5

[eclipse 2]: test(arbre4,X),test(arbre5,Y),isomorphes(X,Y).

X = arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, arb_bin(6, vide, vide), arb_bin(7, vide, vide)))
Y = arb_bin(3, arb_bin(5, arb_bin(6, vide, vide), arb_bin(7, vide, vide)), arb_bin(4, vide, vide))
Yes (0.00s cpu, solution 1, maybe more) ?
;

No (0.00s cpu)


[eclipse 3]: test(arbre4,X),test(arbre2,Y),isomorphes(X,Y).

No (0.00s cpu)
*

%%**************************************************

/* Question 2.6

[eclipse 6]: test(arbre_vide,X),infixe(X,L).

X = vide
L = []
Yes (0.00s cpu)


[eclipse 7]: test(arbre1,X),infixe(X,L).

X = arb_bin(1, arb_bin(2, arb_bin(6, vide, vide), vide), arb_bin(3, arb_bin(4, vide, vide), arb_bin(5, vide, vide)))
L = [6, 2, 1, 4, 3, 5]
Yes (0.00s cpu)
*/

%%**************************************************

/* Question 2.7 
[eclipse 2]: test(arbre_vide,X),nb_etiq_sup(5,X,N).

X = vide
N = 0
Yes (0.00s cpu)

[eclipse 6]: test(arbre7,X),nb_etiq_sup(6,X,N).

X = arb_bin(8, arb_bin(4, arb_bin(2, vide, vide), arb_bin(6, vide, vide)), arb_bin(12, arb_bin(10, vide, vide), vide))
N = 3
Yes (0.00s cpu, solution 1, maybe more) ? ;
;

No (0.00s cpu)

[
eclipse 7]: test(arbre7,X),nb_etiq_sup(16,X,N).

X = arb_bin(8, arb_bin(4, arb_bin(2, vide, vide), arb_bin(6, vide, vide)), arb_bin(12, arb_bin(10, vide, vide), vide))
N = 0
Yes (0.00s cpu, solution 1, maybe more) ? ;
;

No (0.00s cpu)
*/


%%**************************************************

/* Question 2.8


[eclipse 15]: test(arbre_vide,B),insertion_arbre_ordonne(5,B,B1).

B = vide
B1 = arb_bin(5, vide, vide)
Yes (0.00s cpu)

[eclipse 17]: test(arbre7,B),insertion_arbre_ordonne(7,B,B1).

B = arb_bin(8, arb_bin(4, arb_bin(2, vide, vide), arb_bin(6, vide, vide)), arb_bin(12, arb_bin(10, vide, vide), vide))
B1 = arb_bin(8, arb_bin(4, arb_bin(2, vide, vide), arb_bin(6, vide, arb_bin(7, vide, vide))), arb_bin(12, arb_bin(10, vide, vide), vide))
Yes (0.00s cpu, solution 1, maybe more) ? ;
;

No (0.00s cpu)
[eclipse 18]: test(arbre7,B),insertion_arbre_ordonne(8,B,B1).

B = arb_bin(8, arb_bin(4, arb_bin(2, vide, vide), arb_bin(6, vide, vide)), arb_bin(12, arb_bin(10, vide, vide), vide))
B1 = arb_bin(8, arb_bin(4, arb_bin(2, vide, vide), arb_bin(6, vide, vide)), arb_bin(12, arb_bin(10, vide, vide), vide))
Yes (0.01s cpu, solution 1, maybe more) ? ;
;

No (0.01s cpu)*/

%%**************************************************

/* Question 2.9

[eclipse 5]: test(arbre8,B),insertion_arbre_ordonne1(7,B).

B = arb_bin(8, arb_bin(4, arb_bin(2, _262, _263), arb_bin(6, _266, arb_bin(7, _280, _281))), arb_bin(12, arb_bin(10, _274, _275), _271))
Yes (0.00s cpu, solution 1, maybe more) ? ;
;

No (0.00s cpu)

[eclipse 6]: test(arbre8,B),insertion_arbre_ordonne1(4,B).

B = arb_bin(8, arb_bin(4, arb_bin(2, _262, _263), arb_bin(6, _266, _267)), arb_bin(12, arb_bin(10, _274, _275), _271))
Yes (0.00s cpu, solution 1, maybe more) ? ;
;

No (0.00s cpu)
*/