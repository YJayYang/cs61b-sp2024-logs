import java.util.ArrayList;
import java.util.List;

public class ArrayDeque61B <T> implements Deque61B<T> {
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
        if (size == 0){
            items[0] = x;
            size += 1;
            return;
        }
        T[] newlist = (T[]) new Object[items.length + 1];
        newlist[0] = x;
        for (int i = 0; i < size; i++){
            newlist[i + 1] = items[i];
        }
        size += 1;
        items = newlist;

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
        if (size == 0){
            return true;
        }
        return false;
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
        if (index < 0 || index > size){
            return null;
        }
        return items[index];
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
