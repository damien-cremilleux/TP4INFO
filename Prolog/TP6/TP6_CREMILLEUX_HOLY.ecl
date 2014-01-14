%% TP6
%% Damien Crémilleux, Lauriane Holy

%%%%%%%%%%% First part

copy_prog(program(
                     start, 
                     [stop], 
                     [delta(start, ' ', ' ', right, stop),
                      delta(start, 1, ' ', right, s2),
                      delta(s2, 1, 1, right, s2),
                      delta(s2, ' ', ' ', right, s3),
                      delta(s3, 1, 1, right, s3),
                      delta(s3, ' ', 1, left, s4),
                      delta(s4, 1, 1, left, s4),
                      delta(s4, ' ', ' ', left, s5),
                      delta(s5, 1, 1, left, s5),
                      delta(s5, ' ', 1, right, start)
                     ]
                 )
         ).

initial_state(program(InitialState, _, _), InitialState).

final_states(program(_, FinalStates, _), FinalStates).

transitions(program(_, _, Deltas), Deltas).


%write to meta post format
%compile result with: 
% mpost filename
% epstopdf filename.1
dump_to_mpost(Filename, Dump) :-
        open(Filename, write, Stream),
	        write_header(Stream),
        write_dump(0, Dump, Stream),
        write_end(Stream),
        close(Stream).

write_header(Stream) :-
        write(Stream, 'prologues := 1;\n'),
        write(Stream, 'input turing;\n'),
        write(Stream, 'beginfig(1)\n').

write_end(Stream) :-
        write(Stream, 'endfig;\n'),
        write(Stream, 'end').

write_dump(_, [], _).
write_dump(Y, [(State, Tape) | Tapes], Stream) :-
        write(Stream, 'tape(0, '),
        write(Stream, Y),
        write(Stream, 'cm, 1cm, \"'),
        write(Stream, State),
        write(Stream, '\", '),
        write_tape(Tape, Stream),
        write(Stream, ');\n'),
        Y1 is Y - 2,
        write_dump(Y1, Tapes, Stream).

write_tape(tape(Left, Right), Stream) :-
        length(Left, N),
        write(Stream, '\"'),
        append(Left, Right, L),
        (param(Stream), foreach(X, L) do 
            write(Stream, X)        
        ),
        write(Stream, '\", '),
        write(Stream, N),
        write('\n').

% next(+program, +state, + symbol, -symbol, -direction, -state)
next(Prog, Etat0, Symbol0, Symbol1,Dir, Etat1) :-
	transitions(Prog,LTrans),
	nextTransition(Etat0,Symbol0,LTrans,delta(Etat0,Symbol0,Symbol1,Dir, Etat1)).


%% Renvoie la transition correspondante
%% nextTransitions(+,+,+,-)
nextTransition(Etat0,Symbol0, [delta(Etat0,Symbol0,Symbol1,Dir,Etat1)| _], delta(Etat0,Symbol0,Symbol1,Dir,Etat1)) :-
	!.

nextTransition(Etat0,Symbol0, [delta(_,_,_,_,_)| Rest],Delta) :-
	nextTransition(Etat0,Symbol0,Rest,Delta).
/*	
nextTransition(Etat0,Symbol0, [delta(_,Symbol1,_,_,_)| Rest],Delta) :-
	nextTransition(Etat0,Symbol0,Rest,Delta).
*/
%%update_tape(+Tape,+Symbol,+Direction,-UpdateTape)

update_tape(tape(Left,[_|RestRight]),NewSymb,right,tape(Left2,RestRight)):-
	\==(RestRight, []),
	append(Left,[NewSymb],Left2).

update_tape(tape(Left,[_]),NewSymb,right,tape(Left2,[' '])) :-
	append(Left,[NewSymb],Left2).	


update_tape(tape([Elem],[_|Right]),NewSymb,left,tape([' '],[Elem,NewSymb|Right])).


update_tape(tape(Left,[_|RestRight]),NewSymb,left,tape(Liste,[NewSymb2,NewSymb|RestRight])) :-
	append(Liste,[NewSymb2],Left),
	\==(Liste, []).

%% run_turing_machine(+Program,+Input,-Output,-FinalState)
run_turing_machine(Prog,Input,Output,FinalState) :-
	initial_state(Prog,InitialState),
	final_states(Prog, ListeFinalStates),
	run_turing_machine2(Prog,tape([' '],Input),tape(Left,Right),FinalState,InitialState,ListeFinalStates),
	append(Left,Right,Output).

run_turing_machine2(Prog,tape(Left,[Elem|Right]),Output,FinalState,InitialState,ListeFinalStates) :-
	next(Prog,InitialState,Elem, Symbol1,Dir,FinalState),
	membre(FinalState,ListeFinalStates),
	update_tape(tape(Left,[Elem|Right]),Symbol1,Dir,Output).

run_turing_machine2(Prog,tape(Left,[Elem|Right]),Output,FinalState,InitialState,ListeFinalStates) :-
	next(Prog,InitialState,Elem, Symbol1,Dir,CurrentState),
	update_tape(tape(Left,[Elem|Right]),Symbol1,Dir,Output2),
	run_turing_machine2(Prog, Output2, Output,FinalState,CurrentState,ListeFinalStates).


membre(X,[X|_]).
membre(X,[A|Y]) :-
	\==(X,A),
	membre(X,Y).


%% run_turing_machine(+Program,+Input,-Output,-FinalState)
run_turing_machinev2(Prog,Input,Output,FinalState,Dump) :-
	initial_state(Prog,InitialState),
	final_states(Prog, ListeFinalStates),
	run_turing_machine2v2(Prog,tape([' '],Input),tape(Left,Right),FinalState,InitialState,ListeFinalStates,Dump),
	append(Left,Right,Output).

run_turing_machine2v2(Prog,tape(Left,[Elem|Right]),Output,FinalState,InitialState,ListeFinalStates,[(InitialState,tape(Left,[Elem|Right])),(FinalState,Output)]) :-
	next(Prog,InitialState,Elem, Symbol1,Dir,FinalState),
	membre(FinalState,ListeFinalStates),
	update_tape(tape(Left,[Elem|Right]),Symbol1,Dir,Output).

run_turing_machine2v2(Prog,tape(Left,[Elem|Right]),Output,FinalState,InitialState,ListeFinalStates,[(InitialState,tape(Left,[Elem|Right]))|SuiteDump]) :-
	next(Prog,InitialState,Elem, Symbol1,Dir,CurrentState),
	update_tape(tape(Left,[Elem|Right]),Symbol1,Dir,Output2),
	run_turing_machine2v2(Prog, Output2, Output,FinalState,CurrentState,ListeFinalStates,SuiteDump).


%%%%%%%%%%% Optional part        

%make_pairs(+, -): 'a list * ('a * 'a) list
make_pairs([], _, []).
make_pairs([X | L], L2, Res) :-
        make_pairs_aux(X, L2, Pairs),
        make_pairs(L, L2, RemainingPairs),
        append(Pairs, RemainingPairs, Res).

%make_pairs_aux(+, +, -): 'a * 'a list * ('a * 'a) list
make_pairs_aux(_, [], []).
make_pairs_aux(X, [Y | Ys], [(X, Y) | Zs]) :-
        make_pairs_aux(X, Ys, Zs).

complete(S1, Sym, Symbols, Directions, States, Res) :-
        member(Sym1, Symbols),
        member(Dir, Directions),
        member(S2, States),
        Res = delta(S1, Sym, Sym1, Dir, S2).

complete_list([], _, _, _, []).
complete_list([(S, Sym) | Pairs], Symbols, Directions, States, [Delta | Deltas]) :-
        complete(S, Sym, Symbols, Directions, States, Delta),
        complete_list(Pairs, Symbols, Directions, States, Deltas).



%% TEST 

%% Question 1.1
/*
[eclipse 9]: copy_prog(Prog), next(Prog, start, 1, Newsymbol, Dir, NextState).

Prog = program(start, [stop], [delta(start, ' ', ' ', right, stop), delta(start, 1, ' ', right, s2), delta(s2, 1, 1, right, s2), delta(s2, ' ', ' ', right, s3), delta(s3, 1, 1, right, s3), delta(s3, ' ', 1, left, s4), delta(s4, 1, 1, left, s4), delta(s4, ' ', ' ', left, s5), delta(s5, 1, 1, left, s5), delta(s5, ' ', 1, right, start)])
Newsymbol = ' '
Dir = right
NextState = s2
Yes (0.00s cpu, solution 1, maybe more) ? ;

No (0.00s cpu)



[eclipse 10]: copy_prog(Prog), next(Prog, stop, 1, Newsymbol, Dir, NextState).
No (0.00s cpu)


[eclipse 11]: copy_prog(Prog), next(Prog, s2, ' ', Newsymbol, Dir, NextState).
Prog = program(start, [stop], [delta(start, ' ', ' ', right, stop), delta(start, 1, ' ', right, s2), delta(s2, 1, 1, right, s2), delta(s2, ' ', ' ', right, s3), delta(s3, 1, 1, right, s3), delta(s3, ' ', 1, left, s4), delta(s4, 1, 1, left, s4), delta(s4, ' ', ' ', left, s5), delta(s5, 1, 1, left, s5), delta(s5, ' ', 1, right, start)])
Newsymbol = ' '
Dir = right
NextState = s3



%% Question 1.2

[eclipse 37]: update_tape(tape([' '],[1,' ']),' ',right, UpdateTape).

UpdateTape = tape([' ', ' '], [' '])
Yes (0.00s cpu, solution 1, maybe more) ? ;

No (0.00s cpu)
[eclipse 38]: update_tape(tape([' '],[1,' ']),1,right, UpdateTape).

UpdateTape = tape([' ', 1], [' '])
Yes (0.00s cpu, solution 1, maybe more) ? ;

No (0.00s cpu)

[eclipse 40]: update_tape(tape([' '],[1,' ']),' ',right, UpdateTape).

UpdateTape = tape([' ', ' '], [' '])
Yes (0.00s cpu, solution 1, maybe more) ? ;



[eclipse 34]: update_tape(tape([' '],[1,' ']),' ',right, UpdateTape).

UpdateTape = tape([' ', ' '], [' '])
Yes (0.00s cpu, solution 1, maybe more) ? ;

No (0.00s cpu)



[eclipse 35]: update_tape(tape([' '],[1,' ']),' ',right, UpdateTape).

UpdateTape = tape([' ', ' '], [' '])
Yes (0.00s cpu, solution 1, maybe more) ? ;

No (0.00s cpu)


[eclipse 36]: update_tape(tape([' '],[1,' ']),' ',left, UpdateTape).

UpdateTape = tape([' '], [' ', ' ', ' '])
Yes (0.00s cpu, solution 1, maybe more) ? ;

No (0.00s cpu)


%% Question 1.3
[eclipse 115]: copy_prog(X), run_turing_machine(X,[1],Output,FinalState).

X = program(start, [stop], [delta(start, ' ', ' ', right, stop), delta(start, 1, ' ', right, s2), delta(s2, 1, 1, right, s2), delta(s2, ' ', ' ', right, s3), delta(s3, 1, 1, right, s3), delta(s3, ' ', 1, left, s4), delta(s4, 1, 1, left, s4), delta(s4, ' ', ' ', left, s5), delta(s5, 1, 1, left, s5), delta(s5, ' ', 1, right, start)])
Output = [' ', 1, ' ', 1]
FinalState = stop
Yes (0.00s cpu, solution 1, maybe more) ? ;

No (0.00s cpu)
*/

/* Question 1.4

[eclipse 141]: copy_prog(X), run_turing_machinev2(X,[1],Output,FinalState,Dump).

X = program(start, [stop], [delta(start, ' ', ' ', right, stop), delta(start, 1, ' ', right, s2), delta(s2, 1, 1, right, s2), delta(s2, ' ', ' ', right, s3), delta(s3, 1, 1, right, s3), delta(s3, ' ', 1, left, s4), delta(s4, 1, 1, left, s4), delta(s4, ' ', ' ', left, s5), delta(s5, 1, 1, left, s5), delta(s5, ' ', 1, right, start)])
Output = [' ', 1, ' ', 1]
FinalState = stop
Dump = [(start, tape([' '], [1])), (s2, tape([' ', ' '], [' '])), (s3, tape([' ', ' ', ' '], [' '])), (s4, tape([' ', ' '], [' ', 1])), (s5, tape([' '], [' ', ' ', 1])), (start, tape([' ', 1], [' ', 1])), (stop, tape([' ', 1, ' '], [1]))]
Yes (0.00s cpu, solution 1, maybe more) ? ,
Type ; for more solutions, otherwise <return> ? ;

No (0.00s cpu)
*/