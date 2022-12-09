package pl.edu.wszib.store.paints.database;

import pl.edu.wszib.store.paints.model.Product;



public class ProductsDB {
    private Product[] products = new Product[5];
    private static final ProductsDB instance = new ProductsDB();

    public ProductsDB() {
        products[0] = new Product(0, "DULUX", "farba biala", Product.Categories.FARBA, 84.23, 12, true);
        products[1] = new Product(1, "DULUX", "farba czerwona", Product.Categories.FARBA, 23.15, 23, true);
        products[2] = new Product(2, "JEDYNKA", "farba czerwona", Product.Categories.FARBA, 231.54, 4, true);
        products[3] = new Product(3, "GLUEHOLD", "Klej do drewna", Product.Categories.KLEJ, 33.22, 21, false);
        products[4] = new Product(4, "WOODMANS", "Pedzel Nadzwyczajny", Product.Categories.PEDZEL, 8.00, 97, true);
    }

    public boolean findById(int givenId) {
        for (Product product: this.getProducts()) {
            if (product.getId() == givenId) {
                return true;
            }
        }
        return false;
    }

    public Product getProduct(int givenId) {
        for (Product product: this.getProducts()) {
            if (product.getId() == givenId) {
                return product;
            }
        }
        return null;
    }

    public double buyProduct(int orderedId, int orderedQuantity) {
        int newValueQuantity = this.getProduct(orderedId).getQuantity() - orderedQuantity;
        this.getProduct(orderedId).setQuantity(newValueQuantity);
        return this.getProduct(orderedId).getPrice() * orderedQuantity;
    }



    public Product[] getProducts() {
        return products;
    }
    public static ProductsDB getInstance() {
        return instance;
    }

}
