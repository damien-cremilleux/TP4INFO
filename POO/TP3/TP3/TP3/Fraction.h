#ifndef FRACTION_H
#define FRACTION_H

class Fraction {
private:
	int _numerateur;
	int _denominateur;

public:
	Fraction(int entier);
	Fraction(int numerateur, int denominateur);
	
	Fraction addition(Fraction& const fract1) const;
	Fraction soustraction(Fraction& const fract1) const;
	
	Fraction multiplication(Fraction& const fract1) const;
	Fraction division(Fraction& const fract1) const;

	double evaluer() const;

};

#endif