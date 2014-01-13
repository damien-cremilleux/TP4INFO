/* File parser.mly */
%{
  (* header *)
  open Anasynt
%}

/* declarations */
%token EOL
%token EOF
%token BEGIN END
%token VIRG PTVIRG
%token INT BOOL
%token FLECHEG
%token PLUS
%token INF
%token AND
%token PARG PARD
%token <string> IDENT

 /* Predominent order : AND > INF > PLUS */
%right AND
%right INF
%right PLUS

%start main             /* the entry point */
%type <Anasynt.arbre_bloc> main
%%

 /* rules*/

main:
bloc EOF {$1}
;

bloc:
BEGIN sdecl PTVIRG sinst END { Node_bloc ($2, $4) }
;

sdecl:
decl { [$1] }
| decl VIRG sdecl { $1::$3 }
;

decl:
typ IDENT { 
  match $1 with
    | "int" -> Node_decl_int $2
    | "bool" -> Node_decl_bool $2
    | _ -> failwith "error"
}
;

typ:
INT { "int" }
| BOOL { "bool" }
;

sinst:
inst { [$1] }
| inst PTVIRG sinst { $1::$3 }
;

inst:
bloc { Node_inst_bloc $1 }
| IDENT FLECHEG expr { Node_inst ($1, $3) }
;

expr:
expr PLUS expr { Node_plus ($1, $3) }
| expr INF expr { Node_inf ($1, $3) }
| expr AND expr { Node_and ($1, $3) }
| PARG expr PARD { $2 }
| IDENT { Leaf_ident $1 }
;
