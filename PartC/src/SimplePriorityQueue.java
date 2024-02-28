// ATHANASIOS PANAGIOTIDIS
// p3220143

public class SimplePriorityQueue {
    private LargeDepositor[] heap;
    private int size;

    public SimplePriorityQueue(int initialCapacity) {
        this.heap = new LargeDepositor[initialCapacity + 1];
        this.size = 0;
    }

    public void insert(LargeDepositor depositor) {;
        if (size == heap.length - 1) {
            resize();
        }

        heap[++size] = depositor;
        if (heap[size] != null) {
            swim(size);
        }
    }

    public LargeDepositor removeMax() {
        if (isEmpty()) {
            return null;
        }

        LargeDepositor root = heap[1];
        swap(1, size--);
        heap[size + 1] = null;
        sink(1);

        return root;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void swim(int k) {
        while (k > 1 && compare(k / 2, k) < 0) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k;
            if (j < size && compare(j, j + 1) < 0) {
                j++;
            }

            if (compare(k, j) >= 0) {
                break;
            }

            swap(k, j);
            k = j;
        }
    }

    private void swap(int i, int j) {
        LargeDepositor temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private int compare(int i, int j) {
        // Compare depositors based on suspicion score
        double score1 = calculateScore(heap[i]);
        double score2 = calculateScore(heap[j]);
        return Double.compare(score1, score2);
    }

    private void resize() {
        int newCapacity = size() * 2; // Set the capacity to the size of the binary search tree
        LargeDepositor[] newHeap = new LargeDepositor[newCapacity];
        System.arraycopy(heap, 1, newHeap, 1, size);
        heap = newHeap;
    }

    private double calculateScore(LargeDepositor depositor) {
        if (depositor.getTaxedIncome() < 8000) {
            return Double.MAX_VALUE;
        } else {
            return depositor.getSavings() - depositor.getTaxedIncome();
        }
    }

    // Add a method to get the size of the priority queue
    public int size() {
        return size;
    }
}
