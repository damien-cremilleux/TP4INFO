%% TP1
%% Damien Crémilleux - Lauriane Holy

:-lib(ic).

%% Question 1.1
%%%%%%%%%%%%%%%
couleurVoiture(rouge).
couleurVoiture(vert(clair)).
couleurVoiture(gris).
couleurVoiture(blanc).

couleurBateau(vert).
couleurBateau(noir).
couleurBateau(blanc).

%%choixCouleur(?CouleurBateau, ?CouleurVoiture) :  rend vrai si les couleurs choisies pour le bateau et la voiture sont identiques et font partie des choix existants.
choixCouleur(Couleur,Couleur) :-
        couleurVoiture(Couleur),
        couleurBateau(Couleur).


%% Question 1.2
%%%%%%%%%%%%%%%

%% Prolog peut être considéré comme un solveur de contraintes sur le domaine des arbres car il génère tout l'univers possible et le teste, à l'aide de mécanisme de backtracking et d'unification.


%% Question 1.3
%%%%%%%%%%%%%%%
%% isBetween(?Var, +Min,+Max) fixe une valeur pour Var et est vrai ssi Var a une valeur comprise entre Min et Max.
isBetween(Min,Min,_).

isBetween(Var,Min,Max) :-
        Min2 is Min+1,
        Min2 =< Max,
        isBetween(Var,Min2,Max).


%% Question 1.4
%%%%%%%%%%%%%%%

%commande(-NbResistance, -NbCondensateur)  fixe le nombre de résistances et de condensateurs à commander pour respecter (entre 500 et 1000 resistances et entre 9000 et 20000 condensateurs).
commande(NbResistance,NbCondensateur) :-
        isBetween(NbResistance,5000,10000),
        isBetween(NbCondensateur,9000,20000),
        NbResistance >= NbCondensateur.


%% Question 1.5
%%%%%%%%%%%%%%%
%% Arbre de recherche


%% Question 1.6
%%%%%%%%%%%%%%%

% Version : 
%%commande(NbResistance,NbCondensateur) :-
%% 	NbResistance >= NbCondensateur,
%% 	isBetween(NbResistance,5000,10000),
%% 	isBetween(NbCondensateur,9000,20000).

%% [eclipse 9]: commande(NbRes, NbCond).
%% instantiation fault in NbRes >= NbCond
%% Abort

%% Prolog n'est pas capable de faire une relation mathématique entre deux variables non instinciées. Il génère toutes les valeurs possible à l'aide des prédicats isBetween avant de vérifier si elles respectent la condition posée. C'est l'approche "generate and test".

%% Question 1.7
%%%%%%%%%%%%%%%
%% commande(-NbResistance, -NbCondensateur) rajout des contraintes
commandeCont(NbResistance,NbCondensateur) :-
        NbResistance #:: 5000..10000,
        NbCondensateur #:: 9000..20000,
        NbResistance #>= NbCondensateur.


%% Question 1.8
%%%%%%%%%%%%%%%
%% commande(-NbResistance, -NbCondensateur) rajout du labeling
commandeContLabel(NbResistance,NbCondensateur) :-
        NbResistance #:: 5000..10000,
        NbCondensateur #:: 9000..20000,
        NbResistance #>= NbCondensateur,
        labeling([NbResistance,NbCondensateur]).


%% Question 1.9
%%%%%%%%%%%%%%%
%%chapie(-Chat, -Pies ,-Pattes,-Tetes) liaison des contraintes du problème

chapie(Chats,Pies,Pattes,Tetes):-
        Chats #:: 0..inf,
        Pies #:: 0..inf,
        Pattes #:: 0..inf,
        Tetes #:: 0..inf,
        Pattes #= 4*Chats + 2*Pies,
        Tetes #= Chats + Pies.


%% Question 1.10
%%%%%%%%%%%%%%%%
%%chapie_10(-Chat, -Pies ,-Pattes,-Tetes) rend combien de chat il faut pour avoir 3 fois plus de pattes que de têtes

chapie1_10(Chats,Pies,Pattes,Tetes):-
        chapie(Chats,Pies,Pattes,Tetes),
        Chats #<10000,
        Pies #<10000,
        Pattes #<10000,
        Tetes #<10000,
        Pattes #= 3* Tetes,
        labeling([Chats,Pies,Pattes,Tetes]).


%% Question 1.11
%%%%%%%%%%%%%%%%

%% vabs(?Val, ?AbsVal) ValAbs est la valeur absolue de l'entier relatif Val
%% Version avec un point de choix Prolog 
vabs(Val,AbsVal) :-
        AbsVal #:: 0..inf,
        contVabs(Val,AbsVal),
        labeling([Val,AbsVal]).

contVabs(Val,AbsVal) :-
        Val #= AbsVal.

contVabs(Val,AbsVal) :-
        Val #= -AbsVal.

%% Version avec l'opérateur de disjonction or de ic
vabs2(Val,AbsVal) :-
        AbsVal #:: 0..1000,
        Val #:: -1000..1000,
        (Val #= AbsVal) or (Val #= -AbsVal),
        labeling([Val,AbsVal]).


%% Question 1.12
%%%%%%%%%%%%%%%%



%% Question 1.13
%%%%%%%%%%%%%%%%

%% Ne marche pas
%% faitListe(ListVar,Taille,Min,Max) :-
%% 	dim(ListVar,Taille),
%% 	( for(I,1,Taille),
%% 	param(ListVar,Min,Max)
%%     do
%% (
%%     ListVar[I] #>= Min,
%% 	ListVar[I] #<= Max
%%     )
%% ).

%% faitListe(?ListVar, ?Taille, +Min, +Max)  ListVar est une liste de taille Taille dont les éléments sont dans le domaine Min..Max
faitListe(ListVar,Taille,Min,Max) :-
        length(ListVar,Taille),
        ListVar #:: Min..Max.


%% Question 1.14
%%%%%%%%%%%%%%%%
%% suite(?ListVar) prend une liste en paramètre et contraint les éléments de cette liste à être des termes successifs de la suite.
verif_suite(X1,X2,X3) :-
        vabs(X2,AbsX2),
        X3 #= AbsX2 -X1.

suite([X1,X2,X3]) :-
        verif_suite(X1,X2,X3).

suite([X1,X2,X3|Reste]) :-
        verif_suite(X1,X2,X3),
        suite([X2,X3|Reste]).


%% Question 1.15
%%%%%%%%%%%%%%%%	



/*
%%%%%%%%%%%
%% TESTS %%
%%%%%%%%%%%


%% Question 1.1
%%%%%%%%%%%%%%%
[eclipse 4]: choixCouleur(CouleurBateau,CouleurVoiture).
CouleurBateau = blanc
CouleurVoiture = blanc
Yes (0.00s cpu)


[eclipse 5]: choixCouleur(vert,vert).
No (0.00s cpu)


%% Question 1.3
%%%%%%%%%%%%%%%
[eclipse 33]: isBetween(Var,1,10).
Var = 1
Yes (0.00s cpu, solution 1, maybe more) ? ;

Var = 2
Yes (0.00s cpu, solution 2, maybe more) ? ;

Var = 3
Yes (0.00s cpu, solution 3, maybe more) ? ;

Var = 4
Yes (0.00s cpu, solution 4, maybe more) ? ;

Var = 5
Yes (0.00s cpu, solution 5, maybe more) ? ;

Var = 6
Yes (0.00s cpu, solution 6, maybe more) ? ;

Var = 7
Yes (0.00s cpu, solution 7, maybe more) ? ;

Var = 8
Yes (0.00s cpu, solution 8, maybe more) ? ;

Var = 9
Yes (0.00s cpu, solution 9, maybe more) ? ;

Var = 10
Yes (0.00s cpu, solution 10, maybe more) ? ;

No (0.00s cpu)

[eclipse 34]: isBetween(5,1,10).
Yes (0.00s cpu, solution 1, maybe more) ? ;

No (0.00s cpu)

[eclipse 35]: isBetween(15,1,10).
No (0.00s cpu)


%% Question 1.4
%%%%%%%%%%%%%%%
[eclipse 37]: commande(NbResistance,NbCondensateur).
NbResistance = 9000
NbCondensateur = 9000
Yes (13.03s cpu, solution 1, maybe more) ? ;

NbResistance = 9001
NbCondensateur = 9000
Yes (13.04s cpu, solution 2, maybe more) ? ;

NbResistance = 9001
NbCondensateur = 9001
Yes (13.04s cpu, solution 2, maybe more);

NbResistance = 9002
NbCondensateur = 9000
Yes (13.05s cpu, solution 4, maybe more) ? ;

...


[eclipse 38]: commande(9500,9400).
Yes (0.00s cpu, solution 1, maybe more) ? ;

No (0.00s cpu)

[eclipse 39]: commande(1,5).
No (0.00s cpu)


%% Question 1.7
%%%%%%%%%%%%%%%
[eclipse 43]: commandeCont(NbResistance,NbCondesateur).
NbResistance = NbResistance{9000 .. 10000}
NbCondesateur = NbCondesateur{9000 .. 10000}

Delayed goals:
	NbCondesateur{9000 .. 10000} - NbResistance{9000 .. 10000} #=< 0
Yes (0.00s cpu)


%% Question 1.8
%%%%%%%%%%%%%%%

[eclipse 50]: commandeContLabel(NbResistance,NbCondesateur).
NbResistance = 9000
NbCondesateur = 9000
Yes (0.00s cpu, solution 1, maybe more) ? ;

NbResistance = 9001
NbCondesateur = 9000
Yes (0.01s cpu, solution 2, maybe more) ? ;

NbResistance = 9001
NbCondesateur = 9001
Yes (0.01s cpu, solution 3, maybe more) ? ;

NbResistance = 9002
NbCondesateur = 9000
Yes (0.01s cpu, solution 4, maybe more) ? 

...

[eclipse 51]: commandeContLabel(5,3).
No (0.00s cpu)


[eclipse 52]: commandeContLabel(9500,9300).
Yes (0.00s cpu)


%% Question 1.9
%%%%%%%%%%%%%%%

[eclipse 54]: chapie(2,Pies,Pattes,5).
Pies = 3
Pattes = 14
Yes (0.00s cpu)


%% Question 1.10
%%%%%%%%%%%%%%%%
[eclipse 61]: chapie1_10(Chats,Pies,Pattes,Tetes).

Chats = 0
Pies = 0
Pattes = 0
Tetes = 0
Yes (0.00s cpu, solution 1, maybe more) ? ;

Chats = 1
Pies = 1
Pattes = 6
Tetes = 2
Yes (0.00s cpu, solution 2, maybe more) ? ;

Chats = 2
Pies = 2
Pattes = 12
Tetes = 4
Yes (0.00s cpu, solution 3, maybe more) ? ;
...


%% Question 1.11
%%%%%%%%%%%%%%%%

[eclipse 27]: vabs(2,2).

Yes (0.00s cpu, solution 1, maybe more) ? ;

No (0.01s cpu)
[eclipse 28]: vabs(-2,2).

Yes (0.00s cpu)
[eclipse 29]: vabs(2,-2).

No (0.00s cpu)
[eclipse 30]: vabs(-2,Vabs).

Vabs = 2
Yes (0.00s cpu)
[eclipse 31]: vabs(Abs,5).

Abs = 5
Yes (0.00s cpu, solution 1, maybe more) ? ;

Abs = -5
Yes (0.00s cpu, solution 2)
[eclipse 32]: vabs(Abs,Vabs).

Abs = 0
Vabs = 0
Yes (0.00s cpu, solution 1, maybe more) ? ;

Abs = 1
Vabs = 1
Yes (0.00s cpu, solution 2, maybe more) ? ;

Abs = 2
Vabs = 2
Yes (0.00s cpu, solution 3, maybe more) ? ;

Abs = 3
Vabs = 3
Yes (0.00s cpu, solution 4, maybe more) ? ;

Abs = 4
Vabs = 4
Yes (0.00s cpu, solution 5, maybe more) ? ;

Abs = 5
Vabs = 5
Yes (0.00s cpu, solution 6, maybe more) ? 

%% Deuxième version

[eclipse 22]: vabs2(-2,5).

No (0.00s cpu)
[eclipse 25]: vabs2(-2,5).

No (0.00s cpu)
[eclipse 26]: vabs2(-5,5).

Yes (0.00s cpu)
[eclipse 27]: vabs2(-5,ABs).

ABs = 5
Yes (0.00s cpu)
[eclipse 28]: vabs2(-5,ValAbs).

ValAbs = 5
Yes (0.00s cpu)
[eclipse 29]: vabs2(Ans,3).

Ans = -3
Yes (0.00s cpu, solution 1, maybe more) ? ;

Ans = 3
Yes (0.00s cpu, solution 2, maybe more) ? ;

No (0.01s cpu)
[eclipse 30]: vabs2(Abs,ValAbs).

Abs = -1000
ValAbs = 1000
Yes (0.00s cpu, solution 1, maybe more) ? ;

Abs = -999
ValAbs = 999
Yes (0.00s cpu, solution 2, maybe more) ? ;

Abs = -998
ValAbs = 998
Yes (0.00s cpu, solution 3, maybe more) ? ;

...

%% Question 1.13
%%%%%%%%%%%%%%%%
[eclipse 41]: faitListe([1,2],2,0,3).

Yes (0.00s cpu)
[eclipse 42]: faitListe([1,5],2,0,3).

No (0.00s cpu)
[eclipse 43]: faitListe([1,2],Taille,0,3).

Taille = 2
Yes (0.00s cpu)
[eclipse 44]: faitListe(Liste,5,0,3).

Liste = [_236{0 .. 3}, _254{0 .. 3}, _272{0 .. 3}, _290{0 .. 3}, _308{0 .. 3}]
Yes (0.00s cpu)
[eclipse 45]: faitListe(Liste,Taille,0,3).

Liste = []
Taille = 0
Yes (0.00s cpu, solution 1, maybe more) ? ;

Liste = [_222{0 .. 3}]
Taille = 1
Yes (0.00s cpu, solution 2, maybe more) ? ;

Liste = [_227{0 .. 3}, _245{0 .. 3}]
Taille = 2


%% Question 1.14
%%%%%%%%%%%%%%%%

[eclipse 4]: suite([1,2,3]).

No (0.00s cpu)
[eclipse 5]: suite([1,3,2, -1]).

Yes (0.00s cpu, solution 1, maybe more) ? ;

No (0.00s cpu)
[eclipse 6]: suite([1,3,2,A]).

A = -1
Yes (0.00s cpu, solution 1, maybe more) ? ;

No (0.00s cpu)
[eclipse 7]: suite(A).

A = [_407{-1.0Inf .. 1.0Inf}, 0, _386{-1.0Inf .. 1.0Inf}]


Delayed goals:
	_386{-1.0Inf .. 1.0Inf} + _407{-1.0Inf .. 1.0Inf} #= 0
Yes (0.00s cpu, solution 1, maybe more) ? ;

...





*/