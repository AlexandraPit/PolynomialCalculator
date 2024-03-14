package org.example.model;

import org.example.gui.View;

import java.util.*;

public class Polynomial {
private HashMap<Integer, Monomial> terms;

public Polynomial(){
    terms = new HashMap<>();
}

public void addTerm(int coeff, int exponent)
{
    Monomial monomial=new Monomial(coeff, exponent);
    terms.put(exponent, monomial);
}
public void removeTerm(int exponent)
{
    terms.remove(exponent);
}
public int getCoeff(int exponent)
{
    Monomial monomial=terms.get(exponent);
    if(monomial!=null)
        return monomial.getCoeff();
    return 0;
}
public int getDegree()
{
    int maxDegree=0;
    for(int exponent:terms.keySet())
    {
        if(exponent>maxDegree)
            maxDegree=exponent;
    }
    return maxDegree;
}
public Map<Integer, Monomial> getTerms() {
        return terms;
    }

    @Override
    public String toString() {
        StringBuilder polynomialString = new StringBuilder();
        boolean firstTerm = true;

        // Get the exponents in descending order
        List<Integer> exponents = new ArrayList<>(terms.keySet());
        Collections.sort(exponents, Collections.reverseOrder());

        for (int exponent : exponents) {
            Monomial monomial = terms.get(exponent);
            int coeff = monomial.getCoeff();

            if (coeff != 0) {
                if (!firstTerm) {
                    polynomialString.append(coeff >= 0 ? " + " : " - "); // Check for negative coefficient
                } else {
                    firstTerm = false;
                    if (coeff < 0) {
                        polynomialString.append("-"); // Append negative sign for first term
                    }
                }
                coeff = Math.abs(coeff); // Ensure positive coefficient for appending
                if (coeff != 1 || exponent == 0) {
                    polynomialString.append(coeff);
                }
                if (exponent != 0) {
                    polynomialString.append(exponent != 1 ? "x^" + exponent : "x");
                }
            }
        }
        if (polynomialString.length() == 0) {
            polynomialString.append("0");
        }
        return polynomialString.toString();
    }

}

