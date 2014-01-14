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
#include "Carte.h"

/* Initialize the static members */
Carte* Carte::firstN=nullptr;
Carte* Carte::lastN=nullptr;
Carte* Carte::firstS=nullptr;
Carte* Carte::lastS=nullptr;

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
Carte::Carte (Couleur couleur, Valeur valeur, char proprietaire):_couleur(couleur), _valeur(valeur),_proprietaire(proprietaire)
{
	/* creation of the packet if it is the first card */
	switch (proprietaire) {
	case 'N':
		if(firstN == nullptr)
		{
			firstN = this;
		} else {
			lastN->nextCard = this;
		}
		lastN = this;
		lastN->nextCard = nullptr;
		break;
	case 'S':
		if(firstS == nullptr)
		{
			firstS = this;
		} else {
			lastS->nextCard = this;
		}
		lastS = this;
		lastS->nextCard = nullptr;
		break;
	default:
		/* IMPOSSIBLE */
		;
	}

}

/**
*\fn afficherN()
*\brief display a card of the N player
*/
void Carte::afficherN() 
{
	if(firstN == nullptr)
	{
		printf("No card");
	}else{

		Carte * cardToDisplay = firstN;
		while(cardToDisplay != nullptr)
		{
			cardToDisplay->afficher();
			cardToDisplay = cardToDisplay->nextCard;
		}	
	}
}

/**
*\fn afficherS()
*\brief display a card of the S player
*/
void Carte::afficherS() 
{
	if(firstS == nullptr)
	{
		printf("No card");
	}else{
		Carte * cardToDisplay = firstS;
		while(cardToDisplay != nullptr)
		{
			cardToDisplay->afficher();
			cardToDisplay = cardToDisplay->nextCard;
		}	
	}
}

/**
*\fn afficher()
*\brief display a card
*/
void Carte::afficher() const
{
	std::cout << VALEUR[this->_valeur-1] << " de " << COULEUR[this->_couleur-1] << std::endl;
}

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
bool Carte::egale(Carte carte)
{
	return (this->_couleur == carte._couleur) &&
		(this->_valeur == carte._valeur) &&
		(this->_proprietaire == carte._proprietaire) ;
}

/**
*\fn Carte::suc()
*\brief Return the next card of the packet
*
* \return Carte the next card
*/
Carte* Carte::suc(){
	return this->nextCard;
}

/**
*\fn Carte::changerProp()
*\brief Change the owner of the card
*
* Put the card at the end of the oppenent's packet
*/
void Carte::changerProp()
{
	switch (this->_proprietaire) {
	case 'N':
		this->_proprietaire = 'S';
		firstN = firstN->nextCard;
		lastS->nextCard = this;
		lastS = this;
		lastS->nextCard = nullptr;
		break;
	case 'S':
		this->_proprietaire = 'N';
		firstS = firstS->nextCard;
		lastN->nextCard = this;
		lastN = this;
		lastN->nextCard = nullptr;
		break;
	default:
		/* IMPOSSIBLE */
		;
	}


}

/**
*\fn Carte::supAbs(Carte carte) const
*\brief Compare two cards
*
* \param[in] carte the card to compare.
*
* \return bool true if the current card has a higher value than the card in the parameter, else false
*/
bool Carte::supAbs(Carte carte) const
{
	return this->_valeur>carte._valeur;
}

/**
*\fn Carte::passerDerriere()
*\brief Put the card at the end of the packet
*/
void Carte::passerDerriere()
{
	switch (this->_proprietaire) {
	case 'N':
		firstN = this->nextCard;
		lastN->nextCard = this;
		lastN = this;
		lastN->nextCard = nullptr;
		break;
	case 'S':
		firstS = this->nextCard;
		lastS->nextCard = this;
		lastS = this;
		lastS->nextCard = nullptr;
		break;
	default:
		/* IMPOSSIBLE */
		;

	}
}