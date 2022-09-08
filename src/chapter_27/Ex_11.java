package chapter_27;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Ex_11 {
    public static void main(String[] args) {
        String[] name1 = {"Tom", "George", "Peter", "Jean", "Jane"};
        String[] name2 = {"Tom", "George", "Michael", "Michelle", "Daniel"};
        String[] name3 = {"Tom", "Peter"};
//        Scanner input = new Scanner(System.in);
//        String[] name1 = new String[5];
//        String[] name2 = new String[5];
//        String[] name3 = new String[2];
//        System.out.print("Enter five strings for array name1 separated by space: ");
//        for (int i = 0; i < 5; i++) {
//            name1[i] = input.next();
//        }
//
//        System.out.print("Enter five strings for array name2 separated by space: ");
//        for (int i = 0; i < 5; i++) {
//            name2[i] = input.next();
//        }
//
//        System.out.print("Enter two strings for array name2 separated by space: ");
//        for (int i = 0; i < 2; i++) {
//            name3[i] = input.next();
//        }

        Ex11HashSet<String> set1 = new Ex11HashSet<>(name1);
        Ex11HashSet<String> set2 = new Ex11HashSet<>(name2);
        System.out.println("set1:" + set1);
        System.out.println("set2:" + set2);
        set1.addAll(set2);
        System.out.println("After addAll:" + set1 + "\n");

        set1 = new Ex11HashSet<>(name1);
        set2 = new Ex11HashSet<>(name2);
        System.out.println("set1:" + set1);
        System.out.println("set2:" + set2);
        set1.removeAll(set2);
        System.out.println("After removeAll:" + set1 + "\n");

        set1 = new Ex11HashSet<>(name1);
        set2 = new Ex11HashSet<>(name2);
        System.out.println("set1:" + set1);
        System.out.println("set2:" + set2);
        set1.retainAll(set2);
        System.out.println("After retainAll:" + set1 + "\n");

        set1 = new Ex11HashSet<>(name1);
        set2 = new Ex11HashSet<>(name2);
        System.out.println("set1:" + set1);
        System.out.println("set2:" + set2);
        set1.retainAll(set2);
        System.out.println("set1 contains all set2? " + set1.containsAll(set2) + "\n");

        set1 = new Ex11HashSet<>(name1);
        set2 = new Ex11HashSet<>(name3);
        System.out.println("set1:" + set1);
        System.out.println("set2:" + set2);
        System.out.println("set1 contains all set2? " + set1.containsAll(set2) + "\n");

        Object[] name4 = set1.toArray();
        System.out.print("name4: ");
        for (Object e : name4) {
            System.out.print(e + " ");
        }

        String[] name5 = new String[set1.size()];
        String[] name6 = set1.toArray(name5);
        System.out.print("\nname6: ");
        for (Object e : name6) {
            System.out.print(e + " ");
        }
    }
}

class Ex11HashSet<E> implements Collection<E> {
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
    private LinkedList<E>[] table;

    /**
     * Construct a set with the default capacity and load factor
     */
    public Ex11HashSet() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
    }

    /**
     * Construct a set with the specified initial capacity and
     * default load factor
     */
    public Ex11HashSet(int initialCapacity) {
        this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
    }

    /**
     * Construct a set from a array
     */
    public Ex11HashSet(E[] list) {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
        this.addAll(Arrays.asList(list));
    }


    /**
     * Construct a set with the specified initial capacity
     * and load factor
     */
    public Ex11HashSet(int initialCapacity, float loadFactorThreshold) {
        if (initialCapacity > MAXIMUM_CAPACITY)
            this.capacity = MAXIMUM_CAPACITY;
        else
            this.capacity = trimToPowerOf2(initialCapacity);

        this.loadFactorThreshold = loadFactorThreshold;
        //noinspection unchecked
        table = new LinkedList[capacity];
    }

    /**
     * Remove all elements from this set
     */
    @Override
    public void clear() {
        size = 0;
        removeElements();
    }

    /**
     * Return true if the element is in the set
     */
    @Override
    public boolean contains(Object e) {
        int bucketIndex = hash(e.hashCode());
        if (table[bucketIndex] != null) {
            LinkedList<E> bucket = table[bucketIndex];
            return bucket.contains(e);
        }

        return false;
    }

    /**
     * Add an element to the set
     */
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
            table[bucketIndex] = new LinkedList<>();
        }

        // Add e to hashTable[index]
        table[bucketIndex].add(e);

        size++; // Increase size

        return true;
    }

    /**
     * Remove the element from the set
     */
    @Override
    public boolean remove(Object e) {
        if (!contains(e))
            return false;

        int bucketIndex = hash(e.hashCode());

        // Create a linked list for the bucket if it is not created
        if (table[bucketIndex] != null) {
            LinkedList<E> bucket = table[bucketIndex];
            bucket.remove(e);
        }

        size--; // Decrease size

        return true;
    }

    /**
     * Return true if the set contains no elements
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Return the number of elements in the set
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Return an iterator for the elements in this set
     */
    @Override
    @NotNull
    public java.util.Iterator<E> iterator() {
        return new Ex11HashSetIterator(this);
    }

    /**
     * Inner class for iterator
     */
    private class Ex11HashSetIterator implements java.util.Iterator<E> {
        // Store the elements in a list
        private final java.util.ArrayList<E> list;
        private int current = 0; // Point to the current element in list
        private final Ex11HashSet<E> set;

        /**
         * Create a list from the set
         */
        public Ex11HashSetIterator(Ex11HashSet<E> set) {
            this.set = set;
            list = setToList();
        }

        /**
         * Next element for traversing?
         */
        @Override
        public boolean hasNext() {
            return current < list.size();
        }

        /**
         * Get current element and move cursor to the next
         */
        @Override
        public E next() {
            return list.get(current++);
        }

        /**
         * Remove the element returned by the last next()
         */
        @Override
        public void remove() {
            // Left as an exercise
            // You need to remove the element from the set
            // You also need to remove it from the list
            set.remove(list.get(--current));
            list.remove(current);
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
        //noinspection unchecked
        table = new LinkedList[capacity]; // Create a new hash table
        size = 0; // Reset size

        // Add from the old table to the new table
        this.addAll(list);
    }

    /**
     * Copy elements in the hash set to an array list
     */
    private ArrayList<E> setToList() {
        ArrayList<E> list = new ArrayList<>();

        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                list.addAll(table[i]);
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
    public boolean addAll(@NotNull Collection<? extends E> c) {
        int initialSize = size();
        for (E e : c) add(e);
        return size() != initialSize;
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        for (Object o : c) {
            if (!contains(o))
                return false;
        }
        return true;
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        int initialSize = size();
        for (Object o : c) {
            remove(o);
        }
        return size() != initialSize;
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        boolean modified = false;

        Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            if (!c.contains(iterator.next())) {
                iterator.remove();
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public Object[] toArray() {
        return setToList().toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return setToList().toArray(a);
    }
}
