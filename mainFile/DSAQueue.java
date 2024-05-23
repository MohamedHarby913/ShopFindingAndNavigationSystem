import java.io.Serializable;
import java.util.*;

public class DSAQueue<object> implements Iterable<object>, Serializable{
    private DSALinkedList<object> list;

    public DSAQueue() {
        list = new DSALinkedList<>();
    }

    public void enqueue(object value) {
        list.insertLast(value);
    }
    
    public object dequeue() {
        object output = peek();
        list.removeFirst(); 
        return output;
    }


    public object peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return list.peekFirst();
    }
    public boolean isEmpty() {
        return list.isEmpty();
    }

    public Iterator<object> iterator() {
        return list.iterator();
    }


}
