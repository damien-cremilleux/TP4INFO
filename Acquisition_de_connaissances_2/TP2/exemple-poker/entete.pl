
%%%%%%%%%%%%%%%%%%%%%%%%%
% Paramètres d'exécution
%

:- set( clauselength, 100 ).      % on borne la taille des clauses
:- set( minacc, 0.99 ).          % [0-1] on autorise x% de bruit au maximum
:- set( noise, 1).               % nb max d'e- pouvant etre couverts par une hypothese
%:- set( minposfrac, 0.03 ).      % pourcentage min de couverture de E+
:- set( minpos, 2 ).              % nb e+ minimal pour une hypothese
:- set( minscore, 0.1 ).          % score minimal pour une hypothese
:- set( verbose, 0 ).             % pas de blabla, on travaille
:- set( i, 3 ).                   % longueur max de connexion avec les var de tete
:- set( depth, 30 ).              % profondeur de preuve (pour couverture)
:- set( newvars, 20).             % nombre max var introduite
:- set( nodes, 100000 ).           % nb maximum de clauses construites dans la recherche
:- set( check_useless, true).     % pour avoir des var qui servent a qq chose ds bot
:- set( record, true ).           % on garde une trace...
:- set( recordfile, 'test_modif_v2'). % ...dans ce fichier
:- set( evalfn, coverage ).       %  fonction d'evaluation de la qualite des clauses
:- set( lazy_on_contradiction, true).
:- set( search, bf ).      % shorter first: bf, best first: heuristic




%%%%%%%%%%%%%%%%%%%%%%%%% Definition du langage d'hypothese Lh
% Modes
%       modeh are mode declarations for head literals
%       modeb are mode declarations for body literals
%       + represents input arguments
%       - represents output arguments
%       + or - are followed by type of each argument
%       in this example, the type "int" is a built-in unary predicate

:- modeh(1,carre(+main)).

:- modeb(*,cartes(+main,main(-carte,-carte,-carte,-carte,-carte))).
:- modeb(5,a_carte(+main,-carte)).
:- modeb(1,valeur(+carte,-valeur)).
:- modeb(1,couleur(+carte,-couleur)).

:- determination(carre/1,cartes/2).
:- determination(carre/1,a_carte/2).
:- determination(carre/1,valeur/2).
:- determination(carre/1,couleur/2).


cartes(Jeu,main(C1,C2,C3,C4,C5)) :-
	a_carte(Jeu,C1),
	a_carte(Jeu,C2),
	\==(C1,C2),
	a_carte(Jeu,C3),
	\==(C1,C3),
	\==(C2,C3),
	a_carte(Jeu,C4),
	\==(C1,C4),
	\==(C2,C4),
	\==(C3,C4),
	a_carte(Jeu,C5),
	\==(C1,C5),
	\==(C2,C5),
	\==(C3,C5),
	\==(C4,C5).
