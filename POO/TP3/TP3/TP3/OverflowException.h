#ifndef OVERFLOWEXCEPTION_H
#define OVERFLOWEXCEPTION_H

#include <exception>

class OverflowException: public std::exception {
	public:
		
		virtual const char * what() const throw();
};

#endif