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



let _ =
  (** 1. Construct a lex buffer from the standard input channel *)
  let lexbuf = Lexing.from_channel stdin in
      
  (** 2. Construct the list of tokens *)
  let tokens = scanner lexbuf in

  (** 3. Print the tokens *)
  List.iter (fun tk -> Printf.fprintf stdout "Token: %a\n" Ulex.print_token tk) tokens ;
  Printf.printf "DONE\n"

(* (\** la liste de nos tests*\) *)
(* (\*Tous les tests renvoient pass!, c'est a dire fonctionnent :) *\) *)
(* let test = *)
(*   (\*test quelques tokens*\) *)
(*   [("toto et <",[UL_IDENT "toto";UL_AND; UL_INF; UL_EOF]); *)

(*    (\* test la phrase du TP*\) *)
(*    ("jojo et /* commentaires */ ) cheval = sinon (", *)
(*     [UL_IDENT "jojo"; UL_AND; 		     *)
(*      UL_PARCLOSE; UL_IDENT "cheval"; UL_EQUAL; UL_IDENT "sinon";						      *)
(*      UL_PAROPEN; UL_EOF]); *)

(*    (\*test le piège du commentaire avec étoiles et slashs*\) *)
(*    ("/***** blabla * petit / etoile ***/", [UL_EOF]); *)

(*    (\*Test une partie de tous les autres tokens*\) *)
(*    ("deux < trois = true but (trois > deux et trois<>dix) = false",  *)
(*     [UL_IDENT "deux"; UL_INF; UL_IDENT "trois"; UL_EQUAL; UL_IDENT "true"; *)
(*      UL_IDENT "but"; UL_PAROPEN; UL_IDENT "trois"; UL_SUP; UL_IDENT "deux"; *)
(*      UL_AND; UL_IDENT "trois"; UL_DIFF; UL_IDENT "dix"; UL_PARCLOSE; *)
(*      UL_EQUAL; UL_IDENT "false"; UL_EOF]); *)

(*    (\*test le piège du commentaire avec étoiles et slashs*\) *)
(*    ("jojo /***** blabla * petit / etoile/***/ toto", [UL_IDENT "jojo" ; UL_IDENT "toto";UL_EOF]) *)
(*   ] *)




(* (\**fonction réalisant l'analyse d'une chaine de caractère et testant son égalité avec le resultat attendu*\) *)
(* let analyse exp res = *)
(*   (\** Construct the list of tokens *\) *)
(*   let tokens = scanner exp *)
(*   in  *)
(*     if res=tokens  *)
(*     then Printf.printf "Pass!\n"  *)
(*     else Printf.printf "Test Fail\n" *)


(* (\** Fonction lancer au début, prend en paramètre notre liste de test, et renvoie une réponse *)
(* dans le terminal selon le résultat*\) *)
 
(* let _ =  *)
(*   List.iter ( *)
(*     fun a-> let lexbuf = Lexing.from_string (fst a)  *)
(*     in analyse lexbuf (snd a)) test *)
 
