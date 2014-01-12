#ifndef ZEROEXCEPTION_H
#define ZEROEXCEPTION_H

#include <exception>

class ZeroException: public std::exception {
	public:
		
		virtual const char * what() const throw();
};

#endif