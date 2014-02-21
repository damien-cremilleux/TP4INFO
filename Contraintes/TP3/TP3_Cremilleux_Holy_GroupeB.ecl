%% TP3
%% Damien Crémilleux - Lauriane Holy

:-lib(ic_symbolic).
:-lib(ic).
:-lib(arrays).


%% Définition des domaines
:- local domain(machine(m1,m2)).

%% Question 1
%%%%%%%%%%%%%
%% taches(?Taches) : unifie Taches au tableau des tâches
taches([](tache(3,[], m1, _),
	tache(8,[],m1, _),
	tache(8,[4,5],m1, _),
	tache(6,[],m2, _),
	tache(3,[1],m2, _),
	tache(4,[1,7],m1, _),
	tache(8,[3,5],m1, _),
	tache(6,[4],m2, _),
	tache(6,[6,7],m2, _),
	tache(6,[9,12],m2, _),
	tache(3,[1],m2, _),
	tache(6,[7,8],m2, _))).

%% taches2(?Taches) : unifie Taches au tableau des tâches, mais le tableau est plus simple
taches2([](tache(1,[],m1, _),
	tache(5,[],m1, _),
	tache(3,[2],m2, _))).

%% Question 2
%%%%%%%%%%%%%
%%affiche(+Taches) : affiche chaque élément et l'affiche.
affiche(Taches) :- 
	(foreachelem(X, Taches)
    do
writeln(X)
).

%% domaine_machine(+Taches) : contraint le domaine des machines
domaine_machine(Taches) :-
	(foreachelem(tache(_Duree,_Noms, Machines, _Debut), Taches)
    do
Machines &:: machine
).


%% Question 3
%%%%%%%%%%%%%
%% domaines(+Taches,?Fin) : impose que toute tâche de Taches démarre après l'instant 0 et finisse avant Fin.
domaines(Taches,Fin) :-
	(foreachelem(tache(Duree,_Noms,_Machine,Debut),Taches), param(Fin)
    do
(Debut #>= 0,
Debut + Duree #< Fin)). 			%Pas de <= car la tache doit être finie avant fin

%% Question 4
%%%%%%%%%%%%%
%%getVarList(+Taches,?Fin,?List) : recupère la listes des variables
getVarList(Taches, Fin, Liste):-
	(foreachelem(tache(_Duree,_Noms,_Machine,Debut),Taches),
	fromto([Fin],In, Out, Liste)
    do
Out = [Debut| In]
).


%% Question 5
%%%%%%%%%%%%%
%% solve(?Tache,?Fin) : trouve un ordonnancement qui respecte les contraintes de domaines
solve(Taches,Fin) :-
	taches(Taches),
	domaines(Taches,Fin),
	getVarList(Taches,Fin,Liste),
	labeling(Liste).

%% Question 6
%%%%%%%%%%%%%
%% precedence(+Taches) :  contraint chaque tache à démarrer après la fin de ses tâches préliminaires.
precedence(Taches) :-
	(foreachelem(tache(_Duree,Noms,_Machine,Debut),Taches), param(Taches)
    do
(
    foreach(X,Noms), param(Taches,Debut)
do
(
    tache(Duree2, _Noms2, _Machine2, Debut2) is Taches[X],
	Debut #>= Duree2 + Debut2
    )
)
).


%% Question 7
%%%%%%%%%%%%%
%% conflits(+Taches) : impose que sur une machine sur une machine deux tâches ne se déroulent pas en même temps
conflits(Taches) :-
	dim(Taches,[IndiceMax]),
	(for(Indice,1,IndiceMax), 
	param(Taches,IndiceMax)
	do
    (
	tache(Duree,_Noms,Machine,Debut) is Taches[Indice],
	IndiceSuiv is Indice+1,
	(for(Indice2,IndiceSuiv,IndiceMax),
	param(Taches,Duree,Machine,Debut)
	do
    (
	tache(Duree2, _Noms2, Machine2,Debut2) is Taches[Indice2],
	((Debut2 #>= Debut) and (Debut2 #< (Debut + Duree))) => (Machine &\= Machine2),
	((Debut #>= Debut2) and (Debut #< (Debut2 + Duree2))) => (Machine &\= Machine2)
    )
)
)
).

%% Résolution du problème
solve2(Taches,Fin) :-
	taches(Taches),
	domaines(Taches,Fin),
	domaine_machine(Taches),
	precedence(Taches),
	conflits(Taches),
	getVarList(Taches,Fin,Liste),
	labeling(Liste),
	affiche(Taches).


%% Question 8
%%%%%%%%%%%%%
%% La première solution de eclipse indique un temps de 44. Comme avec le labeling on génère l'ensemble des solutions possibles, on va ajouter la contrainte que fin soit inférieure ou égale à 44.
solveFinale(Taches,Fin) :-
	taches(Taches),
	domaines(Taches,Fin),
	domaine_machine(Taches),
	precedence(Taches),
	conflits(Taches),
	getVarList(Taches,Fin,Liste),
	Fin #=< 44,
	labeling(Liste),
	affiche(Taches).
%% Nous n'obtenons pas de meilleur temps. La réponse optimale est donc 44.


/*

%%%%%%%%%%%
%% TESTS %%
%%%%%%%%%%%

%% Question 1
%%%%%%%%%%%%%

[eclipse 7]: taches(A).

A = [](tache(3, [], m1, _180), tache(8, [], m1, _185), tache(8, [4, 5], m1, _190), tache(6, [], m2, _199), tache(3, [1], m2, _204), tache(4, [1, 7], m1, _211), tache(8, [3, 5], m1, _220), tache(6, [4], m2, _229), tache(6, [6, 7], m2, _236), tache(6, [9, 12], m2, _245), tache(3, [1], m2, _254), tache(6, [7, 8], m2, _261))
Yes (0.00s cpu)

[eclipse 8]: taches([](tache(4,[],m1,_))).

No (0.00s cpu)


%% Question 2
%%%%%%%%%%%%%
[eclipse 15]: taches(T),affiche(T).
tache(3, [], m1, _246)
tache(8, [], m1, _251)
tache(8, [4, 5], m1, _256)
tache(6, [], m2, _265)
tache(3, [1], m2, _270)
tache(4, [1, 7], m1, _277)
tache(8, [3, 5], m1, _286)
tache(6, [4], m2, _295)
tache(6, [6, 7], m2, _302)
tache(6, [9, 12], m2, _311)
tache(3, [1], m2, _320)
tache(6, [7, 8], m2, _327)

T = [](tache(3, [], m1, _246), tache(8, [], m1, _251), tache(8, [4, 5], m1, _256), tache(6, [], m2, _265), tache(3, [1], m2, _270), tache(4, [1, 7], m1, _277), tache(8, [3, 5], m1, _286), tache(6, [4], m2, _295), tache(6, [6, 7], m2, _302), tache(6, [9, 12], m2, _311), tache(3, [1], m2, _320), tache(6, [7, 8], m2, _327))
Yes (0.00s cpu)



%% Question 3
%%%%%%%%%%%%%
[eclipse 20]: taches(X),domaines(X,40),affiche(X).
tache(3, [], m1, _490{1 .. 36})
tache(8, [], m1, _609{1 .. 31})
tache(8, [4, 5], m1, _728{1 .. 31})
tache(6, [], m2, _847{1 .. 33})
tache(3, [1], m2, _966{1 .. 36})
tache(4, [1, 7], m1, _1085{1 .. 35})
tache(8, [3, 5], m1, _1204{1 .. 31})
tache(6, [4], m2, _1323{1 .. 33})
tache(6, [6, 7], m2, _1442{1 .. 33})
tache(6, [9, 12], m2, _1561{1 .. 33})
tache(3, [1], m2, _1680{1 .. 36})
tache(6, [7, 8], m2, _1799{1 .. 33})

X = [](tache(3, [], m1, _490{1 .. 36}), tache(8, [], m1, _609{1 .. 31}), tache(8, [4, 5], m1, _728{1 .. 31}), tache(6, [], m2, _847{1 .. 33}), tache(3, [1], m2, _966{1 .. 36}), tache(4, [1, 7], m1, _1085{1 .. 35}), tache(8, [3, 5], m1, _1204{1 .. 31}), tache(6, [4], m2, _1323{1 .. 33}), tache(6, [6, 7], m2, _1442{1 .. 33}), tache(6, [9, 12], m2, _1561{1 .. 33}), tache(3, [1], m2, _1680{1 .. 36}), tache(6, [7, 8], m2, _1799{1 .. 33}))
Yes (0.00s cpu)
[eclipse 21]: taches(X),domaines(X,Fin),affiche(X).
tache(3, [], m1, _492{1 .. 1.0Inf})
tache(8, [], m1, _658{1 .. 1.0Inf})
tache(8, [4, 5], m1, _805{1 .. 1.0Inf})
tache(6, [], m2, _950{1 .. 1.0Inf})
tache(3, [1], m2, _1095{1 .. 1.0Inf})
tache(4, [1, 7], m1, _1240{1 .. 1.0Inf})
tache(8, [3, 5], m1, _1385{1 .. 1.0Inf})
tache(6, [4], m2, _1530{1 .. 1.0Inf})
tache(6, [6, 7], m2, _1675{1 .. 1.0Inf})
tache(6, [9, 12], m2, _1820{1 .. 1.0Inf})
tache(3, [1], m2, _1965{1 .. 1.0Inf})
tache(6, [7, 8], m2, _2110{1 .. 1.0Inf})

X = [](tache(3, [], m1, _492{1 .. 1.0Inf}), tache(8, [], m1, _658{1 .. 1.0Inf}), tache(8, [4, 5], m1, _805{1 .. 1.0Inf}), tache(6, [], m2, _950{1 .. 1.0Inf}), tache(3, [1], m2, _1095{1 .. 1.0Inf}), tache(4, [1, 7], m1, _1240{1 .. 1.0Inf}), tache(8, [3, 5], m1, _1385{1 .. 1.0Inf}), tache(6, [4], m2, _1530{1 .. 1.0Inf}), tache(6, [6, 7], m2, _1675{1 .. 1.0Inf}), tache(6, [9, 12], m2, _1820{1 .. 1.0Inf}), tache(3, [1], m2, _1965{1 .. 1.0Inf}), tache(6, [7, 8], m2, _2110{1 .. 1.0Inf}))
Fin = Fin{10 .. 1.0Inf}

There are 12 delayed goals. Do you want to see them? (y/n) 

Delayed goals:
	-(_492{1 .. 1.0Inf}) + Fin{10 .. 1.0Inf} #> 3
	-(_658{1 .. 1.0Inf}) + Fin{10 .. 1.0Inf} #> 8
	-(_805{1 .. 1.0Inf}) + Fin{10 .. 1.0Inf} #> 8
	-(_950{1 .. 1.0Inf}) + Fin{10 .. 1.0Inf} #> 6
	-(_1095{1 .. 1.0Inf}) + Fin{10 .. 1.0Inf} #> 3
	-(_1240{1 .. 1.0Inf}) + Fin{10 .. 1.0Inf} #> 4
	-(_1385{1 .. 1.0Inf}) + Fin{10 .. 1.0Inf} #> 8
	-(_1530{1 .. 1.0Inf}) + Fin{10 .. 1.0Inf} #> 6
	-(_1675{1 .. 1.0Inf}) + Fin{10 .. 1.0Inf} #> 6
	-(_1820{1 .. 1.0Inf}) + Fin{10 .. 1.0Inf} #> 6
	-(_1965{1 .. 1.0Inf}) + Fin{10 .. 1.0Inf} #> 3
	-(_2110{1 .. 1.0Inf}) + Fin{10 .. 1.0Inf} #> 6
Yes (0.00s cpu)


%% Question 4
%%%%%%%%%%%%%

[eclipse 23]: taches(X),domaines(X,Fin),getVarList(X,Fin,Liste).

X = [](tache(3, [], m1, _519{1 .. 1.0Inf}), tache(8, [], m1, _685{1 .. 1.0Inf}), tache(8, [4, 5], m1, _832{1 .. 1.0Inf}), tache(6, [], m2, _977{1 .. 1.0Inf}), tache(3, [1], m2, _1122{1 .. 1.0Inf}), tache(4, [1, 7], m1, _1267{1 .. 1.0Inf}), tache(8, [3, 5], m1, _1412{1 .. 1.0Inf}), tache(6, [4], m2, _1557{1 .. 1.0Inf}), tache(6, [6, 7], m2, _1702{1 .. 1.0Inf}), tache(6, [9, 12], m2, _1847{1 .. 1.0Inf}), tache(3, [1], m2, _1992{1 .. 1.0Inf}), tache(6, [7, 8], m2, _2137{1 .. 1.0Inf}))
Fin = Fin{10 .. 1.0Inf}
Liste = [_2137{1 .. 1.0Inf}, _1992{1 .. 1.0Inf}, _1847{1 .. 1.0Inf}, _1702{1 .. 1.0Inf}, _1557{1 .. 1.0Inf}, _1412{1 .. 1.0Inf}, _1267{1 .. 1.0Inf}, _1122{1 .. 1.0Inf}, _977{1 .. 1.0Inf}, _832{1 .. 1.0Inf}, _685{1 .. 1.0Inf}, _519{1 .. 1.0Inf}, Fin{10 .. 1.0Inf}]

There are 12 delayed goals. Do you want to see them? (y/n) 

Delayed goals:
	-(_519{1 .. 1.0Inf}) + Fin{10 .. 1.0Inf} #> 3
	-(_685{1 .. 1.0Inf}) + Fin{10 .. 1.0Inf} #> 8
	-(_832{1 .. 1.0Inf}) + Fin{10 .. 1.0Inf} #> 8
	-(_977{1 .. 1.0Inf}) + Fin{10 .. 1.0Inf} #> 6
	-(_1122{1 .. 1.0Inf}) + Fin{10 .. 1.0Inf} #> 3
	-(_1267{1 .. 1.0Inf}) + Fin{10 .. 1.0Inf} #> 4
	-(_1412{1 .. 1.0Inf}) + Fin{10 .. 1.0Inf} #> 8
	-(_1557{1 .. 1.0Inf}) + Fin{10 .. 1.0Inf} #> 6
	-(_1702{1 .. 1.0Inf}) + Fin{10 .. 1.0Inf} #> 6
	-(_1847{1 .. 1.0Inf}) + Fin{10 .. 1.0Inf} #> 6
	-(_1992{1 .. 1.0Inf}) + Fin{10 .. 1.0Inf} #> 3
	-(_2137{1 .. 1.0Inf}) + Fin{10 .. 1.0Inf} #> 6
Yes (0.00s cpu)
[eclipse 24]: taches(X),domaines(X,40),getVarList(X,40,Liste).

X = [](tache(3, [], m1, _520{1 .. 36}), tache(8, [], m1, _639{1 .. 31}), tache(8, [4, 5], m1, _758{1 .. 31}), tache(6, [], m2, _877{1 .. 33}), tache(3, [1], m2, _996{1 .. 36}), tache(4, [1, 7], m1, _1115{1 .. 35}), tache(8, [3, 5], m1, _1234{1 .. 31}), tache(6, [4], m2, _1353{1 .. 33}), tache(6, [6, 7], m2, _1472{1 .. 33}), tache(6, [9, 12], m2, _1591{1 .. 33}), tache(3, [1], m2, _1710{1 .. 36}), tache(6, [7, 8], m2, _1829{1 .. 33}))
Liste = [_1829{1 .. 33}, _1710{1 .. 36}, _1591{1 .. 33}, _1472{1 .. 33}, _1353{1 .. 33}, _1234{1 .. 31}, _1115{1 .. 35}, _996{1 .. 36}, _877{1 .. 33}, _758{1 .. 31}, _639{1 .. 31}, _520{1 .. 36}, 40]
Yes (0.01s cpu)


%% Question 5
%%%%%%%%%%%%%


[eclipse 36]: solve(T,40).

T = [](tache(3, [], m1, 1), tache(8, [], m1, 1), tache(8, [4, 5], m1, 1), tache(6, [], m2, 1), tache(3, [1], m2, 1), tache(4, [1, 7], m1, 1), tache(8, [3, 5], m1, 1), tache(6, [4], m2, 1), tache(6, [6, 7], m2, 1), tache(6, [9, 12], m2, 1), tache(3, [1], m2, 1), tache(6, [7, 8], m2, 1))
Yes (0.00s cpu, solution 1, maybe more) ? ;

T = [](tache(3, [], m1, 2), tache(8, [], m1, 1), tache(8, [4, 5], m1, 1), tache(6, [], m2, 1), tache(3, [1], m2, 1), tache(4, [1, 7], m1, 1), tache(8, [3, 5], m1, 1), tache(6, [4], m2, 1), tache(6, [6, 7], m2, 1), tache(6, [9, 12], m2, 1), tache(3, [1], m2, 1), tache(6, [7, 8], m2, 1))
Yes (0.00s cpu, solution 2, maybe more) ? ;

T = [](tache(3, [], m1, 3), tache(8, [], m1, 1), tache(8, [4, 5], m1, 1), tache(6, [], m2, 1), tache(3, [1], m2, 1), tache(4, [1, 7], m1, 1), tache(8, [3, 5], m1, 1), tache(6, [4], m2, 1), tache(6, [6, 7], m2, 1), tache(6, [9, 12], m2, 1), tache(3, [1], m2, 1), tache(6, [7, 8], m2, 1))
Yes (0.00s cpu, solution 3, maybe more) ? 
[eclipse 37]: solve(T,Fin).

T = [](tache(3, [], m1, 1), tache(8, [], m1, 1), tache(8, [4, 5], m1, 1), tache(6, [], m2, 1), tache(3, [1], m2, 1), tache(4, [1, 7], m1, 1), tache(8, [3, 5], m1, 1), tache(6, [4], m2, 1), tache(6, [6, 7], m2, 1), tache(6, [9, 12], m2, 1), tache(3, [1], m2, 1), tache(6, [7, 8], m2, 1))
Fin = 10
Yes (0.00s cpu, solution 1, maybe more) ? ;

T = [](tache(3, [], m1, 1), tache(8, [], m1, 1), tache(8, [4, 5], m1, 1), tache(6, [], m2, 1), tache(3, [1], m2, 1), tache(4, [1, 7], m1, 1), tache(8, [3, 5], m1, 1), tache(6, [4], m2, 1), tache(6, [6, 7], m2, 1), tache(6, [9, 12], m2, 1), tache(3, [1], m2, 1), tache(6, [7, 8], m2, 1))
Fin = 11
Yes (0.00s cpu, solution 2, maybe more) ? 


%% Question 6
%%%%%%%%%%%%%

[eclipse 48]: taches(T), precedence(T), affiche(T).
tache(3, [], m1, _768{-1.0Inf .. 1.0Inf})
tache(8, [], m1, _300)
tache(8, [4, 5], m1, _533{-1.0Inf .. 1.0Inf})
tache(6, [], m2, _505{-1.0Inf .. 1.0Inf})
tache(3, [1], m2, _640{-1.0Inf .. 1.0Inf})
tache(4, [1, 7], m1, _899{-1.0Inf .. 1.0Inf})
tache(8, [3, 5], m1, _1006{-1.0Inf .. 1.0Inf})
tache(6, [4], m2, _1337{-1.0Inf .. 1.0Inf})
tache(6, [6, 7], m2, _1459{-1.0Inf .. 1.0Inf})
tache(6, [9, 12], m2, _1678{-1.0Inf .. 1.0Inf})
tache(3, [1], m2, _1916{-1.0Inf .. 1.0Inf})
tache(6, [7, 8], m2, _1785{-1.0Inf .. 1.0Inf})

T = [](tache(3, [], m1, _768{-1.0Inf .. 1.0Inf}), tache(8, [], m1, _300), tache(8, [4, 5], m1, _533{-1.0Inf .. 1.0Inf}), tache(6, [], m2, _505{-1.0Inf .. 1.0Inf}), tache(3, [1], m2, _640{-1.0Inf .. 1.0Inf}), tache(4, [1, 7], m1, _899{-1.0Inf .. 1.0Inf}), tache(8, [3, 5], m1, _1006{-1.0Inf .. 1.0Inf}), tache(6, [4], m2, _1337{-1.0Inf .. 1.0Inf}), tache(6, [6, 7], m2, _1459{-1.0Inf .. 1.0Inf}), tache(6, [9, 12], m2, _1678{-1.0Inf .. 1.0Inf}), tache(3, [1], m2, _1916{-1.0Inf .. 1.0Inf}), tache(6, [7, 8], m2, _1785{-1.0Inf .. 1.0Inf}))

There are 15 delayed goals. Do you want to see them? (y/n) 

Delayed goals:
	-(_505{-1.0Inf .. 1.0Inf}) + _533{-1.0Inf .. 1.0Inf} #> 6
	-(_640{-1.0Inf .. 1.0Inf}) + _533{-1.0Inf .. 1.0Inf} #> 3
	-(_768{-1.0Inf .. 1.0Inf}) + _640{-1.0Inf .. 1.0Inf} #> 3
	-(_768{-1.0Inf .. 1.0Inf}) + _899{-1.0Inf .. 1.0Inf} #> 3
	-(_1006{-1.0Inf .. 1.0Inf}) + _899{-1.0Inf .. 1.0Inf} #> 8
	-(_533{-1.0Inf .. 1.0Inf}) + _1006{-1.0Inf .. 1.0Inf} #> 8
	-(_640{-1.0Inf .. 1.0Inf}) + _1006{-1.0Inf .. 1.0Inf} #> 3
	-(_505{-1.0Inf .. 1.0Inf}) + _1337{-1.0Inf .. 1.0Inf} #> 6
	-(_899{-1.0Inf .. 1.0Inf}) + _1459{-1.0Inf .. 1.0Inf} #> 4
	-(_1006{-1.0Inf .. 1.0Inf}) + _1459{-1.0Inf .. 1.0Inf} #> 8
	-(_1459{-1.0Inf .. 1.0Inf}) + _1678{-1.0Inf .. 1.0Inf} #> 6
	-(_1785{-1.0Inf .. 1.0Inf}) + _1678{-1.0Inf .. 1.0Inf} #> 6
	-(_768{-1.0Inf .. 1.0Inf}) + _1916{-1.0Inf .. 1.0Inf} #> 3
	-(_1006{-1.0Inf .. 1.0Inf}) + _1785{-1.0Inf .. 1.0Inf} #> 8
	-(_1337{-1.0Inf .. 1.0Inf}) + _1785{-1.0Inf .. 1.0Inf} #> 6
Yes (0.01s cpu)


[eclipse 65]: taches2(T), domaines(T,20), precedence(T), affiche(T).
tache(8, [], m1, _421{1 .. 11})
tache(5, [], m1, _540{1 .. 10})
tache(4, [2], m2, _659{6 .. 15})

T = [](tache(8, [], m1, _421{1 .. 11}), tache(5, [], m1, _540{1 .. 10}), tache(4, [2], m2, _659{6 .. 15}))


Delayed goals:
	_540{1 .. 10} - _659{6 .. 15} #=< -5
Yes (0.00s cpu)


%% Question 7
%%%%%%%%%%%%%


[eclipse 39]: solve2(Taches,Fin).
tache(3, [], m1, 0)
tache(8, [], m1, 29)
tache(8, [4, 5], m1, 9)
tache(6, [], m2, 0)
tache(3, [1], m2, 6)
tache(4, [1, 7], m1, 25)
tache(8, [3, 5], m1, 17)
tache(6, [4], m2, 12)
tache(6, [6, 7], m2, 31)
tache(6, [9, 12], m2, 37)
tache(3, [1], m2, 9)
tache(6, [7, 8], m2, 25)

Taches = [](tache(3, [], m1, 0), tache(8, [], m1, 29), tache(8, [4, 5], m1, 9), tache(6, [], m2, 0), tache(3, [1], m2, 6), tache(4, [1, 7], m1, 25), tache(8, [3, 5], m1, 17), tache(6, [4], m2, 12), tache(6, [6, 7], m2, 31), tache(6, [9, 12], m2, 37), tache(3, [1], m2, 9), tache(6, [7, 8], m2, 25))
Fin = 44
Yes (0.01s cpu, solution 1, maybe more) ? ;
tache(3, [], m1, 0)
tache(8, [], m1, 29)
tache(8, [4, 5], m1, 9)
tache(6, [], m2, 0)
tache(3, [1], m2, 6)
tache(4, [1, 7], m1, 25)
tache(8, [3, 5], m1, 17)
tache(6, [4], m2, 12)
tache(6, [6, 7], m2, 31)
tache(6, [9, 12], m2, 37)
tache(3, [1], m2, 9)
tache(6, [7, 8], m2, 25)

Taches = [](tache(3, [], m1, 0), tache(8, [], m1, 29), tache(8, [4, 5], m1, 9), tache(6, [], m2, 0), tache(3, [1], m2, 6), tache(4, [1, 7], m1, 25), tache(8, [3, 5], m1, 17), tache(6, [4], m2, 12), tache(6, [6, 7], m2, 31), tache(6, [9, 12], m2, 37), tache(3, [1], m2, 9), tache(6, [7, 8], m2, 25))
Fin = 45


%% Question 8
%%%%%%%%%%%%%

eclipse 44]: ["tp3_cremilleux_holy_groupeB.ecl"].
tp3_cremilleux_holy_groupeB.ecl compiled 4008 bytes in 0.02 seconds

Yes (0.02s cpu)
[eclipse 45]: solveFinale(Taches,Fin).
tache(3, [], m1, 0)
tache(8, [], m1, 29)
tache(8, [4, 5], m1, 9)
tache(6, [], m2, 0)
tache(3, [1], m2, 6)
tache(4, [1, 7], m1, 25)
tache(8, [3, 5], m1, 17)
tache(6, [4], m2, 12)
tache(6, [6, 7], m2, 31)
tache(6, [9, 12], m2, 37)
tache(3, [1], m2, 9)
tache(6, [7, 8], m2, 25)

Taches = [](tache(3, [], m1, 0), tache(8, [], m1, 29), tache(8, [4, 5], m1, 9), tache(6, [], m2, 0), tache(3, [1], m2, 6), tache(4, [1, 7], m1, 25), tache(8, [3, 5], m1, 17), tache(6, [4], m2, 12), tache(6, [6, 7], m2, 31), tache(6, [9, 12], m2, 37), tache(3, [1], m2, 9), tache(6, [7, 8], m2, 25))
Fin = 44
Yes (0.02s cpu, solution 1, maybe more) ? 

*/