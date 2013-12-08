/**
TP 9 Prolog

@author Damien CREMILLEUX
@author Lauriane HOLY
@version Annee scolaire 2013/2014
*/

%%Liste Copains pour les tests
copain([damien, arnaud, samuel, aurelien, lauriane, clement]).
exemple([plutot,riri,fifi,loulou]).


%% Question 1.1
%% combiner(+Copains,-Binomes) : 'a list * ('a * 'a) list
%% Produit la liste de tous les binomes

%% Associe un binome avec le reste de la liste
associer(_,[],[]).
associer(Binome,[Bin1| Reste],[(Binome,Bin1)|ResTemp]) :-
	associer(Binome,Reste,ResTemp).
	
combiner([],[]).
combiner([Copain1|Rest],Binomes) :-
	associer(Copain1,Rest,Binomes1),
	combiner(Rest,Binomes2),
	append(Binomes1,Binomes2,Binomes).

%%Question 1.2
%%extraire(+AllPossibleBinomes, +NbBinomes, -Tp, -RemainingBinomes)
%% 

extraire2(BinomeRestant,0,[],BinomeRestant,_).

extraire2([(Bin1,Bin2)|Reste],NbBinomes,[(Bin1,Bin2)|Tp],RemainingBinomes,BinDejaPris) :-
	\==(NbBinomes,0),
	not(member(Bin1,BinDejaPris)),
	not(member(Bin2,BinDejaPris)),
	NbBinomes2 is NbBinomes-1,
	extraire2(Reste,NbBinomes2,Tp,RemainingBinomes,[Bin1,Bin2|BinDejaPris]).

extraire2([(Bin1,Bin2)|Reste],NbBinomes,Tp,[(Bin1,Bin2)|RemainingBinomes],BinDejaPris) :-
	\==(NbBinomes,0),
	extraire2(Reste,NbBinomes,Tp,RemainingBinomes,BinDejaPris).
	
extraire(AllPossibleBinomes,NbBinomes, Tp, RemainingBinomes) :-
	extraire2(AllPossibleBinomes,NbBinomes, Tp, RemainingBinomes,[]).


%% Question 1.3
%% les_tps(+Copain,-Tp)

nombre_binome([],0).
nombre_binome([_,_|Rest],Res) :-
	nombre_binome(Rest,Res2),
	Res is Res2+1.



les_tps(Copain,Tp) :-
	combiner(Copain,Binomes),
	nombre_binome(Copain,NbBin),
	les_tps2(Binomes, NbBin, Tp).

les_tps2([],_,[]).

les_tps2(Copains, NbBin, [Tp2|Reste]) :-
	extraire(Copains,NbBin, Tp2, RemainingBinomes),
	les_tps2(RemainingBinomes,NbBin, Reste),
	!.

	


%%%%%%%%%%%%%%%%%%%%%%%%%%% TEST %%%%%%%%%%%%%%%%%%%%%%%%%%
/*

%%Question 1.1
[eclipse 13]: copain(X), combiner(X,Binomes).

X = [damien, arnaud, samuel, aurelien, lauriane, clement]
Binomes = [(damien, arnaud), (damien, samuel), (damien, aurelien), (damien, lauriane), (damien, clement), (arnaud, samuel), (arnaud, aurelien), (arnaud, lauriane), (arnaud, clement), (samuel, aurelien), (samuel, lauriane), (samuel, clement), (aurelien, lauriane), (aurelien, clement), (lauriane, clement)]
Yes (0.00s cpu)

[eclipse 15]: exemple(X), combiner(X,Binomes).

X = [plutot, riri, fifi, loulou]
Binomes = [(plutot, riri), (plutot, fifi), (plutot, loulou), (riri, fifi), (riri, loulou), (fifi, loulou)]
Yes (0.00s cpu)

%% Question 1.2
[eclipse 7]: exemple(X), combiner(X,Binomes),extraire(Binomes,2,Tp,R).

X = [plutot, riri, fifi, loulou]
Binomes = [(plutot, riri), (plutot, fifi), (plutot, loulou), (riri, fifi), (riri, loulou), (fifi, loulou)]
Tp = [(plutot, riri), (fifi, loulou)]
R = [(plutot, fifi), (plutot, loulou), (riri, fifi), (riri, loulou)]
Yes (0.00s cpu, solution 1, maybe more) ? ;

X = [plutot, riri, fifi, loulou]
Binomes = [(plutot, riri), (plutot, fifi), (plutot, loulou), (riri, fifi), (riri, loulou), (fifi, loulou)]
Tp = [(plutot, fifi), (riri, loulou)]
R = [(plutot, riri), (plutot, loulou), (riri, fifi), (fifi, loulou)]
Yes (0.00s cpu, solution 2, maybe more) ? ;

X = [plutot, riri, fifi, loulou]
Binomes = [(plutot, riri), (plutot, fifi), (plutot, loulou), (riri, fifi), (riri, loulou), (fifi, loulou)]
Tp = [(plutot, loulou), (riri, fifi)]
R = [(plutot, riri), (plutot, fifi), (riri, loulou), (fifi, loulou)]
Yes (0.00s cpu, solution 3, maybe more) ? ;

No (0.00s cpu)

%% Question 1.3

[eclipse 41]: exemple(X), les_tps(X,Res).

X = [plutot, riri, fifi, loulou]
Res = [[(plutot, riri), (fifi, loulou)], [(plutot, fifi), (riri, loulou)], [(plutot, loulou), (riri, fifi)]]
Yes (0.00s cpu)
[eclipse 42]: copain(X), les_tps(X,Res).

X = [damien, arnaud, samuel, aurelien, lauriane, clement]
Res = [[(damien, arnaud), (samuel, aurelien), (lauriane, clement)], [(damien, samuel), (arnaud, lauriane), (aurelien, clement)], [(damien, aurelien), (arnaud, clement), (samuel, lauriane)], [(damien, lauriane), (arnaud, aurelien), (samuel, clement)], [(damien, clement), (arnaud, samuel), (aurelien, lauriane)]]
Yes (0.00s cpu)
*/