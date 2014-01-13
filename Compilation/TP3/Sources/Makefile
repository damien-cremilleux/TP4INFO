#		TP3. Analyse lexicale et syntaxique
#		    ascendante, ocamllex et ocamlyacc
#
#	Makefile donne a titre d'exemple
#
#
# Historique:
#	17 Nov. 08		Par : Laurent Hubert
#	03 Oct. 08		Par : Benoit Boyer
#


GEN_MLI= $(subst .mly,.mli, $(wildcard *.mly))
GEN_ML= $(subst .mll,.ml, $(subst .mly,.ml, $(wildcard *.mll *.mly)))

ML= $(wildcard *.ml) $(GEN_ML)
MLI= $(wildcard *.mli) $(GEN_MLI)

CMO= $(subst .ml,.cmo, $(ML))

# Nom de l'executable produit (vous pouvez le changer a votre guise)
OUT=tp3


CFLAGS=-dtypes
CC=ocamlc $(CFLAGS)
RM=rm -rf

.PHONY: clean all

# Pour compiler
all: $(OUT)

$(OUT): $(CMO)
	$(CC) $^ -o $@
	@echo "===> $@ est compile avec succes!!!"

-include .depend

%.cmi:	%.mli
	$(CC) -c $<

%.cmo :	%.ml
	$(CC) -c $<

%.ml :	%.mll
	ocamllex $<

%.ml %.mli : %.mly
	ocamlyacc -v $< # l'option -v genere %.output qui contient la table SLR


clean:
	$(RM) $(OUT) *~ .depend $(GEN_MLI) $(GEN_ML) *.{annot,output,cmi,cmo}

.depend: $(ML) $(MLI)
	ocamldep $^ > .depend
