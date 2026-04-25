
public class DSAGraph {
    DSALinkedList vertices;

    public DSAGraph() {
        vertices = new DSALinkedList();
    }
// ADD Vertex
    public void addVertex(String label, Object value) {
        if (label == null || label.trim().isEmpty()) {
            throw new IllegalArgumentException("label cannot be null or empty");
        }
        if(hasVertex(label))
        {
            throw new IllegalArgumentException("Vertex already exists");
        }
        vertices.insertLast(new DSAGraphNode(label, value));
    }
// Remove Vertex
    public void removeVertex(String label) {
        DSAGraphNode vertexToRemove = getVertex(label);

        if (vertexToRemove == null)
            throw new IllegalArgumentException("Vertex not found");

        
        Object curr = vertices.getHead();

        while (curr != null) {
            DSAGraphNode v = (DSAGraphNode) vertices.getNodeValue(curr);
            v.getAdjacent().remove(vertexToRemove);
            curr = vertices.getNextNode(curr);
        }
        vertices.remove(vertexToRemove);
    }
// add Edge
    public void addEdge(String label1, String label2) {
        DSAGraphNode v1 = getVertex(label1);
        DSAGraphNode v2 = getVertex(label2);

        if (v1 == null || v2 == null) {
            throw new IllegalArgumentException("Vertex not found");
        }

        v1.addEdge(v2);
        v2.addEdge(v1);
    }
//Remove Edge
    public void removeEdge(String label1, String label2) {
        DSAGraphNode v1 = getVertex(label1);
        DSAGraphNode v2 = getVertex(label2);

        if (v1 == null || v2 == null)
            throw new IllegalArgumentException("One or both vertices not found");

        v1.getAdjacent().remove(v2);
        v2.getAdjacent().remove(v1); // remove this if directed graph
    }
// Check if Vertex if present or not
    public boolean hasVertex(String label) {
        boolean found = false;
        Object curr = vertices.getHead();

        while(curr != null && !found)
        {
            DSAGraphNode v = (DSAGraphNode) vertices.getNodeValue(curr);
            if(v.getLabel().equals(label))
            {
                found = true;
            }
            curr = vertices.getNextNode(curr);
        }
        return found;
        }
// Get Vertex Count
    public int getVertexCount() {

        return vertices.size();

    }
// Get Edge Count
    public int getEdgeCount() {
        int edgeCount = 0;
        Object curr = vertices.getHead();

        while(curr != null) 
        {
            DSAGraphNode v = (DSAGraphNode) vertices.getNodeValue(curr);
            edgeCount += v.getAdjacent().size();
            curr = vertices.getNextNode(curr);
        }

        edgeCount = edgeCount / 2; // For undirected graph so the program wont take same edge twice

        return edgeCount;
    }

    private DSAGraphNode getVertex(String label) {
        DSAGraphNode foundNode = null;
        boolean found = false;
        Object curr = vertices.getHead();

        while(curr != null && !found)
        {
            DSAGraphNode v = (DSAGraphNode) vertices.getNodeValue(curr);
            if (v.getLabel().trim().equals(label.trim())) {
                foundNode = v;
                found = true;   
            }
            curr = vertices.getNextNode(curr);
        }
        return foundNode;

    }

    private DSALinkedList getAdjacent(String label) {
        DSAGraphNode v = getVertex(label);
        if (v == null) {
            throw new IllegalArgumentException("Vertex not found");
        }
        return v.getAdjacent();
    }

    public boolean isAdjacent(String label1, String label2) {
        boolean found = false;
        DSAGraphNode v1 = getVertex(label1);

        if (v1 == null) {
            throw new IllegalArgumentException("Vertex not found");
        }

        DSALinkedList adjList = v1.getAdjacent();

        Object curr = adjList.getHead();

       while ( curr != null && !found)
       {
            DSAGraphNode v = (DSAGraphNode) adjList.getNodeValue(curr);
            if (v.getLabel().equals(label2)) {
                found = true;
            }
            curr = adjList.getNextNode(curr);

        }
        return found;

    }



    public void displayAsList() {
        Object curr = vertices.getHead();

        while( curr != null)
        {
            DSAGraphNode v = (DSAGraphNode) vertices.getNodeValue(curr);
            System.out.print(v.getLabel() + ": ");

            DSALinkedList adj = v.getAdjacent();
            Object adjCurr = adj.getHead();
            while( adjCurr != null)
            {
                DSAGraphNode w = (DSAGraphNode) adj.getNodeValue(adjCurr);
                System.out.print(w.getLabel() + " ");
                adjCurr = adj.getNextNode(adjCurr);
            }
            System.out.println();
            curr = vertices.getNextNode(curr);
        }
    }

    public void displayAsMatrix() {
        
        int n = vertices.size();
        DSAGraphNode[] arr = new DSAGraphNode[n];
        
        Object curr = vertices.getHead();
        int i = 0;

        while(curr!=null)
        {
            arr[i++] = (DSAGraphNode) vertices.getNodeValue(curr);
            curr = vertices.getNextNode(curr);
        }

        // Print header row
        System.out.print("  ");
        for ( i = 0; i < n; i++) {
            
            System.out.print(arr[i].getLabel() + " ");
        }
        System.out.println();

        // Print matrix body
        for (i = 0; i < n; i++) {
            ;
            System.out.print(arr[i].getLabel() + " ");

            for (int j = 0; j < n; j++) {
                

                if (arr[i].getAdjacent().contains(arr[j])) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }

            System.out.println();
        }

    }

    public DSAQueue breathFirstSearch() {
        DSAQueue T = new DSAQueue(100);
        DSAQueue Q = new DSAQueue(100);

        clearVisited();

        Object startObject = vertices.getHead();

        
        if (startObject != null)
    {
        DSAGraphNode v = (DSAGraphNode) vertices.getNodeValue(startObject);

        v.setVisited();
        Q.enqueue(v);

        while (!Q.isEmpty())
        {
            
            v = (DSAGraphNode) Q.dequeue();
            DSALinkedList adjList = v.getAdjacent();
            Object curr = adjList.getHead();

           while(curr != null)
            {
                DSAGraphNode w = (DSAGraphNode) adjList.getNodeValue(curr);

                if (!w.getVisited())
                {
                    w.setVisited();

                    T.enqueue(v);
                    T.enqueue(w);

                    Q.enqueue(w);
                }
                curr = adjList.getNextNode(curr);
            }
        }
    }

    return T;
    }

    public DSAQueue depthFirstSearch()
{
    DSAQueue T = new DSAQueue(100);
    DSAStack S = new DSAStack(100);

    clearVisited();

    Object startObject = vertices.getHead();

    if(startObject != null)
    {
        DSAGraphNode v = (DSAGraphNode) vertices.getNodeValue(startObject);

        v.setVisited();
        S.push(v);

        while (!S.isEmpty())
        {
            v = (DSAGraphNode) S.peek();

            DSAGraphNode w = getUnvisitedAdjacent(v);

            if (w != null)
            {
                w.setVisited();

                T.enqueue(v);
                T.enqueue(w);

                S.push(w);
            }
            else
            {
                S.pop();
            }
        }
    }

    return T;
}

    private DSAGraphNode getUnvisitedAdjacent(DSAGraphNode v) {
        DSAGraphNode unvisited = null;
        DSALinkedList adj = v.getAdjacent();
        boolean found = false;
        Object curr = adj.getHead();

        while (curr != null && !found) {
            DSAGraphNode w = (DSAGraphNode) vertices.getNodeValue(curr);

            if (!w.getVisited()) {
                unvisited = w;
                found = true;
            }

            curr = adj.getNextNode(curr);
        }

        return unvisited;
    }

    private void clearVisited() {
        Object curr = vertices.getHead();

        while(curr != null)
        {
            DSAGraphNode v = (DSAGraphNode) vertices.getNodeValue(curr);
            v.clearVisited();
            curr = vertices.getNextNode(curr);
        }
    }

    
}
