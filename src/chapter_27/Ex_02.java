package chapter_27;

public class Ex_02 {
    public static void main(String[] args) {
        // Create a map
        MyMap<String, Integer> map = new Ex02HashMap<>();
        map.put("Smith", 30);
        map.put("Anderson", 31);
        map.put("Lewis", 29);
        map.put("Cook", 29);
        map.put("Smith", 65);

        System.out.println("Entries in map: " + map);

        System.out.println("The age for " + "Lewis is " +
                map.get("Lewis"));

        System.out.println("Is Smith in the map? " +
                map.containsKey("Smith"));
        System.out.println("Is age 33 in the map? " +
                map.containsValue(33));

        map.remove("Smith");
        System.out.println("Entries in map: " + map);

        map.clear();
        System.out.println("Entries in map: " + map);

        MyMap<Integer, Integer> myMap = new Ex02HashMap<>(11, 0.75f);
        myMap.put(44, 1);
        myMap.put(4, 2);
        myMap.put(16, 3);
        myMap.put(28, 4);
        myMap.put(21, 5);
        myMap.remove(16);
        myMap.put(26, 6);
        System.out.println(myMap);
    }
}

class Ex02HashMap<K, V> implements MyMap<K, V> {
    // Define the default hash table size. Must be a power of 2
    private static final int DEFAULT_INITIAL_CAPACITY = 6;

    // Define the maximum hash table size. 1 << 30 is same as 2^30
    private static final int MAXIMUM_CAPACITY = 1 << 30;

    // Current hash table capacity. Capacity is a power of 2
    private int capacity;

    // Define default load factor
    private static final float DEFAULT_MAX_LOAD_FACTOR = 0.5f;

    // Specify a load factor used in the hash table
    private float loadFactorThreshold;

    // The number of entries in the map
    private int size = 0;

    // Hash table is an array with each cell that is a linked list
    Ex02Entry<K, V>[] table;

    /**
     * Construct a map with the default capacity and load factor
     */
    public Ex02HashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
    }

    /**
     * Construct a map with the specified initial capacity and
     * default load factor
     */
    public Ex02HashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
    }

    /**
     * Construct a map with the specified initial capacity
     * and load factor
     */
    public Ex02HashMap(int initialCapacity, float loadFactorThreshold) {
        this.capacity = Math.min(initialCapacity, MAXIMUM_CAPACITY);

        this.loadFactorThreshold = loadFactorThreshold;
        //noinspection unchecked
        table = new Ex02Entry[capacity];
    }

    /**
     * Remove all of the entries from this map
     */
    @Override
    public void clear() {
        size = 0;
        removeEntries();
    }

    /**
     * Return true if the specified key is in the map
     */
    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /**
     * Return true if this map contains the value
     */
    @Override
    public boolean containsValue(V value) {
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                if (table[i].getValue().equals(value))
                    return true;
            }
        }

        return false;
    }

    /**
     * Return a set of entries in the map
     */
    @Override
    public java.util.Set<MyMap.Entry<K, V>> entrySet() {
        java.util.Set<MyMap.Entry<K, V>> set =
                new java.util.HashSet<>();

        for (int i = 0; i < capacity; i++) {
            if (table[i] != null && table[i].state.equals(Ex02Entry.State.OCCUPIED)) {
                set.add(table[i]);
            }
        }

        return set;
    }

    /**
     * Return the value that matches the specified key
     */
    @Override
    public V get(K key) {
        int index = probeIndex(key);
        return index == -1 ? null : table[index].value;
    }

    /**
     * Return true if this map contains no entries
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Return a set consisting of the keys in this map
     */
    @Override
    public java.util.Set<K> keySet() {
        java.util.Set<K> set = new java.util.HashSet<>();

        for (int i = 0; i < capacity; i++) {
            if (table[i] != null && table[i].state.equals(Ex02Entry.State.OCCUPIED)) {
                set.add(table[i].getKey());
            }
        }

        return set;
    }

    /**
     * Add an entry (key, value) into the map
     */
    @Override
    public V put(K key, V value) {
        // Check if the key is already in the table
        int index = probeIndex(key);
        if (index != -1) { // The key is in the table
            V oldValue = table[index].value;
            // Replace old value with new value
            table[index].value = value;
            // Return the old value for the key
            return oldValue;
        }

        // Check load factor
        if (size >= capacity * loadFactorThreshold) {
            if (capacity == MAXIMUM_CAPACITY)
                throw new RuntimeException("Exceeding maximum capacity");

            rehash();
        }

        // Insert the value
        int availableIndex = probeAvailableIndex(key);
        if (table[availableIndex] == null) {
            table[availableIndex] = new Ex02Entry<>(key, value);
        } else {
            table[availableIndex].key = key;
            table[availableIndex].value = value;
            table[availableIndex].state = Ex02Entry.State.OCCUPIED;
        }
        size++; // Increase size

        return value;
    }

    /** Return the index of the Entry of the key.
     * Return -1 if key is not in the table */
    private int probeIndex(K key) {
        int k = hash(key.hashCode()), i = 1,
                probeIndex = k % capacity;
        while (table[probeIndex] != null) {
            if (table[probeIndex].getKey().equals(key)) {
                if (table[probeIndex].state.equals(Ex02Entry.State.OCCUPIED))
                    return probeIndex; // Found the Entry
                else
                    return -1; // The key were here but was removed
            }

            probeIndex = (k + i) % capacity; // Next probe
            i <<= 1;
        }

        return table[probeIndex] == null ? -1 : probeIndex;
    }

    /** Return the index of the Entry available for insertion
     * by looking for marked Entry or null (empty).*/
    private int probeAvailableIndex(K key) {
        int k = hash(key.hashCode()), i = 1,
                probeIndex = k % capacity;
        while (table[probeIndex] != null) {
            if (table[probeIndex].state.equals(Ex02Entry.State.MARKED))
                break; // Found the available that is marked

            probeIndex = (k + i) % capacity; // Next probe
            i <<= 1;
        }

        return probeIndex;
    }

    /**
     * Remove the entries for the specified key
     */
    @Override
    public void remove(K key) {
        int index = probeIndex(key);

        if (index != -1) {
            table[index].state = Ex02Entry.State.MARKED;
            size--;
        }
    }

    /**
     * Return the number of entries in this map
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Return a set consisting of the values in this map
     */
    @Override
    public java.util.Set<V> values() {
        java.util.Set<V> set = new java.util.HashSet<>();

        for (int i = 0; i < capacity; i++) {
            if (table[i] != null && table[i].state.equals(Ex02Entry.State.OCCUPIED)) {
                set.add(table[i].value);
            }
        }

        return set;
    }

    /**
     * Hash function
     */
    private int hash(int hashCode) {
        return Math.abs(hashCode % capacity);
    }

    /**
     * Remove all entries from each bucket
     */
    private void removeEntries() {
        for (int i = 0; i < capacity; i++) {
            table[i] = null;
        }
    }

    /**
     * Rehash the map
     */
    private void rehash() {
        java.util.Set<Entry<K, V>> set = entrySet(); // Get entries
        capacity <<= 1; // Double capacity
        //noinspection unchecked
        table = new Ex02Entry[capacity]; // Create a new hash table
        size = 0; // Reset size to 0

        for (Entry<K, V> entry : set) {
            if (((Ex02Entry<K, V>) entry).state.equals(Ex02Entry.State.OCCUPIED))
                put(entry.getKey(), entry.getValue()); // Store to new table
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");

        for (int i = 0; i < capacity; i++) {
            if (table[i] != null && table[i].state.equals(Ex02Entry.State.OCCUPIED))
                builder.append(table[i]);
        }

        builder.append("]");
        return builder.toString();
    }

    public static class Ex02Entry<K, V> extends Entry<K, V> {
        enum State {OCCUPIED, MARKED}

        private State state;

        public Ex02Entry(K key, V value) {
            super(key, value);
            state = State.OCCUPIED;
        }
    }
}