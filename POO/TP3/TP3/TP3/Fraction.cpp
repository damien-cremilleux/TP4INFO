#include "Fraction.h"
#include "ZeroException.h"
#include "OverflowException.h"
#include "UnderflowException.h"
#include <limits>

using namespace std;

Fraction::Fraction(int entier):_numerateur(entier), _denominateur(1)
{}

Fraction::Fraction(int numerateur, int denominateur):_numerateur(numerateur), _denominateur(denominateur)
{
	if (_denominateur == 0) throw ZeroException();
}


Fraction Fraction::addition(Fraction& const fract1) const{
	int nouveau_deno;
	int nouveau_num;
	nouveau_deno = fract1._denominateur * this->_denominateur;
	nouveau_num = this->_numerateur * fract1._denominateur + fract1._numerateur * this->_denominateur;

	if(
		(static_cast<double>(numeric_limits<int>::max()) - this->evaluer()) < fract1.evaluer())
		{
			throw OverflowException(); /* on vérifie que max >a +b, on lance une exception sinon */
	}

	return Fraction(nouveau_num,nouveau_deno);
}

Fraction Fraction::soustraction(Fraction& const fract1) const{
	return this->addition(Fraction(-fract1._numerateur, fract1._denominateur));
}

Fraction Fraction::multiplication(Fraction& const fract1) const{
	int nouveau_deno;
	int nouveau_num;
	nouveau_deno = fract1._denominateur * this->_denominateur;
	nouveau_num = this->_numerateur * fract1._numerateur;

	if((static_cast<double>(numeric_limits<int>::max()) / fract1.evaluer()) < this->evaluer()) throw OverflowException();

	return Fraction(nouveau_num,nouveau_deno);
}

Fraction Fraction::division(Fraction& const fract1) const{
	return this->multiplication(Fraction(fract1._denominateur, fract1._numerateur));
}

 double Fraction::evaluer() const {
	 return ((double)this->_numerateur/(double)this->_denominateur);
 }