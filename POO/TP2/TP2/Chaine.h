/**
* \file Chaine.h
* \brief Fichier d'en-tete pour la gestion des chaines de caractères
* \author Damien Cremilleux, Lauriane Holy
* \date 3/10/2013
*
*/

#ifndef CHAINE_H
#define CHAINE_H

using namespace std;

/**
* \class Chaine
* \brief classe chaine, represenation des chaines de caractères
*/
class Chaine{

private:
	/*!< La chaine de de caractère est stockée dans un tableau*/
	char * _p_chaine;

public :
	/**
	* \fn Chaine()
	* \ Constructeur pas défaut de la classe Chaine
	* 
	* Initialise un tableau vide
	*
	* \return Chaine, une chaine de caractère vide
	*/
	Chaine();

	/**
	* \fn Chaine(const char* chaine)
	* \ Constructeur de la classe Chaine à partir d'un tableau
	* 
	* \param[in] chaine, un pointeur sur un tableau de caractères
	*
	* \return Chaine, une chaine de caractère vide
	*/
	Chaine(const char* chaine);

	/**
	* \fn Chaine(const Chaine& chaine)
	* \ Constructeur par recopie de la classe Chaine à partir d'une reference
	* 
	*
	* \param[in] chaine, réference sur une chaine de caractères
	*
	* \return Chaine, une chaine de caractère vide
	*/
	Chaine(const Chaine& chaine);

	/**
	* \fn ~Chaine()
	* \ Destructeur de la classe Chaine
	*/
	~Chaine();

	/**
	* \fn void afficherChaine() const
	* \brief affiche la chaine de caractère appelé par la méthode
	* 
	*/
	void afficherChaine() const;

	/**
	* \fn bool operator== (const Chaine& c) const
	* \  par recopie de la classe Chaine à partir d'une reference
	* 
	*
	* \param[in] chaine, réference sur une chaine de caractères
	*
	* \return Chaine, une chaine de caractère vide
	*/
	bool operator== (const Chaine& chaine) const;

	/**
	* \fn bool operator> (const Chaine& c) const
	* \brief compare si la chaine passé en paramètre est inférieur à celle sur laquelle la méthode est appelé
	*
	* \param[in] chaine, réference sur une chaine de caractères
	*
	* \return bool le résultat de la comparaison
	*/
	bool operator> (const Chaine& chaine) const;

	/**
	* \fn bool operator< (const Chaine& c) const
	* \brief compare si la chaine passé en paramètre est superieur à celle sur laquelle la méthode est appelé
	*
	* \param[in] chaine, réference sur une chaine de caractères
	*
	* \return bool le résultat de la comparaison
	*/
	bool operator< (const Chaine& chaine) const;

	/**
	* \fn bool operator>= (const Chaine& c) const
	* \brief compare si la chaine passé en paramètre est inférieur ou égale à celle sur laquelle la méthode est appelé
	*
	* \param[in] chaine, réference sur une chaine de caractères
	*
	* \return bool le résultat de la comparaison
	*/
	bool operator>= (const Chaine& chaine) const;

	/**
	* \fn bool operator<= (const Chaine& c) const
	* \brief compare si la chaine passé en paramètre est superieur ou égale à celle sur laquelle la méthode est appelé
	*
	* \param[in] chaine, réference sur une chaine de caractères
	*
	* \return bool le résultat de la comparaison
	*/
	bool operator<= (const Chaine& chaine) const;

	/**
	* \fn bool operator+ (const Chaine& c) const
	* \brief concaténation de la chaine passée en paramètre et de la chaine passé en argument implicite
	*
	* \param[in] chaine, réference sur une chaine de caractères
	*
	* \return chaine, la nouvelle chaine issu de la concatenation
	*/
	Chaine operator+ (const Chaine& chaine) const;

	/**
	* \fn bool operator+= (const Chaine& c) const
	* \brief concaténation de la chaine passée en paramètre et de la chaine passé en argument implicite, la concaténation se fait sur cette dernière
	*
	* \param[in] chaine, réference sur une chaine de caractères
	*
	* \return chaine, la chaine appelante suite à la concatenation
	*/
	Chaine& operator+= (const Chaine& chaine);

	/**
	* \fn char index_char(int ind)
	* \brief renvoi le caractère à l'indexe donné en paramètre
	*
	* \param[in] ind, l'indexe du caractère à renvoyer
	*
	* \return char, le caractère de la chaine a l'indexe ind
	*/
	int index_char(const char c);

	/**
	* \fn Chaine sous_chaine(char deb, char fin)
	* \brief extrait la sous-chaine commençant par le caractère deb et se terminant par fin
	*
	* \param[in] char deb, le premier caractère de la sous-chaine à extraire
	* \param[in] char fin, le dernier caractère de la sous-chaine à extraire
	*
	* \return chaine, la sous chaine (deb ... fin) extraite
	*/
	Chaine sous_chaine(char deb, char fin);

	/**
	* \fn Chaine sous_chaine(int ins1, int ind2)
	* \brief extrait la sous-chaine commençant à la position ind1 et se terminant à ind2
	*
	* \param[in] char ind1, la position du premier caractère de la sous-chaine à extraire
	* \param[in] char ind2, la position du dernier caractère de la sous-chaine à extraire
	*
	* \return chaine, la sous chaine extraite
	*/
	Chaine sous_chaine(int ind1, int ind2);

};

#endif