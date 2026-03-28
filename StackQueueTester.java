import java.util.*;

public class StackQueueTester
{
    public static void main(String[] args)
    {
        System.out.println("===== STACK TEST =====");
        DSAStack testStack = new DSAStack();

        // Test push
        testStack.push("A");
        testStack.push("B");
        testStack.push("C");

        // Test peek
        System.out.println("Top of testStack: " + testStack.peek());

        // Test pop
        
            System.out.println("Popped: " + testStack.pop());
            System.out.println("Popped: " + testStack.pop());
            // System.out.println("Popped: " + testStack.pop());  // remove comment tags to check empty pop

        // Test empty pop (should throw exception)
        try
        {
            testStack.pop();
        }
        catch (NoSuchElementException e)
        {
            System.out.println("Stack Error Caught: " + e.getMessage());
        }


        System.out.println("\n===== QUEUE TEST =====");
        DSAQueue testQueue = new DSAQueue();

        // Test enqueue
        testQueue.enqueue("1");
        testQueue.enqueue("2");
        testQueue.enqueue("3");

        // Test peek
        System.out.println("Front of testQueue: " + testQueue.queue.peekFirst());

        // Test dequeue
        
            System.out.println("Dequeued: " + testQueue.dequeue());
            System.out.println("Dequeued: " + testQueue.dequeue());
            // System.out.println("Dequeued: " + testQueue.dequeue()); // remove comments tags to test empty dequeue

        // Test empty dequeue (should throw exception)
        try
        {
            testQueue.dequeue();
        }
        catch (NoSuchElementException e)
        {
            System.out.println("Queue Error Caught: " + e.getMessage());
        }
    }
}