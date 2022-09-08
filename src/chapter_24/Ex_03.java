package chapter_24;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;

public class Ex_03 {
    public static void main(String[] args) {
        // Create a list for strings
        TwoWayLinkedList<String> list = new TwoWayLinkedList<>();

        // Add elements to the list
        list.add("America"); // Add it to the list
        System.out.println("(1) " + list);

        list.add(0, "Canada"); // Add it to the beginning of the list
        System.out.println("(2) " + list);

        list.add("Russia"); // Add it to the end of the list
        System.out.println("(3) " + list);

        list.addLast("France"); // Add it to the end of the list
        System.out.println("(4) " + list);

        list.add(2, "Germany"); // Add it to the list at index 2
        System.out.println("(5) " + list);

        list.add(5, "Norway"); // Add it to the list at index 5
        System.out.println("(6) " + list);

        list.add(0, "Poland"); // Same as list.addFirst("Poland")
        System.out.println("(7) " + list);

        // Remove elements from the list
        list.remove(0); // Same as list.remove("Australia") in this case
        System.out.println("(8) " + list);

        list.remove(2); // Remove the element at index 2
        System.out.println("(9) " + list);

        list.remove(list.size() - 1); // Remove the last element
        System.out.print("(10) " + list + "\n(11) ");


        for (String s : list)
            System.out.print(s.toUpperCase() + " ");

        System.out.print("\n(12) ");
        ListIterator<String> iterator = list.listIterator(1);
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }

        iterator.add("Japan");
        System.out.print("\n(13) ");
        while (iterator.hasPrevious()) {
            System.out.print(iterator.previous() + " ");
        }

        iterator.remove();
        System.out.print("\n(14) ");
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }

        iterator.set("Nippon");
        System.out.print("\n(15) ");
        while (iterator.hasPrevious()) {
            System.out.print(iterator.previous() + " ");
        }

        list.clear();
        System.out.println("\nAfter clearing the list, the list size is "
                + list.size());


        Integer[] integers = new Integer[]{1, 2, 3, 4, 3, 1, 0, 5};
        TwoWayLinkedList<Integer> integerTwoWayLinkedList = new TwoWayLinkedList<>(integers);
        ListIterator<Integer> integerListIterator = integerTwoWayLinkedList.listIterator();
        integerListIterator.next();
        integerListIterator.remove();
        for (Integer integer : integerTwoWayLinkedList) {
            System.out.print(integer + " ");
        }
        System.out.println("Index of 1: " + integerTwoWayLinkedList.indexOf(1));
        System.out.println("LastIndex of 1: " + integerTwoWayLinkedList.lastIndexOf(1));
        System.out.println("List contains 3? " + integerTwoWayLinkedList.contains(3));
        System.out.println("List contains 6? " + integerTwoWayLinkedList.contains(6));
        integerTwoWayLinkedList.set(2, 6);
        System.out.println("List contains 6 now? " + integerTwoWayLinkedList.contains(6));
    }
}

class TwoWayLinkedList<E> implements ExMyList<E> {
    private Node<E> head, tail;
    private int size = 0;

    /** Create an empty list */
    public TwoWayLinkedList() {
    }

    /** Create a list from an array of objects */
    public TwoWayLinkedList(E[] objects) {
        this.addAll(Arrays.asList(objects));
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        else if (index == 0) {
            return head.element;
        }
        else if (index == size - 1) {
            return tail.element;
        }

        int i = 1;
        Node<E> current = head;
        while (i++ < index) {
            current = current.next;
        }
        return current.next.element; // Cheat one iteration with this
    }

    /** Return the head element in the list */
    public E getFirst() {
        if (size == 0) {
            return null;
        }
        else {
            return head.element;
        }
    }

    /** Return the last element in the list */
    public E getLast() {
        if (size == 0) {
            return null;
        }
        else {
            return tail.element;
        }
    }

    @Override
    public void add(int index, E o) {
        if (index == 0) {
            addFirst(o);
        }
        else if (index >= size) {
            addLast(o);
        }
        else {
            Node<E> current = head;
            for (int i = 1; i < index; i++) {
                current = current.next;
            }
            Node<E> temp = current.next;
            current.next = new Node<>(o);
            (current.next).next = temp;
            (current.next).previous = current;
            temp.previous = current.next;
            size++;
        }
    }

    /** Add an element to the beginning of the list */
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e); // Create a new node

        if (head == null) {
            head = tail = newNode; // The new node is the only node in the list
        } else {
            newNode.next = head; // link the new node to the old head
            head.previous = newNode; // link the old head to the new node
            head = newNode; // head now points to the new node
        }
        size++; // Increase list size
    }

    /** Add an element to the end of the list */
    public void addLast(E e) {
        Node<E> newNode = new Node<>(e); // Create a new for element e

        if (tail == null) {
            head = tail = newNode; // The new node is the only node in the list
        } else {
            newNode.previous = tail; // Link the old tail to the new node
            tail.next = newNode; // Link the new node with the last node
            tail = newNode; // tail now points to the new node
        }
        size++; // Increase size
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        else if (index == 0) {
            return removeFirst();
        }
        else if (index == size - 1) {
            return removeLast();
        }
        else {
            Node<E> previous = head;

            for (int i = 1; i < index; i++) {
                previous = previous.next;
            }

            Node<E> current = previous.next;
            previous.next = current.next;
            (current.next).previous = previous;
            size--;
            return current.element;
        }
    }

    /** Remove the head node and
     *  return the object that is contained in the removed node. */
    public E removeFirst() {
        if (size == 0) {
            return null;
        } else if (size == 1) {
            E temp = head.element;
            clear();
            return temp;
        } else {
            E temp = head.element;
            head = head.next;
            head.previous = null;
            size--;
            return temp;
        }
    }

    /** Remove the last node and
     * return the object that is contained in the removed node. */
    public E removeLast() {
        if (size == 0) {
            return null;
        } else if (size == 1) {
            E temp = tail.element;
            clear();
            return temp;
        } else {
            E temp = tail.element;
            tail = tail.previous;
            tail.next = null;
            size--;
            return temp;
        }
    }

    @Override
    public int indexOf(Object e) {
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            if (current.element.equals(e))
                return i;
            current = current.next;
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Node<E> current = tail;
        for (int i = size - 1; i >= 0; i--) {
            if (current.element.equals(o))
                return i;
            current = current.previous;
        }

        return -1;
    }


    @Override
    public E set(int index, E o) {
        if (index < 0 || index > size - 1)
            return null;

        Node<E> current = head;
        for (int i = 0; i < index; i++)
            current = current.next;

        E temp =  current.element;
        current.element = o;

        return temp;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(Object e) {
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            if (current.element.equals(e))
                return true;
            current = current.next;
        }
        return false;
    }

    /** Override toString() to return elements in the list */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");

        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            assert current != null;
            result.append(current.element);
            current = current.next;
            if (current != null) {
                result.append(", "); // Separate two elements with a comma
            }
            else {
                result.append("]"); // Insert the closing ] in the string
            }
        }

        return result.toString();
    }

    @NotNull
    @Override
    public Iterator<E> iterator() {
        return new TwoWayLinkedListIterator();
    }

    public ListIterator<E> listIterator() {
        return new TwoWayLinkedListIterator();
    }

    public ListIterator<E> listIterator(int index) {
        return new TwoWayLinkedListIterator(index);
    }

    private class TwoWayLinkedListIterator implements ListIterator<E> {
        private int index = 0;
        private Node<E> next = head, previous = null;
        private boolean nextCalled = false, previousCalled = false;

        public TwoWayLinkedListIterator() {

        }

        public TwoWayLinkedListIterator(int index) {
            this.index = index;
            next = head;
            for (int i = 0; i < index; i++) {
                next = next.next;
            }
            if (next != null) previous = next.previous;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public E next() {
            if (hasNext()) {
                nextCalled = true;
                previousCalled = false;
                // Shift cursor to the right
                index++;
                previous = next;
                next = next.next;
                return previous.element;
            }
            return null;
        }

        @Override
        public boolean hasPrevious() {
            return previous != null;
        }

        @Override
        public E previous() {
            if (hasPrevious()) {
                previousCalled = true;
                nextCalled = false;
                // Shift cursor to the left
                index--;
                next = previous;
                previous = previous.previous;
                return next.element;
            }
            return null;
        }

        @Override
        public int nextIndex() {
            return index;
        }

        @Override
        public int previousIndex() {
            return index - 1;
        }

        @Override
        public void remove() {
            if (nextCalled) {
                next = previous;
                previous = previous.previous;
                TwoWayLinkedList.this.remove(--index);
                nextCalled = false;
            }
            else if (previousCalled) {
                previous = next;
                next = next.next;
                TwoWayLinkedList.this.remove(index);
                previousCalled = false;
            } else {
                throw new IllegalStateException();
            }

        }

        @Override
        public void set(E e) {
            if (nextCalled) {
                previous.element = e;
            }
            else if (previousCalled) {
                next.element = e;
            } else {
                throw new IllegalStateException();
            }
        }

        @Override
        public void add(E e) {
            nextCalled = previousCalled = false;
            TwoWayLinkedList.this.add(index++, e);
            previous = previous.next;
        }
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    private static class Node<E> {
        E element;
        Node<E> next, previous;

        public Node(E e) {
            element = e;
        }
    }
}
