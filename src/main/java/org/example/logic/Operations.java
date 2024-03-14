package org.example.logic;

import org.example.model.Polynomial;

public class Operations {
    public Polynomial add(Polynomial poly1, Polynomial poly2)
    {
        Polynomial result = new Polynomial();

        // Iterate through the terms of the first polynomial and add them to the result polynomial
        for (int exponent : poly1.getTerms().keySet()) {
            int coeff = poly1.getCoeff(exponent);
            result.addTerm(coeff, exponent);
        }

        // Iterate through the terms of the second polynomial and add them to the result polynomial
        for (int exponent : poly2.getTerms().keySet()) {
            int coeff = poly2.getCoeff(exponent);
            // If the same exponent exists in the result polynomial, add the coefficients
            if (result.getTerms().containsKey(exponent)) {
                int newCoeff = result.getCoeff(exponent) + coeff;
                result.addTerm(newCoeff, exponent);
            } else {
                result.addTerm(coeff, exponent); // Add the term to the result polynomial
            }
        }

        return result;
    }
    public Polynomial subtract(Polynomial poly1, Polynomial poly2)
    {
        Polynomial result = new Polynomial();

        // Iterate through the terms of the first polynomial and add them to the result polynomial
        for (int exponent : poly1.getTerms().keySet()) {
            int coeff = poly1.getCoeff(exponent);
            result.addTerm(coeff, exponent);
        }

        // Iterate through the terms of the second polynomial and add them to the result polynomial
        for (int exponent : poly2.getTerms().keySet()) {
            int coeff = poly2.getCoeff(exponent);
            // If the same exponent exists in the result polynomial, add the coefficients
            if (result.getTerms().containsKey(exponent)) {
                int newCoeff = result.getCoeff(exponent) - coeff;
                result.addTerm(newCoeff, exponent);
            } else {
                result.addTerm(-coeff, exponent); // Add the term to the result polynomial
            }
        }

        return result;
    }
    public Polynomial multiply(Polynomial poly1, Polynomial poly2) {
        Polynomial result = new Polynomial();
        for (int exponent : poly1.getTerms().keySet()) {
            int coeff = poly1.getCoeff(exponent);
            for (int exponent2 : poly2.getTerms().keySet()) {
                result.addTerm(coeff * poly2.getCoeff(exponent2), exponent + exponent);
            }
        }
        return result;
    }
    public Polynomial divide(Polynomial poly1, Polynomial poly2)
    {
        return poly1;
    }
    public Polynomial derivative(Polynomial poly1)
    {
        Polynomial result = new Polynomial();
        for (int exponent : poly1.getTerms().keySet()) {

            int coeff = poly1.getCoeff(exponent);
            System.out.println(coeff);
            result.addTerm(coeff*exponent, exponent-1);
        }
        return result;
    }
    public Polynomial integrate(Polynomial poly1)
    {
        Polynomial result = new Polynomial();
        for (int exponent : poly1.getTerms().keySet()) {

            int coeff = poly1.getCoeff(exponent);
            result.addTerm(coeff/(exponent+1), exponent+1);
        }
        return result;
    }

}
