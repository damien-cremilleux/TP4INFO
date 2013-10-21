(* Description of the lexical analyzer *)

{
  open Ulex (* Ulex contains the type definition of lexical units *)
}

let letter = ['a'-'z''A'-'Z']
let number = ['0'-'9']
let beginComment = "/*"
let endComment = '*'+'/'
let noSlashOrStar = [^'*''/'] 		(* a character which is neither '*' or '/' *)
let listStar = '*'+[^'/']		(* a list of '*' which is not followed by a '/' *)
let listSlash = '/'+[^'*']       	(* a list of '/' which is not followed by a '*' *)


  
  rule token = parse
    | [' ' '\t']        { token lexbuf }     (* skip blanks *)
    | ['\n' ]           { token lexbuf }	    (* skip end of line *)
    | beginComment (beginComment | noSlashOrStar | listStar | listSlash)* endComment  { token lexbuf } (* skip comments *)
    | eof               { UL_EOF }
    | "ou"              { UL_OR }
    | "et"              { UL_AND }
    | '='               { UL_EQUAL }
    | "<>"              { UL_DIFF }
    | '<'               { UL_INF }
    | '>'               { UL_SUP }
    | '('               { UL_PAROPEN }
    | ')'               { UL_PARCLOSE }
    | letter (letter | number)* as lxm { UL_IDENT(lxm) }

