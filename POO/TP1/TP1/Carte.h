/**
* \file carte.cpp
* \brief Methods relative to cards
* \author Crémilleux Damien
* \author Holy Lauriane
* \version 1.10
* \date 16/09/2013
*
* All methods needed to use cards (for game) are implemented in this file.
*
*/

#include <string>
#include <sstream> 
#include <iostream>

/**
* \enum Couleur 
* \brief The 4 colours of the cards 
*/
enum Couleur{PIQUE = 1, TREFLE, COEUR, CARREAU};
static const std::string COULEUR[] = {"pique", "trefle","coeur","carreau"};

/**
* \enum Valeur
* \brief The value of the cards 
*/
enum Valeur {AS = 1, DEUX, TROIS, QUATRE, CINQ, SIX, SEPT, HUIT, NEUF, DIX, VALET, DAME, ROI};
static const std::string VALEUR[] = {"as", "deux","trois","quate", "cinq", "six", "sept", "huit", "neuf", "dix", "valet", "dame","roi"};

/**
* \class Carte
* \brief Representation of a card
*/
class Carte {
	/*!< Colour of the card */
	Couleur _couleur;
	/*!< Value of the card */
	Valeur _valeur;
	/*!< Owner of the card */
	char _proprietaire;
	/*!< The next card in the packet */
	Carte* nextCard;
		
	/*!< The first and the last card of the packets */
	static Carte* firstN;
	static Carte* lastN;
	static Carte* firstS;
	static Carte* lastS;

public:
	/**
*\fn Carte (Couleur couleur, Valeur valeur,  char proprietaire);
*\brief Constructor
*
* Create a new card
*
* \param[in] couleur colour of the new card.
* \param[in] valeur value of the new card.
* \param[in] proprietaire owner of the new card.
*
* \return Carte a new card
*/
	Carte (Couleur couleur, Valeur valeur,  char proprietaire);

	/* access methods */
	Couleur couleur() const;
	Valeur valeur() const;
	char proprietaire() const;
	
	static Carte* getNTete();
	static Carte* getSTete();

/**
*\fn afficherN()
*\brief display a card of the N player
*/
	static void afficherN() ;

	/**
*\fn afficherS()
*\brief display a card of the S player
*/
	static void afficherS() ;

	/**
*\fn afficher()
*\brief display a card
*/
	void afficher() const;

/**
*\fn Carte::egale(Carte carte)
*\brief Test the equality between two cards 
*
* Test only the value, not the colour
*
* \param[in] carte the card to compare
*
* \return bool true if the value is the same, else false
*/
	bool egale(Carte carte) ;

/**
*\fn Carte::suc()
*\brief Return the next card of the packet
*
* \return Carte the next card
*/
	Carte* suc();

/**
*\fn Carte::changerProp()
*\brief Change the owner of the card
*
* Put the card at the end of the oppenent's packet
*/
	void changerProp();

/**
*\fn Carte::supAbs(Carte carte) const
*\brief Compare two cards
*
* \param[in] carte the card to compare.
*
* \return bool true if the current card has a higher value than the card in the parameter, else false
*/
	bool supAbs(Carte carte) const;

/**
*\fn Carte::passerDerriere()
*\brief Put the card at the end of the packet
*/
	void passerDerriere();

};	

/**
* access methods 
*/

inline Couleur Carte::couleur() const {return _couleur;}
inline Valeur Carte::valeur() const {return _valeur;}
inline char Carte::proprietaire() const {return _proprietaire;}
inline Carte* Carte::getNTete() {return firstN;}
inline Carte* Carte::getSTete() {return firstS;}