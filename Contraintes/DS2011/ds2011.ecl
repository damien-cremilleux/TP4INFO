:-lib(ic).
:-lib(ic_global).

solve(L,N) :-
        generer(L,N),
        poserContraintes(L,N),
        labeling(L).

generer(L,N) :-
        dim(L,[N]),
        N1 is N-1,
        L #:: 0..N1.

poserContraintes(L,N) :-
        (for(I, 0, N-1), param(L)
        do
            occurrences(I,L,Ni),
            L[I+1] #= Ni
        ).