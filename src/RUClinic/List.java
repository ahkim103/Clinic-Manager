package RUClinic;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A generic List class that stores elements of type E in a linear data structure
 * @author Katrina Tong
 * @author Andrew Kim
 */
public class List<E> implements Iterable<E> {
    protected E[] objects;
    protected int size;
    protected static final int INITIAL_CAPACITY = 4;

    /**
     * Constructor for the list class, creating an array of type E of intial size 4
     */
    public List() {
        objects = (E[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Method that iterates through the array to find a certain e
     * @param e the value being searched for
     * @return index i where the wanted e is located, -1 if not
     */
    private int find(E e) {
        for (int i = 0; i < size; i++) {
            if (objects[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Method to grow the list by size 4 when the list gets full
     */
    private void grow() {
        E[] newObjects = (E[]) new Object[objects.length + 4];
        for (int i = 0; i < size; i++) {
            newObjects[i] = objects[i];
        }
        objects = newObjects;
    }

    /**
     * Method to iterate through the list to see if it contains a certain e
     * @param e the object being searched for in the list
     * @return true if e is found, false if not
     */
    public boolean contains(E e) {
        return find(e) != -1;
    }

    /**
     * Method to add an object e to the list
     * Conditionals are added to prevent duplicates and to grow the list if necessary
     * @param e the object being added to the list
     */
    public void add(E e) {
        if (contains(e)) { return; }
        if (size == objects.length) { grow(); }
        objects[size++] = e;
    }

    /**
     * Method to remove an object e from the list
     * If the object e cannot be found, does nothing
     * @param e the object being removed from the list
     */
    public void remove(E e) {
        int index = find(e);
        if (index == -1) { return; }
        for (int i = index; i < size - 1; i++) {
            objects[i] = objects[i + 1];
        }
        objects[--size] = null;
    }

    /**
     * Method to check if the list is empty
     * @return true if it is empty, false if not
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Method to return the size of the list
     * @return size of the list
     */
    public int size() {
        return size;
    }

    /**
     * Method to return the object at a specific index in the list
     * @param index the position in the list a certain object is
     * @return the object of index in the list
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return objects[index];
    }

    /**
     * Method to set a certain object e in a specific index in the list
     * @param index position where the object e is going to be inserted
     * @param e the object being inserted in the list
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        objects[index] = e;
    }

    /**
     * Method to return the index of a certain value in the list
     * @param e object being searched for
     * @return the index of the object e in the list
     */
    public int indexOf(E e) {
        return find(e);
    }

    /**
     * Method to return an iterator over the elements in the list.
     * @return an Iterator for the list
     */
    @Override
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    /**
     * Inner class that implements the Iterator interface to allow
     * iteration over the elements in the list.
     */
    private class ListIterator implements Iterator<E> {
        private int currentIndex = 0;

        /**
         * Method to check if there are more elements to iterate over.
         * @return true if there are more elements, false if none
         */
        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        /**
         * Method to return the next element in the iteration.
         * If no more elements are available, throws NoSuchElementException.
         * @return the next element in the list
         * @throws NoSuchElementException if no more elements to return
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements to iterate over.");
            }
            return objects[currentIndex++]; // Return current object and move to next
        }
    }

}


