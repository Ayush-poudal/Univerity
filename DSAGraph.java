
public class DSAGraph {
    DSALinkedList vertices;

    public DSAGraph() {
        vertices = new DSALinkedList();
    }

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

    public void removeVertex(String label) {
        DSAGraphNode vertexToRemove = getVertex(label);

        if (vertexToRemove == null)
            throw new IllegalArgumentException("Vertex not found");

        // Get array copy of vertices
        Object[] vertexArray = vertices.toArray();

        for (int i = 0; i < vertexArray.length; i++) {
            DSAGraphNode v = (DSAGraphNode) vertexArray[i];
            v.getAdjacent().remove(vertexToRemove);
        }

        // Remove from main vertex list
        vertices.remove(vertexToRemove);
    }

    public void addEdge(String label1, String label2) {
        DSAGraphNode v1 = getVertex(label1);
        DSAGraphNode v2 = getVertex(label2);

        if (v1 == null || v2 == null) {
            throw new IllegalArgumentException("Vertex not found");
        }

        v1.addEdge(v2);
        v2.addEdge(v1);
    }

    public void removeEdge(String label1, String label2) {
        DSAGraphNode v1 = getVertex(label1);
        DSAGraphNode v2 = getVertex(label2);

        if (v1 == null || v2 == null)
            throw new IllegalArgumentException("One or both vertices not found");

        v1.getAdjacent().remove(v2);
        v2.getAdjacent().remove(v1); // remove this if directed graph
    }

    public boolean hasVertex(String label) {
        boolean found = false;
        Object[] arr = vertices.toArray();

        for (int i = 0; i < arr.length & !found; i++) {
            DSAGraphNode v = (DSAGraphNode) arr[i];

            if (v.getLabel().equals(label))

            {
                found = true;
            }
        }
        return found;
    }

    public int getVertexCount() {

        return vertices.size();

    }

    public int getEdgeCount() {
        int edgeCount = 0;
        Object[] arr = vertices.toArray();

        for (int i = 0; i < arr.length; i++) {
            DSAGraphNode v = (DSAGraphNode) arr[i];
            edgeCount += v.getAdjacent().size();
        }

        edgeCount = edgeCount / 2;

        return edgeCount;
    }

    private DSAGraphNode getVertex(String label) {
        DSAGraphNode foundNode = null;
        boolean found = false;
        Object[] arr = vertices.toArray();

        for (int i = 0; i < arr.length && !found; i++) {
            DSAGraphNode v = (DSAGraphNode) arr[i];

            if (v.getLabel().trim().equals(label.trim())) {
                foundNode = v;
                found = true;
            }
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

        Object[] adjArr = adjList.toArray();

        for (int i = 0; i < adjArr.length; i++) {
            DSAGraphNode v = (DSAGraphNode) adjArr[i];
            if (v.getLabel().equals(label2)) {
                found = true;
            }

        }
        return found;

    }

    public void displayAsList() {
        Object[] arr = vertices.toArray();

        for (int i = 0; i < arr.length; i++) {
            DSAGraphNode v = (DSAGraphNode) arr[i];
            System.out.print(v.getLabel() + ": ");

            Object[] adj = v.getAdjacent().toArray();
            for (int j = 0; j < adj.length; j++) {
                DSAGraphNode w = (DSAGraphNode) adj[j];
                System.out.print(w.getLabel() + " ");
            }
            System.out.println();
        }
    }

    public void displayAsMatrix() {
        Object[] arr = vertices.toArray();
        int n = arr.length;

        // Print header row
        System.out.print("  ");
        for (int i = 0; i < n; i++) {
            DSAGraphNode v = (DSAGraphNode) arr[i];
            System.out.print(v.getLabel() + " ");
        }
        System.out.println();

        // Print matrix body
        for (int i = 0; i < n; i++) {
            DSAGraphNode rowVertex = (DSAGraphNode) arr[i];
            System.out.print(rowVertex.getLabel() + " ");

            for (int j = 0; j < n; j++) {
                DSAGraphNode colVertex = (DSAGraphNode) arr[j];

                if (rowVertex.getAdjacent().contains(colVertex)) {
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

        Object[] arr = vertices.toArray();
        
        if (arr.length > 0)
    {
        DSAGraphNode v = (DSAGraphNode) arr[0];

        v.setVisited();
        Q.enqueue(v);
        while (!Q.isEmpty())
        {
            
            v = (DSAGraphNode) Q.dequeue();

            Object[] adj = v.getSortedAdjacent();

            for (int i = 0; i < adj.length; i++)
            {
                DSAGraphNode w = (DSAGraphNode) adj[i];

                if (!w.getVisited())
                {
                    w.setVisited();

                    T.enqueue(v);
                    T.enqueue(w);

                    Q.enqueue(w);
                }
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

    Object[] arr = vertices.toArray();

    if (arr.length > 0)
    {
        DSAGraphNode v = (DSAGraphNode) arr[0];

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
        Object[] adj = v.getAdjacent().toArray();
        boolean found = false;
        int i = 0;

        while (i < adj.length && !found) {
            DSAGraphNode w = (DSAGraphNode) adj[i];

            if (!w.getVisited()) {
                unvisited = w;
                found = true;
            }

            i++;
        }

        return unvisited;
    }

    private void clearVisited() {
        Object[] arr = vertices.toArray();

        for (int i = 0; i < arr.length; i++) {
            DSAGraphNode v = (DSAGraphNode) arr[i];
            v.clearVisited();
        }
    }

    
}
