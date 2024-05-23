import java.io.Serializable;
import java.util.*;

public class DSALinkedList<object> implements Iterable<object>, Serializable {

    private DSAListNode<object> head;
    private DSAListNode<object> tail; 

    private static class DSAListNode<object> implements Serializable{
        private object value;
        private DSAListNode<object> next;
        private DSAListNode<object> prev;

        public DSAListNode(object value) {
            this.value = value;
        }
    }

    public DSALinkedList() {
        head = null;
        tail = null;
    }
    public void insertFirst(object newValue) {
        DSAListNode<object> newNode = new DSAListNode<object> (newValue);
        if (isEmpty()) {
            tail = newNode;  
        } else {
            newNode.next = head;
            head.prev = newNode;
        }
        head = newNode;
    }

    public void insertLast(object newValue) {
        DSAListNode<object> newNode = new DSAListNode<object> (newValue);
        if (isEmpty()) {
            head = newNode;  
        } else {
            tail.next = newNode;
            newNode.prev = tail;
        }
        tail = newNode;
    }

    
    public void deleteNodeWithValue(object valueToRemove) {
        DSAListNode<object> current = head;
        while (current != null) {
            if (current.value.equals(valueToRemove)) {
                if (current == head) {
                    head = head.next;
                    if (head == null) {
                        tail = null;  
                    } else {
                        head.prev = null;  
                    }
                } else if (current == tail) {
                    tail = tail.prev;
                    if (tail == null) {
                        head = null;  
                    } else {
                        tail.next = null;  
                    }
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                return; 
            }
            current = current.next;
        }
    }
    

    public boolean isEmpty() {
        return head == null;
    }

    public object peekFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        return head.value;
    }
    
    public object peekLast() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        return tail.value;
    }
    
    public object removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
    
        object nodeValue = head.value;
        head = head.next;
        if (head == null) {
            tail = null;  
        } else {
            head.prev = null;  
        }
        return nodeValue;
    }
    
    public object removeLast() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
    
        object nodeValue = tail.value;
        tail = tail.prev;
        if (tail == null) {
            head = null;  
        } else {
            tail.next = null;  
        }
        return nodeValue;
    }

    public Iterator<object> iterator() {
        return new DSALinkedListIterator<object>(head);
    }
    
    private static class DSALinkedListIterator<object> implements Iterator<object>, Serializable{
        private DSAListNode<object> current;

        public  DSALinkedListIterator(DSAListNode<object> head) {
            current = head;
        }

        public boolean hasNext() {
            return current != null;
        }

        public object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            object value = current.value;
            current = current.next;
            return value;
        }

        public void remove() {
            throw new UnsupportedOperationException("remove");
        }
    }
    
}
