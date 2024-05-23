public class Shop {
    private String shopNumber;
    private String shopName;
    private String category;
    private String location;
    private int rating;

    public Shop(String shopNumber, String shopName, String category, String location, int rating) {
        this.shopNumber = shopNumber;
        this.shopName = shopName;
        this.category = category;
        this.location = location;
        this.rating = rating;
    }

    public String getShopNumber() {
        return shopNumber;
    }

    public void setShopNumber(String shopNumber) {
        this.shopNumber = shopNumber;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    
    public String toString() {
        return "Shop: " +
                "shopNumber = " + shopNumber + '\n' +
                "shopName = " + shopName + '\n' +
                "category = " + category + '\n' +
                "location = " + location + '\n' +
                "rating = " + rating;
    }
}
