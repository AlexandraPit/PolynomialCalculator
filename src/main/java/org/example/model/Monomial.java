package org.example.model;

import org.example.view.View;

public class Monomial {
    private double coeff;
    private int exponent;

    public Monomial(double coeff, int exponent) {
        this.coeff = coeff;
        this.exponent = exponent;
    }

    public double getCoeff() {
        return coeff;
    }

    public int getExponent() {
        return exponent;
    }

    public Monomial divide(Monomial other) {
        if (other.coeff == 0) {
            throw new ArithmeticException("Division by zero");
        }
        double newCoeff = this.coeff / other.coeff;
        int newExponent = this.exponent - other.exponent;
        return new Monomial(newCoeff, newExponent);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monomial monomial = (Monomial) o;
        return Double.compare(monomial.coeff, coeff) == 0 &&
                exponent == monomial.exponent;
    }

}
