package deque;

//         0 1  2 3 4 5 6 7
// items: [6 9 -1 2 0 0 0 0 ...]
// size: 5

/* Invariants:
 addLast: The next item we want to add, will go into position size
 getLast: The item we want to return is in position size - 1
 size: The number of items in the list should be size.
*/

/* Building ArrayDeque with arrays as the core data structure*/
public class ArrayDeque<T> {
    /** array to save data */
    private T[] array;
    /** size of the deque */
    private int size;
    /** size of the array */
    private int length;
    /** front index */
    private int front;
    /** last index */
    private int last;

    /** Constructor for ArrayDeque, with default size of the array being 8*/
    public ArrayDeque() {
        array = (T[]) new Object[8];
        size = 0;
        length = 8;
        front = 4;
        last = 4;
    }

    /** Check if the deque is empty*/
    public boolean isEmpty() {
        return size == 0;
    }

    /** return the size of the deque */
    public int size() {
        return size;
    }

    /** return the "index - 1" given an index */
    private int minusOne(int index) {
        if (index == 0) {
            return length - 1;
        }
        return index - 1;
    }

    /** return the "index + 1" given an index */
    private int plusOne(int index, int module) {
        index %= module;
        if (index == module - 1) {
            return 0;
        }
        return index + 1;
    }

    private void grow() {
        T[] newArray = (T[]) new Object[length * 2];
        int ptr1 = front;
        int ptr2 = length;
        while (ptr1 != last) {
            newArray[ptr2] = array[ptr1];
            ptr1 = plusOne(ptr1, length);
            ptr2 = plusOne(ptr2, length * 2);
        }
        front = length;
        last = ptr2;
        array = newArray;
        length *= 2;
    }

    private void shrink() {
        T[] newArray = (T[]) new Object[length / 2];
        int ptr1 = front;
        int ptr2 = length / 4;
        while (ptr1 != last) {
            newArray[ptr2] = array[ptr1];
            ptr1 = plusOne(ptr1, length);
            ptr2 = plusOne(ptr2, length / 2);
        }
        front = length / 4;
        last = ptr2;
        array = newArray;
        length /= 2;
    }

    /** Add one item at the front of the deque. */
    public void addFirst(T item) {
        if (size == length - 1) {
            grow();
        }
        front = minusOne(front);
        array[front] = item;
        size += 1;
    }

    /** Add one item at the last of the deque. */
    public void addLast(T item) {
        if (size == length - 1) {
            grow();
        }
        array[last] = item;
        last = plusOne(last, length);
        size += 1;
    }

    /** Remove the first item. */
    public T removeFirst() {
        if (length >= 16 && length / size >= 4) {
            shrink();
        }
        if (size == 0) {
            return null;
        }
        T ret = array[front];
        front = plusOne(front, length);
        size -= 1;
        return ret;
    }

    /** Remove the last item. */
    public T removeLast() {
        if (length >= 16 && length / size >= 4) {
            shrink();
        }
        if (size == 0) {
            return null;
        }
        last = minusOne(last);
        size -= 1;
        return array[last];
    }

    /** return the item indexed at index.*/
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int ptr = front;
        for (int i = 0; i < index; i++) {
            ptr = plusOne(ptr, length);
        }
        return array[ptr];
    }

    /** print the entire deque from front to end. */
    public void printDeque() {
        int ptr = front;
        while (ptr != last) {
            System.out.print(array[ptr] + " ");
            ptr = plusOne(ptr, length);
        }
    }
}


