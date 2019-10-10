package helpers;

import domain.*;

import java.io.*;
import java.util.Scanner;

public class PrintWriter implements IDataManager {

    @Override
    public void loadProductsFromFile(Shop shop) {
        File shoptxt = new File("shop.txt");
        try {
            Scanner scannerFile = new Scanner(shoptxt);
            while (scannerFile.hasNextLine()) {
                Scanner scannerline = new Scanner(scannerFile.nextLine());
                scannerline.useDelimiter(",");
                String ProductType = scannerline.next();
                String idS = scannerline.next();
                int id = Integer.parseInt(idS);
                String title = scannerline.next();
                String isLoanedS = scannerline.next();
                boolean isLoaned = Boolean.parseBoolean(isLoanedS);

                //This is really crappy cause I don't know how to do it, heeeelppp haha :)
                if(ProductType.equals("Game")){
                    Product product = new Game(title);
                    shop.addProduct(product);
                }else if(ProductType.equals("Movie")){
                    Product product = new Movie(title);
                    shop.addProduct(product);
                }else{
                    Product product = new CD(title);
                    shop.addProduct(product);
                }
                //till here
            }
        }  catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("Error while reading", ex);
        }
    }

    @Override
    public void saveProductsToFile(Shop shop) {
        File shoptxt =new File("shop.txt");
        try {
            java.io.PrintWriter printWriter = new java.io.PrintWriter(shoptxt);
            printWriter.print(shop.showAllProducts());
            printWriter.close();
        }  catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("Error while writing", ex);
        }
    }
}
