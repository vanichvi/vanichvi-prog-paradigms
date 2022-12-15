package queue;

import java.util.Objects;

public class LinkedQueue extends AbstractQueue {
    private class Node {
        final Object value;
        Node next;

        public Node(Object value, Node next) {
            Objects.requireNonNull(value);

            this.value = value;
            this.next = next;
        }
    }

    private int size = 0;
    private Node head = null;
    private Node tail = null;

    protected LinkedQueue createImpl() {
        return new LinkedQueue();
    }

    protected void enqueueImpl(Object element) {
        if (size != 0) {
            tail = tail.next = new Node(element, null);
        } else {
            head = tail = new Node(element, null);
        }
        size++;
    }

    protected Object elementImpl() {
        return head.value;
    }

    protected Object dequeueImpl() {
        Object ans = head.value;
        head = head.next;
        size--;
        return ans;
    }

    public int size() {
        return size;
    }

}
