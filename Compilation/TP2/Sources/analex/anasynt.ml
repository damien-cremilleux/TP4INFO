(* Description of the syntaxique analyser *)

#load "ulex.cmo"
open Ulex;;
open List;;

(* Unité syntaxique terminale *)
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

(* Unité syntaxique non terminale *)
type vn = 
  | US_EXPR
  | US_TERMB
  | US_SUITEEXPR
  | US_SUITETERMB
  | US_RELATION
  | US_FACTEURB
  | US_OP

(* Unité syntaxique : union des terminaux et non terminaux *)
type v =
  | Vt of vt
  | Vn of vn

(* Définition d'un arbre concret *)
type arbre_concret =
  | Leaf of vt
  | Node of vn * arbre_concret list;;

(* Feuille de l'arbre abstrait *)
type leaf_IDENT =
  | Ident of string;;

(* Définition de l'arbre abstrait *)
type arbre_abstrait =
  | Node_EQUAL of leaf_IDENT * leaf_IDENT
  | Node_DIFF of leaf_IDENT * leaf_IDENT
  | Node_INF of leaf_IDENT * leaf_IDENT
  | Node_SUP of leaf_IDENT * leaf_IDENT
  | Node_SUPEG of leaf_IDENT * leaf_IDENT
  | Node_INFEG of leaf_IDENT * leaf_IDENT
  | Node_OR of  arbre_abstrait * arbre_abstrait
  | Node_AND of  arbre_abstrait * arbre_abstrait
  | Node_SIALORSSINON of arbre_abstrait * arbre_abstrait * arbre_abstrait

(* dérivation *)
let derivation (vn, ul) = match vn, ul with
  | US_EXPR, UL_IDENT ident -> [Vn US_TERMB; Vn US_SUITEEXPR]
  | US_EXPR, UL_PAROPEN -> [Vn US_TERMB; Vn US_SUITEEXPR]
  | US_EXPR, UL_SI -> [Vn US_TERMB; Vn US_SUITEEXPR]
  | US_EXPR, _ -> failwith "Erreur a US_EXPR : UL_IDENT, UL_PAROPEN ou UL_SI attendu." (* On cherche à détecter les erreurs au plus tôt *)

  | US_SUITEEXPR, UL_OR -> [Vt US_OR; Vn US_EXPR]
  | US_SUITEEXPR, UL_EOF -> []
  | US_SUITEEXPR, _ -> (* failwith "Erreur a US_SUITEEXPR : UL_OR ou UL_EOF attendu." *)[]

  | US_TERMB, UL_IDENT ident -> [Vn US_FACTEURB; Vn US_SUITETERMB]
  | US_TERMB, UL_PAROPEN -> [Vn US_FACTEURB; Vn US_SUITETERMB]
  | US_TERMB, UL_SI -> [Vn US_FACTEURB; Vn US_SUITETERMB]
  | US_TERMB, _ -> failwith "Erreur a US_TERMB : UL_IDENT, UL_PAROPEN ou UL_SI attendu."

  | US_SUITETERMB, UL_AND -> [Vt US_AND; Vn US_TERMB]
  | US_SUITETERMB, UL_EOF -> []
  | US_SUITETERMB, _ -> (* failwith "Erreur a US_SUITETERMB : UL_AND ou UL_EOF attendu." *) []

  | US_FACTEURB, UL_IDENT ident -> [Vn US_RELATION]
  | US_FACTEURB, UL_PAROPEN -> [Vt US_PAROPEN; Vn US_EXPR; Vt US_PARCLOSE]
  | US_FACTEURB, UL_SI -> [Vt US_SI; Vn US_EXPR; Vt US_ALORS; Vn US_EXPR; Vt US_SINON; Vn US_EXPR; Vt US_FSI]
  | US_FACTEURB, _ -> failwith "Erreur a US_FACTEURB : UL_IDENT, UL_PAROPEN ou UL_SI attendu."

  | US_RELATION, UL_IDENT ident -> [Vt (US_IDENT ""); Vn US_OP; Vt (US_IDENT "")] (* on initialyse US_IDENT a vide car la valaeur sera attribuée dans analyse_caractere *)
  | US_RELATION, _ -> failwith "Erreur a US_RELATION : UL_IDENT attendu."

  | US_OP, UL_EQUAL -> [Vt US_EQUAL]
  | US_OP, UL_DIFF -> [Vt US_DIFF]
  | US_OP, UL_INF -> [Vt US_INF]
  | US_OP, UL_SUP -> [Vt US_SUP]
  | US_OP, UL_SUPEG -> [Vt US_SUPEG]
  | US_OP, UL_INFEG -> [Vt US_INFEG]
  | US_OP, _ -> failwith "Erreur a US_OP : UL_EQUAL, UL_DIFF, UL_INF, UL_SUP, UL_SUPEG ou UL_INFEG attendu."

  | x,y -> failwith "Derivation impossible";;

(* Analyse d'un terminal *)
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
    | x,y -> failwith "Erreur d'analyse";;

(* Analyse de l'expression *)
let rec analyse_caractere (v, liste_ul1) =
  match v, liste_ul1 with
    | Vt vt, ul::reste -> analyse_vt (vt, ul), reste
    | Vn vn,  ul::reste -> let liste_arbre, liste_ul2 = analyse_mot (derivation (vn, ul), ul::reste) in Node (vn, liste_arbre), liste_ul2
    | _,_ -> failwith "Problème lors de l'analyse  des caractères"
and
    analyse_mot (liste_v, liste_ul1) =
  match liste_v, liste_ul1 with
    | [], [] -> [],[]
    | v::reste, [] -> failwith "Erreur, expression terminee alors qu'un token est attendu"
    | [],l -> [], l
    | v::reste1, liste_ul1 -> let arbre, liste_ul2 = analyse_caractere (v, liste_ul1) in
      let suite_arbre, suite_liste_ul = analyse_mot (reste1, liste_ul2) in
	arbre::suite_arbre, suite_liste_ul
	  
(* Création de l'arbre abstrait à parit de l'arbre concret *)
let rec creer_arbre_abstrait arb_concret =
  match arb_concret with
    | Node (US_RELATION,[Leaf(US_IDENT id1);Node (US_OP, [Leaf US_EQUAL]);Leaf (US_IDENT id2)])
      -> Node_EQUAL(Ident id1, Ident id2)
    | Node (US_RELATION,[Leaf(US_IDENT id1);Node (US_OP, [Leaf US_DIFF]);Leaf (US_IDENT id2)])
      -> Node_DIFF(Ident id1, Ident id2)
    | Node (US_RELATION,[Leaf(US_IDENT id1);Node (US_OP, [Leaf US_INF]);Leaf (US_IDENT id2)])
      -> Node_INF(Ident id1, Ident id2)
    | Node (US_RELATION,[Leaf(US_IDENT id1);Node (US_OP, [Leaf US_SUP]);Leaf (US_IDENT id2)])
      -> Node_SUP(Ident id1, Ident id2)
    | Node (US_RELATION,[Leaf(US_IDENT id1);Node (US_OP, [Leaf US_SUPEG]);Leaf (US_IDENT id2)])
      -> Node_SUPEG(Ident id1, Ident id2)
    | Node (US_RELATION,[Leaf(US_IDENT id1);Node (US_OP, [Leaf US_INFEG]);Leaf (US_IDENT id2)])
      -> Node_INFEG(Ident id1, Ident id2)
    | Node (US_RELATION,_) -> failwith "Impossible de créer l'arbre abstrait, la relation n'est pas correcte"

    | Node (US_FACTEURB,[Leaf US_SI; arb_concret1; Leaf US_ALORS; arb_concret2; Leaf US_SINON; arb_concret3; Leaf US_FSI]) 
      -> Node_SIALORSSINON(creer_arbre_abstrait arb_concret1, creer_arbre_abstrait arb_concret2, creer_arbre_abstrait arb_concret3)
    | Node (US_FACTEURB, [Node (US_RELATION, suite)])
      -> creer_arbre_abstrait (Node (US_RELATION, suite))
    | Node (US_FACTEURB, [Leaf US_PAROPEN; Node (US_EXPR, suite); Leaf US_PARCLOSE])
      -> creer_arbre_abstrait (Node (US_EXPR, suite))
    | Node (US_FACTEURB, _) -> failwith "Impossible de créer l'arbre abstrait, le facteurB n'est pas correct"

    | Node (US_EXPR, [Node (US_TERMB, suite); Node (US_SUITEEXPR, [])])
      -> creer_arbre_abstrait (Node (US_TERMB, suite))
    | Node (US_EXPR, [Node (US_TERMB, suite1); Node (US_SUITEEXPR, [Leaf US_OR; Node (US_EXPR, suite2)])])
      -> Node_OR(creer_arbre_abstrait (Node (US_TERMB, suite1)), creer_arbre_abstrait (Node (US_EXPR, suite2)))
    | Node (US_EXPR, _) -> failwith "Impossible de créer l'arbre abstrait, l'expression n'est pas correcte"

    | Node (US_TERMB, [Node (US_FACTEURB, suite); Node (US_SUITETERMB, [])])
      -> creer_arbre_abstrait (Node (US_FACTEURB, suite))
    | Node (US_TERMB, [Node (US_FACTEURB, suite1); Node (US_SUITETERMB, [Leaf US_AND; Node (US_TERMB, suite2)])])
      -> Node_AND(creer_arbre_abstrait (Node (US_FACTEURB, suite1)), creer_arbre_abstrait (Node (US_TERMB, suite2)))
    | Node (US_TERMB, _) -> failwith "Impossible de créer l'arbre abstrait, le termb n'est pas correct"

    | _ -> failwith "Impossible de créer l'arbre abstrait, l'arbre concret n'est pas correct"



(* ****************************  *)
(* Tests de notre implémentation *)
(* ****************************  *)

(* Tests positifs *)
let x =
  analyse_caractere(Vn US_EXPR,[UL_IDENT "t"; UL_SUP; UL_IDENT "y";UL_AND;UL_PAROPEN;UL_IDENT "x";UL_EQUAL;UL_IDENT "y";UL_PARCLOSE;UL_EOF]);;

let x2 =
  creer_arbre_abstrait (fst x);; 

let y =
  analyse_caractere(Vn US_EXPR,[UL_IDENT "y" ;UL_SUP;UL_IDENT "t";UL_EOF]);;

let y2 =
  creer_arbre_abstrait (fst y);; 

let z =
  analyse_caractere(Vn US_EXPR,[UL_PAROPEN;UL_IDENT "x";UL_EQUAL;UL_IDENT "y";UL_PARCLOSE;UL_EOF]);;

let z2 =
  creer_arbre_abstrait (fst z);; 

let a =
  analyse_caractere(Vn US_EXPR,[UL_IDENT "t"; UL_SUP; UL_IDENT "y";UL_AND;UL_IDENT "x";UL_EQUAL;UL_IDENT "y";UL_EOF]);;

let a2 =
  creer_arbre_abstrait (fst a);; 

let b =
  analyse_caractere(Vn US_EXPR,[UL_SI;UL_IDENT "t";UL_SUP;UL_IDENT "u"; UL_ALORS; UL_IDENT "y" ;UL_INF;UL_IDENT "v";UL_SINON;UL_IDENT "x";UL_EQUAL;UL_IDENT "y";UL_FSI;UL_EOF]);;

let b2 =
  creer_arbre_abstrait (fst b);;

let c =
  analyse_caractere(Vn US_EXPR,[UL_SI;UL_IDENT "t";UL_SUP;UL_IDENT "u"; UL_ALORS; UL_IDENT "y" ;UL_INF;UL_IDENT "v";UL_SINON;UL_SI;UL_IDENT "t";UL_SUP;UL_IDENT "u"; UL_ALORS; UL_IDENT "y" ;UL_INF;UL_IDENT "v";UL_SINON;UL_IDENT "x";UL_EQUAL;UL_IDENT "y";UL_FSI;UL_FSI;UL_EOF]);;

let c2 =
  creer_arbre_abstrait (fst c);;


(* Tests négatifs *)
let d  = 
  analyse_caractere(Vn US_EXPR,[UL_EOF]);;

let e  = 
  analyse_caractere(Vn US_EXPR,[UL_IDENT "t";UL_EOF]);;

let f  = 
  analyse_caractere(Vn US_EXPR,[UL_AND;UL_EOF]);;

let g  = 
  analyse_caractere(Vn US_EXPR,[UL_IDENT "t";UL_AND;UL_IDENT "r";UL_EOF]);;
