(* Description of the syntaxique analyser *)

(* Expr -> Termb SuiteExpr *)
(* SuiteExpr -> "ou" Expr | € *)

(* Termb -> Facteurb SuiteTermb *)
(* SuiteTermb -> "et" Termb | € *)


(* --------------------------------------------------------------------- *)

(* premier("ou" Expr) inter premier(€) = ø *)

(* suivant(SuiteExpr) = premier(€) U suivant(Epxr) *)
(* suivant(Expr) = Termb Suivant(SuiteExpr) *)

(* suivant(SuiteExpr) = ø *)
(* suivant(Expr) = ø  *)

(* null("ou" Expr) = Faux et null(€) = Vrai  *)

(* pareil pour la seconde règle. *)

#load "ulex.cmo
open Ulex;;
open List;;

type vt = 
  | US_OR
  | US_AND
  | US_SI
  | US_ALORS
  | US_SINON
  | US_FSI
  | US_EQUAL
  | US_DIFF
  | US_INF
  | US_SUP
  | US_SUPEG
  | US_INFEG
  | US_PAROPEN 
  | US_PARCLOSE 
  | US_IDENT of string

type vn = 
  | US_EXPR
  | US_TERMB
  | US_SUITEEXPR
  | US_SUITETERMB
  | US_RELATION
  | US_FACTEURB
  | US_OP

type v =
  | Vt of vt
  | Vn of vn


type arbre_concret =
  | Leaf of vt
  | Node of vn * arbre_concret list;;

(* dérivation *)
let derivation (vn, ul) = match vn, ul with
  | US_EXPR, UL_IDENT ident -> [Vn US_TERMB; Vn US_SUITEEXPR]
  | US_EXPR, UL_PAROPEN -> [Vn US_TERMB; Vn US_SUITEEXPR]
  | US_EXPR, UL_SI -> [Vn US_TERMB; Vn US_SUITEEXPR]
  | US_SUITEEXPR, UL_OR -> [Vt US_OR; Vn US_EXPR]
  | US_SUITEEXPR, _ -> []
  | US_TERMB, UL_IDENT ident -> [Vn US_FACTEURB; Vn US_SUITEEXPR]
  | US_TERMB, UL_PAROPEN -> [Vn US_FACTEURB; Vn US_SUITEEXPR]
  | US_TERMB, UL_SI -> [Vn US_FACTEURB; Vn US_SUITEEXPR]
  | US_SUITETERMB, UL_AND -> [Vt US_AND; Vn US_TERMB]
  | US_SUITETERMB, _ -> []
  | US_FACTEURB, UL_IDENT ident -> [Vn US_RELATION]
  | US_FACTEURB, UL_PAROPEN -> [Vt US_PAROPEN; Vn US_EXPR; Vt US_PARCLOSE]
  | US_FACTEURB, UL_SI -> [Vt US_SI; Vn US_EXPR; Vt US_ALORS; Vn US_EXPR; Vt US_SINON; Vn US_EXPR; Vt US_FSI]
  | US_RELATION, UL_IDENT ident -> [Vt (US_IDENT ""); Vn US_OP; Vt (US_IDENT "")] (* on initialyse US_IDENT à vide car la valaeur sera attribuée dans analyse_caractere *)
  | US_OP, UL_EQUAL -> [Vt US_EQUAL]
  | US_OP, UL_DIFF -> [Vt US_DIFF]
  | US_OP, UL_INF -> [Vt US_INF]
  | US_OP, UL_SUP -> [Vt US_SUP]
  | US_OP, UL_SUPEG -> [Vt US_SUPEG]
  | US_OP, UL_INFEG -> [Vt US_INFEG]
  | x,y -> failwith "Bad Grammar";;

(*  *)

let analyse_vt (vt, ul) =
  match vt, ul with
  | US_OR, UL_OR -> Leaf US_OR
  | US_AND, UL_AND -> Leaf US_AND
  | US_SI, UL_SI -> Leaf US_SI
  | US_ALORS, UL_ALORS -> Leaf US_ALORS
  | US_SINON, UL_SINON -> Leaf US_SINON
  | US_FSI, UL_FSI -> Leaf US_FSI
  | US_EQUAL, UL_EQUAL -> Leaf US_EQUAL
  | US_DIFF, UL_DIFF -> Leaf US_DIFF
  | US_INF, UL_INF -> Leaf US_INF
  | US_SUP, UL_SUP -> Leaf US_SUP
  | US_SUPEG, UL_SUPEG -> Leaf US_SUPEG
  | US_INFEG, UL_INFEG -> Leaf US_INFEG
  | US_PAROPEN, UL_PAROPEN -> Leaf US_PAROPEN
  | US_PARCLOSE, UL_PARCLOSE -> Leaf US_PARCLOSE
  | US_IDENT _, UL_IDENT ident -> Leaf (US_IDENT ident)
  | x,y -> failwith "Bad Grammar";;

let rec analyse_caractere (v, liste_ul1) =
  match v, liste_ul1 with
  | Vt vt, ul::reste -> analyse_vt (vt, ul), reste
  | Vn vn,  ul::reste -> let liste_arbre, liste_ul2 = analyse_mot (derivation (vn, ul), ul::reste) in Node (vn, liste_arbre), liste_ul2
  | _,_ -> failwith "Probleme analyse caractere"
and
    analyse_mot (liste_v, liste_ul1) =
  match liste_v, liste_ul1 with
  | [], [] -> [],[]
  | _, [] -> failwith "Erreur, expression terminee alors qu'un token est attendu"
  | [],l -> [], l 
  | v::reste1, liste_ul1 -> let arbre, liste_ul2 = analyse_caractere (v, liste_ul1) in
			    let suite_arbre, suite_liste_ul = analyse_mot (reste1, liste_ul2) in
			    arbre::suite_arbre, suite_liste_ul



let x =
analyse_caractere(Vn US_RELATION,[UL_IDENT "z"; UL_SUP; UL_IDENT "e"]);;

(* let y = *)
(* analyse_mot([Vn US_EXPR],[UL_IDENT "y" ;UL_SUP;UL_IDENT "t"]);; *)
