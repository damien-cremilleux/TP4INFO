#load "lexer.cmo";;
#load "ulex.cmo";;
#load "anasynt.cmo";;
open Ulex;;
open String;;

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

let test string = creer_arbre_abstrait(fst (analyse_caractere(Vn US_EXPR,scanner (Lexing.from_string string))));;

(* tests positifs *)

let test_sialorssinon1 = test "si a < b alors z = b2 sinon z = a fsi";;

let test_sialorssinon2 = test "si (a <> b ou b = c) alors si c >= d alors e = f sinon t > r fsi sinon t = z et x <> f fsi";;

let test_par0  = test "a < b ou b > c et d = c ou d <= e";;

let test_par1 = test "(a < b ou b > c) et (d = c ou d <= e)";;

let test_par2 = test "(a < b ou (b > c et d = c)) ou d <= e";;


(* test négatifs *)

let test_ident = test "a";;

let test_sialorssinon3 = test "si a < b alors z = b2 sinon z = a";;

let test_sialorssinon4 = test "si a < b alors z = b2 fsi";;

let test_par3 = test "()";;

let test_par4 = test "(a < b ou (b > c et d = c) ou d <= e";;

let test_ou1 = test "(a < b ou c) et (d = c ou d <= e)";;

let test_op1 = test "a < (b < c)";;

