/*!
* \file  seq_main.cpp
* \brief  Test for reading sequences  
* \date to be updated
* \author to be updated
*/

#include "sequences.h"
#include "CaractereException.h"

using namespace std;

int main()
{
	string s1,s2,s3;

	cout << "Entrez une sequence proteique: ";
	cin >> s1;
	cout << "Entrez son nom: ";
	cin >> s2;

	try{	
		seqprot P1(s1,s2);
		cout << P1 << endl;
	} 
	catch(exception& e)
	{
		cout<< e.what() << endl;
	}


	cout << "Entrez une sequence d'adn: ";
	cin >> s1;
	cout << "Entrez son nom: ";
	cin >> s2;

	try{	
		seqadn A1(s1,s2);
		cout << A1 << endl;

	} 
	catch(exception& e)
	{
		cout<< e.what() << endl;
	}

	cout << "Entrez une sequence d'arn: ";
	cin >> s1;
	cout << "Entrez son nom: ";
	cin >> s2;

	try{	
		seqarn R1(s1,s2);
		cout << R1 << endl;

	} 
	catch(exception& e)
	{
		cout<< e.what() << endl;
	}

	return 0;

}