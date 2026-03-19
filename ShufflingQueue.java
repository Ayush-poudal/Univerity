public class ShufflingQueue extends DSAQueue {

    public ShufflingQueue(int maxSize) {
        super(maxSize);
    }

    @Override
    public void enqueue(Object item) {
        if (count == queue.length) {
            throw new IllegalStateException("Queue is full");
        }
        queue[count] = item;  // add to the end
        count++;
    }

    @Override
    public Object dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        Object frontItem = queue[0];

        // Shift all elements left
        for (int i = 1; i < count; i++) {
            queue[i - 1] = queue[i];
        }
        queue[count - 1] = null; // optional, free reference
        count--;
        return frontItem;
    }

    @Override
    public Object peekFront() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return queue[0];
    }
}