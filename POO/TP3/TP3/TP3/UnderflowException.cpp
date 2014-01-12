#include <iostream>
#include "UnderflowException.h"

using namespace std;

const char * UnderflowException::what() const throw()
{
	return "Erreur, débordement inférieur.";
  
} 