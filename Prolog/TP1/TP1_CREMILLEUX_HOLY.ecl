/**
TP Base Valois - Famille de France

@author Damien CREMILLEUX
@author Lauriane HOLY
@version Annee scolaire 2013/2014
*/


/* ----------------------------------- Données -----------------------------------*/
homme(charles_V).
homme(charles_VI).
homme(charles_VII).
homme(louis_XI).
homme(charles_VIII).
homme(louis_XII).
homme(francois_I).
homme(henri_II).
homme(francois_II).
homme(charles_IX).
homme(henri_III).
homme(jean_II).
homme(philippe_VI).
homme(charles_d_Orleans).
homme(charles_de_Valois).
homme(louis_d_Orleans).
homme(jean_d_angouleme).
homme(charles_d_angouleme).

femme(anne_de_cleves).
femme(louise_de_Savoie).
femme(claude_de_france).
femme(anne_de_Bretagne).
femme(catherine_de_medicis).
femme(charlotte_de_Savoie).
femme(marie_d_anjou).
femme(isabeau_de_Baviere).
femme(valentine_de_milan).
femme(jeanne_de_Bourbon).
femme(bonne_de_luxembourg).
femme(jeanne_de_Bourgogne).
femme(marie_Stuart).
femme(elisabeth_d_autriche).
femme(louise_de_lorraine).
femme(marguerite_de_Rohan).

mere(marguerite_de_Rohan, charles_d_angouleme).
mere(jeanne_de_Bourgogne, jean_II).
mere(bonne_de_luxembourg, charles_V).
mere(jeanne_de_Bourbon, charles_VI).
mere(jeanne_de_Bourbon, louis_d_Orleans).
mere(valentine_de_milan, charles_d_Orleans).
mere(valentine_de_milan, jean_d_angouleme).
mere(isabeau_de_Baviere, charles_VII).
mere(marie_d_anjou, louis_XI).
mere(charlotte_de_Savoie, charles_VIII).
mere(anne_de_Bretagne, claude_de_france).
mere(claude_de_france, henri_II).
mere(anne_de_cleves, louis_XII).
mere(louise_de_Savoie, francois_I).
mere(catherine_de_medicis, francois_II).
mere(catherine_de_medicis, charles_IX).
mere(catherine_de_medicis, henri_III).

epoux(marguerite_de_Rohan, jean_d_angouleme).
epoux(louise_de_lorraine, henri_III).
epoux(elisabeth_d_autriche, charles_IX).
epoux(marie_Stuart, francois_II).
epoux(jeanne_de_Bourgogne, philippe_VI).
epoux(bonne_de_luxembourg, jean_II).
epoux(jeanne_de_Bourbon, charles_V).
epoux(valentine_de_milan, louis_d_Orleans).
epoux(isabeau_de_Baviere, charles_VI).
epoux(marie_d_anjou, charles_VII).
epoux(charlotte_de_Savoie, louis_XI).
epoux(catherine_de_medicis, henri_II).
epoux(anne_de_cleves, charles_d_Orleans).
epoux(louise_de_Savoie, charles_d_angouleme).
epoux(claude_de_france, francois_I).
epoux(anne_de_Bretagne, charles_VIII).
epoux(anne_de_Bretagne, louis_XII).
epoux(H,F) :- homme(H), femme(F), epoux(F,H).

pere(louis_XII, claude_de_france).
pere(charles_de_Valois, philippe_VI).
pere(philippe_VI, jean_II).
pere(jean_II, charles_V).
pere(charles_V, charles_VI).
pere(charles_VI, charles_VII).
pere(charles_VII, louis_XI).
pere(charles_d_Orleans, louis_XII).
pere(charles_d_angouleme, francois_I).
pere(francois_I, henri_II).
pere(henri_II, francois_II).
pere(henri_II, charles_IX).
pere(henri_II, henri_III).
pere(louis_d_Orleans, charles_d_Orleans).
pere(charles_V, louis_d_Orleans).
pere(jean_d_angouleme, charles_d_angouleme).
pere(louis_d_Orleans, jean_d_angouleme).

roi(charles_V, le_sage, 1364, 1380).
roi(charles_VI, le_bien_aime, 1380, 1422).
roi(charles_VII, xx, 1422, 1461).
roi(louis_XI, xx, 1461, 1483).
roi(charles_VIII, xx, 1483, 1498).
roi(louis_XII, le_pere_du_peuple, 1498, 1515).
roi(francois_I, xx, 1515, 1547).
roi(henri_II, xx, 1547, 1559).
roi(francois_II, xx, 1559, 1560).
roi(charles_IX, xx, 1560, 1574).
roi(henri_III, xx, 1574, 1589).
roi(jean_II, le_bon, 1350, 1364).
roi(philippe_VI, de_valois, 1328, 1350).

/* ----------------- Règles ----------------- */

/*---- Question 1 : ----*/

/*1.1*/
/* 
E est un enfant de P. 
mode : efant(?E,?P)
*/ 
enfant(E,P):-pere(P,E).
enfant(E,P):-mere(P,E).


/*1.2*/
/* 
P est un parent direct de E.
mode : parent(?P,?E)
*/
parent(P,E):-enfant(E,P).


/*1.3*/
/* 
G est le grand-père de E.
mode : grand_pere(?G,?E)
*/
grand_pere(G,E):- 
				pere(G,P),
				parent(P,E).

				
/*1.4*/
/* 
F est un frère de E.
mode : frere(?F,?E)
*/
frere(F,E):-
			homme(F),
			pere(P,F),
			pere(P,E),
			mere(M,F),
			mere(M,E),
			\==(F,E).


/*1.5*/
/* 
O est un oncle de N.
mode : oncle(?O,?N)
*/
oncle(O,N):-
			frere(O,P),
			parent(P,N).

			
/*1.6*/
/*
C est un cousin de E.
mode : cousin(?C,?E)
*/
cousin(C,E):-
			parent(P,C),
			oncle(P,E).

			
/*1.7*/
/* 
En l'an D, le règne du roi R1 se termine et celui du roi R2 débute.
mode : le_roi_est_mort_vive_le_roi(?R1,?D,?R2)
*/
le_roi_est_mort_vive_le_roi(R1,D,R2):-
										roi(R1,_,_,D),
										roi(R2,_,D,_).


/*---- Question 1 : ----*/

/*2.1*/
/*
X est un ancetre de Y
mode : ancetre(?X,?Y)
*/

ancetre(X,Y):- parent(X,Y).
ancetre(X,Y):- parent(X,P), ancetre(P,Y).



/*2.2*/
/*
on fait varier l'ordre des clauses 
X est un ancetre de Y
mode : ancetre(?X,?Y)
*/
/*
ancetre(X,Y):- parent(X,P), ancetre(P,Y).
ancetre(X,Y):- parent(X,Y).

Avec ce prédicat (en inversant les clauses) l'interrogation de la base donne les mêmes réponses
*/


/*2.3*/
/*
on fait varier l'ordre des buts 
X est un ancetre de Y
mode : ancetre(?X,?Y)

ancetre(X,Y):- parent(X,Y).
ancetre(X,Y):- ancetre(P,Y),parent(X,P).
Avec ce prédicat (en inversant les buts) l'interrogation de la base  permet d'obtenir toutes les réponses et boucle à l'infini par la suite.
*/

/*2.4*/
/*
on fait varier l'ordre des clauses et des buts
X est un ancetre de Y
mode : ancetre(?X,?Y)

ancetre(X,Y):- ancetre(P,Y),parent(X,P).
ancetre(X,Y):- parent(X,Y).
Avec ce prédicat (en inversant les clauses et les buts) l'interrogation de la base ne marche pas et boucle à l'infini.
*/

	
/* ----------------- Tests ----------------- */


/*---- Question 1 : ----*/

/*
1.1 :
	- Données : données fournis,
	- But :  ?-enfant(E,P).

	- Temps d'exécution : 00.0,
	- Nombre de réponses : 34,
	- Réponses : 

E = claude_de_france
P = louis_XII
Yes (0.00s cpu, solution 1, maybe more) ? ;

E = philippe_VI
P = charles_de_Valois
Yes (0.00s cpu, solution 2, maybe more) ? ;

E = jean_II
P = philippe_VI
Yes (0.00s cpu, solution 3, maybe more) ? ;

E = charles_V
P = jean_II
Yes (0.00s cpu, solution 4, maybe more) ? ;

...


En mode TRACE :
	- Données : données fournis,
	- But : ?-enfant(E,P).

	- Temps d'exécution : 00.0,
	- Nombre de réponses : 34,
	- Réponses : 

  (1) 1 CALL  enfant(E, P)   %> creep
  (2) 2 CALL  pere(P, E)   %> creep
  (2) 2 *EXIT  pere(louis_XII, claude_de_france)   %> creep
  (1) 1 *EXIT  enfant(claude_de_france, louis_XII)   %> creep

E = claude_de_france
P = louis_XII
Yes (0.00s cpu, solution 1, maybe more) ? ;

  (1) 1 REDO  enfant(E, P)   %> creep
  (2) 2 REDO  pere(P, E)   %> creep
  (2) 2 *EXIT  pere(charles_de_Valois, philippe_VI)   %> creep
  (1) 1 *EXIT  enfant(philippe_VI, charles_de_Valois)   %> creep

E = philippe_VI
P = charles_de_Valois
Yes (0.00s cpu, solution 2, maybe more) ? 
*/


/*
1.2 :
	- Données : données fournis,
	- But :  ?-parent(E,P).

	- Temps d'exécution : 00.0,
	- Nombre de réponses : 34,
	- Réponses : 
	
E = louis_XII
P = claude_de_france
Yes (0.00s cpu, solution 1, maybe more) ? ;

E = charles_de_Valois
P = philippe_VI
Yes (0.00s cpu, solution 2, maybe more) ? ;

E = philippe_VI
P = jean_II
Yes (0.00s cpu, solution 3, maybe more) ? ;

E = jean_II
P = charles_V
Yes (0.00s cpu, solution 4, maybe more) ? ;
*/


/*
1.3 :
	- Données : données fournis,
	- But :  ?-grand_pere(G,E).

	- Temps d'exécution : 00.0,
	- Nombre de réponses : 17,
	- Réponses : 

G = louis_XII
E = henri_II
Yes (0.00s cpu, solution 1, maybe more) ? ;

G = charles_de_Valois
E = jean_II
Yes (0.00s cpu, solution 2, maybe more) ? ;

G = philippe_VI
E = charles_V
Yes (0.00s cpu, solution 3, maybe more) ? ;

G = jean_II
E = charles_VI
Yes (0.00s cpu, solution 4, maybe more) ? ;

No (0.00s cpu)

Mode TRACE :
	- Données : données fournis,
	- But :  ?-grand_pere(G,E).

	- Temps d'exécution : 00.0,
	- Nombre de réponses : 17,
	- Réponses : 


[eclipse 15]: ?-grand_pere(G,E).
  (1) 1 CALL  grand_pere(G, E)   %> creep
  (2) 2 CALL  pere(G, _276)   %> creep
  (2) 2 *EXIT  pere(louis_XII, claude_de_france)   %> creep
  (3) 2 CALL  parent(claude_de_france, E)   %> creep
  (4) 3 CALL  enfant(E, claude_de_france)   %> creep
  (5) 4 CALL  pere(claude_de_france, E)   %> creep
  (5) 4 FAIL  pere(..., ...)   %> creep
  (4) 3 NEXT  enfant(E, claude_de_france)   %> creep
  (6) 4 CALL  mere(claude_de_france, E)   %> creep
  (6) 4 EXIT  mere(claude_de_france, henri_II)   %> creep
  (4) 3 EXIT  enfant(henri_II, claude_de_france)   %> creep
  (3) 2 EXIT  parent(claude_de_france, henri_II)   %> creep
  (1) 1 *EXIT  grand_pere(louis_XII, henri_II)   %> creep

G = louis_XII
E = henri_II
*/


/*
1.4 :
	- Données : données fournis,
	- But :  frere(F,E).

	- Temps d'exécution : 00.0,
	- Nombre de réponses : 10,
	- Réponses : 
	
F = charles_VI
E = louis_d_Orleans
Yes (0.00s cpu, solution 1, maybe more) ? ;

F = francois_II
E = charles_IX
Yes (0.00s cpu, solution 2, maybe more) ? ;

F = francois_II
E = henri_III
Yes (0.00s cpu, solution 3, maybe more) ? ;

F = charles_IX
E = francois_II
Yes (0.00s cpu, solution 4, maybe more) ? ;
*/


/*
1.5 :
	- Données : données fournis,
	- But :  oncle(O,N).
	
	- Temps d'exécution : 00.0,
	- Nombre de réponses : 5,
	- Réponses : 

O = charles_VI
N = charles_d_Orleans
Yes (0.00s cpu, solution 1, maybe more) ? ;

O = charles_VI
N = jean_d_angouleme
Yes (0.00s cpu, solution 2, maybe more) ? ;

O = charles_d_Orleans
N = charles_d_angouleme
Yes (0.00s cpu, solution 3, maybe more) ? ;

O = louis_d_Orleans
N = charles_VII
Yes (0.00s cpu, solution 4, maybe more) ? ;

O = jean_d_angouleme
N = louis_XII
Yes (0.00s cpu, solution 5, maybe more) ? ;

No (0.00s cpu)
*/


/*
1.6 :
	- Données : données fournis,
	- But :  ?-cousin(C,E).
	
	- Temps d'exécution : 00.0,
	- Nombre de réponses : 6,
	- Réponses : 

C = charles_VII
E = charles_d_Orleans
Yes (0.00s cpu, solution 1, maybe more) ? ;

C = charles_VII
E = jean_d_angouleme
Yes (0.00s cpu, solution 2, maybe more) ? ;

C = louis_XII
E = charles_d_angouleme
Yes (0.00s cpu, solution 3, maybe more) ? ;

C = charles_d_Orleans
E = charles_VII
Yes (0.00s cpu, solution 4, maybe more) ? ;

C = charles_d_angouleme
E = louis_XII
Yes (0.00s cpu, solution 5, maybe more) ? ;

C = jean_d_angouleme
E = charles_VII
Yes (0.01s cpu, solution 6, maybe more) ? ;

No (0.01s cpu)
*/


/*
1.7 :
	- Données : données fournis,
	- But :  ?-le_roi_est_mort_vive_le_roi(R1,D,R2).

	- Temps d'exécution : 00.0,
	- Nombre de réponses : 12,
	- Réponses : 

R1 = charles_V
D = 1380
R2 = charles_VI
Yes (0.00s cpu, solution 1, maybe more) ? ;

R1 = charles_VI
D = 1422
R2 = charles_VII
Yes (0.00s cpu, solution 2, maybe more) ? ;

R1 = charles_VII
D = 1461
R2 = louis_XI
Yes (0.00s cpu, solution 3, maybe more) ? ;

R1 = louis_XI
D = 1483
R2 = charles_VIII
Yes (0.00s cpu, solution 4, maybe more) ? ;
*/


/*---- Question 2 : ----*/

/*
2.1 :
	- Données : données fournis,
	- But :  ?-ancetre(X, jean_d_angouleme).

	- Temps d'exécution : 00.0,
	- Nombre de réponses : 9,
	- Réponses : 

X = louis_d_Orleans
Yes (0.00s cpu, solution 1, maybe more) ? 
[eclipse 3]: ?-ancetre(X, jean_d_angouleme).

X = louis_d_Orleans
Yes (0.00s cpu, solution 1, maybe more) ? ;

X = valentine_de_milan
Yes (0.00s cpu, solution 2, maybe more) ? ;

X = charles_de_Valois
Yes (0.00s cpu, solution 3, maybe more) ? ;

X = philippe_VI
Yes (0.00s cpu, solution 4, maybe more) ? ;

No (0.00s cpu)
*/

/*
2.2 :
On fait varier l'ordre des clauses :
	- Données : données fournis,
	- But :  ?-ancetre(X, jean_d_angouleme).

	- Temps d'exécution : 00.0,
	- Nombre de réponses : 9,
	- Réponses : 

X = charles_de_Valois
Yes (0.00s cpu, solution 1, maybe more) ? ;

X = philippe_VI
Yes (0.00s cpu, solution 2, maybe more) ? ;

X = jean_II
Yes (0.00s cpu, solution 3, maybe more) ? ;

X = charles_V
Yes (0.00s cpu, solution 4, maybe more) ? ;

...

X = valentine_de_milan
Yes (0.00s cpu, solution 9)

*/

