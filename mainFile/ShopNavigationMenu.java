import java.util.*;

public class ShopNavigationMenu {
    

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ShopFindingAndNavigationSystem Navigationsystem = new ShopFindingAndNavigationSystem();
        boolean exit = false;
        while (!exit) {
            System.out.println("=== Shop Navigation System Menu ===");
            System.out.println("1. New shop ");
            System.out.println("2. Close a shop");
            System.out.println("3. Establishing new path");
            System.out.println("4. Close a path");
            System.out.println("5. Search for shops by category");
            System.out.println("6. Find a path between shops");
            System.out.println("7. Show the shops on the map");
            System.out.println("8. display all shops information");
            System.out.println("9. Update shop information");
            System.out.println("10. Exit");
            System.out.println(" ");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine(); 
            System.out.println(" ");

            switch (choice) {
                case 1:
                    addShop(input, Navigationsystem);
                    System.out.println(" ");
                    break;
                case 2:
                    deleteShop(input, Navigationsystem);
                    System.out.println(" ");

                    break;
                case 3:
                    addEdge(input, Navigationsystem);
                    System.out.println(" ");

                    break;
                case 4:
                    deleteEdge(input, Navigationsystem);
                    System.out.println(" ");

                    break;
                case 5:
                    searchByCategory(input, Navigationsystem);
                    System.out.println(" ");

                    break;
                case 6:
                    findPath(input, Navigationsystem);
                    System.out.println(" ");

                    break;
                case 7:
                    Navigationsystem.displayGraph();
                    System.out.println(" ");

                    break;
                case 8:
                    Navigationsystem.displayShopInformation();
                    System.out.println(" ");

                    break;
                case 9:
                    UpdateShop( input,  Navigationsystem);
                    System.out.println(" ");

                    break;
                case 10:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 10.");
                    System.out.println(" ");

            }
            System.out.println(); 
        }
        input.close();
    }

    public static void addShop(Scanner input, ShopFindingAndNavigationSystem Navigationsystem) {
        String shopNumber;
        int rating;
        do {
            System.out.print("Enter shop number: ");
            shopNumber = input.nextLine();
            if (Navigationsystem.shopPresent(shopNumber)){
                System.out.println("There is already a shop with that Shop Number");
            }
        } while (Navigationsystem.shopPresent(shopNumber));
        System.out.print("Enter shop name: ");
        String shopName = input.nextLine();
        System.out.print("Enter category: ");
        String category = input.nextLine();
        System.out.print("Enter location: ");
        String location = input.nextLine();
        do {
            System.out.print("Enter rating: ");
            rating = input.nextInt();
            input.nextLine();
            if (rating < 1 || rating > 5 ){
                System.out.println("The shop rating should be between 1-5 ");
            }
        } while (rating < 1 || rating > 5);
        
        Navigationsystem.addShopNode(shopNumber, shopName, category, location, rating);
        System.out.println("Shop added successfully.");
    }

    public static void deleteShop(Scanner input, ShopFindingAndNavigationSystem Navigationsystem) {
        String shopNumber;
        if (Navigationsystem.numberOfShops() > 0 ){
            do {
                System.out.print("Enter shop number to close: ");
                shopNumber = input.nextLine();
                if (!Navigationsystem.shopPresent(shopNumber)){
                    System.out.println("There is no a shop with that Shop Number");
                }
            } while (!Navigationsystem.shopPresent(shopNumber));
            Navigationsystem.deleteShopNode(shopNumber);
            System.out.println("Shop closed successfully.");
        }
        else {
            System.out.println("There are no shops in the map to begin with");
        }
    }

    public static void addEdge(Scanner input, ShopFindingAndNavigationSystem Navigationsystem) {
        String shopNumber1, shopNumber2;
        if (Navigationsystem.numberOfShops() > 1){
            do {
                System.out.print("Enter shop number 1: ");
                shopNumber1 = input.nextLine();
                if (!Navigationsystem.shopPresent(shopNumber1)){
                    System.out.println("There is no a shop with that Shop Number");
                }
            } while (!Navigationsystem.shopPresent(shopNumber1));
            do {
                System.out.print("Enter shop number 2: ");
                shopNumber2 = input.nextLine();
                if (!Navigationsystem.shopPresent(shopNumber2)){
                    System.out.println("There is no a shop with that Shop Number");
                }
            } while (!Navigationsystem.shopPresent(shopNumber2));
            if(!Navigationsystem.pathPresent(shopNumber1, shopNumber2)){
                Navigationsystem.addingEdge(shopNumber1, shopNumber2);
                System.out.println("Path added successfully.");
            }
            else {
                System.out.println("The path is already present");
            }
        }
        else {
            System.out.println("There are no enough shops to create a path between");
        }
    }

    public static void deleteEdge(Scanner input, ShopFindingAndNavigationSystem Navigationsystem) {
        String shopNumber1, shopNumber2;
        if (Navigationsystem.numberOfShops() > 1){
            do {
                System.out.print("Enter shop number 1: ");
                shopNumber1 = input.nextLine();
                if (!Navigationsystem.shopPresent(shopNumber1)){
                    System.out.println("There is no a shop with that Shop Number");
                }
            } while (!Navigationsystem.shopPresent(shopNumber1));
            do {
                System.out.print("Enter shop number 2: ");
                shopNumber2 = input.nextLine();
                if (!Navigationsystem.shopPresent(shopNumber2)){
                    System.out.println("There is no a shop with that Shop Number");
                }
            } while (!Navigationsystem.shopPresent(shopNumber2));
            if (Navigationsystem.pathPresent(shopNumber1, shopNumber2) ){
                Navigationsystem.deletingEdge(shopNumber1, shopNumber2);
                System.out.println("Path deleted successfully.");
            }
            else{
                System.out.println("There is already no path between these two shops");
            }
        }
        else {
            System.out.println("There are no enough shops to close a path");
        }
    }

    public static void searchByCategory(Scanner input, ShopFindingAndNavigationSystem Navigationsystem) {
        System.out.print("Enter category to search: ");
        String category = input.nextLine();
        Navigationsystem.searchByCategory(category);
    }

    public static void findPath(Scanner input, ShopFindingAndNavigationSystem Navigationsystem) {
        String sourceShop, destinationShop;
        do {
            System.out.print("Enter source shop number: ");
            sourceShop = input.nextLine();
            if (!Navigationsystem.shopPresent(sourceShop)){
                System.out.println("There is no a shop with that Shop Number");
            }
        } while (!Navigationsystem.shopPresent(sourceShop));
        do {
            System.out.print("Enter destination shop number: ");
            destinationShop = input.nextLine();
            if (!Navigationsystem.shopPresent(destinationShop)){
                System.out.println("There is no a shop with that Shop Number");
            }
        } while (!Navigationsystem.shopPresent(destinationShop));
        Navigationsystem.findPath(sourceShop, destinationShop);
    }


    public static void UpdateShop(Scanner input, ShopFindingAndNavigationSystem Navigationsystem) {
        String shopNumber;
        if( Navigationsystem.numberOfShops() > 0){
            do {
                System.out.print("Enter shop number: ");
                shopNumber = input.nextLine();
                if (!Navigationsystem.shopPresent(shopNumber)){
                    System.out.println("There is no shop with that Shop Number");
                }
            } while (!Navigationsystem.shopPresent(shopNumber));
            System.out.print("Enter new shop name: ");
            String shopName = input.nextLine();
            System.out.print("Enter new category: ");
            String category = input.nextLine();
            System.out.print("Enter new location: ");
            String location = input.nextLine();
            
            Navigationsystem.updateShopInformation(shopNumber, shopName, category, location);
            System.out.println("Shop Updated successfully.");
        }
        else {
            System.out.println("There are no shops to begin with");
        }
    }
}
