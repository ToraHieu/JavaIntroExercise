package chapter_27;

import chapter_26.AVLTree;

import java.util.Collection;

public class Ex_15 {
    public static void main(String[] args) {
        // Create a MyHashSet
        java.util.Collection<String> set = new Ex05HashSet<>();
        set.add("Smith");
        set.add("Anderson");
        set.add("Lewis");
        set.add("Cook");
        set.add("Smith");

        System.out.println("Elements in set: " + set);
        System.out.println("Number of elements in set: " + set.size());
        System.out.println("Is Smith in set? " + set.contains("Smith"));

        set.remove("Smith");
        System.out.print("Names in set in uppercase are ");
        for (String s: set)
            System.out.print(s.toUpperCase() + " ");

        set.clear();
        System.out.println("\nElements in set: " + set);
    }
}

class Ex15HashSet<E extends Comparable<E>> implements Collection<E> {
    // Define the default hash table size. Must be a power of 2
    private static final int DEFAULT_INITIAL_CAPACITY = 4;

    // Define the maximum hash table size. 1 << 30 is same as 2^30
    private static final int MAXIMUM_CAPACITY = 1 << 30;

    // Current hash table capacity. Capacity is a power of 2
    private int capacity;

    // Define default load factor
    private static final float DEFAULT_MAX_LOAD_FACTOR = 0.75f;

    // Specify a load factor threshold used in the hash table
    private float loadFactorThreshold;

    // The number of elements in the set
    private int size = 0;

    // Hash table is an array with each cell that is a linked list
    private AVLTree<E>[] table;

    /**
     * Construct a set with the default capacity and load factor
     */
    public Ex15HashSet() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
    }

    /**
     * Construct a set with the specified initial capacity and
     * default load factor
     */
    public Ex15HashSet(int initialCapacity) {
        this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
    }

    /**
     * Construct a set with the specified initial capacity
     * and load factor
     */
    public Ex15HashSet(int initialCapacity, float loadFactorThreshold) {
        if (initialCapacity > MAXIMUM_CAPACITY)
            this.capacity = MAXIMUM_CAPACITY;
        else
            this.capacity = trimToPowerOf2(initialCapacity);

        this.loadFactorThreshold = loadFactorThreshold;
        //noinspection unchecked
        table = new AVLTree[capacity];
    }

    /** Remove all elements from this set */
    @Override
    public void clear() {
        size = 0;
        removeElements();
    }

    /** Return true if the element is in the set */
    @Override
    public boolean contains(Object e) {
        int bucketIndex = hash(e.hashCode());
        if (table[bucketIndex] != null) {
            AVLTree<E> bucket = table[bucketIndex];
            return bucket.contains(e);
        }

        return false;
    }

    /** Add an element to the set */
    @Override
    public boolean add(E e) {
        if (contains(e)) // Duplicate element not stored
            return false;

        if (size + 1 > capacity * loadFactorThreshold) {
            if (capacity == MAXIMUM_CAPACITY)
                throw new RuntimeException("Exceeding maximum capacity");

            rehash();
        }

        int bucketIndex = hash(e.hashCode());

        // Create a linked list for the bucket if it is not created
        if (table[bucketIndex] == null) {
            table[bucketIndex] = new AVLTree<>();
        }

        // Add e to hashTable[index]
        table[bucketIndex].add(e);

        size++; // Increase size

        return true;
    }

    /** Remove the element from the set */
    @Override
    public boolean remove(Object e) {
        if (!contains(e))
            return false;

        int bucketIndex = hash(e.hashCode());

        // Create a linked list for the bucket if it is not created
        if (table[bucketIndex] != null) {
            AVLTree<E> bucket = table[bucketIndex];
            bucket.remove(e);
        }

        size--; // Decrease size

        return true;
    }

    /** Return true if the set contains no elements */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /** Return the number of elements in the set */
    @Override
    public int size() {
        return size;
    }

    /** Return an iterator for the elements in this set */
    @Override
    public java.util.Iterator<E> iterator() {
        return new Ex15HashSetIterator(this);
    }

    /**
     * Inner class for iterator
     */
    private class Ex15HashSetIterator implements java.util.Iterator<E> {
        // Store the elements in a list
        private java.util.ArrayList<E> list;
        private int current = 0; // Point to the current element in list
        private Ex15HashSet<E> set;

        /**
         * Create a list from the set
         */
        public Ex15HashSetIterator(Ex15HashSet<E> set) {
            this.set = set;
            list = setToList();
        }

        /** Next element for traversing? */
        @Override
        public boolean hasNext() {
            return current < list.size();
        }

        /** Get current element and move cursor to the next */
        @Override
        public E next() {
            return list.get(current++);
        }

        /** Remove the element returned by the last next() */
        @Override
        public void remove() {
            // Left as an exercise
            // You need to remove the element from the set
            // You also need to remove it from the list
        }
    }

    /**
     * Hash function
     */
    private int hash(int hashCode) {
        return hashCode & (capacity - 1);
    }

    /**
     * Return a power of 2 for initialCapacity
     */
    private int trimToPowerOf2(int initialCapacity) {
        int capacity = 1;
        while (capacity < initialCapacity) {
            capacity <<= 1;
        }

        return capacity;
    }

    /**
     * Remove all e from each bucket
     */
    private void removeElements() {
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                table[i].clear();
            }
        }
    }

    /**
     * Rehash the set
     */
    private void rehash() {
        java.util.ArrayList<E> list = setToList(); // Copy to a list
        capacity <<= 1; // Double capacity
        table = new AVLTree[capacity]; // Create a new hash table
        size = 0; // Reset size

        for (E element : list) {
            add(element); // Add from the old table to the new table
        }
    }

    /**
     * Copy elements in the hash set to an array list
     */
    private java.util.ArrayList<E> setToList() {
        java.util.ArrayList<E> list = new java.util.ArrayList<>();

        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                for (E e : table[i]) {
                    list.add(e);
                }
            }
        }

        return list;
    }

    @Override
    public String toString() {
        java.util.ArrayList<E> list = setToList();
        StringBuilder builder = new StringBuilder("[");

        // Add the elements except the last one to the string builder
        for (int i = 0; i < list.size() - 1; i++) {
            builder.append(list.get(i)).append(", ");
        }

        // Add the last element in the list to the string builder
        if (list.size() == 0)
            builder.append("]");
        else
            builder.append(list.get(list.size() - 1)).append("]");

        return builder.toString();
    }

    @Override
    public boolean addAll(Collection<? extends E> arg0) {
        // Left as an exercise
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> arg0) {
        // Left as an exercise
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> arg0) {
        // Left as an exercise
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> arg0) {
        // Left as an exercise
        return false;
    }

    @Override
    public Object[] toArray() {
        // Left as an exercise
        return null;
    }

    @Override
    public <T> T[] toArray(T[] arg0) {
        // Left as an exercise
        return null;
    }
}