package ui;

import domain.*;
import exceptions.OperationNotAvailable;
import javafx.collections.transformation.SortedList;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ShopUI {

    private Shop shop;

    public ShopUI(Shop shop) {
        this.shop = shop;
    }

    public void showMenu(){
        String menu = "1. Add product\n2. Show product\n3. Show rental price\n" +
                "4. Show all products\n5. Rent product\n6.See availability\n\n0. Program closes";
        String input;
        int choice;
        do{
            choice=-1;
            input = (JOptionPane.showInputDialog(menu));
            if (input == null)
                return;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Please insert a valid option number!");
                continue;
            }
            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    showProduct();
                    break;
                case 3:
                    showRentalPrice();
                    break;
                case 4:
                    showAllProducts();
                    break;
                case 5:
                    rentProduct();
                    break;
                case 6:
                    seeAvailability();
                    break;
                case 0:
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Please choose an option from the list!!");
            }
        } while(choice!=0);

    }

    public void addProduct() {
        String title = JOptionPane.showInputDialog("Enter the title:");
        if (title.isEmpty()){
            JOptionPane.showMessageDialog(null, "The title cannot be empty!");
            return;
        }

        String[] choices = { "CD","Game","Movie" };
        String input = (String) JOptionPane.showInputDialog(null, "Select the type:",
                "", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);

        switch(input){
            case "CD":
                shop.addProduct(new CD(title));
                break;
            case "Game":
                shop.addProduct(new Game(title));
                break;
            case("Movie"):
                shop.addProduct(new Movie(title));
                break;
        }

    }

    public void showProduct(){
        Product product = findProduct();
        if (product == null) {
            JOptionPane.showMessageDialog(null, "Product with id " + product.getId() + " not found");
        }
        else JOptionPane.showMessageDialog(null, product.getTitle());
    }

    public void showRentalPrice(){
        Product product = findProduct();
        if (product != null){
            int days = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of days:"));
            JOptionPane.showMessageDialog(null, product.getPrice(days));
        } else JOptionPane.showMessageDialog(null, "Product with id " + product.getId() + " not found");

    }

    public Product findProduct(){
        int id;
        try {
            id = Integer.parseInt(JOptionPane.showInputDialog("Enter the id:"));
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Please insert a valid option number!");
            return null;
        }
        for (Product product: shop.getProducts()){
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public void showAllProducts(){

        String list="";
        int count=0;
        Collections.sort(shop.getProducts(),Collections.reverseOrder(
                Comparator.comparing(o -> o.getClass().getName())));

        for (Product product: shop.getProducts()) {
            list += ++count + ". Category: " + product.getClass().getSimpleName() + " Id: " +
                    product.getId() + " Title: " + product.getTitle() + "\n";
        }
        JOptionPane.showMessageDialog(null, list);
    }

    public void rentProduct(){
        ArrayList<String> descriptions= new ArrayList<>();
        ArrayList<Product> availableProducts = new ArrayList<>();

        for (Product product: shop.getProducts()) {
            if (product.getCurrentState().getClass() != LendableState.class)
                continue;
            availableProducts.add(product);
            descriptions.add("Title: " + product.getTitle() + " ID: " + product.getId() );
        }

        if (availableProducts.isEmpty()){
            JOptionPane.showMessageDialog(null, "There are no available products for renting");
            return;
        }

        Product input = (Product)JOptionPane.showInputDialog(null, "Select the product:",
                "", JOptionPane.QUESTION_MESSAGE, null, availableProducts.toArray(), availableProducts.toArray()[0]);

        if(input!=null) {
            try {
                input.loan();
            } catch (OperationNotAvailable operationNotAvailable) {
                //TODO: create dialog;
            }
        }
    }

    public void seeAvailability(){
        Product product = findProduct();
        if (product==null)
            JOptionPane.showMessageDialog(null, "There is no product with provided id!");
        else if(product.getCurrentState().getClass() == LoanedState.class)
            JOptionPane.showMessageDialog(null, "Product is loaned");
        else JOptionPane.showMessageDialog(null, "Product is available");
    }
}
