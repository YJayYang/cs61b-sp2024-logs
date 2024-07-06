package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private T[] items;
    private int size;
    private static int RFACTOR = 2;

    public ArrayDeque61B() {
        items = (T[]) new Object[8];
        size = 0;

    }
    public void resize(int cap) {

        T[] a = (T[]) new Object[cap];
        for (int i = 0; i <= size; i++) {
            a[i] = items[i];
        }
        items = a;

    }

    @Override
    public void addFirst(T x) {
        if (size == 0) {
            items[0] = x;
            size += 1;
            return;
        }
        T[] newArray = (T[]) new Object[items.length + 1];
        newArray[0] = x;
        for (int i = 0; i < items.length; i++) {
            newArray[i + 1] = items[i];
        }
        size += 1;
        items = newArray;
    }

    @Override
    public void addLast(T x) {
        if (items.length == size) {
            resize(size * RFACTOR);
        }
        items[size] = x;
        size += 1;

    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for (int i = 0; i < size; i++){
            returnList.add(items[i]);
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T temp = items[0];
        // Shift elements to the left
        for (int i = 1; i < size; i++) {
            items[i - 1] = items[i];
        }
        // Set the last element to null and decrease the size
        items[size - 1] = null;
        size -= 1;
        return temp;
    }

    @Override
    public T removeLast() {
        if (size == 1){
            return null;
        }
        T returnItem = items[size-1];
        items[size - 1] = null;
        size -= 1;
        return returnItem;
    }

    @Override
    public T get(int index) {
        return items[index];
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }

    private class ArraySetIterator implements Iterator<T> {
        private int wizPos;

        public ArraySetIterator() {
            wizPos = 0;
        }
        @Override
        public boolean hasNext() {
            return wizPos < size;
        }
        @Override
        public T next() {
            T returnItem = items[wizPos];
            wizPos += 1;
            return returnItem;
        }
    }
    /* Returns true if this map contains a mapping for the specified key.
     */
    public boolean contains(T x) {
        for (int i = 0; i < size; i += 1) {
            if (items[i].equals(x)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o instanceof ArrayDeque61B otherSet) {
            if (this.size != otherSet.size) { return false; }
            for (T x : this) {
                if (!otherSet.contains(x)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }


}
