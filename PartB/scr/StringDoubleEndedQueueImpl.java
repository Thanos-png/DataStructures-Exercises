import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringDoubleEndedQueueImpl<T extends City> implements StringDoubleEndedQueue<T> {
    
    // Node class for the doubly linked list
    public static class Node<T extends City> {
        T data;
        Node<T> next;
        Node<T> prev;

        Node(T data) {
            this.data = data;
        }

        // Copy constructor for Node
        public Node(Node<T> original) {
            this.data = original.data;
            this.next = original.next;
            this.prev = original.prev;
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

    // Copy constructor
    public StringDoubleEndedQueueImpl(StringDoubleEndedQueueImpl<T> original) {
        this(); // Initialize an empty queue

        Node<T> originalCurrent = original.first;
        Node<T> previousNode = null;  // To keep track of the last copied node

        while (originalCurrent != null) {
            Node<T> newNode = new Node<>(originalCurrent);
            if (previousNode != null) {
                // Connect the previous node to the current node
                previousNode.next = newNode;
                newNode.prev = previousNode;
            } else {
                // If it's the first node, update the first reference
                first = newNode;
            }

            // Update the last reference for each iteration
            last = newNode;
            previousNode = newNode;
            originalCurrent = originalCurrent.next;
        }

        size = original.size();
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

    public Node<T> getFirstNode() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return first;
    }

    @Override
    public T getLast() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return last.data;
    }

    public Node<T> getLastNode() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return last;
    }

    public void swap(Node<T> item1, Node<T> item2) {
        // Check if nodes are not null and are different
        if (item1 != null && item2 != null && item1 != item2) {
            boolean flag1 = true;
            boolean flag2 = true;
            // Update next pointers of the previous nodes
            if (item2.prev != null) {
                if (item1.prev != null) {
                    item1.prev.next = item2;
                } else {
                    flag1 = false;
                    item2.prev.next = item1;
                    item1.prev = item2.prev;
                    first = item2;
                    item2.prev = null;
                }
            }
            if (flag1) {
                if (item2.prev != null) {
                    item2.prev.next = item1;
                } else {
                    flag1 = false;
                    item1.prev.next = item2;
                    item2.prev = item1.prev;
                    first = item1;
                    item1.prev = null;
                }
            }

            // Update previous pointers of the next nodes
            if (item2.next != null) {
                if (item1.next != null) {
                    item1.next.prev = item2;
                } else {
                    flag2 = false;
                    item2.next.prev = item1;
                    item1.next = item2.next;
                    last = item2;
                    item2.next = null;
                }
            }
            if (flag2) {
                if (item2.next != null) {
                    item2.next.prev = item1;
                } else {
                    flag2 = false;
                    item1.next.prev = item2;
                    item2.next = item1.next;
                    last = item1;
                    item1.next = null;
                }
            }

            // Swap previous pointers
            if (flag1) {
                Node<T> tempPrev = item2.prev;
                item2.prev = item1.prev;
                item1.prev = tempPrev;
            }

            // Swap next pointers
            if (flag2) {
                Node<T> tempNext = item1.next;
                item1.next = item2.next;
                item2.next = tempNext;
            }
        }
    }

    public Node<T> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Error: Index out of range");
        }

        Node<T> current;
        if (index <= size / 2) {
            // Start from the front
            current = first;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            // Start from the end
            current = last;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        if (current == null) {
            throw new IllegalStateException("Unexpected null node encountered");
        }
        return current;
    }

    public T removeNode(Node<T> nodeToRemove) {
        if (nodeToRemove == null) {
            throw new NoSuchElementException("Node not found");
        }

        if (size != 1) {
            if (nodeToRemove.prev != null) {
                if (nodeToRemove.next != null) {
                    nodeToRemove.prev.next = nodeToRemove.next;
                    nodeToRemove.next.prev =  nodeToRemove.prev;
                }
            } else {
                if (nodeToRemove.next != null) {
                    first = nodeToRemove.next;
                    nodeToRemove.next.prev = null;
                }
            }

            if (nodeToRemove.next != null) {
                nodeToRemove.next.prev = nodeToRemove.prev;
                nodeToRemove.prev.next = nodeToRemove.next;
            } else {
                last = nodeToRemove.prev;
                nodeToRemove.prev.next = null;
            }
        } else {
            first = null;
            last = null;
        }

        size--;
        return nodeToRemove.data;
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

    public void printQueue(PrintStream stream, int i) {
        Node<T> current = first;
        while (current != null) {
            stream.print(current.data.getName() + " ");
            current = current.next;
        }
        stream.println();
    }

    @Override
    public int size() {
        return size;
    }
}
