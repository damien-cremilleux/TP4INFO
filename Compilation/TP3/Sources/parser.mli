type token =
  | EOL
  | EOF
  | BEGIN
  | END
  | VIRG
  | PTVIRG
  | INT
  | BOOL
  | FLECHEG
  | PLUS
  | INF
  | AND
  | PARG
  | PARD
  | IDENT of (string)

val main :
  (Lexing.lexbuf  -> token) -> Lexing.lexbuf -> Anasynt.arbre_bloc
