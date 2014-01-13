(* Analyseur lexical *)

{
  open Parser  (* The type token is defined in parser.mli *)

  let keyword_table = Hashtbl.create 53
  let _ =
    List.iter (fun (kwd, tok) -> Hashtbl.add keyword_table kwd tok)
      [ "begin", BEGIN;
        "end", END;
	"int", INT;
	"bool", BOOL;
	"and", AND]
}

let lettre = ['a'-'z''A'-'Z']
let chiffre = ['0'-'9']


  rule token = parse
    | [' ' '\t']     { token lexbuf }      (* skip blanks *)
    | ['\n' ]        { Lexing.new_line lexbuf; token lexbuf }
    | ',' {VIRG}
    | ';' {PTVIRG}
    | "<-" {FLECHEG}
    | '+' {PLUS}
    | '<' {INF}
    | '(' {PARG}
    | ')' {PARD}
    | lettre (lettre|chiffre)* as id { try
					 Hashtbl.find keyword_table id
				       with Not_found ->
					 IDENT id }
    | eof   { EOF }
