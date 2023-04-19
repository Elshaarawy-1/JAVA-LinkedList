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


public class SingleLinkedList implements ILinkedList {
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
                if (index == length - 1) tail = curr;
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
