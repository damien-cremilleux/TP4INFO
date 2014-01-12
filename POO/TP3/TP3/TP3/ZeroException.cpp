#include <iostream>
#include "ZeroException.h"

using namespace std;

const char * ZeroException::what() const throw()
{
	return "Erreur, le diviseur est egal a zero.";
  
} 