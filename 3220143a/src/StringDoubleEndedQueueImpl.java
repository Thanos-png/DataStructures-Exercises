import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringDoubleEndedQueueImpl<T> implements StringDoubleEndedQueue<T> {
    
    // Node class for the doubly linked list
    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        Node(T data) {
            this.data = data;
        }
    }

    private Node<T> first; // Front of the queue
    private Node<T> last;  // End of the queue
    private int size;   // Number of elements in the queue

    public StringDoubleEndedQueueImpl() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void addFirst(T item) {
        Node<T> newNode = new Node<>(item);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            newNode.next = first;
            first.prev = newNode;
            first = newNode;
        }
        size++;
    }

    @Override
    public T removeFirst() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }

        T removedItem = first.data;
        first = first.next;
        if (first != null) {
            first.prev = null;
        } else {
            last = null; // Queue is empty
        }
        size--;
        return removedItem;
    }

    @Override
    public void addLast(T item) {
        Node<T> newNode = new Node<>(item);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            newNode.prev = last;
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    @Override
    public T removeLast() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }

        T removedItem = last.data;
        last = last.prev;
        if (last != null) {
            last.next = null;
        } else {
            first = null; // Queue is empty
        }
        size--;
        return removedItem;
    }

    @Override
    public T getFirst() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return first.data;
    }

    @Override
    public T getLast() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return last.data;
    }

    @Override
    public void printQueue(PrintStream stream) {
        Node<T> current = first;
        while (current != null) {
            stream.print(current.data + " ");
            current = current.next;
        }
        stream.println();
    }

    @Override
    public int size() {
        return size;
    }
}
