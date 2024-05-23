import java.util.*;
public class DSAStack<object> implements Iterable<object> {
    private DSALinkedList<object> list;

    public DSAStack() {
        list = new DSALinkedList<>();
    }

    public void push(object value) {
        list.insertFirst(value);
    }

    public object pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        object output = list.peekFirst();
        list.removeFirst();
        return output;
    }

    public object top() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
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
