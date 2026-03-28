import java.util.*;

public class Menu 
{
    public static void main(String[] args)
    {
        int choice;
        String value;
        DSALinkedList list = new DSALinkedList();
        do
        {
        System.out.println("Enter 1 for Viewing the LinkedList \n Enter 2 for InsertFirst \n Enter 3 for InsertLast \n Enter 4 for RemoveFirst \n Enter 5 for RemoveLast \n Enter 6 for ViewFirst \n Enter 7 for ViewLast \n Enter 0 to exit");
        Scanner sc = new Scanner(System.in);
        choice = sc.nextInt();
        sc.nextLine();
        
        switch (choice) {
            case 0:
                break;
            case 1:
                list.peekAll();
                break;
            case 2:
                System.out.println("Enter the value to insert first");
                value = sc.nextLine();
                list.insertFirst(value);
                break;
            case 3:
                System.out.println("Enter the value to insert last");
                value = sc.nextLine();
                list.insertLast(value);
                break;
            case 4:
                System.out.println("We removed = " + list.removeFirst());
                break;
            case 5:
                System.out.println("We removed = " + list.removeLast());
                break;
            case 6:
                System.out.println("The value is " + list.peekFirst());
                break;
            case 7:
                System.out.println("The value is " + list.peekLast());
                break;
            default:
                throw new IllegalStateException("Enter within the options");
                
        }
    }while(choice!=0);
    
    
    }
}
