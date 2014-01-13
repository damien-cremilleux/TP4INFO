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

open Parsing;;
# 3 "parser.mly"
  (* header *)
  open Anasynt
# 23 "parser.ml"
let yytransl_const = [|
  257 (* EOL *);
    0 (* EOF *);
  258 (* BEGIN *);
  259 (* END *);
  260 (* VIRG *);
  261 (* PTVIRG *);
  262 (* INT *);
  263 (* BOOL *);
  264 (* FLECHEG *);
  265 (* PLUS *);
  266 (* INF *);
  267 (* AND *);
  268 (* PARG *);
  269 (* PARD *);
    0|]

let yytransl_block = [|
  270 (* IDENT *);
    0|]

let yylhs = "\255\255\
\001\000\002\000\003\000\003\000\005\000\006\000\006\000\004\000\
\004\000\007\000\007\000\008\000\008\000\008\000\008\000\008\000\
\000\000"

let yylen = "\002\000\
\002\000\005\000\001\000\003\000\002\000\001\000\001\000\001\000\
\003\000\001\000\003\000\003\000\003\000\003\000\003\000\001\000\
\002\000"

let yydefred = "\000\000\
\000\000\000\000\000\000\017\000\000\000\006\000\007\000\000\000\
\000\000\000\000\001\000\000\000\000\000\005\000\000\000\010\000\
\000\000\000\000\004\000\000\000\002\000\000\000\000\000\016\000\
\000\000\009\000\000\000\000\000\000\000\000\000\015\000\000\000\
\000\000\000\000"

let yydgoto = "\002\000\
\004\000\016\000\008\000\017\000\009\000\010\000\018\000\025\000"

let yysindex = "\025\000\
\255\254\000\000\012\255\000\000\032\000\000\000\000\000\031\255\
\033\255\024\255\000\000\254\254\012\255\000\000\032\255\000\000\
\036\255\037\255\000\000\246\254\000\000\254\254\246\254\000\000\
\019\255\000\000\014\255\246\254\246\254\246\254\000\000\034\255\
\025\255\019\255"

let yyrindex = "\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\039\255\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\038\255\000\000\000\000\000\000\000\000\000\000\000\000\
\028\255\000\000\000\000\000\000\000\000\000\000\000\000\000\255\
\003\255\004\255"

let yygindex = "\000\000\
\000\000\044\000\033\000\026\000\000\000\000\000\000\000\248\255"

let yytablesize = 48
let yytable = "\003\000\
\003\000\023\000\012\000\024\000\012\000\013\000\014\000\013\000\
\014\000\012\000\012\000\015\000\012\000\013\000\027\000\013\000\
\014\000\006\000\007\000\032\000\033\000\034\000\028\000\029\000\
\030\000\001\000\031\000\028\000\029\000\030\000\011\000\011\000\
\011\000\028\000\029\000\012\000\013\000\014\000\021\000\020\000\
\008\000\022\000\028\000\003\000\005\000\019\000\000\000\026\000"

let yycheck = "\002\001\
\002\001\012\001\003\001\014\001\005\001\003\001\003\001\005\001\
\005\001\010\001\011\001\014\001\013\001\011\001\023\000\013\001\
\013\001\006\001\007\001\028\000\029\000\030\000\009\001\010\001\
\011\001\001\000\013\001\009\001\010\001\011\001\003\001\000\000\
\005\001\009\001\010\001\005\001\004\001\014\001\003\001\008\001\
\003\001\005\001\009\001\005\001\001\000\013\000\255\255\022\000"

let yynames_const = "\
  EOL\000\
  EOF\000\
  BEGIN\000\
  END\000\
  VIRG\000\
  PTVIRG\000\
  INT\000\
  BOOL\000\
  FLECHEG\000\
  PLUS\000\
  INF\000\
  AND\000\
  PARG\000\
  PARD\000\
  "

let yynames_block = "\
  IDENT\000\
  "

let yyact = [|
  (fun _ -> failwith "parser")
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : 'bloc) in
    Obj.repr(
# 31 "parser.mly"
         (_1)
# 127 "parser.ml"
               : Anasynt.arbre_bloc))
; (fun __caml_parser_env ->
    let _2 = (Parsing.peek_val __caml_parser_env 3 : 'sdecl) in
    let _4 = (Parsing.peek_val __caml_parser_env 1 : 'sinst) in
    Obj.repr(
# 35 "parser.mly"
                             ( Node_bloc (_2, _4) )
# 135 "parser.ml"
               : 'bloc))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : 'decl) in
    Obj.repr(
# 39 "parser.mly"
     ( [_1] )
# 142 "parser.ml"
               : 'sdecl))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 2 : 'decl) in
    let _3 = (Parsing.peek_val __caml_parser_env 0 : 'sdecl) in
    Obj.repr(
# 40 "parser.mly"
                  ( _1::_3 )
# 150 "parser.ml"
               : 'sdecl))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : 'typ) in
    let _2 = (Parsing.peek_val __caml_parser_env 0 : string) in
    Obj.repr(
# 44 "parser.mly"
          ( 
  match _1 with
    | "int" -> Node_decl_int _2
    | "bool" -> Node_decl_bool _2
    | _ -> failwith "error"
)
# 163 "parser.ml"
               : 'decl))
; (fun __caml_parser_env ->
    Obj.repr(
# 53 "parser.mly"
    ( "int" )
# 169 "parser.ml"
               : 'typ))
; (fun __caml_parser_env ->
    Obj.repr(
# 54 "parser.mly"
       ( "bool" )
# 175 "parser.ml"
               : 'typ))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : 'inst) in
    Obj.repr(
# 58 "parser.mly"
     ( [_1] )
# 182 "parser.ml"
               : 'sinst))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 2 : 'inst) in
    let _3 = (Parsing.peek_val __caml_parser_env 0 : 'sinst) in
    Obj.repr(
# 59 "parser.mly"
                    ( _1::_3 )
# 190 "parser.ml"
               : 'sinst))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : 'bloc) in
    Obj.repr(
# 63 "parser.mly"
     ( Node_inst_bloc _1 )
# 197 "parser.ml"
               : 'inst))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 2 : string) in
    let _3 = (Parsing.peek_val __caml_parser_env 0 : 'expr) in
    Obj.repr(
# 64 "parser.mly"
                     ( Node_inst (_1, _3) )
# 205 "parser.ml"
               : 'inst))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 2 : 'expr) in
    let _3 = (Parsing.peek_val __caml_parser_env 0 : 'expr) in
    Obj.repr(
# 68 "parser.mly"
               ( Node_plus (_1, _3) )
# 213 "parser.ml"
               : 'expr))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 2 : 'expr) in
    let _3 = (Parsing.peek_val __caml_parser_env 0 : 'expr) in
    Obj.repr(
# 69 "parser.mly"
                ( Node_inf (_1, _3) )
# 221 "parser.ml"
               : 'expr))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 2 : 'expr) in
    let _3 = (Parsing.peek_val __caml_parser_env 0 : 'expr) in
    Obj.repr(
# 70 "parser.mly"
                ( Node_and (_1, _3) )
# 229 "parser.ml"
               : 'expr))
; (fun __caml_parser_env ->
    let _2 = (Parsing.peek_val __caml_parser_env 1 : 'expr) in
    Obj.repr(
# 71 "parser.mly"
                 ( _2 )
# 236 "parser.ml"
               : 'expr))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : string) in
    Obj.repr(
# 72 "parser.mly"
        ( Leaf_ident _1 )
# 243 "parser.ml"
               : 'expr))
(* Entry main *)
; (fun __caml_parser_env -> raise (Parsing.YYexit (Parsing.peek_val __caml_parser_env 0)))
|]
let yytables =
  { Parsing.actions=yyact;
    Parsing.transl_const=yytransl_const;
    Parsing.transl_block=yytransl_block;
    Parsing.lhs=yylhs;
    Parsing.len=yylen;
    Parsing.defred=yydefred;
    Parsing.dgoto=yydgoto;
    Parsing.sindex=yysindex;
    Parsing.rindex=yyrindex;
    Parsing.gindex=yygindex;
    Parsing.tablesize=yytablesize;
    Parsing.table=yytable;
    Parsing.check=yycheck;
    Parsing.error_function=parse_error;
    Parsing.names_const=yynames_const;
    Parsing.names_block=yynames_block }
let main (lexfun : Lexing.lexbuf -> token) (lexbuf : Lexing.lexbuf) =
   (Parsing.yyparse yytables 1 lexfun lexbuf : Anasynt.arbre_bloc)
