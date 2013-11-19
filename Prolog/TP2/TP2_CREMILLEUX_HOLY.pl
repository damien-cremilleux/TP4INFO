/**
TP Manipulation de termes construits

@author Damien CREMILLEUX
@author Lauriane HOLY
@version Annee scolaire 2013/2014
**/


/*
	hauteur(Valeur)
*/
hauteur(deux).
hauteur(trois).
hauteur(quatre).
hauteur(cinq).
hauteur(six).
hauteur(sept).
hauteur(huit).
hauteur(neuf).
hauteur(dix).
hauteur(valet).
hauteur(dame).
hauteur(roi).
hauteur(as).

/*
	couleur(Valeur)
*/
couleur(trefle).
couleur(carreau).
couleur(coeur).
couleur(pique).

/*
	succ_hauteur(H1, H2)
*/
succ_hauteur(deux, trois).
succ_hauteur(trois, quatre).
succ_hauteur(quatre, cinq).
succ_hauteur(cinq, six).
succ_hauteur(six, sept).
succ_hauteur(sept, huit).
succ_hauteur(huit, neuf).
succ_hauteur(neuf, dix).
succ_hauteur(dix, valet).
succ_hauteur(valet, dame).
succ_hauteur(dame, roi).
succ_hauteur(roi, as).

/*
	succ_couleur(C1, C2)
*/
succ_couleur(trefle, carreau).
succ_couleur(carreau, coeur).
succ_couleur(coeur, pique).

/*
  carte_test
  cartes pour tester le prédicat EST_CARTE
*/

carte_test(c1,carte(sept,trefle)).
carte_test(c2,carte(neuf,carreau)).
carte_test(ce1,carte(7,trefle)).
carte_test(ce2,carte(sept,t)).

/* 
	main_test(NumeroTest, Main) 
	mains pour tester le prédicat EST_MAIN 
*/

main_test(main_triee_une_paire, main(carte(sept,trefle), carte(valet,coeur), carte(dame,carreau), carte(dame,pique), carte(roi,pique))).
% attention ici m2 représente un ensemble de mains	 
main_test(m2, main(carte(valet,_), carte(valet,coeur), carte(dame,carreau), carte(roi,coeur), carte(as,pique))).
main_test(main_triee_deux_paires, main(carte(valet,trefle), carte(valet,coeur), carte(dame,carreau), carte(roi,coeur), carte(roi,pique))).
main_test(main_triee_brelan, main(carte(sept,trefle), carte(dame,carreau), carte(dame,coeur), carte(dame,pique), carte(roi,pique))).	
main_test(main_triee_suite,main(carte(sept,trefle),carte(huit,pique),carte(neuf,coeur),carte(dix,carreau),carte(valet,carreau))).
main_test(main_triee_full,main(carte(deux,coeur),carte(deux,pique),carte(quatre,trefle),carte(quatre,coeur),carte(quatre,pique))).

main_test(merreur1, main(carte(sep,trefle), carte(sept,coeur), carte(dame,pique), carte(as,trefle), carte(as,pique))).
main_test(merreur2, main(carte(sep,trefle), carte(sept,coeur), carte(dame,pique), carte(as,trefle))).

% ============================================================================= 
%        QUESTION 1 : est_carte(carte(Hauteur,Couleur))
% =============================================================================
/* mode : (+) */

est_carte(carte(H,C)):-
	hauteur(H),
	couleur(C). 

% ============================================================================= 
%	QUESTION 2 : est_main(main(C1,C2,C3,C4,C5))
% ============================================================================= 

/* mode : (+) */
est_main(main(C1,C2,C3,C4,C5)):- 
	est_carte(C1),
	est_carte(C2),
	est_carte(C3),
	est_carte(C4),
	est_carte(C5),
	\==(C1,C2),
	\==(C1,C3),
	\==(C1,C4),
	\==(C1,C5),
	\==(C2,C3),
	\==(C2,C4),
	\==(C2,C5),
	\==(C3,C4),
	\==(C3,C5),
	\==(C4,C5).

% =============================================================================
%       QUESTION 3 : inf_carte(C1,C2) première version
% ============================================================================= 

/* définition de inf_hauteur et inf_couleur qui nous serviront pour écrire inf_carte.*/
/* mode : (?,?) */
inf_hauteur(H1,H2) :-
	succ_hauteur(H1,H2).
inf_hauteur(H1,H2) :-
	succ_hauteur(H1,H3),
	inf_hauteur(H3,H2).

/* mode : (?,?) */
inf_couleur(C1,C2) :-
	succ_couleur(C1,C2).
inf_couleur(C1,C2) :-
	succ_couleur(C1,C3),
	inf_couleur(C3,C2).


/* Dans la deuxième clause, on écrit directement dans la règle que les deux cartes ont la meme couleur (H1).
On evite également les singletons en mettant des variables inconnues _.*/

/*mode (?,?)*/
inf_carte(carte(H1,_),carte(H2,_)):-
	inf_hauteur(H1,H2).
inf_carte(carte(H1,C1),carte(H1,C2)):- 
	inf_couleur(C1,C2).
	

% ============================================================================= 
%       QUESTION 3 : inf_carte_b(C1,C2) deuxième version
% =============================================================================

/*mode (?,?)*/
inf_carte_b(carte(H1,C1),carte(H2,C2)):-
	inf_hauteur(H1,H2),
	est_carte(carte(H1,C1)),
	est_carte(carte(H2,C2)).

inf_carte_b(carte(H1,C1),carte(H1,C2)):- 
	inf_couleur(C1,C2),
	est_carte(carte(H1,C1)).


% ==============================================================================
%       QUESTION 4 : est_main_triee(main(C1,C2,C3,C4,C5))
% ==============================================================================

/* mode (+) */
est_main_triee(main(C1,C2,C3,C4,C5)):- 
	inf_carte(C1,C2),
	inf_carte(C2,C3),
	inf_carte(C3,C4),
	inf_carte(C4,C5).


% ==============================================================================
%       QUESTION 5 : une_paire(main(C1,C2,C3,C4,C5))
% ==============================================================================

/*mode (+)*/
une_paire(main(carte(H,_),carte(H,_),_,_,_)).
une_paire(main(_,carte(H,_),carte(H,_),_,_)).
une_paire(main(_,_,carte(H,_),carte(H,_),_)).
une_paire(main(_,_,_,carte(H,_),carte(H,_))).


% ==============================================================================
%       QUESTION 6 : deux_paires(main(C1,C2,C3,C4,C5))
% ==============================================================================

/*mode (+)*/
deux_paires(main(carte(H1,_),carte(H1,_),carte(H2,_),carte(H2,_),_)).
deux_paires(main(carte(H1,_),carte(H1,_),_,carte(H2,_),carte(H2,_))).
deux_paires(main(_,carte(H1,_),carte(H1,_),carte(H2,_),carte(H2,_))).



% ============================================================================= 
%       QUESTION 7 : brelan(main(C1,C2,C3,C4,C5))
% ============================================================================= 

/* les cartes étant triées, si la première carte à la même hauteur que la troisième alors la deuxième à également la meme hauteur.*/

/*mode (+)*/
brelan(main(carte(H1,_),carte(_,_),carte(H1,_),carte(_,_),carte(_,_))).
brelan(main(carte(_,_),carte(H2,_),carte(_,_),carte(H2,_),carte(_,_))).
brelan(main(carte(_,_),carte(_,_),carte(H3,_),carte(_,_),carte(H3,_))).


% ============================================================================= 
%       QUESTION 8 : suite(main(C1,C2,C3,C4,C5))
% ==============================================================================

/*mode (+)*/
suite(main(carte(H1,_),carte(H2,_),carte(H3,_),carte(H4,_),carte(H5,_))):- 
	succ_hauteur(H1,H2),
	succ_hauteur(H2,H3),
	succ_hauteur(H4,H5).

% ============================================================================= 
%       QUESTION 9 : full(main(C1,C2,C3,C4,C5))
% ============================================================================= 

/*mode (+)*/
full(main(carte(H1,_),carte(H1,_),carte(H3,_),carte(_,_),carte(H3,_))):-
	\==(H1,H3).
full(main(carte(H1,_),carte(_,_),carte(H1,_),carte(H4,_),carte(H4,_))):-
	\==(H1,H4).


% ==============================================================================

/* TESTS QUESTION 1 : carte_test

2.1

	- Données : données fournis,
	- But : ?-est_carte(C).

	- Temps d'exécution : 00.0,
	- Nombre de réponses : 52,
	- Réponses : 

C = carte(deux, trefle)
Yes (0.00s cpu, solution 1, maybe more) ? ;

C = carte(deux, carreau)
Yes (0.00s cpu, solution 2, maybe more) ? ;

C = carte(deux, coeur)
Yes (0.00s cpu, solution 3, maybe more) ? ;

C = carte(deux, pique)
Yes (0.00s cpu, solution 4, maybe more) ? ;
[...]

*/

% ============================================================================= 

/*  Tests QUESTION 2 : est_main
2.2

	- Données : données fournis,
	- But : ?-est_main(M) 

	- Temps d'exécution : 02.0 (pour une main),
	- Nombre de réponses : 52*51*50*49*48,
	- Réponses : 

M = main(carte(deux, trefle), carte(deux, carreau), carte(deux, coeur), carte(deux, pique), carte(trois, trefle))
Yes (0.02s cpu, solution 1, maybe more) ? ;

M = main(carte(deux, trefle), carte(deux, carreau), carte(deux, coeur), carte(deux, pique), carte(trois, carreau))
Yes (0.02s cpu, solution 2, maybe more) ? ;

M = main(carte(deux, trefle), carte(deux, carreau), carte(deux, coeur), carte(deux, pique), carte(trois, coeur))
Yes (0.02s cpu, solution 3, maybe more) ? ;

M = main(carte(deux, trefle), carte(deux, carreau), carte(deux, coeur), carte(deux, pique), carte(trois, pique))
Yes (0.02s cpu, solution 4, maybe more) ? 
[...]

*/

% ============================================================================= 

/* TESTS QUESTION 3 premiere version

Test des fonctions intermédiaires :

	- Données : données fournis,
	- But : inf_couleur(pique,pique).
	- Temps d'exécution : 0.0,
	- Nombre de réponses : 1,
	- Réponses : 

No (0.00s cpu)


	- Données : données fournis,
	- But : inf_hauteur(deux,six). 

	- Temps d'exécution : 0.0,
	- Nombre de réponses : 1,
	- Réponses : 

Yes (0.00s cpu, solution 1, maybe more) ? ;
No


	- Données : données fournis,
	- But : ?-inf_carte(carte(six,trefle),carte(sept,pique)).
	- Mode trace,

	- Temps d'exécution : 0.0,
	- Nombre de réponses : 1,
	- Réponses : 

  (1) 1 CALL  inf_carte(carte(six, trefle), carte(sept, pique))   %> creep
  (2) 2 CALL  inf_hauteur(six, sept)   %> creep
  (3) 3 CALL  succ_hauteur(six, sept)   %> creep
  (3) 3 EXIT  succ_hauteur(six, sept)   %> creep
  (2) 2 *EXIT  inf_hauteur(six, sept)   %> creep
  (1) 1 *EXIT  inf_carte(carte(six, trefle), carte(sept, pique))   %> creep

Yes (0.00s cpu, solution 1, maybe more) ? c

  (1) 1 REDO  inf_carte(carte(six, trefle), carte(sept, pique))   %> creep
  (2) 2 REDO  inf_hauteur(six, sept)   %> creep
  (4) 3 CALL  succ_hauteur(six, _364)   %> creep
  (4) 3 EXIT  succ_hauteur(six, sept)   %> creep
  (5) 3 CALL  inf_hauteur(sept, sept)   %> creep
  (6) 4 CALL  succ_hauteur(sept, sept)   %> creep
  (6) 4 FAIL  succ_hauteur(..., ...)   %> creep
  (5) 3 NEXT  inf_hauteur(sept, sept)   %> creep
  (7) 4 CALL  succ_hauteur(sept, _593)   %> creep
  (7) 4 EXIT  succ_hauteur(sept, huit)   %> creep
  (8) 4 CALL  inf_hauteur(huit, sept)   %> creep
  (9) 5 CALL  succ_hauteur(huit, sept)   %> creep
  (9) 5 FAIL  succ_hauteur(..., ...)   %> creep
  (8) 4 NEXT  inf_hauteur(huit, sept)   %> creep
  (10) 5 CALL  succ_hauteur(huit, _822)   %> creep
  (10) 5 EXIT  succ_hauteur(huit, neuf)   %> creep
  (11) 5 CALL  inf_hauteur(neuf, sept)   %> creep
  (12) 6 CALL  succ_hauteur(neuf, sept)   %> creep
  (12) 6 FAIL  succ_hauteur(..., ...)   %> creep
  (11) 5 NEXT  inf_hauteur(neuf, sept)   %> creep
  (13) 6 CALL  succ_hauteur(neuf, _1051)   %> creep
  (13) 6 EXIT  succ_hauteur(neuf, dix)   %> creep
  (14) 6 CALL  inf_hauteur(dix, sept)   %> creep
  (15) 7 CALL  succ_hauteur(dix, sept)   %> creep
  (15) 7 FAIL  succ_hauteur(..., ...)   %> creep
  (14) 6 NEXT  inf_hauteur(dix, sept)   %> creep
  (16) 7 CALL  succ_hauteur(dix, _1280)   %> creep
  (16) 7 EXIT  succ_hauteur(dix, valet)   %> creep
  (17) 7 CALL  inf_hauteur(valet, sept)   %> creep
  (18) 8 CALL  succ_hauteur(valet, sept)   %> creep
  (18) 8 FAIL  succ_hauteur(..., ...)   %> creep
  (17) 7 NEXT  inf_hauteur(valet, sept)   %> creep
  (19) 8 CALL  succ_hauteur(valet, _1509)   %> creep
  (19) 8 EXIT  succ_hauteur(valet, dame)   %> creep
  (20) 8 CALL  inf_hauteur(dame, sept)   %> creep
  (21) 9 CALL  succ_hauteur(dame, sept)   %> creep
  (21) 9 FAIL  succ_hauteur(..., ...)   %> creep
  (20) 8 NEXT  inf_hauteur(dame, sept)   %> creep
  (22) 9 CALL  succ_hauteur(dame, _1738)   %> creep
  (22) 9 EXIT  succ_hauteur(dame, roi)   %> creep
  (23) 9 CALL  inf_hauteur(roi, sept)   %> creep
  (24) 10 CALL  succ_hauteur(roi, sept)   %> creep
  (24) 10 FAIL  succ_hauteur(..., ...)   %> creep
  (23) 9 NEXT  inf_hauteur(roi, sept)   %> creep
  (25) 10 CALL  succ_hauteur(roi, _1967)   %> creep
  (25) 10 EXIT  succ_hauteur(roi, as)   %> creep
  (26) 10 CALL  inf_hauteur(as, sept)   %> creep
  (27) 11 CALL  succ_hauteur(as, sept)   %> creep
  (27) 11 FAIL  succ_hauteur(..., ...)   %> creep
  (26) 10 NEXT  inf_hauteur(as, sept)   %> creep
  (28) 11 CALL  succ_hauteur(as, _2196)   %> creep
  (28) 11 FAIL  succ_hauteur(..., ...)   %> creep
  (26) 10 FAIL  inf_hauteur(..., ...)   %> creep
  (23) 9 FAIL  inf_hauteur(..., ...)   %> creep
  (20) 8 FAIL  inf_hauteur(..., ...)   %> creep
  (17) 7 FAIL  inf_hauteur(..., ...)   %> creep
  (14) 6 FAIL  inf_hauteur(..., ...)   %> creep
  (11) 5 FAIL  inf_hauteur(..., ...)   %> creep
  (8) 4 FAIL  inf_hauteur(..., ...)   %> creep
  (5) 3 FAIL  inf_hauteur(..., ...)   %> creep
  (2) 2 FAIL  inf_hauteur(..., ...)   %> creep
  (1) 1 NEXT  inf_carte(carte(six, trefle), carte(sept, pique))   %> creep
  (29) 2 CALL  six == sept   %> creep
  (29) 2 FAIL  ... == ...   %> creep
  (1) 1 FAIL  inf_carte(..., ...)   %> creep

No (0.00s cpu)


	- Données : données fournis,
	- But : ?-inf_carte(C1,carte(cinq,coeur)).

	- Temps d'exécution : 0.01,
	- Nombre de réponses : 14,
	- Réponses : 

C1 = carte(quatre, trefle)
Yes (0.00s cpu, solution 1, maybe more) ? ;

C1 = carte(quatre, carreau)
Yes (0.01s cpu, solution 2, maybe more) ? ;

C1 = carte(quatre, coeur)
Yes (0.01s cpu, solution 3, maybe more) ? ;

[...]

C1 = carte(cinq, trefle)
Yes (0.01s cpu, solution 14, maybe more) ? ;

No (0.01s cpu)



*/

% ==============================================================================

/* TESTS QUESTION 3 deuxieme version

	- Données : données fournis,
	- But : ?-inf_carte_b(C1,carte(cinq,coeur)).

	- Temps d'exécution : 00.0,
	- Nombre de réponses : 14,
	- Réponses : 

C1 = carte(quatre, trefle)
Yes (0.00s cpu, solution 1, maybe more) ? ;

C1 = carte(quatre, carreau)
Yes (0.00s cpu, solution 2, maybe more) ? ;

C1 = carte(quatre, coeur)
Yes (0.00s cpu, solution 3, maybe more) ? ;

C1 = carte(quatre, pique)
Yes (0.00s cpu, solution 4, maybe more) ? ;

*/

% ==============================================================================

/* TESTS QUESTION 4

	- Données : données fournis,
	- But : ?-est_main_triee(main(carte(valet,trefle), carte(valet,coeur), carte(dame,carreau), carte(roi,coeur), carte(roi,pique))).

	- Temps d'exécution : 0.0,
	- Nombre de réponses : 1,
	- Réponses : 
Yes (0.00s cpu, solution 1, maybe more) ? ;
No (0.00s cpu)

*/

% ============================================================================= 

/* TESTS QUESTION 5
	
	- Données : données fournis,
	- But : ?-une_paire(main(carte(sept,trefle), carte(valet,coeur), carte(dame,carreau), carte(dame,pique), carte(roi,pique))).

	- Temps d'exécution : 0.00,
	- Nombre de réponses : 1,
	- Réponses : 
Yes (0.00s cpu, solution 1, maybe more) ?
No (0.00s cpu)


	- Données : données fournis,
	- But : ?-une_paire(main(carte(sept,trefle), carte(valet,coeur), carte(dame,carreau), carte(roi,pique), carte(as,pique))).

	- Temps d'exécution : 0.0,
	- Nombre de réponses : 0,
	- Réponses : 
No (0.00s cpu)
*/

% ==============================================================================

/* TESTS QUESTION 6
	
	- Données : données fournis,
	- But : ?-deux_paires(main(carte(valet,trefle), carte(valet,coeur), carte(dame,carreau), carte(roi,coeur), carte(roi,pique))).

	- Temps d'exécution : 00.0,
	- Nombre de réponses : 1,
	- Réponses : 
Yes (0.00s cpu, solution 1, maybe more) ? ;
No (0.01s cpu)


	- Données : données fournis,
	- But : ?-deux_paires(main(carte(valet,trefle), carte(valet,coeur), carte(dame,carreau), carte(roi,coeur), carte(as,pique))).

	- Temps d'exécution : 00.0,
	- Nombre de réponses : 0,
	- Réponses : 
No (0.00s cpu)


	- Données : données fournis,
	- But : deux_paires(main(carte(neuf,trefle), carte(valet,carreau), carte(valet,ccoeur), carte(roi,coeur), carte(roi,pique))).

	- Temps d'exécution : 00.0,
	- Nombre de réponses : 1,
	- Réponses : 
Yes (0.00s cpu)


*/

% ==============================================================================


/* TESTS QUESTION 7

	- Données : données fournis,
	- But : brelan(main(carte(sept,trefle), carte(dame,carreau), carte(dame,coeur), carte(dame,pique), carte(roi,pique))).

	- Temps d'exécution : 00.0,
	- Nombre de réponses : 1,
	- Réponses : 
Yes (0.00s cpu, solution 1, maybe more) ? ;
No (0.00s cpu)


	- Données : données fournis,
	- But : ?-brelan(main(carte(sept,trefle), carte(dame,trefle), carte(dame,carreau), carte(dame,coeur), carte(dame,pique))).

	- Temps d'exécution : 00.0,
	- Nombre de réponses : 2,
	- Réponses : 
Yes (0.00s cpu, solution 1, maybe more) ? ;
Yes (0.00s cpu, solution 2)


	- Données : données fournis,
	- But : brelan(main(carte(sept,trefle), carte(dame,carreau), carte(dame,coeur), carte(roi,pique), carte(as,pique))).

	- Temps d'exécution : 00.0,
	- Nombre de réponses : 0,
	- Réponses : 
No (0.00s cpu)


*/

% ==============================================================================

/* TESTS QUESTION 8
	
	- Données : données fournis,
	- But : ?-suite(main(carte(sept,trefle),carte(huit,pique),carte(neuf,coeur),carte(dix,carreau),carte(valet,carreau))).

	- Temps d'exécution : 00.0,
	- Nombre de réponses : 1,
	- Réponses : 
Yes (0.00s cpu)

	- Données : données fournis,
	- But : ?-suite(main(carte(sept,trefle),carte(huit,pique),carte(neuf,coeur),carte(dix,carreau),carte(roi,carreau))).

	- Temps d'exécution : 00.0,
	- Nombre de réponses : 0,
	- Réponses : 

No (0.00s cpu)


*/

% ============================================================================= 

/* TESTS QUESTION 9

	- Données : données fournis,
	- But : ?-full(main(carte(deux,coeur),carte(deux,pique),carte(quatre,trefle),carte(quatre,coeur),carte(quatre,pique))).

	- Temps d'exécution : 00.0,
	- Nombre de réponses : 1,
	- Réponses : 

Yes (0.00s cpu, solution 1, maybe more) ? ;
No (0.00s cpu)


	- Données : données fournis,
	- But : ?-full(main(carte(deux,coeur),carte(deux,pique),carte(quatre,trefle),carte(quatre,coeur),carte(cinq,pique))).

	- Temps d'exécution : 00.0,
	- Nombre de réponses : 0,
	- Réponses : 

No (0.00s cpu)


	- Données : données fournis,
	- But : ?-full(main(carte(deux,coeur),carte(trois,pique),carte(quatre,trefle),carte(quatre,coeur),carte(quatre,pique))).

	- Temps d'exécution : 00.0,
	- Nombre de réponses : 0,
	- Réponses : 

No (0.00s cpu)

*/
