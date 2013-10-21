(* TP Emacs et OCaml *)
(* Damien Crémilleux & Tom Demulier--Chevret  *)

exception BadExpr

let rec eval = function
  | Node ((Int i), _) -> i
  | Node ((Op op), [l; r]) ->
      let fonction_operateur =
	(match op with
	   | Add -> ( + )
	   | Mul -> ( * ))
      in fonction_operateur (eval l) (eval r)
  | _ -> raise BadExpr


(* Affiche le résultat de l'évaluation d'une chaîne de caractères *)
(* val eval_string : string -> int = <fun> *)
let eval_string chaine =
  eval (parse (get_token) (stream_of_string chaine));;

(* # eval_string "5*9";; *)
(* - : int = 45 *)
