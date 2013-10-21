(* TP Emacs et OCaml *)
(* Damien Crémilleux & Tom Demulier--Chevret  *)

type stream = {cont:string; mutable pos:int ; mutable mark:int }

exception End_of_Flux

exception ParseError of (string * int * int)

let read s =
  if (s.pos < String.length s.cont) then
    let c = String.get s.cont s.pos in
      s.pos <- s.pos + 1;
      c
  else
    begin
      s.pos <- s.pos + 1;
      raise End_of_Flux
    end

let backward s =
  if (s.pos > 0) then
    s.pos <- s.pos - 1
  else
    ()

let reset_mark s = s.mark <- s.pos


let stream_of_string str = {cont=str; pos=0; mark=0}


type op = Add | Mul

type char = SPC | DGT | NEG | SYM | BRA | EOF | ANY

type state = Init | Neg | Numb | Symb | Ko | Eof

type data = 
  | Op of op
  | Int of int
  | LB | RB | End

type tree = Node of data * tree list

exception LexError of (string * int * int)


let rec lex_input input state acc =
  let c, chr = try 
    let c = read input in 
      c, match c with
	| ' ' | '\t' | '\n' | '\r' -> SPC
	| '0'..'9' -> DGT
	| '+' | '*' -> SYM
	| '-' -> NEG
	| '(' | ')' -> BRA
	| _ -> ANY
  with End_of_Flux -> ' ', EOF
  in
    match state with
      | Init ->
	  begin
	    match chr with
	      | SPC -> lex_input input Init acc
	      | NEG -> lex_input input Neg "-"
	      | DGT -> lex_input input Numb (Char.escaped c)
	      | SYM -> lex_input input Symb (Char.escaped c)
	      | BRA -> Symb, (Char.escaped c)
	      | EOF -> Eof, ""
	      | ANY -> Ko, (Char.escaped c)
	  end
      | Neg ->
	  begin
	    match chr with
	      | DGT -> lex_input input Numb (acc^(Char.escaped c))
	      | _ -> Ko, acc^(Char.escaped c)
	  end
      | Numb ->
	  begin
	    match chr with
	      | DGT ->
	    	  lex_input input Numb (acc^(Char.escaped c))
	      | _ ->
	    	  backward input;
	    	  Numb, acc
	  end
      | Symb ->
	  begin
	    match chr with
	      | SYM ->
	    	  lex_input input Symb (acc^(Char.escaped c))
	      | _ ->
	    	  backward input;
	    	  Symb, acc
	  end
      | Ko ->
	  failwith "Invalid expression: unexpected character found"
      | _ ->
	  invalid_arg "lex_input"

let get_token input =
  let symbol = function
    | "(" -> LB
    | ")" -> RB
    | "+" -> Op Add
    | "*" -> Op Mul
    | _ as str -> raise (LexError (str, input.mark+1, input.pos+1))
  and integer n = Int (int_of_string n) in
    reset_mark input;
    match lex_input input Init "" with
      | Symb, str -> symbol str
      | Numb, str -> integer str
      | Eof, _ -> End
      | Ko, str -> raise (LexError (str, input.mark+1, input.pos+1))
      | _ -> assert false

let get_tokens input =
  let rec get_tokens_rec input acc =
    match (get_token input) with
      | End -> End::acc
      | tk -> get_tokens_rec input (tk::acc)
  in
    List.rev (get_tokens_rec input [])

let _ = get_tokens (stream_of_string "35+40*78+(17+79)")

  (* Gammaire : E => E + E | E * E | (E) | Int *)

let parse lexer input =
  let rec parse lexer input st tk lst =
    let s = List.hd st in
    let raise_ParseError () =
      raise (ParseError (
	       String.sub input.cont input.mark (input.pos - input.mark),
	       input.mark+1, input.pos+1))
    and reduce r =
      match r, lst, st with
	| 1, re::_::le::l, _::_::_::st ->
	    parse lexer input st tk (Node (Op Add, [le; re])::l) (* E + E *)
	| 2, re::_::le::l, _::_::_::st ->
	    parse lexer input st tk (Node (Op Mul, [le; re])::l) (* E * E *)
	| 3, _::e::_::l, _::_::_::st ->
	    parse lexer input st tk (e::l) (* (E) *)
	| 4, l, _::st ->
	    parse lexer input st tk l (* Int *)
	| _  -> assert false
    and shift s' =
      parse lexer input (s'::st) (lexer input) (Node (tk, [])::lst)
    and goto_E s' tk =
      parse lexer input (s'::st) tk lst
    in
      match s with
	| 0 ->
	    begin
	      match tk with
		| LB -> shift 2
		| Int _ -> shift 1
		| _ ->
		    if lst <> [] then goto_E 4 tk else raise_ParseError ()
	    end
	| 1 ->
	    begin
	      match tk with
		| Op _ | RB | End -> reduce 4
		| _ -> raise_ParseError ()
	    end
	| 2 ->
	    begin
	      match tk with
		| LB -> shift 2
		| Int _ -> shift 1
		| _ ->
		    if lst <> [] then goto_E 3 tk else raise_ParseError ()
	    end
	| 3 ->
	    begin
	      match tk with
		| Op Add -> shift 5
		| Op Mul -> shift 6
		| RB -> shift 9
		| _ -> raise_ParseError ()
	    end
	| 4 ->
	    begin
	      match tk with
		| Op Add -> shift 5
		| Op Mul -> shift 6
		| End -> List.hd lst (* Accept *)
		| _ -> raise_ParseError ()
	    end
	| 5 ->
	    begin
	      match tk with
		| LB -> shift 2
		| Int _ -> shift 1
		| _ ->
		    if lst <> []
		    then goto_E 7 tk
		    else raise_ParseError ()
	    end
	| 6 ->
	    begin
	      match tk with
		| LB -> shift 2
		| Int _ -> shift 1
		| _ ->
		    if lst <> []
		    then goto_E 8 tk
		    else raise_ParseError ()
	    end
	| 7 ->
	    begin
	      match tk with
		| Op Add -> reduce 1
		| Op Mul -> shift 6
		| RB -> reduce 1
		| End -> reduce 1
		| _ -> raise_ParseError ()
	    end
	| 8 ->
	    begin
	      match tk with
		| Op _ | RB | End -> reduce 2
		| _ -> raise_ParseError ()
	    end
	| 9 ->
	    begin
	      match tk with
		| Op _ | RB | End -> reduce 3
		| _ -> raise_ParseError ()
	    end
	| _ -> failwith "Bug in parser: invalid state"
  in
  let first = lexer input
  in
    parse lexer input [0] first []
	



