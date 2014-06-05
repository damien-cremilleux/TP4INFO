:-lib(ic).
:-lib(ic_global).

solve(Hex) :-
        generer(Hex),
        poserContraintes(Hex),
        labeling(Hex).

generer(Hex) :-
        dim(Hex,[19]),
        Hex #:: 1..19.

poserContraintes(Hex) :-
        alldifferent(Hex),
        
        A is Hex[1],
        B is Hex[2],
        C is Hex[3],
        D is Hex[4],
        E is Hex[5],
        F is Hex[6],
        G is Hex[7],
        H is Hex[8],
        I is Hex[9],
        J is Hex[10],
        K is Hex[11],
        L is Hex[12],
        M is Hex[13],
        N is Hex[14],
        O is Hex[15],
        P is Hex[16],
        Q is Hex[17],
        R is Hex[18],
        S is Hex[19],
        
        Somme is 38,
        
        sumlist([A,D,H],Somme),
        sumlist([B,E,I,M],Somme),
        sumlist([C,F,J,N,Q],Somme),
        sumlist([G,K,O,R],Somme),
        sumlist([L,P,S],Somme),
        sumlist([H,M,Q],Somme),
        sumlist([D,I,N,R],Somme),
        sumlist([A,E,J,O,S],Somme),
        sumlist([B,F,K,P],Somme),
        sumlist([C,G,L],Somme),
        
        
        % S1 #= S2, 
        % S2 #= S3,
        % S3 #= S4, 
        % S4 #= S5,
        % S5 #= S6,
        % S6 #= S7, 
        % S7 #= S8,
        % S8 #= S9,
        % S9 #= S10,
        
        A #< S,
        A #< C.