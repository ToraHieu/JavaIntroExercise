package chapter_27;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class Ex_05 {
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

class Ex05HashSet<E> implements Collection<E> {
    private MyHashMap<E, E> map;

    public Ex05HashSet() {
        map = new MyHashMap<>();
    }

    public Ex05HashSet(int initialCapacity) {
        map = new MyHashMap<>(initialCapacity);
    }

    public Ex05HashSet(int initialCapacity, float loadFactorThreshold) {
        map = new MyHashMap<>(initialCapacity, loadFactorThreshold);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        //noinspection unchecked
        return map.containsKey((E)o);
    }

    @NotNull
    @Override
    public Iterator<E> iterator() {
        return new Ex05HashSetIterator();
    }

    private class Ex05HashSetIterator implements Iterator<E> {
        // Store the elements in a list
        private final java.util.ArrayList<E> list;
        private int current = 0; // Point to the current element in list

        public Ex05HashSetIterator() {
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
    }

    private java.util.ArrayList<E> setToList() {
        ArrayList<E> list = new java.util.ArrayList<>();
        LinkedList<MyMap.Entry<E, E>>[] table = map.table;

        for (LinkedList<MyMap.Entry<E, E>> entries : table) {
            if (entries != null && entries.size() > 0)
                for (MyMap.Entry<E, E> entry : entries)
                    list.add(entry.key);
        }

        return list;
    }

    @Override
    public boolean add(E e) {
        // Duplicated element
        if (map.get(e) != null)
            return false;

        map.put(e, e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (!contains(o))
            return false;

        //noinspection unchecked
        map.remove((E)o);
        return true;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public String toString() {
        LinkedList<MyMap.Entry<E, E>>[] table = map.table;

        StringBuilder builder = new StringBuilder("[");

        for (LinkedList<MyMap.Entry<E, E>> entries : table) {
            if (entries != null && entries.size() > 0)
                for (MyMap.Entry<E, E> entry : entries)
                    builder.append(entry.key).append(", ");
        }

        if (builder.length() > 1) {
            builder.delete(builder.length() - 2, builder.length());
        }
        builder.append("]");
        return builder.toString();
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
    public boolean addAll(@NotNull Collection<? extends E> c) {
        for (E e : c) add(e);
        return true;
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        boolean result = false;
        for (Object o : c) {
            result |= remove(o);
        }
        return result;
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        return false;
    }

    @NotNull
    @Override
    public Object[] toArray() {
        return setToList().toArray();
    }

    @NotNull
    @Override
    public <T> T[] toArray(@NotNull T[] a) {
        return null;
    }
}
