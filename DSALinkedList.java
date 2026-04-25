
import java.util.*;

public class DSALinkedList {
    private class DSALinkNode {
        Object value;
        DSALinkNode next;
        DSALinkNode prev;

        DSALinkNode(Object inValue) {
            value = inValue;
            next = null;
            prev = null;
        }

        Object getValue() {
            return value;
        }

        void setValue(Object inValue) {
            value = inValue;
        }

        DSALinkNode getNext() {
            return next;
        }

        void setNext(DSALinkNode newNext) {
            next = newNext;
        }

        DSALinkNode getPrev() {
            return prev;
        }

        void setPrev(DSALinkNode newPrev) {
            prev = newPrev;
        }

    }

    public boolean isEmpty() {
        return head == null;
    }

    private DSALinkNode head;
    private DSALinkNode tail;

    DSALinkedList() {
        head = null;
        tail = null;

    }

    public void insertFirst(Object newValue) {
        DSALinkNode newNode = new DSALinkNode(newValue);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;

        } else {
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
        }
    }

    public void insertLast(Object newValue) {
        DSALinkNode newNode = new DSALinkNode(newValue);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;

        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;

        }
    }

    public Object peekFirst() {
        Object nodeValue;

        if (isEmpty()) {
            throw new NoSuchElementException("The linked List is Empty");

        } else {
            nodeValue = head.getValue();
            return nodeValue;
        }
    }

    public Object peekLast() {
        Object nodeValue;
        if (isEmpty()) {
            throw new NoSuchElementException("The linked List is Empty");
        } else {
            nodeValue = tail.getValue();
            return nodeValue;
        }
    }

    public Object removeFirst() {
        Object nodeValue;

        if (isEmpty()) {
            throw new NoSuchElementException("The linked List is Empty");

        } else if (head == tail) {
            nodeValue = head.getValue();
            head = null;
            tail = null;

        }

        else {
            nodeValue = head.getValue();
            head = head.getNext();
            head.setPrev(null);

        }
        return nodeValue;
    }

    public Object removeLast() {
        Object nodeValue;

        if (isEmpty()) {
            throw new NoSuchElementException("The linked List is Empty");
        } else if (head == tail) {
            nodeValue = head.getValue();
            head = null;
            tail = null;
        } else {
            nodeValue = tail.getValue();
            tail = tail.getPrev();
            tail.setNext(null);

        }
        return nodeValue;
    }

    public void remove(Object value) {
        if (isEmpty())
            throw new IllegalStateException("List is empty");

        DSALinkNode curr = head;

        while (curr != null) {
            if (curr.value.equals(value)) {
                // Removing head
                if (curr == head) {
                    removeFirst();
                }
                // Removing tail
                else if (curr == tail) {
                    removeLast();
                }
                // Removing middle node
                else {
                    curr.prev.next = curr.next;
                    curr.next.prev = curr.prev;
                    
                }
                return; // stop after first match
            }

            curr = curr.next;
        }
    }

    public boolean contains(Object value) {
        DSALinkNode curr = head;
        boolean found = false;

        while (curr != null && !found) {
            if (curr.value.equals(value)) {
                found = true;
            } else {
                curr = curr.next;
            }
        }

        return found;
    }

    public int size() {
        int count = 0;
        DSALinkNode curr = head;

        while (curr != null) {
            count++;
            curr = curr.getNext();
        }

        return count;
    }

    public DSALinkNode getHead() {
        return head;
    }

    public void peekAll()
{
    if (isEmpty())
    {
        System.out.println("List is empty");
        return;
    }

    DSALinkNode curr = head;

    while (curr != null)
    {
        System.out.println(curr.getValue());
        curr = curr.getNext();
    }
}


public Object getNextNode(Object nodeObj) {
    DSALinkNode node = (DSALinkNode) nodeObj;
    return node.getNext();
}

public Object getNodeValue(Object nodeObj) {
    DSALinkNode node = (DSALinkNode) nodeObj;
    return node.getValue();
}



     

    public static void main(String[] args) {
        DSALinkedList list = new DSALinkedList();

        // 1. Test isEmpty() on new list
        System.out.println("List empty? " + list.isEmpty()); // true

        // 2. Test insertFirst()
        list.insertFirst("A");
        System.out.println("Peek first: " + list.peekFirst()); // A
        System.out.println("Peek last: " + list.peekLast()); // A

        // 3. Test insertLast()
        list.insertLast("B");
        list.insertLast("C");
        System.out.println("Peek first: " + list.peekFirst()); // A
        System.out.println("Peek last: " + list.peekLast()); // C

        // 4 Test viewing all the element
        System.out.println("All elements are");
        list.peekAll();

        // 5. Test removeFirst()
        System.out.println("Removed first: " + list.removeFirst()); // A
        System.out.println("Peek first now: " + list.peekFirst()); // B

        // 6. Test removeLast()
        System.out.println("Removed last: " + list.removeLast()); // C
        System.out.println("Peek last now: " + list.peekLast()); // B

        // 7. Test removing only element
        System.out.println("Removed first: " + list.removeFirst()); // B
        System.out.println("List empty? " + list.isEmpty()); // true

        // 8. Test exception on empty list
        try {
            list.removeFirst(); // should throw exception
        } catch (Exception e) {
            System.out.println("Caught exception as expected: " + e);
        }
    }
}
