all : lexer.mll main.ml ulex.ml anasynt.ml
#	ocamlbuild -cflags -annot main.native
	ocamlbuild -cflags -annot main.byte 
anasynt :  lexer.mll ulex.ml anasynt.ml
	ocamlbuild -tag debug  -cflags -annot anasynt.byte 