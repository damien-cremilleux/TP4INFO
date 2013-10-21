(* Main program *)

open Ulex 
open String


(** scanner : lexbuf -> token list 
    scanner lexbuf constructs the list of token read from the argument lexbuf
    (ends when reading UL_EOF )
*)
let scanner lexbuf = 
  
  let rec scanner_rec n l = 
    try 
      match Lexer.token lexbuf with
      | UL_EOF as tk ->  (tk::l)
      |   tk   -> scanner_rec (n+1) (tk :: l) 
    with x -> 
      begin
	Printf.printf "Warning : exception %s was raised after reading %i tokens\n" 
	  (Printexc.to_string x) n;
	l
      end
  in
  List.rev (scanner_rec 0 [])


(* Old method, to test with stdin *)
(* let _ = *)
(*   (\** 1. Construct a lex buffer from the standard input channel *\) *)
(*   let lexbuf = Lexing.from_channel stdin in *)
    
(*   (\** 2. Construct the list of tokens *\) *)
(*   let tokens = scanner lexbuf in *)

(*   (\** 3. Print the tokens *\) *)
(*   List.iter (fun tk -> Printf.fprintf stdout "Token: %a\n" Ulex.print_token tk) tokens ; *)
(*   Printf.printf "DONE\n" *)



(* Our tests *)
let test =

  (* a simple test, about idents and logic relations *)
  [("toto et titi tutu ou tata",
    [UL_IDENT "toto";UL_AND; UL_IDENT "titi"; UL_IDENT "tutu";UL_OR ; UL_IDENT "tata"; UL_EOF]);

  (* an other simple test, about idents and logic relations, but who must fail due to the first ident *)
   ("toto et titi tutu ou tata",
    [UL_IDENT "tyty";UL_AND; UL_IDENT "titi"; UL_IDENT "tutu";UL_OR ; UL_IDENT "tata"; UL_EOF]);
   
   (* exemple given in the TP *)
   ("jojo et /* commentaires */ ) cheval = sinon (",
    [UL_IDENT "jojo"; UL_AND;UL_PARCLOSE; UL_IDENT "cheval"; UL_EQUAL; UL_IDENT "sinon"; UL_PAROPEN; UL_EOF]);

   (* test the operator*)
   (" = titi = <> jean < > et",
    [UL_EQUAL; UL_IDENT "titi"; UL_EQUAL;UL_DIFF; UL_IDENT "jean"; UL_INF; UL_SUP; UL_AND; UL_EOF]);

   (* first test of the comment *)
   ("toto /* this is a comment */ et /* another comment */ =",
    [UL_IDENT "toto"; UL_AND;UL_EQUAL;UL_EOF]);
   
   (* second test of the comment *)
   ("toto /* this is a comment /* more complicated */ et /* another / *** complicated /* comment */ = ",
    [UL_IDENT "toto"; UL_AND;UL_EQUAL;UL_EOF]);

   (* a more tricky one *)
   ("= /***** blabla * petit /*/ etoile/***/ toto ",
    [UL_EQUAL ; UL_IDENT "toto";UL_EOF]);

   (* the lats test *)
   ("(tests are ) ( terminated (()", 
    [UL_PAROPEN; UL_IDENT "tests"; UL_IDENT "are"; UL_PARCLOSE; UL_PAROPEN; UL_IDENT "terminated";UL_PAROPEN;UL_PAROPEN;UL_PARCLOSE;UL_EOF]);
  ]


(* Test if the result is the one expected*)
let analyse exp res =
  (** Construct the list of tokens *)
  let tokens = scanner exp
  in 
  if res=tokens 
  then Printf.printf "OK\n" 
  else Printf.printf "Fail\n"


(* Launch the test *)
let _ =
  List.iter (
    fun a-> let lexbuf = Lexing.from_string (fst a)
	    in analyse lexbuf (snd a)) test
    

