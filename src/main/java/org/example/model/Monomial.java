package org.example.model;

public class Monomial {
    private int coeff;
    private int exponent;
    public Monomial(int coeff, int exponent)
    {
        this.coeff=coeff;
        this.exponent=exponent;
    }
    public int getCoeff() {
        return coeff;
    }

    public int getExponent() {
        return exponent;
    }

}
