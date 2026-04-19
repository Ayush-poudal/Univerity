public class DSAQueue {
    protected Object[] queue;
    protected int count;
    protected int front;
    protected int rear;

    public DSAQueue(int maxSize) {
        queue = new Object[maxSize];
        count = 0;
        front = 0;
        rear = 0;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    public void enqueue(Object item) {
        if (count == queue.length)
            throw new IllegalStateException("Queue is full");

        queue[rear] = item;
        rear = (rear + 1) % queue.length;
        count++;
    }

    public Object dequeue() {
        if (isEmpty())
            throw new IllegalStateException("Queue is empty");

        Object item = queue[front];
        front = (front + 1) % queue.length;
        count--;

        return item;
    }

    public Object peekFront() {
        if (isEmpty())
            throw new IllegalStateException("Queue is empty");

        return queue[front];
    }
}