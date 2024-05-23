import java.util.*;

public class DSAGraphVertex {
    private String label;
    private Object value;
    private DSALinkedList<DSAGraphVertex> links;
    private boolean visited;
    private DSALinkedList<DSAGraphVertex> adjacent;

    public DSAGraphVertex(String inLabel, Object inValue) {
        label = inLabel;
        value = inValue;
        links = new DSALinkedList<>(); 
        visited = false;
        this.adjacent = new DSALinkedList<>();
    }

   
    public String getLabel() {
        return label;
    }

    public Object getValue() {
        return value;
        
    }

    public DSALinkedList<DSAGraphVertex> getAdjacent() { 
        return links;
    }

    public void addEdge(DSAGraphVertex vertex) {
        links.insertLast(vertex); 
    }

    
    
    
    

    public void clearEdges() {
        adjacent = new DSALinkedList<>(); 
    }
    
    


    public void setVisited(boolean status) {
        visited = status;
    }

    public void clearVisited() {
        visited = false;
    }

    public boolean getVisited() {
        return visited;
    }

    public String toString() {
        return label;
    }
}
