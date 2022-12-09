package pl.edu.wszib.store.paints.model;

public class Product {
    private int id;
    private String company;
    private String name;
    private Categories category;
    private double price;
    private int quantity;
    private boolean available;

    public Product() {
    }

    public Product(int id, String company, String name, Categories category,
                   double price, int quantity, boolean available ) {
        this.id = id;
        this.company = company;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.available = available;
    }

    public int getId() { return this.id; }
    public String getCompany() { return this.company; }
    public void setCompany(String company) { this.company = company; }
    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }
    public Categories getCategory() { return this.category; }
    public void setCategory(Categories category) { this.category = category; }
    public double getPrice() { return this.price; }
    public void setPrice(double price) { this.price = price; }
    public int getQuantity() { return this.quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void addQuantity(int value) { this.quantity += value; }
    public boolean getAvailable() { return this.available; }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return String.format("%-5d %-10s %25s %10.2f %10d %10s %10s",
                this.getId(),
                this.getCompany(),
                this.getName(),
                this.getPrice(),
                this.getQuantity(),
                this.getAvailable() ? "Nie" : "Tak",
                this.getCategory().name());
    }

    public enum Categories {
        FARBA,
        KLEJ,
        PEDZEL
    }
}
