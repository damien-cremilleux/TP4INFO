% DS 2013 - Damien Cremilleux

:-lib(ic).

solve(Tab) :-
        tabPersonne(Tab),
        poserContraintes(Tab),
        labeling(Tab),
        printTab(Tab).

tabPersonne(Tab) :-
        dim(Tab,[4,4]),
        Tab :: 0..1.

poserContraintes(Tab) :-
        contrainteSym(Tab),
        contrainte1(Tab),
        contrainte2(Tab),
        contrainte3(Tab),
        contrainte4(Tab),
        contrainte5(Tab),
        contrainte6(Tab),
        contrainte7(Tab).

contrainteSym(Tab) :-
        (multifor([I,J],[1,1],[4,4]),
         param(Tab)
        do
            Case1 is Tab[I,J],
            Case2 is Tab[J,I],
            Case1 #= Case2
        ).

contrainte1(Tab) :-
        Case is Tab[4,3],
        Case #= 1.

contrainte2(Tab) :-
        Case is Tab[2,4],
        Case #= 0.

contrainte3(Tab) :-
        Case is Tab[3,1],
        Case #= 0.

contrainte4(Tab) :-
        (multifor([I,J],[1,1],[4,4]),
         param(Tab)
        do
            Case1 is Tab[I,J],
            Case2 is Tab[J,I],
            (Case1 #= 0) => (Case2 #= 0)
        ).

contrainte5(Tab) :-
        (for(I, 1, 4),
         param(Tab)
        do
            Case1 is Tab[I,2],
            Case2 is Tab[1,I],
            (Case1 #= 1) => (Case2 #= 1)
        ).

contrainte6(Tab) :-
        (for(I, 1, 4),
         param(Tab)
        do
            Case1 is Tab[2,I],
            Case2 is Tab[4,I],
            (Case1 #= 1) => (Case2 #= 1)
        ).

contrainte7(Tab) :-
        (for(I, 1, 4),
         param(Tab)
        do
            Case1 is Tab[I,1],
            Case2 is Tab[I,2],
            Case3 is Tab[I,3],
            Case4 is Tab[I,4],
            Case1 or Case2 or Case3 or Case4
        ).

printTab(Tab) :-
        dim(Tab,[4,4]),
        (for(I, 1, 4), param(Tab) do
            (for(J, 1, 4), param(Tab, I) do
                X is Tab[I,J],
                (var(X) -> write("_"); printf(" %2d",[X]))
            ),
            nl
        ),
        nl.