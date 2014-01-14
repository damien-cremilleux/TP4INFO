(* Arbre syntaxique abstrait *)


type decl =
  | Node_decl_int of string
  | Node_decl_bool of string

type expr =
  | Node_plus of expr * expr
  | Node_inf of expr * expr
  | Node_and of expr * expr
  | Leaf_ident of string

type arbre_bloc =
  | Node_bloc of decl list * inst list
and 
inst =
  | Node_inst of string * expr
  | Node_inst_bloc of arbre_bloc

