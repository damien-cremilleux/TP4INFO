#include "Fraction.h"
#include <stdio.h>
#include <iostream>
#include <stdlib.h>
#include <exception>

using namespace std;

void main(){
	Fraction f = Fraction(1);

	Fraction f2 = Fraction(1,2);

	try{
		Fraction f3 = Fraction(1,0);
	}
	catch(exception& e)
	{ 
		cout<< e.what() << endl;
	}
	Fraction f4  = f.addition(f2);
	cout << f4.evaluer() << endl;

	Fraction f5  = f2.soustraction(f);
	cout << f5.evaluer() << endl;

	Fraction f6  = f2.soustraction(f2);
	cout << f6.evaluer() << endl;

	Fraction f7  = f2.multiplication(f);
	cout << f7.evaluer() << endl;

	Fraction f8  = f4.multiplication(f2);
	cout << f8.evaluer() << endl;
		
	Fraction f9  = f.division(f2);
	cout << f9.evaluer() << endl;

	try{
	Fraction f10  = f.division(Fraction(0,1));
	cout << f10.evaluer() << endl;
	}
		catch(exception& e)
	{ 
		cout<< e.what() << endl;
	}

		Fraction f10 = Fraction(INT_MAX,1);

	try{
		Fraction f11  = f10.addition(f10);
	cout << f11.evaluer() << endl;
	}
		catch(exception& e)
	{ 
		cout<< e.what() << endl;
	}
	

	cout << "finf" <<endl;
}