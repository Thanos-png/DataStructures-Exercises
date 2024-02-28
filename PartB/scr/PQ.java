import java.util.NoSuchElementException;

class PQ<T extends City>{
    private StringDoubleEndedQueueImpl<T> queue;

    public PQ() {
        this.queue = new StringDoubleEndedQueueImpl<>();
    }

    // Copy constructor
    public PQ(PQ<T> original) {
        this.queue = new StringDoubleEndedQueueImpl<>(original.queue);
    }

    public PQ<T> copy() {
        return new PQ<>(this);
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }

    public void insert(T x) {
        queue.addLast(x);
        heapifyUp();
    }

    public T min() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority queue is empty");
        }
        return queue.getFirst();
    }

    public T getMin() {
        T min = min();
        queue.removeFirst();
        if (queue.size() > 1) {
            heapifyDown();
        }
        return min;
    }

    public T remove(int id) {
        int index = findIndexById(id);
        if (index == -1) {
            throw new IllegalArgumentException("City with ID " + id + " not found in priority queue");
        }

        StringDoubleEndedQueueImpl.Node<T> nodeToRemove = queue.getNode(index);
        T removedCity = nodeToRemove.data;
        queue.removeNode(nodeToRemove);
        // T removedCity = queue.removeNode(nodeToRemove);
        heapifyDown();

        return removedCity;
    }

    private int findIndexById(int id) {
        if (!isEmpty()) {
            StringDoubleEndedQueueImpl.Node<T> current = queue.getFirstNode();
            int index = 0;

            while (current != null) {
                if (current.data.getID() == id) {
                    return index; // Return the index if the ID is found
                }
                current = current.next;
                index++;
            }
        }
        return -1; // Return -1 if the ID is not found
    }

    private void heapifyUp() {
        StringDoubleEndedQueueImpl.Node<T> current = queue.getLastNode();
        // int index = size() - 1;
        int parentIndex = ((size() - 1) - 1) / 2;
        if (parentIndex < 0) {
            return;
        }
        StringDoubleEndedQueueImpl.Node<T> parent = queue.getNode(parentIndex);
        while (parentIndex >= 0 && current != queue.getFirstNode() && current.data.compareTo(parent.data) < 0) {
            queue.swap(current, parent);
            parentIndex = (parentIndex - 1) / 2;
            if (parentIndex >= 0) {
                parent = queue.getNode(parentIndex);
            }
        }
    }

    private void heapifyDown() {
        StringDoubleEndedQueueImpl.Node<T> current = queue.getFirstNode();
        StringDoubleEndedQueueImpl.Node<T> left;
        StringDoubleEndedQueueImpl.Node<T> right;
        StringDoubleEndedQueueImpl.Node<T> child;
        int index = 0;
        int leftIndex;
        int rightIndex;
        while (current != null) {
            index = findIndexById(current.data.getID());
            leftIndex = 2 * index + 1;
            rightIndex = 2 * index + 2;
            left = (leftIndex < queue.size()) ? queue.getNode(leftIndex) : null;
            right = (rightIndex < queue.size()) ? queue.getNode(rightIndex) : null;
            child = (left != null && right != null) ? (left.data.compareTo(right.data) < 0 ? left : right) : (left != null ? left : right);
            if (child != null && current.data.compareTo(child.data) > 0) {
                queue.swap(current, child);
                current = child;
            } else {
                break;
            }
        }
    }

/*
    private void resizeHeap() {
        int newCapacity = 2 * heap.length; // Double the capacity (you can choose a different strategy)
        City[] newHeap = new City[newCapacity];

        // Copy existing elements to the new array
        System.arraycopy(heap, 0, newHeap, 0, heap.length);

        // Update the reference to the new array
        heap = newHeap;
    }
*/
}
