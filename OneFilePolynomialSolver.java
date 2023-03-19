import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

import java.util.*;

interface ILinkedList {
/**
* Inserts a specified element at the specified position in the list.
* @param index
* @param element
*/
public void add(int index, Object element);
/**
* Inserts the specified element at the end of the list.
* @param element
*/
public void add(Object element);
/**
* @param index
* @return the element at the specified position in this list.
*/
public Object get(int index);

/**
* Replaces the element at the specified position in this list with the
* specified element.
* @param index
* @param element
*/
public void set(int index, Object element);
/**
* Removes all of the elements from this list.
*/
public void clear();
/**
* @return true if this list contains no elements.
*/
public boolean isEmpty();
/**
* Removes the element at the specified position in this list.
* @param index
*/
public void remove(int index);
/**
* @return the number of elements in this list.
*/
public int size();
/**
* @param fromIndex
* @param toIndex
* @return a view of the portion of this list between the specified fromIndex and toIndex, inclusively.
*/
public ILinkedList sublist(int fromIndex, int toIndex);
/**
* @param o
* @return true if this list contains an element with the same value as the specified element.
*/
public boolean contains(Object o);
}

class Node{
    Object item;
    Node next;
}


class SingleLinkedList implements ILinkedList {
    /* Implement your linked list class here*/
    Node head = null, tail = null;
    int length = 0;

    static void printlinked(ILinkedList linkedList){
        System.out.print("[");
        for (int i = 0; i < linkedList.size(); i++) {
            System.out.print(linkedList.get(i));
            if (i != linkedList.size() -1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    @Override
    public void add(int index, Object element) {
        if (index < 0 || index > length) 
            throw new RuntimeException("Error");
        else
        {
            Node newNode = new Node();
            newNode.item = element;
            if (index == length) {
                add(element);
            }
            else if (index == 0) {
                newNode.next = head;
                head = newNode;
            }
            else
            {
                Node curr = head;
                for (int i = 1; i < index; i++)
                    curr = curr.next;

                newNode.next = curr.next;
                curr.next = newNode;
            }
            length++;
        }
    }

    @Override
    public void add(Object element) {
        Node newNode = new Node();
        newNode.item = element;
        if (length == 0) {
            head = tail = newNode;
            newNode.next = null;
        }
        else
        {
            tail.next = newNode;
            newNode.next = null;
            tail = newNode;
        }
        length++;
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index >= length)
            throw new RuntimeException("Error");
        else{
            Node curr = head;
            for (int i = 0; i < index; i++) {
                curr = curr.next;
            }
            return curr.item;
        }
    }

    @Override
    public void set(int index, Object element) {
        if (index < 0 || index >= length)
            throw new RuntimeException("Error");
        else
        {
            Node curr = head;
            for (int i = 0; i < index; i++) {
                curr = curr.next;
            }
            curr.item = element;
        }
    }

    @Override
    public void clear() {
        Node curr = new Node();
        while(head != null){
            curr = head;
            head = null;
            head = curr.next;
        }
        length = 0;
    }

    @Override
    public boolean isEmpty() {
        return (length == 0);
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= length) 
            throw new RuntimeException("Error");
        else{
            Node curr = head;
            if (index == 0) {
                head = head.next ;
                curr = null;;
            }
            else
            {
                Node temp = new Node();
                for (int i = 1; i < index; i++)
                    curr = curr.next;
                temp = curr.next;
                curr.next = temp.next;
                temp = null;
            }
        }
        length--;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public ILinkedList sublist(int fromIndex, int toIndex) {
        if (fromIndex < 0 || fromIndex > length || toIndex < 0 || toIndex > length || toIndex < fromIndex) 
            throw new RuntimeException("Error");
        else
        {
            ILinkedList sub = new SingleLinkedList();
            for (int j = fromIndex; j <= toIndex; j++) {
                sub.add(get(j));
            }
        return sub;
        }
    }
    @Override
    public boolean contains(Object o) {
        Node curr = new Node();
        curr = head;
        for (int i = 0; i < length; i++) {
            if (o.equals(curr.item)) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }
}




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


    public void readPolynomial(char poly, Scanner sc){
        String sin = sc.nextLine().replaceAll("\\[|\\]", "");
        String[] s = sin.split(",");;
        int[][] arr = new int[2][s.length];
        if (s.length == 1 && s[0].isEmpty())
            arr = new int[][]{};
        else {
            for(int i = 0; i < s.length; ++i){
                arr[0][i] = s.length - i -1;
                arr[1][i] = Integer.parseInt(s[i]);
            }
        }

        setPolynomial(poly, arr);
    }

    public char readChar(Scanner sc){
        String polyName = sc.nextLine().trim();
        if (polyName.length() != 1 || polyName == "R"){
            throw new RuntimeException("Error");
        }
        return polyName.charAt(0);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        boolean input_loop = true;
        PolynomialSolver polynomialSolver = new PolynomialSolver();
        try {
            while(sc.hasNext()){
                int[][] result;
                char polyName;
                String output;
                String command = sc.nextLine();
                switch (command){
                    case "set":
                        polynomialSolver.readPolynomial(polynomialSolver.readChar(sc),sc);
                        continue;
                    case "print":
                        output = polynomialSolver.print(polynomialSolver.readChar(sc));
                        System.out.println(output);
                        continue;
                    case "add":
                        result = polynomialSolver.add(polynomialSolver.readChar(sc),polynomialSolver.readChar(sc));
                        polynomialSolver.setPolynomial('R', result);
                        output = polynomialSolver.print('R');
                        System.out.println(output);
                        break;
                    case "sub":
                        result = polynomialSolver.subtract(polynomialSolver.readChar(sc),polynomialSolver.readChar(sc));
                        polynomialSolver.setPolynomial('R', result);
                        output = polynomialSolver.print('R');
                        System.out.println(output);
                        break;
                    case "mult":
                        result = polynomialSolver.multiply(polynomialSolver.readChar(sc),polynomialSolver.readChar(sc));
                        polynomialSolver.setPolynomial('R', result);
                        output = polynomialSolver.print('R');
                        System.out.println(output);
                        break;
                    case "clear":
                        polyName = polynomialSolver.readChar(sc);
                        polynomialSolver.clearPolynomial(polyName);
                        output = polynomialSolver.print(polyName);
                        System.out.println(output);
                        break;
                    case "eval":
                        polyName = polynomialSolver.readChar(sc);
                        float c = Float.parseFloat(sc.nextLine());
                        float evalResult = polynomialSolver.evaluatePolynomial(polyName,c);
                        if(Math.abs(evalResult-(int)evalResult) < 1e-13)
                            System.out.println((int)evalResult);
                        else
                            System.out.println(evalResult);
                        break;
                    default:
                        throw new RuntimeException("Not a valid command");
                }
            }
        } catch (Exception e) {
            System.out.println("Error");
        }    
    }

    public ILinkedList getPolynomial(char poly){
        switch(poly){
            case 'A':
                return A;
            case 'B':
                return B;
            case 'C':
                return C;
            case 'R':
                return R;
            default:
                throw new RuntimeException("Error");
        }

    }
    @Override
    public void setPolynomial(char poly, int[][] terms) {
        ILinkedList polylinked = getPolynomial(poly);
        polylinked.clear();
        for (int i = 0; i < terms[0].length; i++) {
            Term newTerm = new Term();
            newTerm.exponent = terms[0][i];
            newTerm.coefficient = terms[1][i];
                polylinked.add(newTerm);
        }        
    }

    public String print(char poly) {
        ILinkedList polylinked = getPolynomial(poly);;
        String result = new String();

        if(polylinked.isEmpty()){
            result = "[]";
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
                        if (i != 0 && result.length() != 0) result += "+";
                        result = result + "x";
                    }
                    else{
                        if (ind.coefficient > 0 && i != 0) {
                            if (result.length() != 0) result += "+";
                            result += ind.coefficient + "x";
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
        getPolynomial(poly).clear();
    }

    @Override
    public float evaluatePolynomial(char poly, float value) {
        float evalresult = 0;
        ILinkedList polylinked = getPolynomial(poly);
        if(polylinked.isEmpty())
            throw new RuntimeException("Polynomial can't have size zero");
        for (int i = 0; i < polylinked.size(); i++) {
            Term ind = (Term)polylinked.get(i);
            evalresult += ind.coefficient * (Math.pow(value, ind.exponent));
        }
        return evalresult;
    }

    @Override
    public int[][] add(char poly1, char poly2) {

        ILinkedList polylinked1 = getPolynomial(poly1);
        ILinkedList polylinked2 = getPolynomial(poly2);
        if(polylinked1.size() == 0 || polylinked2.size() == 0)
            throw new RuntimeException("Polynomial can't have size zero");

        int size = Math.max(polylinked1.size(), polylinked2.size());
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
        ILinkedList polylinked = getPolynomial(poly2);
        for (int i = 0; i < polylinked.size(); i++) {
            ((Term)(polylinked.get(i))).coefficient *= -1;
        }
        int[][] subresult = add(poly1, poly2);
        for (int i = 0; i < polylinked.size(); i++) {
            ((Term)(polylinked.get(i))).coefficient *= -1;
        }
        return subresult;
    }

    public int[][] initPolyArray(int size){
        int[][] result = new int[2][size];
        for (int i = 0; i < size; i++) {
            result[0][i] = size-i-1;
            result[1][i] = 0;
        }
        return result;
    }
    @Override
    public int[][] multiply(char poly1, char poly2) {
        ILinkedList polyList1 = getPolynomial(poly1);
        ILinkedList polyList2 = getPolynomial(poly2);
        if(polyList1.size() == 0 || polyList2.size() == 0)
            throw new RuntimeException("Polynomial can't have size zero");

        // get max degree to initialize array
        int degree = 0;
        for (int i = 0; i< polyList1.size(); i++){
            for(int j = 0; j < polyList2.size(); j++){
                Term term1 = (Term)polyList1.get(i);
                Term term2 = (Term)polyList2.get(j);
                Term resultTerm = new Term();
                resultTerm.exponent = term1.exponent + term2.exponent;
                degree = Math.max(resultTerm.exponent,degree);
            }
        }

        int[][] result = initPolyArray(degree + 1);
        for (int i = 0; i< polyList1.size(); i++) {
            for (int j = 0; j < polyList2.size(); j++) {
                Term term1 = (Term)polyList1.get(i);
                Term term2 = (Term)polyList2.get(j);
                Term resultTerm = new Term();
                resultTerm.exponent = term1.exponent + term2.exponent;
                resultTerm.coefficient = term1.coefficient * term2.coefficient;
                result[1][degree-resultTerm.exponent] += resultTerm.coefficient;
            }
        }
        return result;
    }
}



