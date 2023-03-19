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
        
        
        String sin2 = sc.nextLine().replaceAll("\\[|\\]", "");
        String[] s2 = sin2.split(", ");;
        int[][] arr2 = new int[2][s2.length];
        if (s.length == 1 && s[0].isEmpty())
            arr2 = new int[][]{};
        else {
            for(int i = 0; i < s2.length; ++i){
                arr2[0][i] = s2.length - i -1;
                arr2[1][i] = Integer.parseInt(s2[i]);
            }
        }

        problem.setPolynomial('B', arr2);
        int[][] result = problem.subtract('A', 'B');
        problem.setPolynomial('R', result);
        String output = problem.print('R');
        System.out.println(output);



        // String output = problem.print('A');
        // System.out.println(output);
        // float testeval = problem.evaluatePolynomial('A', 5);
        // System.out.println(testeval);
        // problem.clearPolynomial('A');
        // output = problem.print('A');
        // System.out.println(output);

    }

    @Override
    public void setPolynomial(char poly, int[][] terms) {
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
            case 'R':
                polylinked = R;
                break;
            default:
                break;
        }
        for (int i = 0; i < terms[0].length; i++) {
            Term newTerm = new Term();
            newTerm.exponent = terms[0][i];
            newTerm.coefficient = terms[1][i];
            polylinked.add(newTerm);
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
            case 'R':
                polylinked = R;
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
        float evalresult = 0;
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
        for (int i = 0; i < polylinked.size(); i++) {
            Term ind = (Term)polylinked.get(i);
            evalresult += ind.coefficient * (Math.pow(value, ind.exponent));
        }
        return evalresult;
    }

    @Override
    public int[][] add(char poly1, char poly2) {
        ILinkedList polylinked1 = new SingleLinkedList();
        ILinkedList polylinked2 = new SingleLinkedList();
        switch(poly1){
            case 'A':
                polylinked1 = A;
                break;
            case 'B':
                polylinked1 = B;
                break;
            case 'C':
                polylinked1 = C;
                break;
            default:
                break;
        }
        switch(poly2){
            case 'A':
                polylinked2 = A;
                break;
            case 'B':
                polylinked2 = B;
                break;
            case 'C':
                polylinked2 = C;
                break;
            default:
                break;
        }
        int size = (polylinked1.size()>polylinked2.size())? polylinked1.size() : polylinked2.size();
        int [][] addresult = new int[2][size];
        for (int i = 0; i < size; i++) {
            addresult[0][i] = size -i -1;
        }

        if (polylinked1.size() > polylinked2.size()) {
            int index1 = polylinked1.size()-1, index2 = polylinked2.size() -1;
            while (index1 >= 0) {
                addresult[1][index1] = 0;
                if (index2 >= 0) {
                    addresult[1][index1] =  ((Term)polylinked2.get(index2)).coefficient;
                }
                addresult[1][index1] += ((Term)polylinked1.get(index1)).coefficient;
                index1--;
                index2--;
            }
        }
        else{
            int index1 = polylinked1.size()-1, index2 = polylinked2.size() -1;
            while (index2 >= 0) {
                addresult[1][index2] = 0;
                if (index1 >= 0) {
                    addresult[1][index2] =  ((Term)polylinked1.get(index1)).coefficient;
                }
                addresult[1][index2] += ((Term)polylinked2.get(index2)).coefficient;
                index1--;
                index2--;
            }
        }
        return addresult;
    }

    @Override
    public int[][] subtract(char poly1, char poly2) {
        ILinkedList polylinked = new SingleLinkedList();
        switch(poly2){
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
            ((Term)(polylinked.get(i))).coefficient *= -1;
        }
        int[][] subresult = add(poly1, poly2);
        for (int i = 0; i < polylinked.size(); i++) {
            ((Term)(polylinked.get(i))).coefficient *= -1;
        }
        return subresult;
    }

    @Override
    public int[][] multiply(char poly1, char poly2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'multiply'");
    }
}


