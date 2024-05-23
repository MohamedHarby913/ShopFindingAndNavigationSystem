import java.util.*;

public class DSAGraph {
    private DSALinkedList<DSAGraphVertex> vertices;

    public DSAGraph() {
        vertices = new DSALinkedList<>();
    }

    public void addVertex(String label, Object value) {
        DSAGraphVertex newVertex = new DSAGraphVertex(label, value);
        vertices.insertLast(newVertex);
    }

    

    public void addEdge(String label1, String label2) {
        DSAGraphVertex vertex1 = getVertex(label1);
        DSAGraphVertex vertex2 = getVertex(label2);

        if (vertex1 == null || vertex2 == null) {
            throw new IllegalArgumentException("One or both vertices do not exist");
        }

        vertex1.addEdge(vertex2);
       
        vertex2.addEdge(vertex1);
    }

    public void deleteEdge(String label1, String label2) {
        DSAGraphVertex vertex1 = getVertex(label1);
        DSAGraphVertex vertex2 = getVertex(label2);
    
        if (vertex1 == null || vertex2 == null) {
            throw new IllegalArgumentException("One or both vertices do not exist");
        }
    
        vertex1.getAdjacent().deleteNodeWithValue(vertex2);
        vertex2.getAdjacent().deleteNodeWithValue(vertex1);
    }
    
   
    public void deleteVertex(String label) {
        DSAGraphVertex vertexToRemove = getVertex(label);
        if (vertexToRemove != null) {
            for (DSAGraphVertex vertex : vertices) {
                if (vertex != vertexToRemove) {
                    vertex.getAdjacent().deleteNodeWithValue(vertexToRemove);
                }
            }
            vertices.deleteNodeWithValue(vertexToRemove);
        }
    }
    

    public boolean hasVertex(String label) {
        return getVertex(label) != null;
    }

    public int getVertexCount() {
        int count = 0;
        for (DSAGraphVertex vertex : vertices) {
            count++;
        }
        return count;
    }

    public int getEdgeCount() {
        int count = 0;
        for (DSAGraphVertex vertex : vertices) {
            DSALinkedList<DSAGraphVertex> adjacentVertices = vertex.getAdjacent();
            count += countAdjacent(adjacentVertices);
        }
        return count / 2;
    }
    
    private int countAdjacent(DSALinkedList<DSAGraphVertex> adjacentVertices) {
        int count = 0;
        for (DSAGraphVertex vertex : adjacentVertices) {
            count++;
        }
        return count;
    }
    
    public Iterable<DSAGraphVertex> getVertices() {
    return vertices;
}

    public DSAGraphVertex getVertex(String label) {
        DSAGraphVertex value = null;
        for (DSAGraphVertex vertex : vertices) {
            if (vertex.getLabel().equals(label)) {
                value = vertex;
            }
        }
        return value;
    }

    public DSALinkedList<DSAGraphVertex> getAdjacent(String label) {
        DSAGraphVertex vertex = getVertex(label);
        if (vertex == null) {
            throw new IllegalArgumentException("Vertex does not exist");
        }
        DSALinkedList<DSAGraphVertex> adjacentVertices = new DSALinkedList<>();
        for (DSAGraphVertex adjacent : vertex.getAdjacent()) {
            adjacentVertices.insertLast(adjacent);
        }
        return adjacentVertices;
    }
    
    

    public boolean isAdjacent(String label1, String label2) {
        DSAGraphVertex vertex1 = getVertex(label1);
        DSAGraphVertex vertex2 = getVertex(label2);
        boolean answer = false;
        if (vertex1 == null || vertex2 == null) {
            throw new IllegalArgumentException("One or both vertices do not exist");
        }
        
        DSALinkedList<DSAGraphVertex> adjacentVertices = vertex1.getAdjacent();
        for (DSAGraphVertex adjacent : adjacentVertices) {
            if (adjacent.getLabel().equals(label2)) {
                answer =  true;
            }
        }
        return answer;
    }
    

    public void displayAsList() {
        for (DSAGraphVertex vertex : vertices) {
            System.out.print(vertex + ": ");
            for (DSAGraphVertex adjacent : vertex.getAdjacent()) {
                System.out.print(adjacent + " ");
            }
            System.out.println();
        }
    }

    public void displayAsMatrix() {
        int n = getVertexCount();
        boolean[][] matrix = new boolean[n][n];

        for (DSAGraphVertex vertex : vertices) {
            int i = getIndex(vertex.getLabel());
            for (DSAGraphVertex adjacent : vertex.getAdjacent()) {
                int j = getIndex(adjacent.getLabel());
                matrix[i][j] = true;
            }
        }

        
        for (boolean[] row : matrix) {
            for (boolean value : row) {
                System.out.print((value ? 1 : 0) + " ");
            }
            System.out.println();
        }
    }

    private int getIndex(String label) {
        int index = 0;
        int answer = -1;
        for (DSAGraphVertex vertex : vertices) {
            if (vertex.getLabel().equals(label)) {
                answer =  index;
            }
            index++;
        }
        return answer;
    }


    public DSAQueue<String> breadthFirstSearch(String startShop, String destinationShop) {
        DSAGraphVertex startVertex = getVertex(startShop);
        DSAGraphVertex destinationVertex = getVertex(destinationShop);
    
        if (startVertex == null || destinationVertex == null) {
            throw new IllegalArgumentException("One or both vertices do not exist");
        }
    
        resetVisitedStatus();
    
        DSAQueue<String> traversalPath = new DSAQueue<>();
        DSAQueue<String> queue = new DSAQueue<>(); 
    
        startVertex.setVisited(true);
        queue.enqueue(startShop);
    
        DSAHashTable parentMap = new DSAHashTable(); 
        parentMap.put(startShop, null);
    
        while (!queue.isEmpty()) {
            String currentShop = queue.dequeue();
            traversalPath.enqueue(currentShop);
    
            if (currentShop.equals(destinationShop)) {
                return constructPath(startVertex, destinationVertex, parentMap);
            }
    
            DSAGraphVertex currentVertex = getVertex(currentShop);
            for (DSAGraphVertex adjacentVertex : currentVertex.getAdjacent()) {
                String adjacentShop = adjacentVertex.getLabel();
                if (!adjacentVertex.getVisited()) {
                    adjacentVertex.setVisited(true);
                    queue.enqueue(adjacentShop);
                    parentMap.put(adjacentShop, currentShop);
                }
            }
        }
    
        throw new IllegalArgumentException("No path exists between the specified shops");
    }
    
    public DSAQueue<String> depthFirstSearch(String sourceLabel, String destinationLabel) {
        DSAGraphVertex sourceVertex = getVertex(sourceLabel);
        DSAGraphVertex destinationVertex = getVertex(destinationLabel);
        DSAQueue<String> answer = new DSAQueue<>();
    
        if (sourceVertex == null || destinationVertex == null) {
            throw new IllegalArgumentException("Source or destination vertex does not exist");
        }
    
        resetVisitedStatus();
    
        DSAHashTable parentMap = new DSAHashTable(); 
        boolean found = DFSUtil(sourceVertex, destinationVertex, parentMap);
    
        if (found) {
            answer = constructPath(sourceVertex, destinationVertex, parentMap);
        }
    
        return answer;
    }
    
    private void resetVisitedStatus() {
        for (DSAGraphVertex vertex : vertices) {
            vertex.setVisited(false);
        }
    }
    
    private boolean DFSUtil(DSAGraphVertex currentVertex, DSAGraphVertex destinationVertex, DSAHashTable parentMap) {
        boolean found = DFSUtilRecursive(currentVertex, destinationVertex, parentMap);
    
        return found;
    }
    
    private boolean DFSUtilRecursive(DSAGraphVertex currentVertex, DSAGraphVertex destinationVertex, DSAHashTable parentMap) {
        boolean answer = false;
        if (currentVertex.equals(destinationVertex)) {
            answer =  true;
        }
        
        if(!answer){
        currentVertex.setVisited(true);
    
            for (DSAGraphVertex adjacentVertex : currentVertex.getAdjacent()) {
                if (!adjacentVertex.getVisited()) {
                    parentMap.put(adjacentVertex.getLabel(), currentVertex.getLabel());
                    if (DFSUtilRecursive(adjacentVertex, destinationVertex, parentMap)) {
                        answer =  true;
                    }
                }
            }
        }
        return answer;
    }
    
    private DSAQueue<String> constructPath(DSAGraphVertex startVertex, DSAGraphVertex destinationVertex, DSAHashTable parentMap) {
        DSAQueue<String> path = new DSAQueue<>();
        DSAGraphVertex currentVertex = destinationVertex;
    
        while (currentVertex != null && !currentVertex.equals(startVertex)) {
            path.enqueue(currentVertex.getLabel());
            String parentLabel = (String) parentMap.get(currentVertex.getLabel()); 
            currentVertex = getVertex(parentLabel);
        }
    
        path.enqueue(startVertex.getLabel());
        return path;
    }
    
    
    
    
    
    
}
