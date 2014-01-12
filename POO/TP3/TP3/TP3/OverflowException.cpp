#include <iostream>
#include "OverflowException.h"

using namespace std;

const char * OverflowException::what() const throw()
{
	return "Erreur, débordement supérieur.";
  
} 