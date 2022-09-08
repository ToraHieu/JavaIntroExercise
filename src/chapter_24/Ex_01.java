package chapter_24;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Ex_01 {
    public static void main(String[] args) {
        new Ex_01();
    }

    public Ex_01() {
        Scanner input = new Scanner(System.in);
        String[] name1 = new String[5];
        String[] name2 = new String[5];
        String[] name3 = new String[2];
        System.out.print("Enter five strings for array name1 separated by space: ");
        for (int i = 0; i < 5; i++) {
            name1[i] = input.next();
        }

        System.out.print("Enter five strings for array name2 separated by space: ");
        for (int i = 0; i < 5; i++) {
            name2[i] = input.next();
        }

        System.out.print("Enter two strings for array name3 separated by space: ");
        for (int i = 0; i < 2; i++) {
            name3[i] = input.next();
        }

        ExMyList<String> list1 = new MyTestArrayList<>(name1);
        ExMyList<String> list2 = new MyTestArrayList<>(name2);
        System.out.println("list1: " + list1);
        System.out.println("list2: " + list2);
        list1.addAll(list2);
        System.out.println("After addAll: list1 is " + list1 + "\n");

        list1 = new MyTestArrayList<>(name1);
        list2 = new MyTestArrayList<>(name2);
        System.out.println("list1: " + list1);
        System.out.println("list2: " + list2);
        list1.removeAll(list2);
        System.out.println("After removeAll: list1 is " + list1 + "\n");

        list1 = new MyTestArrayList<>(name1);
        list2 = new MyTestArrayList<>(name2);
        System.out.println("list1: " + list1);
        System.out.println("list2: " + list2);
        list1.retainAll(list2);
        System.out.println("After retainAll: list1 is " + list1 + "\n");

        list1 = new MyTestArrayList<>(name1);
        list2 = new MyTestArrayList<>(name2);
        list2.retainAll(list1);
        System.out.println("list1: " + list1);
        System.out.println("list2: " + list2);
        System.out.println("list1 contains all list2? " + list1.containsAll(list2) + "\n");

        list1 = new MyTestArrayList<>(name1);
        list2 = new MyTestArrayList<>(name2);
        System.out.println("list1: " + list1);
        System.out.println("list2: " + list2);
        System.out.println("list1 contains all list2? " + list1.containsAll(list2) + "\n");

        Object[] name4 = list1.toArray();
        System.out.print("name4: ");
        for (Object e : name4) {
            System.out.print(e + " ");
        }

        String[] name5 = new String[list1.size()];
        String[] name6 = list1.toArray(name5);
        System.out.print("\nname6: ");
        for (Object e : name6) {
            System.out.print(e + " ");
        }
    }
}

interface ExMyList<E> extends Collection<E> {
    /**
     * Add a new element at the specified index in this list
     */
    void add(int index, E e);

    /**
     * Return the element from this list at the specified index
     */
    E get(int index);

    /**
     * Return the index of the first matching element in this list.
     * Return -1 if no match.
     */
    int indexOf(Object e);

    /**
     * Return the index of the last matching element in this list
     * Return -1 if no match.
     */
    int lastIndexOf(E e);

    /**
     * Remove the element at the specified position in this list
     * Shift any subsequent elements to the left.
     * Return the element that was removed from the list.
     */
    E remove(int index);

    /**
     * Replace the element at the specified position in this list
     * with the specified element and returns the new set.
     */
    E set(int index, E e);

    /**
     * Add a new element at the end of this list
     */
    @Override
    default boolean add(E e) {
        add(size(), e);
        return true;
    }

    /**
     * Return true if this list contains no elements
     */
    @Override
    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Remove the first occurrence of the element e
     * from this list. Shift any subsequent elements to the left.
     * Return true if the element is removed.
     */
    @Override
    default boolean remove(Object e) {
        if (indexOf(e) >= 0) {
            remove(indexOf(e));
            return true;
        } else
            return false;
    }

    @Override
    default boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!this.contains(o))
                return false;
        }

        return true;
    }

    @Override
    default boolean addAll(@NotNull Collection<? extends E> c) {
        boolean result = false;
        try {
            for (E o : c) {
                this.add(o);
                result = true;
            }
        } catch (Exception ex) {
            return result;
        }
        return result;
    }

    @Override
    default boolean removeAll(@NotNull Collection<?> c) {
        boolean result = false;
        try {
            for (Object o : c) {
                result = this.remove(o) || result;
            }
        } catch (Exception ex) {
            return result;
        }
        return result;
    }

    @Override
    default boolean retainAll(@NotNull Collection<?> c) {
        boolean result = false;
        try {
            Iterator<E> iterator = this.iterator();
            while (iterator.hasNext()) {
                E e = iterator.next();
                if (!c.contains(e)) {
                    iterator.remove();
                    result = true;
                }
            }
        } catch (Exception ex) {
            return result;
        }
        return result;
    }

    @NotNull
    @Override
    default Object[] toArray() {
        Object[] array = new Object[this.size()];
        Iterator<E> iterator = this.iterator();
        for (int i = 0; i < array.length; i++) {
            array[i] = iterator.next();
        }

        return array;
    }

    @NotNull
    @Override
    default <T> T[] toArray(@NotNull T[] array) {
        if (this.size() > array.length) {
            //noinspection unchecked
            array = (T[]) new Object[this.size()];
        }
        Iterator<E> iterator = this.iterator();
        for (int i = 0; i < array.length; i++) {
            //noinspection unchecked
            array[i] = (T)iterator.next();
        }

        return array;
    }
}

class MyTestArrayList<E> implements ExMyList<E> {
    public static final int INITIAL_CAPACITY = 16;
    @SuppressWarnings("unchecked")
    private E[] data = (E[]) new Object[INITIAL_CAPACITY];
    private int size = 0; // Number of elements in the list

    /**
     * Create an empty list
     */
    public MyTestArrayList() {
    }

    /**
     * Create a list from an array of objects
     */
    public MyTestArrayList(E[] objects) {
        for (E object : objects) add(object); // Warning: don’t use super(objects)!
    }

    /** Add a new element at the specified index */
    @Override
    public void add(int index, E e) {
        ensureCapacity();

        // Move the elements to the right after the specified index
        for (int i = size - 1; i >= index; i--)
            data[i + 1] = data[i];

        // Insert new element to data[index]
        data[index] = e;

        // Increase size by 1
        size++;
    }

    /**
     * Create a new larger array, double the current size + 1
     */
    private void ensureCapacity() {
        if (size >= data.length) {
            @SuppressWarnings("unchecked") E[] newData = (E[]) (new Object[size * 2 + 1]);
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
    }

    /** Clear the list */
    @Override
    public void clear() {
        //noinspection unchecked
        data = (E[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    /** Return true if this list contains the element */
    @Override
    public boolean contains(Object e) {
        for (int i = 0; i < size; i++)
            if (e.equals(data[i])) return true;

        return false;
    }

    /** Return the element at the specified index */
    @Override
    public E get(int index) {
        checkIndex(index);
        return data[index];
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException
                    ("Index: " + index + ", Size: " + size);
    }

    /** Return the index of the first matching element
     *  in this list. Return -1 if no match. */
    @Override
    public int indexOf(Object e) {
        for (int i = 0; i < size; i++)
            if (e.equals(data[i])) return i;

        return -1;
    }

    /** Return the index of the last matching element
     *  in this list. Return -1 if no match. */
    @Override
    public int lastIndexOf(E e) {
        for (int i = size - 1; i >= 0; i--)
            if (e.equals(data[i])) return i;

        return -1;
    }

    /** Remove the element at the specified position
     *  in this list. Shift any subsequent elements to the left.
     *  Return the element that was removed from the list. */
    @Override
    public E remove(int index) {
        checkIndex(index);

        E e = data[index];

        // Shift data to the left
        for (int j = index; j < size - 1; j++)
            data[j] = data[j + 1];

        data[size - 1] = null; // This element is now null

        // Decrement size
        size--;

        return e;
    }

    /** Replace the element at the specified position
     *  in this list with the specified element. */
    @Override
    public E set(int index, E e) {
        checkIndex(index);
        E old = data[index];
        data[index] = e;
        return old;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");

        for (int i = 0; i < size; i++) {
            result.append(data[i]);
            if (i < size - 1) result.append(", ");
        }

        return result.toString() + "]";
    }

    /**
     * Trims the capacity to current size
     */
    public void trimToSize() {
        if (size != data.length) {
            @SuppressWarnings("unchecked") E[] newData = (E[]) (new Object[size]);
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        } // If size == capacity, no need to trim
    }

    /** Override iterator() defined in Iterable */
    @NotNull
    @Override
    public java.util.Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator
            implements java.util.Iterator<E> {
        private int current = 0; // Current index

        @Override
        public boolean hasNext() {
            return (current < size);
        }

        @Override
        public E next() {
            return data[current++];
        }

        @Override
        public void remove() {
            MyTestArrayList.this.remove(--current);
        }
    }

    /** Return the number of elements in this list */
    @Override
    public int size() {
        return size;
    }
}

