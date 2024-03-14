package org.example.gui;

import jdk.dynalink.Operation;
import org.example.gui.View;
import org.example.logic.Operations;
import org.example.model.Monomial;
import org.example.model.Polynomial;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {

    private View view;
    private Operations operations=new Operations();
    public Controller(View v){this.view=v;}



    public Polynomial parsePolynomial(String polynomialString) {
        Polynomial polynomial = new Polynomial();
        Pattern termPattern = Pattern.compile("([-+]?\\d*x\\^\\d*|[-+]?\\d*x|[-+]?\\d+)");

        Matcher matcher = termPattern.matcher(polynomialString);
        while (matcher.find()) {
            String term = matcher.group();

            // Split term into coefficient and exponent (if present)
            int coefficient;
            int exponent = 0;

            if (term.contains("x")) {
                int xIndex = term.indexOf("x");
                coefficient = (xIndex == 0 || term.charAt(xIndex - 1) == '+' || term.charAt(xIndex - 1) == '-') ? 1 : Integer.parseInt(term.substring(0, xIndex));
                if (term.contains("^")) {
                    exponent = Integer.parseInt(term.substring(term.indexOf("^") + 1));
                } else {
                    exponent = 1;
                }
            } else {
                coefficient = Integer.parseInt(term);
            }
            if (polynomial.getCoeff(exponent) != 0) {
                coefficient += polynomial.getCoeff(exponent);
            }
            // Accumulate coefficients for terms with the same exponent
            polynomial.addTerm(coefficient, exponent);
        }

        System.out.println(polynomial.toString());
        return polynomial;
    }
}
