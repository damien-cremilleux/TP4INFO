% Une affaire de famille

:- set(i,2).
:- set(verbose,1).

:- modeh(1,daughter(+p,+p)).

:- modeb(*,mother(+p,-p)).
:- modeb(1,mother(-p,+p)).
:- modeb(*,father(+p,-p)).
:- modeb(1,father(-p,+p)).
:- modeb(1,male(+p)).
:- modeb(1,female(+p)).


:- determination(daughter/2,mother/2).
:- determination(daughter/2,father/2).
:- determination(daughter/2,male/1).
:- determination(daughter/2,female/1).

% type definitions
mother(ann,mary).
mother(ann,tom).
mother(mary,rosy).
father(tom,eve).
father(tom,lisa).
father(tom,bob).
female(ann).
female(mary).
female(rosy).
female(lisa).
female(eve).
male(tom).
male(bob).
