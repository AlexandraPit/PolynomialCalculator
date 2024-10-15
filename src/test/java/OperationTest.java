import org.example.control.Operations;
import org.example.model.Polynomial;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class OperationTest {
    @Test
    public void testAddition() {
        Operations operations = new Operations();

        // Create instances of Polynomial representing the polynomials
        Polynomial poly1 = new Polynomial();
        poly1.addTerm(1, 2);  // x^2
        poly1.addTerm(3, 1);  // 3x

        Polynomial poly2 = new Polynomial();
        poly2.addTerm(1, 3);  // x^3
        poly2.addTerm(2, 2);  // 2x^2

        // Call the add method with the Polynomial instances
        Polynomial result = operations.add(poly1, poly2);

        // Define the expected result polynomial
        Polynomial expected = new Polynomial();
        expected.addTerm(1, 3);  // x^3
        expected.addTerm(3, 2);  // 3x^2
        expected.addTerm(3, 1);  // 3x

        // Assert that the result polynomial matches the expected polynomial
        assertEquals(expected, result);
    }

    @Test
    public void testSubtraction() {
        Operations operations = new Operations();
        
        Polynomial poly1 = new Polynomial();
        poly1.addTerm(1, 2);  // x^2
        poly1.addTerm(3, 1);  // 3x

        Polynomial poly2 = new Polynomial();
        poly2.addTerm(1, 3);  // x^3
        poly2.addTerm(2, 2);  // 2x^2

        Polynomial result = operations.subtract(poly1, poly2);

        Polynomial expected = new Polynomial();
        expected.addTerm(-1, 3);  // -x^3
        expected.addTerm(-1, 2);  // -x^2
        expected.addTerm(3, 1);  // 3x

        assertEquals(expected, result);
    }

    @Test
    public void testMultiplication() {
        Operations operations = new Operations();

        // Create instances of Polynomial representing the polynomials
        Polynomial poly1 = new Polynomial();
        poly1.addTerm(1, 2);  // x^2
        poly1.addTerm(3, 1);  // 3x

        Polynomial poly2 = new Polynomial();
        poly2.addTerm(1, 3);  // x^3
        poly2.addTerm(2, 2);  // 2x^2

        Polynomial result = operations.multiply(poly1, poly2);

        Polynomial expected = new Polynomial();
        expected.addTerm(1, 5);  // x^5
        expected.addTerm(5, 4);  // 5x^4
        expected.addTerm(6, 3);  // 6x^3

        assertEquals(expected, result);
    }
    @Test
    public void testDivision() {
        Operations operations = new Operations();

        Polynomial dividend = new Polynomial();
        dividend.addTerm(2, 3);  // 2x^3
        dividend.addTerm(4, 2);  // 4x^2
        dividend.addTerm(6, 1);  // 6x
        dividend.addTerm(8, 0);  // 8

        Polynomial divisor = new Polynomial();
        divisor.addTerm(1, 1);   // x

        List<Polynomial> result = operations.divide(dividend, divisor);


        Polynomial expectedQuotient = new Polynomial();
        expectedQuotient.addTerm(2, 2);  // 2x^2
        expectedQuotient.addTerm(4, 1);  // 4x
        expectedQuotient.addTerm(6, 0);  // 6

        Polynomial expectedRemainder = new Polynomial();
        expectedRemainder.addTerm(8,0);

        assertEquals(expectedQuotient, result.get(1));
        assertEquals(expectedRemainder, result.get(0));
    }

    @Test
    public void testDerivative() {
        Operations operations = new Operations();

        Polynomial poly1 = new Polynomial();
        poly1.addTerm(1, 2);  // x^2
        poly1.addTerm(3, 1);  // 3x


        Polynomial result = operations.derivative(poly1);

        // Define the expected result polynomial
        Polynomial expected = new Polynomial();
        expected.addTerm(2, 1);  // 2x
        expected.addTerm(3, 0);  // 3

        assertEquals(expected, result);
    }

    @Test
    public void testIntegration() {
        Operations operations = new Operations();

        Polynomial poly1 = new Polynomial();
        poly1.addTerm(3, 2);  // 3x^2
        poly1.addTerm(2, 1);  // 2x

        Polynomial result = operations.integrate(poly1);

        Polynomial expected = new Polynomial();
        expected.addTerm(1, 3);  // x^3
        expected.addTerm(1, 2);  // x^2

        assertEquals(expected, result);
    }
}
