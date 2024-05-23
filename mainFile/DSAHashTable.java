import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class DSAHashTable {
    

    private DSALinkedList<DSAHashEntry>[] hashArray;
    private int count;
    private int capacity;

    public DSAHashTable() {
        capacity = 11;
        hashArray = new DSALinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            hashArray[i] = new DSALinkedList<>();
        }
    }

    public void put(String key, Object value) {
        int hashIdx = hash(key, capacity);
        if (hashArray[hashIdx] == null) {
            hashArray[hashIdx] = new DSALinkedList<>();
        }
        // Handle collisions by appending to the linked list
        hashArray[hashIdx].insertLast(new DSAHashEntry(key, value));
        count++;
        
        // Resize the hash table if load factor exceeds a threshold
        if ((double) count / capacity > 0.75) {
            resize(capacity * 2);
        }
    }
    
    
    
    
    

    public Object get(String key) {
        int hashIdx = hash(key, capacity);
        for (DSAHashEntry entry : hashArray[hashIdx]) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        throw new IllegalArgumentException("Key not found");
    }

    public void remove(String key, Object value) {
        int hashIdx = hash(key, capacity);
        DSALinkedList<DSAHashEntry> bucket = hashArray[hashIdx];
        
        for (DSAHashEntry entry : bucket) {
            if (entry.getKey().equals(key) && entry.getValue().equals(value)) {
                bucket.deleteNodeWithValue(entry);
                count--;
            
                if ((double) count / capacity < 0.25 && capacity > 11) {
                    resize(capacity / 2);
                }
                return;
            }
        }
        
        throw new IllegalArgumentException("Key-value pair not found");
    }
    
    
    
    
    

    public double getLoadFactor() {
        return (double) count / capacity;
    }


    public int getCapacity() {
        return capacity;
    }

    private void resize(int size) {
        int newSize = nextPrime(size);
        DSALinkedList<DSAHashEntry>[] newArray = new DSALinkedList[newSize];
        for (int i = 0; i < newSize; i++) {
            newArray[i] = new DSALinkedList<>();
        }
        for (DSALinkedList<DSAHashEntry> bucket : hashArray) {
            for (DSAHashEntry entry : bucket) {
                int hashIdx = hash(entry.getKey(), newSize);
                newArray[hashIdx].insertLast(entry);
            }
        }
        hashArray = newArray;
        capacity = newSize;
    }

    private int hash(String key, int size) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash = (hash * 31 + key.charAt(i)) % size;
        }
        return hash;
    }


    public boolean hasKey(String key) {
    int hashIdx = hash(key, capacity);
    DSALinkedList<DSAHashEntry> bucket = hashArray[hashIdx];
    boolean answer = false;
   
    if (bucket != null && !bucket.isEmpty()) {
        for (DSAHashEntry entry : bucket) {
            if (entry != null && entry.getKey().equals(key)) {
                answer =  true;
            }
        }
    }
    return answer;
}

    
    

    private int nextPrime(int n) {
        while (!isPrime(n)) {
            n++;
        }
        return n;
    }

    private boolean isPrime(int n) {
        boolean answer = true;
        if (n <= 1) {
            answer =  false;
        }
        else if (n <= 3) {
            answer  = true;
        }
        else if (n % 2 == 0 || n % 3 == 0) {
            answer = false;
        }
        else { 
            for (int i = 5; i * i <= n; i += 6) {
                if ( answer = true ){
                    if (n % i == 0 || n % (i + 2) == 0) {
                        answer =  false;
                    }
                }
            }
        }
        return answer;
    }

    public void saveToCSV(String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (DSALinkedList<DSAHashEntry> bucket : hashArray) {
                for (DSAHashEntry entry : bucket) {
                    writer.write(entry.getKey() + "," + entry.getValue() + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Iterable<Object> getValues() {
        DSALinkedList<Object> values = new DSALinkedList<>();
        for (DSALinkedList<DSAHashEntry> bucket : hashArray) {
            for (DSAHashEntry entry : bucket) {
                values.insertLast(entry.getValue());
            }
        }
        return values;
    }


    
    public Iterable<DSAHashEntry> getEntriesInBucket(int bucketIndex) {
        return hashArray[bucketIndex];
    }
    
    

    public Iterable<DSAHashEntry> getAllEntries() {
        DSALinkedList<DSAHashEntry> allEntries = new DSALinkedList<>();
        for (DSALinkedList<DSAHashEntry> bucket : hashArray) {
            if (bucket != null) {
                for (DSAHashEntry entry : bucket) {
                    allEntries.insertLast(entry);
                }
            }
        }
        return allEntries;
    }
    
}
