package ui;

import domain.Shop;
import handlers.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ShopUIFx {
    private Shop shop;

    public ShopUIFx(Shop shop) {
        this.shop = shop;
    }

    public void start(Stage primaryStage){
        primaryStage.setTitle("Shop");

        Button addProductBtn = new Button("Add product");
        Button removeProductBtn = new Button("Remove product");
        Button rentProductBtn = new Button("Rent product");
        Button returnProductBtn = new Button("Return product");
        Button repairProductBtn = new Button("Repair product");
        Button showProductsBtn = new Button("Show available products");
        Button exitBtn = new Button("EXIT");

        GridPane gridPane = new GridPane();
        gridPane.add(addProductBtn,1,1);
        gridPane.add(removeProductBtn,2,1);
        gridPane.add(rentProductBtn,3,1);
        gridPane.add(returnProductBtn,4,1);
        gridPane.add(repairProductBtn,5,1);
        gridPane.add(showProductsBtn,6,1);
        gridPane.add(exitBtn,4,2);
        gridPane.setVgap(20);
        gridPane.setHgap(5);

        Scene scene = new Scene(gridPane,650,100);

        addProductBtn.setOnAction(new AddProductHandler(primaryStage,scene,shop));
        removeProductBtn.setOnAction(new RemoveProductHandler(primaryStage,scene,shop));
        rentProductBtn.setOnAction(new RentProductHandler(primaryStage,scene,shop));
        returnProductBtn.setOnAction(new ReturnProductHandler(primaryStage,scene,shop));
        repairProductBtn.setOnAction(new RepairProductBtn(primaryStage,scene,shop));
        showProductsBtn.setOnAction(new ShowProductsBtn(primaryStage,scene,shop));


        exitBtn.setOnAction((e)->primaryStage.close());

        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
