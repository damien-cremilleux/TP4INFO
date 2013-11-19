/* 
TP1 - Interrogation style base de données

Cremilleux Damien
Holy Lauriane
11/09/2013

Première partie du TP, base Menu
*/


/* Données*/

hors_d_oeuvre(artichauts_Melanie).
hors_d_oeuvre(truffes_sous_le_sel).
hors_d_oeuvre(cresson_oeuf_poche).

viande(grillade_de_boeuf).
viande(poulet_au_tilleul).

poisson(bar_aux_algues).
poisson(saumon_oseille).

dessert(sorbet_aux_poires).
dessert(fraises_chantilly).
dessert(melon_en_surprise).

calories(artichauts_Melanie, 150).
calories(truffes_sous_le_sel, 202).
calories(cresson_oeuf_poche, 212).
calories(grillade_de_boeuf, 532).
calories(poulet_au_tilleul, 400).
calories(bar_aux_algues, 292).
calories(saumon_oseille, 254).
calories(sorbet_aux_poires, 223).
calories(fraises_chantilly, 289).
calories(melon_en_surprise, 122).

/* requetes */

plat_resistance(P) :- viande(P).
plat_resistance(P) :- poisson(P).

repas(H,P,D) :- 
				hors_d_oeuvre(H),
				plat_resistance(P),
				dessert(D).

plat_200_400(P) :- 
					plat_resistance(P),
					calories(P,Valeur), 
					Valeur >= 200,
					Valeur =< 400.

plat_plus_calorique_bar(P) :-
						   plat_resistance(P),
						   calories(P,Valeur),
						   calories(bar_aux_algues, Valeur_bar),
						   Valeur >= Valeur_bar.

calories_repas(H,P,D,Val_totale) :- 
									repas(H,P,D),	
									calories(H,Valeur_H),
									calories(P, Valeur_P),
									calories(D, Valeur_D),
									Val_totale is Valeur_H+Valeur_P+Valeur_D.

repas_equilibre(H,P,D,Val_totale) :-
				  calories_repas(H,P,D,Val_totale),
				  Val_totale =< 800.
	     
/* 1.*/
/*
predicat
[eclipse 27]: ?-hors_d_oeuvre(H).

H = artichauts_Melanie
Yes (0.00s cpu, solution 1, maybe more) ? ;

H = truffes_sous_le_sel
Yes (0.00s cpu, solution 2, maybe more) ? ;

H = cresson_oeuf_poche
Yes (0.00s cpu, solution 3)

[eclipse 28]: ?-viande(X).

H = grillade_de_boeuf
Yes (0.00s cpu, solution 1, maybe more) ? ;

H = poulet_au_tilleul
Yes (0.00s cpu, solution 2)

[eclipse 29]: ?-poisson(Poisson).

Poisson = bar_aux_algues
Yes (0.00s cpu, solution 1, maybe more) ? ;

Poisson = saumon_oseille
Yes (0.00s cpu, solution 2)

[eclipse 2]: ?-dessert(Dessert).

Dessert = sorbet_aux_poires
Yes (0.00s cpu, solution 1, maybe more) ? ;

Dessert = fraises_chantilly
Yes (0.00s cpu, solution 2, maybe more) ? ;

Dessert = melon_en_surprise
Yes (0.00s cpu, solution 3)

[eclipse 3]: ?-calories(Aliment,Valeur).

Aliment = artichauts_Melanie
Valeur = 150
Yes (0.00s cpu, solution 1, maybe more) ? ;

Aliment = truffes_sous_le_sel
Valeur = 202
Yes (0.00s cpu, solution 2, maybe more) ? ;

Aliment = cresson_oeuf_poche
Valeur = 212
Yes (0.00s cpu, solution 3, maybe more) ? ;

Aliment = grillade_de_boeuf
Valeur = 532
Yes (0.00s cpu, solution 4, maybe more) ? ;

Aliment = poulet_au_tilleul
Valeur = 400
Yes (0.00s cpu, solution 5, maybe more) ? ;

Aliment = bar_aux_algues
Valeur = 292
Yes (0.00s cpu, solution 6, maybe more) ? ;

Aliment = saumon_oseille
Valeur = 254
Yes (0.00s cpu, solution 7, maybe more) ? ;

Aliment = sorbet_aux_poires
Valeur = 223
Yes (0.00s cpu, solution 8, maybe more) ? ;

Aliment = fraises_chantilly
Valeur = 289
Yes (0.00s cpu, solution 9, maybe more) ? ;

Aliment = melon_en_surprise
Valeur = 122
Yes (0.00s cpu, solution 10)


*/



/*1.2.1*/

/*

?- plat_resistance(P).

P = grillade_de_boeuf
Yes (0.00s cpu, solution 1, maybe more) ? ;

P = poulet_au_tilleul
Yes (0.00s cpu, solution 2, maybe more) ? ;

P = bar_aux_algues
Yes (0.00s cpu, solution 3, maybe more) ? ;

P = saumon_oseille
Yes (0.00s cpu, solution 4)
*/

/*1.2.2*/

/*
[eclipse 5]: ?-repas(H,P,D).

H = artichauts_Melanie
P = grillade_de_boeuf
D = sorbet_aux_poires
Yes (0.00s cpu, solution 1, maybe more) ? ;

H = artichauts_Melanie
P = grillade_de_boeuf
D = fraises_chantilly
Yes (0.00s cpu, solution 2, maybe more) ? ;

H = artichauts_Melanie
P = grillade_de_boeuf
D = melon_en_surprise
Yes (0.00s cpu, solution 3, maybe more) ? ;

H = artichauts_Melanie
P = poulet_au_tilleul
D = sorbet_aux_poires
Yes (0.00s cpu, solution 4, maybe more) ? ;

H = artichauts_Melanie
P = poulet_au_tilleul
D = fraises_chantilly
Yes (0.00s cpu, solution 5, maybe more) ? ;

H = artichauts_Melanie
P = poulet_au_tilleul
D = melon_en_surprise
Yes (0.00s cpu, solution 6, maybe more) ? ;

H = artichauts_Melanie
P = bar_aux_algues
D = sorbet_aux_poires
Yes (0.00s cpu, solution 7, maybe more) ? ;

H = artichauts_Melanie
P = bar_aux_algues
D = fraises_chantilly
Yes (0.00s cpu, solution 8, maybe more) ? ;

H = artichauts_Melanie
P = bar_aux_algues
D = melon_en_surprise
Yes (0.00s cpu, solution 9, maybe more) ? ;

H = artichauts_Melanie
P = saumon_oseille
D = sorbet_aux_poires
Yes (0.00s cpu, solution 10, maybe more) ? ;

H = artichauts_Melanie
P = saumon_oseille
D = fraises_chantilly
Yes (0.00s cpu, solution 11, maybe more) ? ;

H = artichauts_Melanie
P = saumon_oseille
D = melon_en_surprise
Yes (0.00s cpu, solution 12, maybe more) ? ;

H = truffes_sous_le_sel
P = grillade_de_boeuf
D = sorbet_aux_poires
Yes (0.00s cpu, solution 13, maybe more) ? ;

H = truffes_sous_le_sel
P = grillade_de_boeuf
D = fraises_chantilly
Yes (0.00s cpu, solution 14, maybe more) ? ;

H = truffes_sous_le_sel
P = grillade_de_boeuf
D = melon_en_surprise
Yes (0.00s cpu, solution 15, maybe more) ? ;

H = truffes_sous_le_sel
P = poulet_au_tilleul
D = sorbet_aux_poires
Yes (0.00s cpu, solution 16, maybe more) ? ;

H = truffes_sous_le_sel
P = poulet_au_tilleul
D = fraises_chantilly
Yes (0.00s cpu, solution 17, maybe more) ? ;

H = truffes_sous_le_sel
P = poulet_au_tilleul
D = melon_en_surprise
Yes (0.00s cpu, solution 18, maybe more) ? ;

H = truffes_sous_le_sel
P = bar_aux_algues
D = sorbet_aux_poires
Yes (0.00s cpu, solution 19, maybe more) ? ;

H = truffes_sous_le_sel
P = bar_aux_algues
D = fraises_chantilly
Yes (0.00s cpu, solution 20, maybe more) ? ;

H = truffes_sous_le_sel
P = bar_aux_algues
D = melon_en_surprise
Yes (0.00s cpu, solution 21, maybe more) ? ;

H = truffes_sous_le_sel
P = saumon_oseille
D = sorbet_aux_poires
Yes (0.00s cpu, solution 22, maybe more) ? ;

H = truffes_sous_le_sel
P = saumon_oseille
D = fraises_chantilly
Yes (0.00s cpu, solution 23, maybe more) ? ;

H = truffes_sous_le_sel
P = saumon_oseille
D = melon_en_surprise
Yes (0.00s cpu, solution 24, maybe more) ? ;

H = cresson_oeuf_poche
P = grillade_de_boeuf
D = sorbet_aux_poires
Yes (0.00s cpu, solution 25, maybe more) ? ;

H = cresson_oeuf_poche
P = grillade_de_boeuf
D = fraises_chantilly
Yes (0.00s cpu, solution 26, maybe more) ? ;

H = cresson_oeuf_poche
P = grillade_de_boeuf
D = melon_en_surprise
Yes (0.00s cpu, solution 27, maybe more) ? ;

H = cresson_oeuf_poche
P = poulet_au_tilleul
D = sorbet_aux_poires
Yes (0.00s cpu, solution 28, maybe more) ? ;

H = cresson_oeuf_poche
P = poulet_au_tilleul
D = fraises_chantilly
Yes (0.00s cpu, solution 29, maybe more) ? ;

H = cresson_oeuf_poche
P = poulet_au_tilleul
D = melon_en_surprise
Yes (0.00s cpu, solution 30, maybe more) ? ;

H = cresson_oeuf_poche
P = bar_aux_algues
D = sorbet_aux_poires
Yes (0.00s cpu, solution 31, maybe more) ? ;

H = cresson_oeuf_poche
P = bar_aux_algues
D = fraises_chantilly
Yes (0.00s cpu, solution 32, maybe more) ? ;

H = cresson_oeuf_poche
P = bar_aux_algues
D = melon_en_surprise
Yes (0.00s cpu, solution 33, maybe more) ? ;

H = cresson_oeuf_poche
P = saumon_oseille
D = sorbet_aux_poires
Yes (0.00s cpu, solution 34, maybe more) ? ;

H = cresson_oeuf_poche
P = saumon_oseille
D = fraises_chantilly
Yes (0.00s cpu, solution 35, maybe more) ? ;

H = cresson_oeuf_poche
P = saumon_oseille
D = melon_en_surprise
Yes (0.00s cpu, solution 36)

*/

/* 1.2.3 */

/*
[eclipse 6]: ?-plat_200_400(Plat).

Plat = poulet_au_tilleul
Yes (0.00s cpu, solution 1, maybe more) ? ;

Plat = bar_aux_algues
Yes (0.00s cpu, solution 2, maybe more) ? ;

Plat = saumon_oseille
Yes (0.00s cpu, solution 3)
*/

/*1.2.4*/
/*
[eclipse 11]: ?-plat_plus_calorique_bar(P).

P = grillade_de_boeuf
Yes (0.00s cpu, solution 1, maybe more) ? ;

P = poulet_au_tilleul
Yes (0.00s cpu, solution 2, maybe more) ? ;

P = bar_aux_algues
Yes (0.00s cpu, solution 3, maybe more) ? ;

No (0.00s cpu)
*/


/*
[eclipse 16]: ?-calories_repas(H,P,D,Val).

H = artichauts_Melanie
P = grillade_de_boeuf
D = sorbet_aux_poires
Val = 905
Yes (0.00s cpu, solution 1, maybe more) ? ;

H = artichauts_Melanie
P = grillade_de_boeuf
D = fraises_chantilly
Val = 971
Yes (0.00s cpu, solution 2, maybe more) ? ;

H = artichauts_Melanie
P = grillade_de_boeuf
D = melon_en_surprise
Val = 804
Yes (0.00s cpu, solution 3, maybe more) ? ;

H = artichauts_Melanie
P = poulet_au_tilleul
D = sorbet_aux_poires
Val = 773
Yes (0.00s cpu, solution 4, maybe more) ? ;
*/


/*
[eclipse 18]: repas_equilibre(H,P,D,Val_totale).

H = artichauts_Melanie
P = poulet_au_tilleul
D = sorbet_aux_poires
Val_totale = 773
Yes (0.00s cpu, solution 1, maybe more) ? ;

H = artichauts_Melanie
P = poulet_au_tilleul
D = melon_en_surprise
Val_totale = 672
Yes (0.00s cpu, solution 2, maybe more) ? ;

H = artichauts_Melanie
P = bar_aux_algues
D = sorbet_aux_poires
Val_totale = 665
Yes (0.00s cpu, solution 3, maybe more) ? 
*/

/*

--------MODE TRACE-------- 

[eclipse 22]: repas(H,P,D).
  (1) 1 CALL  repas(H, P, D)   %> creep
  (2) 2 CALL  hors_d_oeuvre(H)   %> creep
  (2) 2 *EXIT  hors_d_oeuvre(artichauts_Melanie)   %> creep
  (3) 2 CALL  plat_resistance(P)   %> creep
  (4) 3 CALL  viande(P)   %> creep
  (4) 3 *EXIT  viande(grillade_de_boeuf)   %> creep
  (3) 2 *EXIT  plat_resistance(grillade_de_boeuf)   %> creep
  (5) 2 CALL  dessert(D)   %> creep
  (5) 2 *EXIT  dessert(sorbet_aux_poires)   %> creep
  (1) 1 *EXIT  repas(artichauts_Melanie, grillade_de_boeuf, sorbet_aux_poires)   %> creep

H = artichauts_Melanie
P = grillade_de_boeuf
D = sorbet_aux_poires
Yes (0.01s cpu, solution 1, maybe more) ? c
Type ; for more solutions, otherwise <return> ? c
Type ; for more solutions, otherwise <return> ? ;
  (1) 1 REDO  repas(artichauts_Melanie, grillade_de_boeuf, D)   %> creep
  (5) 2 REDO  dessert(D)   %> creep
  (5) 2 *EXIT  dessert(fraises_chantilly)   %> creep
  (1) 1 *EXIT  repas(artichauts_Melanie, grillade_de_boeuf, fraises_chantilly)   %> creep

H = artichauts_Melanie
P = grillade_de_boeuf
D = fraises_chantilly
Yes (0.01s cpu, solution 2, maybe more) ? 
*/
