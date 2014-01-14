(* #use "parser.ml";; *)
(* #use "lexer.ml";; *)
#load "parser.cmo"
#load "lexer.cmo"

open Parser
open Lexer
open Lexing
open Parsing

let _ =
(* si le fichier "test" contient un code à analyser *)
 (* bloc  token (Lexing.from_channel (open_in "test"));; *)

(* pour de tous petits tests *)
 let lex = from_string 
"begin
int a,
int c;
a <- c;
c <- a + c;
begin
bool b;
b <- b and b
end
end"

in
 try main token lex with
   | Parse_error -> failwith (let err = lexeme_start_p lex in
					"Erreur dans le fichier "^err.pos_fname
					^", a la ligne "^string_of_int(err.pos_lnum)
					^", caractere "^string_of_int(err.pos_cnum - err.pos_bol));;
(* (\* pour des tests un peu moins petits *\) *)
(*  let lex = from_channel stdin in *)
(*  try bloc token lex with *)
(*    | Parse_error -> failwith (let err = lexeme_start_p lex in *)
(* 					"Erreur dans le fichier "^err.pos_fname *)
(* 					^", a la ligne "^string_of_int(err.pos_lnum) *)
(* 					^", caractere "^string_of_int(err.pos_cnum - err.pos_bol));; *)

(* (\* La suite est utile pour tester l'analyseur lexical tout seul. *\) *)
(* (\* Il faut juste déclarer le type token. Normalement il est déclaré au début du *\) *)
(* (\* fichier parser.ml et provient des %token qui se trouvent dans l'en-tête de *\) *)
(* (\* parser.mly. *\) *)
