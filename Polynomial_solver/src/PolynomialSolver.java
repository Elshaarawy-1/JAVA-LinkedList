import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


interface IPolynomialSolver {
    /**
    * Set polynomial terms (coefficients & exponents)
    * @param poly: name of the polynomial
    * @param terms: array of [coefficients][exponents]
    */
    void setPolynomial(char poly, int[][] terms);
  
    /**
    * Print the polynomial in ordered human readable representation
    * @param poly: name of the polynomial
    * @return: polynomial in the form like 27x^2+x-1
    */
    String print(char poly);
  
    /**
    * Clear the polynomial
    * @param poly: name of the polynomial
    */
      void clearPolynomial(char poly);
  
    /**
    * Evaluate the polynomial
    * @param poly: name of the polynomial
    * @param value: the polynomial constant value
    * @return the value of the polynomial
    */
    float evaluatePolynomial(char poly, float value);
  
    /**
    * Add two polynomials
    * @param poly1: first polynomial
    * @param poly2: second polynomial
    * @return the result polynomial
    */
    int[][] add(char poly1, char poly2);
  
    /**
    * Subtract two polynomials
    * @param poly1: first polynomial
    * @param poly2: second polynomial
    * @return the result polynomial*/
    int[][] subtract(char poly1, char poly2);
  
    /**
    * Multiply two polynomials
    * @param poly1: first polynomial
    * @param poly2: second polynomial
    * @return: the result polynomial
    */
    int[][] multiply(char poly1, char poly2);
}

class Term{
    int exponent;
    int coefficient;
}

public class PolynomialSolver implements IPolynomialSolver{
    ILinkedList A = new SingleLinkedList();
    ILinkedList B = new SingleLinkedList();
    ILinkedList C = new SingleLinkedList();
    ILinkedList R = new SingleLinkedList();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String sin = sc.nextLine().replaceAll("\\[|\\]", "");
        String[] s = sin.split(", ");;
        int[][] arr = new int[2][s.length];
        if (s.length == 1 && s[0].isEmpty())
            arr = new int[][]{};
        else {
            for(int i = 0; i < s.length; ++i){
                arr[0][i] = s.length - i -1;
                arr[1][i] = Integer.parseInt(s[i]);
            }
        }

        PolynomialSolver problem = new PolynomialSolver();
        problem.setPolynomial('A', arr);
        String output = problem.print('A');
        System.out.println(output);
        problem.clearPolynomial('A');
        output = problem.print('A');
        System.out.println(output);

        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }

    @Override
    public void setPolynomial(char poly, int[][] terms) {
        // TODO Auto-generated method stub
        ILinkedList polylinked = new SingleLinkedList();
        switch(poly){
            case 'A':
                polylinked = A;
                break;
            case 'B':
                polylinked = B;
                break;
            case 'C':
                polylinked = C;
                break;
            default:
                break;
        }
        for (int i = 0; i < terms[0].length; i++) {
            Term newTerm = new Term();
            newTerm.exponent = terms[0][i];
            newTerm.coefficient = terms[1][i];
            polylinked.add(newTerm);
            // System.out.println(polylinked.get(i));
        }        
    }

    public String print(char poly) {
        ILinkedList polylinked = new SingleLinkedList();
        String result = new String();
        switch(poly){
            case 'A':
                polylinked = A;
                break;
            case 'B':
                polylinked = B;
                break;
            case 'C':
                polylinked = C;
                break;
            default:
                break;
        }
        for (int i = 0; i < polylinked.size(); i++) {
            Term ind = (Term)polylinked.get(i);
            if (ind.coefficient == 0) {
                continue;
            }
            else{
                if (ind.exponent == 0) {
                    System.out.println(result.length());
                    if (result.length() != 0 && ind.coefficient > 0) result += "+"; 
                    result += ind.coefficient;
                }
                else{
                    if (ind.coefficient == 1) {
                        if (i != 0) result += "+";
                        result = result + "x";
                    }
                    else{
                        if (ind.coefficient > 0 && i != 0) {
                            result = result + "+" + ind.coefficient + "x";
                        }
                        else
                        {
                            result = result + ind.coefficient + "x";

                        }
                    }
                    if (ind.exponent != 1) result += ("^" + ind.exponent);
                }
            }
        }
        return result;
    }

    @Override
    public void clearPolynomial(char poly) {
        ILinkedList polylinked = new SingleLinkedList();
        switch(poly){
            case 'A':
                polylinked = A;
                break;
            case 'B':
                polylinked = B;
                break;
            case 'C':
                polylinked = C;
                break;
            default:
                break;
        }
        polylinked.clear();
    }

    @Override
    public float evaluatePolynomial(char poly, float value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'evaluatePolynomial'");
    }

    @Override
    public int[][] add(char poly1, char poly2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public int[][] subtract(char poly1, char poly2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'subtract'");
    }

    @Override
    public int[][] multiply(char poly1, char poly2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'multiply'");
    }
}



