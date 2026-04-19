import java.util.Scanner;

public class DSAGraphMenu {
    public static void main(String[] args) {

        DSAGraph graph = new DSAGraph();
        Scanner sc = new Scanner(System.in);

        int choice;

        do {
            System.out.println("\nSelect:");
            System.out.println("(1) Add node");
            System.out.println("(2) Delete node");
            System.out.println("(3) Add edge");
            System.out.println("(4) Delete edge");
            System.out.println("(5) displayAsList");
            System.out.println("(6) displayAsMatrix");
            System.out.println("(7) Breadth First Search");
            System.out.println("(8) Depth First Search");
            System.out.println("(0) Exit");

            choice = sc.nextInt();
            sc.nextLine(); // IMPORTANT buffer clear

            switch (choice) {

                case 1: {
                    System.out.print("Enter label: ");
                    String label = sc.nextLine().trim();

                    System.out.print("Enter value: ");
                    String value = sc.nextLine().trim();
                    try{
                    graph.addVertex(label, value);
                    System.out.println("Successfully added");
                    }
                    catch(Exception e )
                    {
                        System.out.println(e.getMessage());
                    }
                    break;
                }

                case 2: {
                    System.out.print("Enter label to delete: ");
                    String label = sc.nextLine().trim();
                    try{
                    graph.removeVertex(label);
                    System.out.println("Successfully removed");
                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                }

                case 3: {
                    System.out.print("Enter first vertex: ");
                    String v1 = sc.nextLine().trim();

                    System.out.print("Enter second vertex: ");
                    String v2 = sc.nextLine().trim();
                    try{
                    graph.addEdge(v1, v2);
                    System.out.println("Edge added.");
                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                }

                case 4: {
                    System.out.print("Enter first vertex: ");
                    String v1 = sc.nextLine().trim();

                    System.out.print("Enter second vertex: ");
                    String v2 = sc.nextLine().trim();
                    try{
                    graph.removeEdge(v1, v2); 
                    System.out.println("Edge removed.");
                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                }

                case 5: {
                    try{
                    graph.displayAsList();
                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                }

                case 6: {
                    try{
                    graph.displayAsMatrix();
                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                }

                case 7: {
                    try{
                    System.out.println("BFS:");
                    DSAQueue bfs = graph.breathFirstSearch();
                    printTraversal(bfs);
                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                }

                case 8: {
                    try{
                    System.out.println("DFS");

                    DSAQueue dfs = graph.depthFirstSearch();
                    printTraversal(dfs);
                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                }

                case 0: {
                    System.out.println("Exiting...");
                    break;
                }

                default: {
                    System.out.println("Invalid choice");
                }
            }

        } while (choice != 0);

        sc.close();
    }

    private static void printTraversal(DSAQueue queue) {
        
        while (!queue.isEmpty()) {
            DSAGraphNode node = (DSAGraphNode) queue.dequeue();
            
            System.out.print(node.getLabel() + " ");
        }
        System.out.println();
    }
}
