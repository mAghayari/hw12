package dto.product;

public class Product implements Comparable<Product> {
    private String name;
    private int id;
    private int stock;
    private double price;
    private String brand;
    private String description;
    private ProductCategory category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Product id: " + id +
                "\nname: " + name +
                "\nprice: " + price +
                "\nbrand: " + brand +
                "\ncategory: " + category +
                "\nstock: " + stock +
                "\n" + description;
    }

    public String productStringForPrintOrder(){
        return "Product id: " + id +
                "\nname: " + name +
                "\nprice: " + price +
                "\nbrand: " + brand +
                "\ncategory: " + category;
    }

    @Override
    public int compareTo(Product product) {
        return this.getPrice() - product.getPrice() < 0 ? -1 : (this.getPrice() - product.getPrice() == 0 ? 0 : 1);
    }
}
