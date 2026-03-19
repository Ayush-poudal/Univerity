public class DSAStack 
{
    Object[] stack;
    int count;
    int top;
    int capacity;


    //constructor
    public DSAStack(int maxSize)
    {
        capacity = maxSize;
        stack = new Object[capacity];
        count = 0;
        top = -1;

    }
    //push
    public void push(Object value)
    {
        if(isFull())
        {
            throw new IllegalStateException("Stack is full");
        }
        else
        {
            top++;
            stack[top] = value;
            count++;
        }
    }


    public Object pop()
    {
        if(isEmpty())
        {
            throw new IllegalStateException("Stack is empty");
        }
        else
        {
            Object value = stack[top];
            top--;
            count--;
            return value;
        }

    }

    // Peek
    public Object peek()
    {
        if(isEmpty())
        {
            throw new IllegalStateException("Stack is empty");
        }
        return stack[top];
    }

    // isEmpty
    public boolean isEmpty()
    {
        return count == 0;
    }

    // isFull
    public boolean isFull()
    {
        return count == capacity;
    }

    // Size
    public int size()
    {
        return count;
    }

}