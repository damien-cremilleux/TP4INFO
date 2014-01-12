#ifndef UNDERFLOWEXCEPTION_H
#define UNDERFLOWEXCEPTION_H

#include <exception>

class UnderflowException: public std::exception {
	public:
		
		virtual const char * what() const throw();
};

#endif