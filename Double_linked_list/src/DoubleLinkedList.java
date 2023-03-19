import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface ILinkedList {
    /**
     * Inserts a specified element at the specified position in the list.
     *
     * @param index
     * @param element
     */
    void add(int index, Object element);

    /**
     * Inserts the specified element at the end of the list.
     *
     * @param element
     */
    void add(Object element);

    /**
     * @param index
     * @return the element at the specified position in this list.
     */
    Object get(int index);

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     *
     * @param index
     * @param element
     */
    void set(int index, Object element);

    /**
     * Removes all of the elements from this list.
     */
    void clear();

    /**
     * @return true if this list contains no elements.
     */
    boolean isEmpty();

    /**
     * Removes the element at the specified position in this list.
     *
     * @param index
     */
    void remove(int index);

    /**
     * @return the number of elements in this list.
     */
    int size();

    /**
     * @param fromIndex
     * @param toIndex
     * @return a view of the portion of this list between the specified fromIndex and toIndex, inclusively.
     */
    ILinkedList sublist(int fromIndex, int toIndex);

    /**
     * @param o
     * @return true if this list contains an element with the same value as the specified element.
     */
    boolean contains(Object o);
}

class Node {
    Object item;
    Node next;
    Node previous;
}

public class DoubleLinkedList implements ILinkedList {
    Node head = null, tail = null;
    int length = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String sin = sc.nextLine().replaceAll("\\[|\\]", "");
        String[] s = sin.split(", ");
        int[] arr = new int[s.length];
        if (s.length == 1 && s[0].isEmpty())
            arr = new int[]{};
        else {
            for (int i = 0; i < s.length; ++i)
                arr[i] = Integer.parseInt(s[i]);
        }
        String operation = sc.next();
        ILinkedList sinlinkedlist = new DoubleLinkedList();
        for (int i = 0; i < arr.length; i++) {
            sinlinkedlist.add(arr[i]);
        }
        try {
            if (operation.equals("add") || operation.equals("addToIndex") || operation.equals("set") || operation.equals("clear") || operation.equals("remove")) {
                switch (operation) {
                    case "add":
                        int addvalue = sc.nextInt();
                        sinlinkedlist.add(addvalue);
                        break;
                    case "addToIndex":
                        int index = sc.nextInt();
                        int value = sc.nextInt();
                        sinlinkedlist.add(index, value);
                        break;
                    case "set":
                        int setindex = sc.nextInt();
                        int newvalue = sc.nextInt();
                        sinlinkedlist.set(setindex, newvalue);
                        break;
                    case "clear":
                        sinlinkedlist.clear();
                        break;
                    case "remove":
                        int remindex = sc.nextInt();
                        sinlinkedlist.remove(remindex);
                        break;
                    default:
                        System.out.println("Error");
                        break;
                }
                printlinked(sinlinkedlist);
            } else {
                switch (operation) {
                    case "get":
                        int getinvalue = sc.nextInt();
                        System.out.println(sinlinkedlist.get(getinvalue));
                        break;
                    case "isEmpty":
                        if (sinlinkedlist.isEmpty()) {
                            System.out.println("True");
                        } else {
                            System.out.println("False");
                        }
                        break;
                    case "contains":
                        int checkcontain = sc.nextInt();
                        if (sinlinkedlist.contains(checkcontain)) {
                            System.out.println("True");
                        } else {
                            System.out.println("False");
                        }
                        break;
                    case "sublist":
                        ILinkedList sublist = new DoubleLinkedList();
                        int startindex = sc.nextInt();
                        int endindex = sc.nextInt();
                        sublist = sinlinkedlist.sublist(startindex, endindex);
                        printlinked(sublist);
                        break;
                    case "size":
                        System.out.println(sinlinkedlist.size());
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    static void printlinked(ILinkedList linkedList) {
        System.out.print("[");
        for (int i = 0; i < linkedList.size(); i++) {
            System.out.print(linkedList.get(i));
            if (i != linkedList.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    @Override
    public void add(int index, Object element) {
        if (index < 0 || index > length)
            throw new RuntimeException("Error");
        else {
            Node newNode = new Node();
            newNode.item = element;
            if (index == length) {
                add(element);
                return;
            } else if (index == 0) {
                newNode.next = head;
                newNode.previous = null;
                head = newNode;
            } else {
                Node curr = getNode(index);
                newNode.next = curr;
                newNode.previous = curr.previous;
                curr.previous.next = newNode;
                curr.previous = newNode;
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
            newNode.previous = null;
        } else {
            tail.next = newNode;
            newNode.next = null;
            newNode.previous = tail;
            tail = newNode;
        }
        length++;
    }

    @Override
    public Object get(int index) {
        return getNode(index).item;
    }

    public Node getNode(int index) {
        if (index < 0 || index >= length)
            throw new RuntimeException("Error");
        Node curr;
        if (index <= length / 2) {
            curr = head;

            for (int i = 0; i < index; i++)
                curr = curr.next;
        } else {
            curr = tail;
            for (int i = 0; i < length - index - 1; i++)
                curr = curr.previous;
        }
        return curr;
    }

    @Override
    public void set(int index, Object element) {
        getNode(index).item = element;
    }

    @Override
    public void clear() {
        Node curr = new Node();
        while (head != null) {
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

        Node curr = getNode(index);
        if (index == 0) {
            head = head.next;
            if(curr.next != null){
                curr.next.previous = null;
            }
        } else if (index == length - 1) {
            tail = tail.previous;
            curr.previous.next = null;
        } else {
            curr.previous.next = curr.next;
            curr.next.previous = curr.previous;
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
        else {
            ILinkedList sub = new DoubleLinkedList();
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