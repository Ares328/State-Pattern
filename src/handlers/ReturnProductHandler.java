package handlers;

import domain.Product;
import domain.Shop;
import exceptions.OperationNotAvailable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ReturnProductHandler extends ShopEventHandler implements EventHandler<ActionEvent> {
    public ReturnProductHandler(Stage primaryStage, Scene mainScene, Shop shop) {
        super(primaryStage,mainScene,shop);
    }

    @Override
    public void handle(ActionEvent event) {
        TextField idField = new TextField("ProductId");

        Button returnBtn = new Button("Return");
        returnBtn.setOnAction((e)->{
            returnProduct(idField.getText());
        });

        Button backBtn = new Button("Back");
        backBtn.setOnAction((e) -> {
            primaryStage.setScene(mainScene);
            primaryStage.show();
        });

        HBox hBox = new HBox(idField,returnBtn);
        hBox.setSpacing(20);
        GridPane gridPane = new GridPane();
        gridPane.add(hBox,1,1);
        gridPane.add(backBtn,1,2);
        gridPane.setVgap(20);

        Scene scene = new Scene(gridPane,650,100);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void returnProduct(String productID){
        try{
            boolean damaged;
            int productIDConverted = Integer.parseInt(productID.trim());
            Product product = shop.getProduct(productIDConverted);

            if(product == null){
                new Alert(Alert.AlertType.ERROR, "Product not found!").showAndWait();
                return;
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Product state confirmation");
            alert.setContentText("Is product damaged?");
            ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(okButton, noButton);
            alert.showAndWait().ifPresent(type -> {
                try {
                    if (type == okButton) {
                        product.reinstate(true);
                        new Alert(Alert.AlertType.INFORMATION, "Fee for damaged product to pay: " + product.getFeePrice()).showAndWait();
                    } else if (type == noButton) {
                        product.reinstate(false);
                        new Alert(Alert.AlertType.INFORMATION, "Product succesfully returned with no fee").showAndWait();
                    }
                } catch(OperationNotAvailable e){
                    new Alert(Alert.AlertType.ERROR, "Product cannot be returned!").showAndWait();
                }
            });
        }
        catch (Exception nullPointerNumberFormatException) {
            new Alert(Alert.AlertType.ERROR, "ProductId not valid").showAndWait();
        }
    }
}
