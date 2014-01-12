#ifndef OVERFLOWEXCEPTION_H
#define OVERFLOWEXCEPTION_H

#include <exception>

class CaractereException: public std::exception {
	public:
		
		virtual const char * what() const throw();
		virtual const char * what(string s) const throw();
};

#endif