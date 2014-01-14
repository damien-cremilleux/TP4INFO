open Ast
open Value

let loop debug =
  let env = ref ["fst", Val_closure([], [Ml_pattern_pair(Ml_pattern_var "x", Ml_pattern_var "_"), Ml_var "x"]);
		 "snd", Val_closure([], [Ml_pattern_pair(Ml_pattern_var "_", Ml_pattern_var "x"), Ml_var "x"])] in
  while true do
    let lexbuf = Lexing.from_channel stdin in
    print_string "# ";
    flush stdout;
    try
      let ast = Parser.main Lexer.get_token lexbuf in
      if debug then begin 
	Print_ast.print_ast ast; print_newline () 
      end;
      let val_option = Eval.eval env ast in
      (match val_option with
      | None -> ()
      | Some v -> 
	print_string "- = ";
	Print_val.print_val v;
	print_newline ())
    with
    | Eval.Error msg -> Printf.printf "error: eval: %s\n" msg
    | Failure msg -> Printf.printf "error: %s\n" msg
  done

let _ = loop (Array.length Sys.argv = 2 && Sys.argv.(1) = "-d")
