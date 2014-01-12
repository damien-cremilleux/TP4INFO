#ifndef ENSEMBLE_H
#define ENSEMBLE_H
#include "list.h"

template <class T>
class Ensemble
{
private :
	List<T> ens;

public:

	//Constructeur
	Ensemble()
	{
	}
	// Constructeur par recopie
	Ensemble(const Ensemble<T>& Eref): ens(Eref.ens)
	{
	}

	// Ajout d'un élément dans l'ensemble
	void add_Elt(const T& elt)
	{
	if (!(ens==elt) )                    // TODO AJOUT D'UNE EXCEPTION ? / return quelque chose 
		ens.addElement(elt);
	}

	// Cardinal de l'ensemble
	int card_Ens() const { 
		return ens.card();
	}

	// opérateur d'affichage d'une liste dans un flux
	friend std::ostream& operator<< <T>(std::ostream& out,const Ensemble<T>& Eref);

	// opérateur de lecture d'une liste dans un flux
	friend std::istream& operator>> <T>(std::istream& in,Ensemble<T>& Eref);

	// union
	Ensemble<T> operator+(const Ensemble& ens2) const {
		Ensemble<T> Eres(*this);
		List<T> l2(ens2.ens);
		for (ListIterator<T> iterlst = l2.beg(); !(iterlst.finished()); ++iterlst) {
			Eres.add_Elt(iterlst.get());
		}
		return Eres;
	}


	//intersection
	Ensemble<T> operator*(const Ensemble& ens2) const {
		Ensemble<T> Eres=Ensemble<T>();
		List<T> l2(ens2.ens);
		for (ListIterator<T> iterlst = l2.beg(); !(iterlst.finished()); ++iterlst) {
			if( ens==iterlst.get())
				Eres.add_Elt(iterlst.get());
		}
		return Eres;
	
	}

};


template <class T>
std::ostream& operator<<(std::ostream& out, const Ensemble<T>& Eref) {
	out << Eref.ens;
	return out;
}

template <class T>
std::istream& operator>>(std::istream& in, Ensemble<T>& Eref) {
	int nb;
	in >> nb;
	for (int i = 0; i < nb; i++) {
		T tmp;
		in >> tmp;
		Eref.add_Elt(tmp);
	}
	return in;
}

#endif

