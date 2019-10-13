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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RepairProductBtn extends ShopEventHandler implements EventHandler<ActionEvent> {

    public RepairProductBtn(Stage primaryStage, Scene mainScene, Shop shop) {
        super(primaryStage,mainScene,shop);
    }

    @Override
    public void handle(ActionEvent event) {
        ListView productListView = new ListView();
        updateProductList(productListView);

        Button repairBtn = new Button("Repair selected product");
        repairBtn.setOnAction((e)->{
            repairProduct(productListView.getSelectionModel().getSelectedItem().toString());
            updateProductList(productListView);
        });

        Button backBtn = new Button("Back");
        backBtn.setOnAction((e) -> {
            primaryStage.setScene(mainScene);
            primaryStage.show();
        });

        GridPane gridPane = new GridPane();
        gridPane.add(productListView,1,1);
        gridPane.add(repairBtn,1,2);
        gridPane.add(backBtn,1,4);
        gridPane.setVgap(10);

        Scene scene = new Scene(gridPane,300,400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void repairProduct(String productID){
        int productIDConverted = Integer.parseInt(productID.split(" ")[2].replace(",","").trim());
        Product product = shop.getProduct(productIDConverted);
        try{
            product.repair();
            new Alert(Alert.AlertType.INFORMATION, "Product has been successfully repaired!").showAndWait();

        } catch (OperationNotAvailable operationNotAvailable) {
            new Alert(Alert.AlertType.ERROR, "Product cannot be repaired!").showAndWait();
        }
    }

    private void updateProductList(ListView list){
        list.getItems().clear();
        for (Product product: shop.getProducts()) {
            list.getItems().add(product);
        }
    }
}
