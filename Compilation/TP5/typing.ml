open Print_ast
open Ast

let is_list_typ = function
 | TList _ -> true
 | _       -> false

let rec typ_of_pattern = 
 function
 | Ml_pattern_var(s,typ) -> TypEnv.singleton s typ , typ
 | Ml_pattern_bool b -> TypEnv.empty , Tbool
 | Ml_pattern_int i  -> TypEnv.empty , Tint
 | Ml_pattern_pair(p1,p2) -> 
  let e1,t1 = typ_of_pattern p1 in
  let e2,t2 = typ_of_pattern p2 in
  TypEnv.add_all e1 e2 , TPair(t1,t2)
 | Ml_pattern_nil ty -> if is_list_typ ty then TypEnv.empty , ty else failwith "Type error"
 | Ml_pattern_cons(x,l) -> 
  let e1,t1 = typ_of_pattern x in
  let e2,t2 = typ_of_pattern l in
  if t2 = TList t1
  then TypEnv.add_all e1 e2, t2
  else failwith (Printf.sprintf "Incompatible types %s <> %s" (string_of_typ t2) (string_of_typ (TList t1)))

let wt_unop op t = 
 match op ,t  with
 | Ml_fst , TPair(x,y) -> x
 | Ml_snd , TPair(x,y) -> y
 | op , _  -> failwith (Printf.sprintf "Type error: %s does not expect type %s"  (string_of_unop op) (string_of_typ t))


let wt_binop op t1 t2 = 
 match op ,t1 , t2  with
 | (Ml_add | Ml_sub | Ml_mult)  , Tint , Tint ->  Tint
 | Ml_less , Tint , Tint ->  Tbool
 | Ml_eq , x, y -> 
  if x = y then  Tbool 
  else  failwith (Printf.sprintf "Type error: = cannot be used with types %s and %s" (string_of_typ x) (string_of_typ y))
 | _  -> failwith (Printf.sprintf "Type error: %s cannot be used with types %s and %s" (string_of_binop op) (string_of_typ t1) (string_of_typ t2))

let same_types l = 
 match l with
 | [] -> failwith "Should have at least one type"
 | e:: l -> 
  if  List.for_all (fun ty -> ty = e) l
  then  e
  else 
   let t' = List.find (fun ty -> ty <> e) l in
   failwith (Printf.sprintf "Type error: patterns with different types %s and %s" (string_of_typ e) (string_of_typ t'))

let rec wt_expr (env:TypEnv.t) = function
 | Ml_int i -> Tint
 | Ml_bool b ->Tbool
 | Ml_nil ty   -> if is_list_typ ty then ty else failwith (Printf.sprintf "Type error: %s should be a list type" (string_of_typ ty))
 | Ml_pair(e1,e2) -> 
  let t1 = wt_expr env e1 in 
  let t2 = wt_expr env e2 in
  TPair(t1,t2)
 | Ml_cons(e1,le1) -> 
  let t1 = wt_expr env e1 in
  let t2 = wt_expr env le1 in
  if t2 = TList t1
  then t2
  else  failwith (Printf.sprintf "Type error: :: cannot be used with types %s and %s" (string_of_typ t1) (string_of_typ t2))
 | Ml_unop(op,e) -> wt_unop op (wt_expr env e)
 | Ml_binop(op,e1,e2) -> wt_binop op (wt_expr env e1) (wt_expr env e2)
 | Ml_var x -> TypEnv.find x env
 | Ml_if(e1,e2,e3) -> 
  let t1 = wt_expr env e1 in 
  let t2 = wt_expr env e2 in 
  let t3 = wt_expr env e3 in
  if t1 = Tbool 
  then if t2 = t3
   then t2
   else failwith (Printf.sprintf "Type error: conditional return different types %s and %s" (string_of_typ t2) (string_of_typ t2))
  else failwith (Printf.sprintf "Type error: conditional should be boolean and not type %s" (string_of_typ t1))
 | Ml_fun l -> 
  let ltyp = List.map (fun (pat,e) ->
   let (env',targs) = typ_of_pattern pat in  
   TFun(targs, wt_expr (TypEnv.update_all env' env) e)) l in
  same_types ltyp
 | Ml_app(e1,e2) -> 
  let t1 = wt_expr env e1 in
  let t2 = wt_expr env e2 in
  begin
   match t1 with
   | TFun(a,r) -> 
    if t2 = a 
    then r 
    else failwith (Printf.sprintf "Type error: cannot apply function of type %s with argument of type %s" (string_of_typ t1) (string_of_typ t2))
   |   _       -> failwith (Printf.sprintf "Type error: term has type %s and cannot be applied with argument of type %s" (string_of_typ t1) (string_of_typ t2))
  end
 | Ml_let (x,e1,e2) -> 
  let t1 = wt_expr env e1 in
  wt_expr (TypEnv.update x t1 env) e2
 | Ml_letrec(x,typ,e1,e2) -> 
  let env' = TypEnv.update x typ env in
  let ty' = wt_expr env' e1 in
  if ty' = typ then   wt_expr env' e2
  else failwith (Printf.sprintf "Type error: let rec with incompatible types %s and %s" (string_of_typ typ) (string_of_typ ty'))


let wt_expr env e =
 try
  wt_expr env e 
 with 
 | Failure s -> raise (Failure s)
 | TypEnv.TypeMismatch(s,t,t') -> failwith (Printf.sprintf "Type error: variable %s has incompatible types %s and %s" s (string_of_typ t) (string_of_typ t'))

let wt_ast tenv ast = 
  match ast with
  | Ml_expr e -> wt_expr (!tenv) e
  | Ml_definition(s,e) -> 
   let ty' = wt_expr !tenv e in
   tenv := TypEnv.update s ty'  !tenv ;
   ty'
  | Ml_definitionrec (s,ty',e) -> 
   let ty = wt_expr (TypEnv.update s ty' !tenv) e in
   if ty = ty'
   then
    begin
     tenv := TypEnv.update s ty !tenv ;
     ty'
    end
   else failwith (Printf.sprintf "Type error: let rec with incompatible types %s and %s" (string_of_typ ty) (string_of_typ ty'))

 


