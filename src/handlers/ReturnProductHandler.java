package handlers;

import domain.Product;
import domain.Shop;
import exceptions.OperationNotAvailable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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

        Button showBtn = new Button("show");
        showBtn.setOnAction((e)->{
            returnProduct(idField.getText());
        });

        Button backBtn = new Button("Back");
        backBtn.setOnAction((e) -> {
            primaryStage.setScene(mainScene);
            primaryStage.show();
        });

        HBox hBox = new HBox(idField,showBtn);
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
            int productIDConverted = Integer.parseInt(productID.trim());
            Product product = shop.getProduct(productIDConverted);
            new Alert(Alert.AlertType.INFORMATION, product.toString()).showAndWait();

        } catch (Exception nullPointerNumberFormatException) {
            new Alert(Alert.AlertType.ERROR, "ProductId not valid").showAndWait();
        }
    }
}
