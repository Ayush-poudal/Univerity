public class CircularQueue extends DSAQueue {
    private int front;
    private int rear;

    public CircularQueue(int maxSize) {
        super(maxSize);
        front = 0;
        rear = -1;
    }

    @Override
    public void enqueue(Object item) {
        if (count == queue.length) {
            throw new IllegalStateException("Queue is full");
        }
        // Move rear forward (wrap around)
        rear = (rear + 1) % queue.length;
        queue[rear] = item;
        count++;
    }

    @Override
    public Object dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        Object frontItem = queue[front];
        queue[front] = null; // optional
        front = (front + 1) % queue.length;
        count--;
        return frontItem;
    }

    @Override
    public Object peekFront() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return queue[front];
    }
}