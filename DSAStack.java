// cited to DSAStack submitted in Practical 3

public class DSAStack 
{
    DSALinkedList stack;
    


    //constructor
    public DSAStack()
    {
        
        stack = new DSALinkedList();
        
        

    }
    //push
    public void push(Object value)
    {
        stack.insertFirst(value);
    }


    public Object pop()
    {
        if(stack.isEmpty())
        {
            throw new IllegalStateException("Stack is empty");
        }
        else
        {
             return stack.removeFirst();
        }

    }

    // Peek
    public Object peek()
    {
        if(stack.isEmpty())
        {
            throw new IllegalStateException("Stack is empty");
        }
        return stack.peekFirst();
    }
    

}