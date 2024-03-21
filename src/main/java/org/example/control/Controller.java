package org.example.control;

import org.example.control.Operations;
import org.example.model.Polynomial;
import org.example.view.View;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {

    private View view;
    private Operations operations = new Operations();

    public Controller(View v) {
        this.view = v;
    }

    public void handleButtonClick(String actionCommand, String polynomial1, String polynomial2) {
        Polynomial result = null;

        switch (actionCommand) {
            case "Add":
                result = operations.add(parsePolynomial(polynomial1), parsePolynomial(polynomial2));
                break;
            case "Subtract":
                result = operations.subtract(parsePolynomial(polynomial1), parsePolynomial(polynomial2));
                break;
            case "Multiply":
                result = operations.multiply(parsePolynomial(polynomial1), parsePolynomial(polynomial2));
                break;
            case "Divide":
                result = handleDivision(polynomial1, polynomial2);
                break;
            case "Derivative":
                result = operations.derivative(parsePolynomial(polynomial1));
                break;
            case "Integrate":
                result = operations.integrate(parsePolynomial(polynomial1));
                break;
        }

        if (!actionCommand.equals("Divide")) {
            view.setResult(result.toString());
        }
    }

    private Polynomial handleDivision(String polynomial1, String polynomial2) {
        Polynomial result = null;
        try {
            Polynomial firstPolynomial = parsePolynomial(polynomial1);
            Polynomial secondPolynomial = parsePolynomial(polynomial2);
            List<Polynomial> resultlist = operations.divide(firstPolynomial, secondPolynomial);
            Polynomial quotient = resultlist.get(1);
            Polynomial remainder = resultlist.get(0);

            StringBuilder sb = new StringBuilder();
            sb.append("<html>");
            sb.append(quotient.toString());
            sb.append("<br>Remainder:");
            sb.append(remainder.toString());
            sb.append("</html>");
            view.setResult(sb.toString());
        } catch (ArithmeticException ex) {
            view.setResult("Error: Division by zero!");
        }
        return result;
    }

    public Polynomial parsePolynomial(String polynomialString) {
        Polynomial polynomial = new Polynomial();
        Pattern termPattern = Pattern.compile("([-+]?\\d*x\\^\\d+|[-+]?\\d*x|[-+]?\\d+)");

        Matcher matcher = termPattern.matcher(polynomialString);
        boolean validInput = false;
        while (matcher.find()) {
            validInput = true;
            String term = matcher.group();
            int coefficient;
            int exponent = 0;
            try {
                if (term.contains("x")) {
                    int xIndex = term.indexOf("x");
                    if (xIndex == 0 || term.charAt(xIndex - 1) == '+' || term.charAt(xIndex - 1) == '-') {
                        coefficient = (term.charAt(0) == '-') ? -1 : 1;
                    } else {
                        coefficient = Integer.parseInt(term.substring(0, xIndex));
                    }
                    if (term.contains("^")) {
                        exponent = Integer.parseInt(term.substring(term.indexOf("^") + 1));
                    } else {
                        exponent = 1;
                    }
                } else {
                    coefficient = Integer.parseInt(term);
                }
                polynomial.addTerm(coefficient, exponent);
            } catch (NumberFormatException e) {
                view.setResult("<html>Error: Invalid polynomial format <br>Expected format:[sign][coefficient]x^[exponent]</html>");
                return null;
            }
        }
        if (!validInput) {
            view.setResult("<html>Error: Invalid polynomial format <br><br>Expected format:<br>[sign][coefficient]x^[exponent]</html>");
            return null;
        }
        return polynomial;
    }
}
