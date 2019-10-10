package helpers;

import domain.Product;
import domain.Shop;

import java.io.*;
import java.util.ArrayList;

public class Serializer implements IDataManager {

    private String fileName;

    public Serializer(String fileName){
        this.fileName = fileName;
    }

    @Override
    public void loadProductsFromFile(Shop shop) {
        try
        {
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(file);

            ArrayList<Product> products = (ArrayList<Product>) in.readObject();

            in.close();
            file.close();

            shop.setProducts(products);
        }
        //TODO: handle
        catch (FileNotFoundException e) {
            System.out.println("No file with product list found");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveProductsToFile(Shop shop) {
        try
        {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(shop.getProducts());
            oos.close();
            fos.close();
        }
        catch (IOException ioe)
        {
            //TODO: handle
            System.out.println("Unable to write products to " + fileName);
        }
    }
}
