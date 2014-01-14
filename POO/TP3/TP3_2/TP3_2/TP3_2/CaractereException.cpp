#include <iostream>
#include "CaractereException.h"

using namespace std;

const char * CaractereException::what() const throw()
{
	return "Erreur, caractere non autorise.";
  
} 

const char * CaractereException::what(string s) const throw()
{
	return (char*) &s;
  
} 
