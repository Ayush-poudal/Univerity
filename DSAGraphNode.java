import java.util.Arrays;
import java.util.Comparator;

public class DSAGraphNode 
{
    String label;
    Object value;
    boolean visited;
    DSALinkedList adjacencyList;
    
    public DSAGraphNode(String inLabel, Object inValue)
    {
        label = inLabel;
        value = inValue;
        adjacencyList = new DSALinkedList();
        visited = false; 
    }

    public String getLabel()
    {
        return label;
    }

    public Object getValue()
    {
        return value;
    }

    public DSALinkedList getAdjacent()
    {
        return adjacencyList;
    }

    public boolean getVisited()
    {
        return visited;
    }

    public String toString()
    {
        return label;
    }

    public void setVisited()
    {
        visited = true;
    }

    public void clearVisited()
    {
        visited = false;
    }

    public void addEdge(DSAGraphNode vertex)
    {
        if(vertex == null)
        {
            throw new IllegalArgumentException("Vertex cannot be null");
        }
        if(!adjacencyList.contains(vertex))
        {
            adjacencyList.insertLast(vertex);
        }
    }

    @Override
    public boolean equals(Object obj)
    {
        boolean equal = false;

        if (obj instanceof DSAGraphNode)
        {
            DSAGraphNode other = (DSAGraphNode)obj;
            equal = this.label.equals(other.label);
        }

        return equal;
    }

    public DSAGraphNode[] getSortedAdjacent()
{
    Object[] adj = adjacencyList.toArray(); 
    DSAGraphNode[] nodes = new DSAGraphNode[adj.length];

    for (int i = 0; i < adj.length; i++)
    {
        nodes[i] = (DSAGraphNode) adj[i];
    }

    Arrays.sort(nodes, new Comparator<DSAGraphNode>() {
        public int compare(DSAGraphNode a, DSAGraphNode b)
        {
            return a.getLabel().compareTo(b.getLabel());
        }
    });

    return nodes;
}
}
