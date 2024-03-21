package org.example.model;

import java.util.*;

public class Polynomial {
    private final HashMap<Integer, Monomial> terms;

    public Polynomial() {
        terms = new HashMap<>();
    }

    public void addTerm(double coeff, int exponent) {
        Monomial existingMonomial = terms.get(exponent);
        if (existingMonomial != null) {
            // If a term with the same exponent already exists, accumulate the coefficients
            coeff += existingMonomial.getCoeff();
        }
        Monomial monomial = new Monomial(coeff, exponent);
        terms.put(exponent, monomial);
    }

    public double getCoeff(int exponent) {
        Monomial monomial = terms.get(exponent);
        if (monomial != null)
            return monomial.getCoeff();
        return 0;
    }

    public Monomial getTermWithMaxExponent() {
        Monomial[] maxTerm = {null}; // Using an array to hold the reference of maxTerm due to the capture in lambda
        int[] maxExponent = {Integer.MIN_VALUE}; // Using an array to hold the max exponent due to capture in lambda
        terms.values().forEach(monomial -> {
            if (monomial.getExponent() > maxExponent[0]) {
                maxTerm[0] = monomial;
                maxExponent[0] = monomial.getExponent();
            }
        });
        return maxTerm[0];
    }
    public int getMaxDegree() {
        final int[] maxDegree = {0}; // Using an array to hold the max degree due to capture in lambda
        terms.keySet().forEach(exponent -> {
            if (exponent > maxDegree[0]) {
                maxDegree[0] = exponent;
            }
        });
        return maxDegree[0];
    }


    public Map<Integer, Monomial> getTerms() {
        return terms;
    }

    @Override
    public String toString() {
        StringBuilder polynomialString = new StringBuilder();


        List<Integer> exponents = new ArrayList<>(terms.keySet());
        exponents.sort(Comparator.reverseOrder());
        boolean[] firstTerm = {true};
        exponents.forEach(exponent -> {
            Monomial monomial = terms.get(exponent);
            double coeff = monomial.getCoeff();

            if (coeff != 0) {
                if (!firstTerm[0]) {
                    polynomialString.append(coeff >= 0 ? " + " : " - ");
                } else {
                    firstTerm[0] = false;
                    if (coeff < 0) {
                        polynomialString.append("-");
                    }
                }
                coeff = Math.abs(coeff);
                if (coeff != 1 || exponent == 0) {
                    polynomialString.append(coeff);
                }
                if (exponent != 0) {
                    polynomialString.append(exponent != 1 ? "x^" + exponent : "x");
                }
            }
        });
        if (polynomialString.isEmpty()) {
            polynomialString.append("0");
        }
        return polynomialString.toString();
    }

    public Polynomial multiply(Monomial monomial) {
        Polynomial result = new Polynomial();

        terms.forEach((exp, monomialEntry) -> {
            double coeff = monomialEntry.getCoeff();
            double newCoeff = coeff * monomial.getCoeff();
            int newExp = exp + monomial.getExponent();

            // Check if there's already a term with the same exponent
            if (result.getTerms().containsKey(newExp)) {
                double existingCoeff = result.getCoeff(newExp);
                result.addTerm(existingCoeff + newCoeff, newExp);
            } else {
                result.addTerm(newCoeff, newExp);
            }
        });

        return result;
    }

    public Polynomial subtract(Polynomial poly) {
        Polynomial result = new Polynomial();

        for (Map.Entry<Integer, Monomial> entry : terms.entrySet()) {
            int exp = entry.getKey();
            double coeff = entry.getValue().getCoeff();
            result.addTerm(coeff, exp);
        }

        for (Map.Entry<Integer, Monomial> entry : poly.getTerms().entrySet()) {
            int exp = entry.getKey();
            double coeff = -entry.getValue().getCoeff();

            if (result.getTerms().containsKey(exp)) {
                double newCoeff = result.getCoeff(exp) + coeff;
                if (newCoeff != 0) {
                    result.addTerm(coeff, exp);
                } else {
                    result.getTerms().remove(exp);
                }
            } else {
                result.addTerm(coeff, exp);
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Polynomial that = (Polynomial) o;
        return Objects.equals(this.terms, that.terms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(terms);
    }
}

