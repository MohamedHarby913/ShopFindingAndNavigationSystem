public class ShopNavigationTest {

    public static void main(String[] args) {
        testAddShop();
        testDeleteShop();
        testAddEdge();
        testDeleteEdge();
        testSearchByCategory();
        testFindPath();
        testUpdateShop();
    }

    public static void testAddShop() {
        ShopFindingAndNavigationSystem system = new ShopFindingAndNavigationSystem();
        
        // Adding new shops
        system.addShopNode("1", "LULU", "Grocery", "Silicon Oasis", 4);
        system.addShopNode("2", "Zara", "Clothing", "Waraaq", 5);
        system.addShopNode("3", "Sharf DG", "Electronics", "Dubai Mall", 3);

        // Displaying the graph after adding shops
        System.out.println("Displaying the graph after adding shops:");
        system.displayGraph();
        System.out.println();
    }

    public static void testDeleteShop() {
        ShopFindingAndNavigationSystem system = new ShopFindingAndNavigationSystem();

        // Adding some shops
        system.addShopNode("1", "LULU", "Grocery", "Silicon Oasis", 4);
        system.addShopNode("2", "Zara", "Clothing", "Waraaq", 5);

        // Deleting a shop
        system.deleteShopNode("1");

        // Displaying the graph after deleting a shop
        System.out.println("Displaying the graph after deleting a shop:");
        system.displayGraph();
        System.out.println();
    }

    public static void testAddEdge() {
        ShopFindingAndNavigationSystem system = new ShopFindingAndNavigationSystem();

        // Adding some shops
        system.addShopNode("1", "LULU", "Grocery", "Silicon Oasis", 4);
        system.addShopNode("2", "Zara", "Clothing", "Waraaq", 5);

        // Establishing a path between shops
        system.addingEdge("1", "2");

        // Displaying the graph after adding an edge
        System.out.println("Displaying the graph after adding an edge:");
        system.displayGraph();
        System.out.println();
    }

    public static void testDeleteEdge() {
        ShopFindingAndNavigationSystem system = new ShopFindingAndNavigationSystem();

        // Adding some shops and establishing a path
        system.addShopNode("1", "LULU", "Grocery", "Silicon Oasis", 4);
        system.addShopNode("2", "Zara", "Clothing", "Waraaq", 5);
        system.addingEdge("1", "2");

        // Deleting a path between shops
        system.deletingEdge("1", "2");

        // Displaying the graph after deleting an edge
        System.out.println("Displaying the graph after deleting an edge:");
        system.displayGraph();
        System.out.println();
    }

    public static void testSearchByCategory() {
        ShopFindingAndNavigationSystem system = new ShopFindingAndNavigationSystem();

        // Adding some shops
        system.addShopNode("1", "LULU", "Grocery", "Silicon Oasis", 4);
        system.addShopNode("2", "Zara", "Clothing", "Waraaq", 5);
        system.addShopNode("4", "Carrfor", "Grocery", "Dubai Mall", 1);


        // Searching for shops by category
        System.out.println("Searching for shops in the 'Grocery' category:");
        system.searchByCategory("Grocery");
        System.out.println();


        System.out.println("Display all shops ");
        system.displayShopInformation();
        System.out.println();


    }

    public static void testFindPath() {
        ShopFindingAndNavigationSystem system = new ShopFindingAndNavigationSystem();

        // Adding some shops and establishing a path
        system.addShopNode("1", "Zara", "Clothing", "Silicon", 5);
        system.addShopNode("2", "Zara", "Clothing", "Waraaq", 2);
        system.addShopNode("3", "Sharf DG", "Electronics", "Mirdif", 3);
        system.addShopNode("4", "Carrfor", "Grocery", "Dubai Mall", 1);
        system.addShopNode("5", "Version", "Electronics", "Silicon", 4);
        system.addingEdge("1", "2");
        system.addingEdge("2", "3");
        system.addingEdge("1", "4");
        system.addingEdge("2", "3");
        system.addingEdge("3", "4");
        system.addingEdge("3", "5");
        system.addingEdge("4", "5");


        // Finding a path between shops
        System.out.println("Finding a path between shops:");
        system.findPath("1", "5");
        System.out.println();
    }

    public static void testUpdateShop() {
        ShopFindingAndNavigationSystem system = new ShopFindingAndNavigationSystem();

        // Adding a shop
        system.addShopNode("1", "LULU", "Grocery", "Silicon Oasis", 4);

        // Updating shop information
        system.updateShopInformation("1", "Lulu", "Grocery", "Dubai Silicon Oasis");

        System.out.println("Searching for shops in the 'Grocery' category:");
        system.searchByCategory("Grocery");
        System.out.println();


    }
}
