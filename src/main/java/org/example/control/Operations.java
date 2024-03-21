package org.example.control;

import org.example.model.Monomial;
import org.example.model.Polynomial;

import java.util.ArrayList;
import java.util.List;

public class Operations {

    public Polynomial add(Polynomial poly1, Polynomial poly2) {
        Polynomial result = new Polynomial();

        poly1.getTerms().forEach((exponent, monomial) -> result.addTerm(monomial.getCoeff(), exponent));
        poly2.getTerms().forEach((exponent, monomial) -> result.addTerm(monomial.getCoeff(), exponent));

        return result;
    }

    public Polynomial subtract(Polynomial poly1, Polynomial poly2) {
        Polynomial result = new Polynomial();

        poly1.getTerms().forEach((exponent, monomial) -> result.addTerm(monomial.getCoeff(), exponent));
        poly2.getTerms().forEach((exponent, monomial) -> result.addTerm(-monomial.getCoeff(), exponent));

        return result;
    }

    public Polynomial multiply(Polynomial poly1, Polynomial poly2) {
        Polynomial result = new Polynomial();
        poly1.getTerms().forEach((exp1, monomial1) ->
                poly2.getTerms().forEach((exp2, monomial2) ->
                        result.addTerm(monomial1.getCoeff() * monomial2.getCoeff(), exp1 + exp2)));
        return result;
    }

    public List<Polynomial> divide(Polynomial N, Polynomial D) {
        List<Polynomial> res = new ArrayList<>();
        Polynomial quotient = new Polynomial(); // Initialize the quotient
        Polynomial remainder = N; // Make a copy of N as remainder

        while (remainder.getMaxDegree() >= D.getMaxDegree() && remainder.getMaxDegree() != 0) {
            Monomial m = remainder.getTermWithMaxExponent(); // Get the leading term of remainder
            Monomial n = D.getTermWithMaxExponent(); // Get the leading term of D

            Monomial p = m.divide(n); // Divide the leading terms
            quotient.addTerm(p.getCoeff(), p.getExponent()); // Add the quotient term to the quotient

            Polynomial aux = D.multiply(p); // Multiply D by the quotient term
            remainder = remainder.subtract(aux); // Subtract the product from the remainder
        }

        res.add(remainder); // Add the remainder as the last term in the result list
        res.add(quotient); // Add the quotient as the last term in the result list

        return res;
    }

    public Polynomial derivative(Polynomial poly1) {
        Polynomial result = new Polynomial();
        poly1.getTerms().forEach((exponent, monomial) -> result.addTerm(monomial.getCoeff() * exponent, exponent - 1));
        return result;
    }

    public Polynomial integrate(Polynomial poly1) {
        Polynomial result = new Polynomial();
        poly1.getTerms().forEach((exponent, monomial) -> result.addTerm(monomial.getCoeff()/ (exponent + 1), exponent + 1));
        return result;
    }

}
