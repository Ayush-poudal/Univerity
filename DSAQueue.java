// cited to DSAQueue submitted in Practical 3

import java.util.*;

public class DSAQueue {
    protected DSALinkedList queue;
    protected int count;  // number of elements in the queue

    public DSAQueue() 
    {
        queue = new DSALinkedList();
    }

   

    public void enqueue(Object item) {
        
        queue.insertLast(item);
        
    }

    public Object dequeue() {
        if (queue.isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return queue.removeFirst();
    }

    public Object peekFront() {
        if (queue.isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return queue.peekFirst();
    }
}