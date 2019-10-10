package domain;

import java.util.ArrayList;

public class Shop {
    private ArrayList<Product> products = new ArrayList<>();

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products){
        this.products = products;
    }

    public String showAllProducts(){
        String uit = "";
        for (Product p: this.products){
            uit += p.toString() + "\n";
        }
        return uit;
    }

    public void addProduct(Product product){
        this.products.add(product);
    }

    public Product getProduct(int ID){
        return products.stream().filter((p)->p.getId() == ID).findFirst().orElse(null);
    }

}
