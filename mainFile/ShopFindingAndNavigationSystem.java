import java.util.*;

public class ShopFindingAndNavigationSystem {
    private DSAGraph shopGraph;
    private DSAHashTable shopHashTable;

    public ShopFindingAndNavigationSystem() {
        shopGraph = new DSAGraph();
        shopHashTable = new DSAHashTable();
    }

    // Method to add a new shop node to the graph and the hash table
    public void addShopNode(String shopNumber, String shopName, String category, String location, int rating) {
        Shop newShop = new Shop(shopNumber, shopName, category, location, rating);
        DSAGraphVertex newShopNode = new DSAGraphVertex(shopNumber, newShop);
        shopGraph.addVertex(shopNumber, newShopNode);
        shopHashTable.put(category, newShop); // Use category as key for instant shop searching
    }
    
    // check if the vertex is present in the graph
    public boolean shopPresent(String shopNumber){
        boolean answer = false; 
        if (shopGraph.hasVertex(shopNumber)){
            answer =  true;
        }
        return answer;
        
    }

    // check if an edge is present between two vertexes 
    public boolean pathPresent(String shopNumber1, String shopNumber2){
        boolean answer = false; 
        if (shopGraph.isAdjacent(shopNumber1, shopNumber2)){
            answer =  true;
        }
        return answer;
        
    }

    // get the number of shops in the graph 
    public int numberOfShops(){
        return shopGraph.getVertexCount();
    }


    // Method to delete a shop node from the graph
    public void deleteShopNode(String shopNumber) {
        boolean found = false;
        for (DSAHashEntry entry : shopHashTable.getAllEntries()) {
            Shop shop = (Shop) entry.getValue();
            if (shop.getShopNumber().equals(shopNumber)) {
                found = true;
                shopGraph.deleteVertex(shopNumber);
                shopHashTable.remove(shop.getCategory(), shop);
            }
        }
        if (!found) {
            // Handle the case where the shop with the specified shopNumber isn't found
            throw new IllegalArgumentException("Shop with shopNumber " + shopNumber);
        }
    }
    

    // Method to update shop information
    public void updateShopInformation(String shopNumber, String shopName, String category, String location) {
        boolean found = false;
        for (DSAHashEntry entry : shopHashTable.getAllEntries()) {
            Shop shop = (Shop) entry.getValue();
            if (shop.getShopNumber().equals(shopNumber)) {
                found = true;
                // Remove the old entry from the hash table
                shopHashTable.remove(shop.getCategory(), shop);
    
                // Update the shop information
                shop.setShopName(shopName);
                shop.setCategory(category);
                shop.setLocation(location);
    
                // Add the updated shop object back to the hash table with the new category
                shopHashTable.put(category, shop);
                break; // Exit the loop once the shop is found and updated
            }
        }
        if (!found) {
            // Handle the case where the shop with the specified shopNumber isn't found
            throw new IllegalArgumentException("Shop with shopNumber " + shopNumber + " not found");
        }
    }
    
    
    
    // iterating through all the hash entries and comparing the category 
    public void searchByCategory(String category) {
        boolean found = false;
        for (DSAHashEntry entry : shopHashTable.getAllEntries()) {
            Shop shop = (Shop) entry.getValue();
            if (shop.getCategory().equals(category)) {
                found = true;
                System.out.println(shop);
                System.out.println("         ");
            }
        }
        if (!found) {
            System.out.println("No shops found in the category: " + category);
        }
    }
    
    
    
    public void addingEdge(String label1, String label2) {
        shopGraph.addEdge(label1, label2);
    }
    
    public void deletingEdge(String shopNumber1, String shopNumber2) {
        if (shopNumber1 != null && shopNumber2 != null) {
            // Check if both shops exist before attempting to delete the edge
            if (shopGraph.hasVertex(shopNumber1) || shopGraph.hasVertex(shopNumber2)) {
                shopGraph.deleteEdge(shopNumber1, shopNumber2);
            } else {
                throw new IllegalArgumentException("One or both vertices do not exist");
            }
        } else {
            throw new IllegalArgumentException("Invalid shop numbers");
        }
    }
    


    

    // Method to display the adjacency list of the graph
    public void displayGraph() {
        shopGraph.displayAsList();
    }

   

    private int calculateEdgeCount(DSAQueue<String> path) {
        DSAQueue<String> tem = new DSAQueue<>();
        for (String shop : path) {
            tem.enqueue(shop);
        }
    
        
        int edgeCount = 0;
    
        // Iterate through the path and count edges
        if(!tem.isEmpty()){
        String prevShop = tem.dequeue();
        while (!tem.isEmpty()) {
            String currentShop = tem.dequeue();
            if (shopGraph.isAdjacent(prevShop, currentShop)) {
                edgeCount++;
            }
            prevShop = currentShop;
        }
    }
        return edgeCount;
    }



    public void findPath(String sourceShop, String destinationShop) {
        if (sourceShop == null || destinationShop == null) {
            throw new IllegalArgumentException("Source and destination shops cannot be null");
        }
    
        if (!shopGraph.hasVertex(sourceShop) || !shopGraph.hasVertex(destinationShop)) {
            throw new IllegalArgumentException("Source or destination shop does not exist");
        }
    
        // Use DFS to find the path
        DSAQueue<String> dfsPath = shopGraph.depthFirstSearch(sourceShop,destinationShop );
        int dfsEdgeCount = calculateEdgeCount(dfsPath);
    
        // Use BFS to find the path
        DSAQueue<String> bfsPath = shopGraph.breadthFirstSearch(sourceShop,destinationShop);
        int bfsEdgeCount = calculateEdgeCount(bfsPath);
    
        // Display paths with error handling
        if (dfsPath.isEmpty()) {
            System.out.println("No path found using Depth-First Search");
            
        } else {
            System.out.println("Path found using Depth-First Search:");
            displayPath(dfsPath);
        }
    
        if (bfsPath.isEmpty()) {
            System.out.println("No path found using Breadth-First Search");
        } else {
            System.out.println("Path found using Breadth-First Search:");
            displayPath(bfsPath);
        }
        
        
        // Compare edge counts to find the shortest path
        System.out.println("DFS Edge Count: " + dfsEdgeCount);
        System.out.println("BFS Edge Count: " + bfsEdgeCount);
    
        // Display the shortest path based on edge count
        if (dfsEdgeCount < bfsEdgeCount) {
            System.out.println("Shortest path found using Depth-First Search:");
            displayPath(dfsPath);
        } else if (dfsEdgeCount > bfsEdgeCount){
            System.out.println("Shortest path found using Breadth-First Search:");
            displayPath(bfsPath);
        }
        else{
            System.out.println("Both Paths are the same distance");
            System.out.println("Depth-First Search:");
            displayPath(dfsPath);
            System.out.println("Breadth-First Search:");
            displayPath(bfsPath);
        }
    }
    
    private void displayPath(DSAQueue<String> path) {
        DSAStack<String> Temp = new DSAStack();
        for (String shop : path) {
            Temp.push(shop); // Push instead of enqueue to reverse the order
        }
        while (!Temp.isEmpty()) {
            System.out.print(Temp.pop() + " "); // pop to print in correct order
        }
        System.out.println();
    }

    public void displayShopInformation(){
        for (DSAHashEntry entry : shopHashTable.getAllEntries()) {
            Shop shop = (Shop) entry.getValue();
            
                System.out.println(shop);
                System.out.println("         ");
        }
    }
}
