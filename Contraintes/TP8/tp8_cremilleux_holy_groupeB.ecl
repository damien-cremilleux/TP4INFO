%% TP6 Contraintes - Histoire de menteurs
%% Damien Crémilleux - Lauriane Holy

:-lib(ic_symbolic).
:-lib(ic).


:-local domain(sexe(femme,homme)).


%% Question 8.1
%% affirme(?S,?A) : l'affirmation A est vraie si S est une femme.

affirme(S,A):-
        (S &= femme) => (A #=1).


%% Question 8.2
%% affirme(?S,?A1,?A2) : si S est un homme, les affirmations A1 et A2 sont l'une vraie, l'autre fausse.

affirme(S,A1,A2) :-
        (S &= homme) => ((A1 #=1 and A2 #=0) or (A1 #=0 and A2 #=1)).

%% Question 8.3
%% domaine(P1,P2,Enf) : définit les domaine de l'ensemble des variables du problème
domaine(P1,P2,Enf) :-
        P1 &:: sexe,
        P2 &:: sexe, 
        Enf &:: sexe.

%% Question 8.4
%% contraintes() : pose les contraintes du problème 
contraintes(P1,P2,Enf):-
        P1 &\= P2,
        AffE #:: 0..1,
        AffEselonP1 #:: 0..1,
        AffP1 #:: 0..1,
        Aff1P2 #:: 0..1,
        Aff2P2 #:: 0..1,
        AffEselonP1 #= (Enf &= femme),
        AffP1 #= (AffEselonP1 #= AffE),
        Aff1P2 #= (Enf &= homme),
        Aff2P2 #= (AffE #= 0),
        affirme(Enf, AffE),
        affirme(P1, AffP1), 
        affirme(P2, Aff1P2, Aff2P2),
        affirme(P2, Aff1P2 and Aff2P2).

labeling_personnes([]).
labeling_personnes([Personne|Reste]):-
        ic_symbolic:indomain(Personne),
        labeling_personnes(Reste).

solve(P1,P2,Enf) :-
        domaine(P1,P2,Enf),
        contraintes(P1,P2,Enf),
        labeling_personnes([P1,P2,Enf]).
