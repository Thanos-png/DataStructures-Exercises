// ATHANASIOS PANAGIOTIDIS
// p3220143

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class SimpleLinkedList implements Iterable<LargeDepositor> {
    private ListNode head;
    private int size;

    @Override
    public Iterator<LargeDepositor> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<LargeDepositor> {
        private ListNode current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public LargeDepositor next() {
            if (!hasNext()) {
                throw new RuntimeException("No more elements in the list");
            }

            LargeDepositor data = current.data;
            current = current.next;
            return data;
        }
    }

    SimpleLinkedList() {
        this.head = null;
        this.size = 0;
    }

    private static class ListNode {
        LargeDepositor data;
        ListNode next;

        ListNode(LargeDepositor data) {
            this.data = data;
            this.next = null;
        }
    }

    public void add(LargeDepositor data) {
        ListNode newNode = new ListNode(data);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void remove(int key) {
        head = removeNode(head, key);
    }

    private ListNode removeNode(ListNode node, int key) {
        if (node == null) {
            System.err.println("Error: AFM not found. Removal aborted.");
            return null;
        }

        if (node.data.key() == key) {
            // Node to remove is found
            size--;
            return node.next;
        }

        // Recursively remove from the rest of the list
        node.next = removeNode(node.next, key);
        return node;
    }

    public List getList() {
        List<LargeDepositor> resultList = new ArrayList<>();
        ListNode current = head;
        while (current != null) {
            resultList.add(current.data);
            current = current.next;
        }
        return resultList;
    }

    public void printList() {
        ListNode current = head;
        // System.out.println("List elements:");
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
        System.out.println();
    }

    public int size() {
        return size;
    }
}
