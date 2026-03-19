public class DSAQueue {
    protected Object[] queue;
    protected int count;  // number of elements in the queue

    public DSAQueue(int maxSize) {
        queue = new Object[maxSize];
        count = 0;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    public void enqueue(Object item) {
        if (count == queue.length) {
            throw new IllegalStateException("Queue is full");
        }
        // Implemented in subclasses
    }

    public Object dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        // Implemented in subclasses
        return null;
    }

    public Object peekFront() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        // Implemented in subclasses
        return null;
    }
}