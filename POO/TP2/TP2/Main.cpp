/**
* \file Chaine.cpp
* \brief Fichier main du projet
* \author Damien Cremilleux, Lauriane Holy
* \date 3/10/2013
*
*/

#include "Chaine.h"
#include <stdio.h>
#include <iostream>

/**
* \fn main
* \brief fonction main permettant de creer les chaines et de tester les fonctions
*
*/
int main (int agc, char* argv[]){
	printf("Debut de la fonction main\n");

	// Creation de tableaux de caractères

	char test[] = "test test";
	char test2[] = "test";
	char test3[] = "ananas";
	char test4[] = "hello world";
	char c = 's';

	// creation de chaines de caractères avec les différents constructeurs
	Chaine c1;
	Chaine c2(test);
	Chaine c3(c2);
	Chaine c4(test2);
	Chaine c5(test3);
	Chaine c6(test4);

	// test des fonctions de chaine.cpp
	c1.afficherChaine(); // empty chaine
	c2.afficherChaine(); // "test test"
	c3.afficherChaine(); // test test"
	cout << (c1 == c3) <<endl;  // false
	cout << (c2 == c3) <<endl;  // true 
	cout << (c4 == c3) <<endl;  // false
	cout << (c4 > c5) <<endl;   // true
	cout << (c4 < c5) <<endl;   // false
	cout << (c5 < c4) <<endl;   // true

	(c4+c5).afficherChaine(); // "testananas"
	(c2 += c5).afficherChaine(); // c2 : "test testananas"
	c2.afficherChaine(); // "test testananas"

	cout << "Indexe du caractere s dans la chaine ";
	c5.afficherChaine();  // "ananas"
	cout << " : " ;
	cout << c5.index_char(c) <<endl; // the first occurence of s is at the position 6

	c6.sous_chaine(6,10).afficherChaine(); // "word"

	printf("fin du main\n");

}