%% TP6 Contraintes - Sur une balançoire
%% Damien Crémilleux - Lauriane Holy
:-lib(ic).
:-lib(branch_and_bound).

getPoids([](24, 39, 85, 60, 165, 6, 32, 123, 7, 14)).

getPlace(Balancoire):-
        dim(Balancoire,[10]),
        Balancoire #:: [-8.. -1, 1.. 8].


%% Chaque personne est à une place différente
place_differente(Balancoire) :-
        alldifferent(Balancoire).


%% La balançoire doit être équilibrée
balancoire_equilibree(Balancoire,Poids,Moment) :-
        (for(I, 1, 10),
         param(Balancoire,Poids),
         fromto(0, In, Out, PoidsTotal),
         fromto(0, In2, Out2, Moment),
         param(Balancoire,Poids)
        do
            Out #= In + Poids[I]*Balancoire[I],
            
            Out2 #=  In2 + Poids[I]*abs(Balancoire[I])
        ),
        PoidsTotal #= 0.

%% Les parents encadrent les enfants
parents_encadrent(Balancoire) :-
        Papa is Balancoire[8],
        Maman is Balancoire[4],
        ic:max(Balancoire,Max),
        ic:min(Balancoire,Min),
        (Papa #= Min) or (Papa #= Max),
        (Maman #= Min) or (Maman #= Max).

%% Les deux plus jeunes sont sur deux côtés opposés, juste devant leur parents
placer_jeunes(Balancoire) :-
        Papa is Balancoire[8],
        Maman is Balancoire[4],
        Dan is Balancoire[6],
        Max is Balancoire[9],
        Papa #< 0 => (Dan #= Papa+1 and Max #= Maman-1) or (Max #= Papa+1 and Dan #= Maman-1),
        Papa #> 0 => (Dan #= Papa-1 and Max #= Maman+1) or (Max #= Papa-1 and Dan #= Maman+1).

%% Il y a cinq personnes de chaque cote
cinq_personnes(Balancoire) :-
        (for(I, 1, 10),
         param(Balancoire),
         fromto(0, In, Out, Total),
         param(Balancoire)
        do
            Out #= In + (Balancoire[I] #> 0) - (Balancoire[I] #< 0) 
        ),
        Total #= 0.


%% On enlève les symétries
%% En effet, il y a un axe de symétrie au mileu. On force le père à être à gauche.
%% Cela permet d'accélérer la recherche des solutions, en élaguant une partie de l'arbre de recherche (la moitié dans notre cas).
enlever_sym(Balancoire) :-
        Papa is Balancoire[8],
        Papa #< 0.


%% Pose toutes les contraintes
contraintes(Balancoire, Poids, Moment) :-
        place_differente(Balancoire),
        cinq_personnes(Balancoire),
        balancoire_equilibree(Balancoire,Poids,Moment),
        parents_encadrent(Balancoire),
        placer_jeunes(Balancoire),
        enlever_sym(Balancoire).

%% Résolution naive du problème
solve(Balancoire, Moment) :-
        getPoids(Poids),
        getPlace(Balancoire),
        contraintes(Balancoire, Poids, Moment),
        labeling(Balancoire).

%% Résolution du problème -- version 1
solve_v1(Balancoire, Moment) :-
        getPoids(Poids),
        getPlace(Balancoire),
        contraintes(Balancoire, Poids, Moment),
        search(Balancoire, 0, most_constrained, indomain, complete, []).

%% Résolution du problème -- version 2
%% Pour chaque variable, on essaye les valeurs en partant du milieu du domaine pour réduire le moment
solve_v2(Balancoire, Moment) :-
        getPoids(Poids),
        getPlace(Balancoire),
        contraintes(Balancoire, Poids, Moment),
        search(Balancoire, 0, input_order, indomain_median, complete, []).

%% Résolution du problème -- version 3
%% On combine les versions 1 et 2
solve_v3(Balancoire, Moment) :-
        getPoids(Poids),
        getPlace(Balancoire),
        contraintes(Balancoire, Poids, Moment),
        search(Balancoire, 0, most_constrained, indomain_median, complete, []).

%% On classe les variables, avec les personnes de forte corpulence devant afin de réduire le moment.
classer_gros(Balancoire, [Luc, Tom, Jim, Lou, Zoe, Ted, Ron, Kim, Max, Dan]):-
        Ron is Balancoire[1],
        Zoe is Balancoire[2],
        Jim is Balancoire[3],
        Lou is Balancoire[4],
        Luc is Balancoire[5],
        Dan is Balancoire[6],
        Ted is Balancoire[7],
        Tom is Balancoire[8],
        Max is Balancoire[9],
        Kim is Balancoire[10].

%% Résolution du problème -- version 4
%% On utilise le nouvel ordre des variables
solve_v4(Balancoire, Moment) :-
        getPoids(Poids),
        getPlace(Balancoire),
        contraintes(Balancoire, Poids, Moment),
        classer_gros(Balancoire, Variables),
        search(Variables, 0, input_order, indomain_median, complete, []).


%%%%%%%%%%
%% TEST %%
%%%%%%%%%%

/*
%% Question 6.3

[eclipse 65]: solve(B,M).

B = [](-6, -5, -1, 8, 5, 7, 3, -8, -7, 1)
M = 2914
Yes (0.03s cpu, solution 1, maybe more) ? ;

	B = [](-6, -5, 2, 8, 4, -7, -2, -8, 7, 5)
    M = 2858
Yes (0.05s cpu, solution 2, maybe more) ? ;

	B = [](-6, -5, 2, 8, 4, 7, -2, -8, -7, 6)
    M = 2872


%%[eclipse 5]: minimize(solve(B,M),M).
Found a solution with cost 2914
Found a solution with cost 2858
Found a solution with cost 2808
Found a solution with cost 2722
Found a solution with cost 2716
Found a solution with cost 2708
Found a solution with cost 2694
Found a solution with cost 2602
Found a solution with cost 2594
Found a solution with cost 2524
Found a solution with cost 2474
Found a solution with cost 2430
Found a solution with cost 2392
Found a solution with cost 2344
Found a solution with cost 2296
Found a solution with cost 2218
Found a solution with cost 2196
Found a solution with cost 2154
Found a solution with cost 2142
Found a solution with cost 2064
Found a solution with cost 1958
Found a solution with cost 1890
Found a solution with cost 1748
Found a solution with cost 1744
Found a solution with cost 1704
Found a solution with cost 1604
Found no solution with cost -1.0Inf .. 1603

B = [](3, -1, 2, 6, 1, -4, -3, -5, 5, -2)
M = 1604
Yes (1.65s cpu)
Question 6.3


%% Version 1
[eclipse 8]: minimize(solve_v1(B,M),M).
Found a solution with cost 2364
Found a solution with cost 2354
Found a solution with cost 2324
Found a solution with cost 2038
Found a solution with cost 1982
Found a solution with cost 1946
Found a solution with cost 1920
Found a solution with cost 1886
Found a solution with cost 1880
Found a solution with cost 1788
Found a solution with cost 1604
Found no solution with cost -1.0Inf .. 1603

B = [](3, -1, 2, 6, 1, -4, -3, -5, 5, -2)
M = 1604
Yes (0.63s cpu)

%% Version 2
[eclipse 15]: minimize(solve_v2(B,M),M).
Found a solution with cost 2276
Found a solution with cost 2106
Found a solution with cost 1944
Found a solution with cost 1930
Found a solution with cost 1912
Found a solution with cost 1906
Found a solution with cost 1898
Found a solution with cost 1704
Found a solution with cost 1604
Found no solution with cost -1.0Inf .. 1603

B = [](3, -1, 2, 6, 1, -4, -3, -5, 5, -2)
M = 1604
Yes (1.19s cpu)

%% Version 3
[eclipse 20]: minimize(solve_v3(B,M),M).
Found a solution with cost 1930
Found a solution with cost 1696
Found a solution with cost 1604
Found no solution with cost -1.0Inf .. 1603

B = [](3, -1, 2, 6, 1, -4, -3, -5, 5, -2)
M = 1604
Yes (0.14s cpu)


%% Version 4

[eclipse 53]: minimize(solve_v4(B,M),M).
Found a solution with cost 1604
Found no solution with cost -1.0Inf .. 1603

B = [](3, -1, 2, 6, 1, -4, -3, -5, 5, -2)
M = 1604
Yes (0.21s cpu)

*/