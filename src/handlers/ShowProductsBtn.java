package handlers;

import domain.Product;
import domain.Shop;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ShowProductsBtn extends ShopEventHandler implements EventHandler<ActionEvent> {
    public ShowProductsBtn(Stage primaryStage, Scene mainScene, Shop shop) {
        super(primaryStage,mainScene,shop);
    }


    @Override
    public void handle(ActionEvent event) {
        ListView productListView = new ListView();
        updateProductList(productListView);


        Button backBtn = new Button("Back");
        backBtn.setOnAction((e) -> {
            primaryStage.setScene(mainScene);
            primaryStage.show();
        });

        GridPane gridPane = new GridPane();
        gridPane.add(productListView,1,1);
        gridPane.add(backBtn,1,4);
        gridPane.setVgap(10);

        Scene scene = new Scene(gridPane,300,400);
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    private void updateProductList(ListView list){
        list.getItems().clear();
        for (Product product: shop.getProducts()) {
            list.getItems().add(product);
        }
    }
}
