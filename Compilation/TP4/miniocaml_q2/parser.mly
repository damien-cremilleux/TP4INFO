%{
  open Ast
%}

%token <int> INT
%token <string> IDENT
%token TRUE FALSE
%token LET REC IN
%token FUNCTION ARROW ALTERNATIVE 
%token IF THEN ELSE
%token ADD SUB MULT LESS
%token EQUAL
%token LEFT_PAREN RIGHT_PAREN
%token LEFT_BRACKET RIGHT_BRACKET CONS
%token COMMA
%token END_OF_EXPRESSION
%token EOF

%nonassoc NO_ALTERNATIVE
%nonassoc ALTERNATIVE IN
%left LESS EQUAL
%right CONS
%left ADD SUB
%left MULT
%nonassoc ELSE

%start main
%type <Ast.ml_ast> main
%%

main:
 | EOF { Printf.printf "\nbye"; exit 0 }
 | LET IDENT EQUAL expr END_OF_EXPRESSION { Ml_definition($2, $4) }
 | LET REC IDENT EQUAL expr END_OF_EXPRESSION { Ml_definitionrec($3, $5) }
 | expr END_OF_EXPRESSION { Ml_expr $1 }
 | error {
    let bol = (Parsing.symbol_start_pos ()).Lexing.pos_bol in
    failwith ("parsing: line " ^ 
		 (string_of_int ((Parsing.symbol_start_pos ()).Lexing.pos_lnum)) ^ 
		 " between character " ^
		 (string_of_int (Parsing.symbol_start () - bol)) ^
		 " and " ^
		 (string_of_int ((Parsing.symbol_end ()) + 1 - bol)))
 }

expr:
 | simple_expr { $1 }
 | LEFT_PAREN expr RIGHT_PAREN { $2 }
 | expr CONS expr { Ml_cons($1, $3) }
 | LEFT_PAREN expr COMMA expr RIGHT_PAREN { Ml_pair($2, $4) }
 | expr ADD expr { Ml_binop(Ml_add, $1, $3) }
 | expr MULT expr { Ml_binop(Ml_mult, $1, $3) }
 | expr SUB expr { Ml_binop(Ml_sub, $1, $3) }
 | expr LESS expr { Ml_binop(Ml_less, $1, $3) }
 | expr EQUAL expr { Ml_binop(Ml_eq, $1, $3) }
 | IF expr THEN expr ELSE expr { Ml_if($2, $4, $6) }
 | FUNCTION pattern_expr_list { Ml_fun $2 }
 | application { List.fold_left (fun res a -> Ml_app(res, a)) (List.hd $1) (List.tl $1) }
 | LET IDENT EQUAL expr IN expr { Ml_let($2, $4, $6) }
 | LET REC IDENT EQUAL expr IN expr { Ml_letrec($3, $5, $7) }

simple_expr:
 | INT { Ml_int $1 }
 | bool { Ml_bool $1 }
 | LEFT_BRACKET RIGHT_BRACKET { Ml_nil }
 | IDENT { Ml_var $1 }
 | LEFT_PAREN ADD RIGHT_PAREN { Ml_fun([Ml_pattern_var "x", Ml_fun([Ml_pattern_var "y", Ml_binop(Ml_add, Ml_var "x", Ml_var "y")])]) }
 | LEFT_PAREN MULT RIGHT_PAREN { Ml_fun([Ml_pattern_var "x", Ml_fun([Ml_pattern_var "y", Ml_binop(Ml_mult, Ml_var "x", Ml_var "y")])]) }
 | LEFT_PAREN SUB RIGHT_PAREN { Ml_fun([Ml_pattern_var "x", Ml_fun([Ml_pattern_var "y", Ml_binop(Ml_sub, Ml_var "x", Ml_var "y")])]) }
 | LEFT_PAREN LESS RIGHT_PAREN { Ml_fun([Ml_pattern_var "x", Ml_fun([Ml_pattern_var "y", Ml_binop(Ml_less, Ml_var "x", Ml_var "y")])]) }
 | LEFT_PAREN EQUAL RIGHT_PAREN { Ml_fun([Ml_pattern_var "x", Ml_fun([Ml_pattern_var "y", Ml_binop(Ml_eq, Ml_var "x", Ml_var "y")])]) }

bool:
 | FALSE { false }
 | TRUE  { true }

pattern:
 | IDENT { Ml_pattern_var $1 } 
 | INT   { Ml_pattern_int $1 }
 | bool  { Ml_pattern_bool $1 }
 | LEFT_PAREN pattern COMMA pattern RIGHT_PAREN { Ml_pattern_pair($2, $4) }
 | LEFT_BRACKET RIGHT_BRACKET { Ml_pattern_nil }
 | pattern CONS pattern { Ml_pattern_cons($1, $3) }

pattern_expr_list:
 | pattern ARROW expr pattern_expr_list_next { ($1, $3) :: $4 }

pattern_expr_list_next:
 | ALTERNATIVE pattern ARROW expr pattern_expr_list_next { ($2, $4) :: $5 }
 | %prec NO_ALTERNATIVE { [] }

application:
 | simple_expr_or_parenthesized_expr simple_expr_or_parenthesized_expr application_next { $1 :: $2 :: $3 }

simple_expr_or_parenthesized_expr:
 | simple_expr { $1 }
 | LEFT_PAREN expr COMMA expr RIGHT_PAREN { Ml_pair($2, $4) }
 | LEFT_PAREN expr RIGHT_PAREN { $2 }

application_next:
 | simple_expr_or_parenthesized_expr application_next { $1 :: $2 }
 | { [] }
