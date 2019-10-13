package handlers;

import domain.Game;
import domain.Movie;
import domain.Shop;
import domain.CD;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AddProductHandler extends ShopEventHandler implements EventHandler<ActionEvent> {

    public AddProductHandler(Stage primaryStage, Scene mainScene, Shop shop){
        super(primaryStage,mainScene,shop);
    }

    @Override
    public void handle(ActionEvent event) {
        TextField titleField = new TextField("Set title");
        TextField priceField = new TextField("Set purchase price");

        MenuItem cd = new MenuItem("CD");
        MenuItem game = new MenuItem("Game");
        MenuItem movie = new MenuItem("Movie");
        MenuButton menuButton = new MenuButton("Product type",null,cd,game,movie);

        cd.setOnAction((e)->{menuButton.setText(cd.getText());});
        game.setOnAction((e)->{menuButton.setText(game.getText());});
        movie.setOnAction((e)->menuButton.setText(movie.getText()));

        Button addBtn = new Button("Add");
        addBtn.setOnAction((e)->{
            addProduct(titleField.getText(),priceField.getText(),menuButton.getText());
        });

        Button backBtn = new Button("Back");
        backBtn.setOnAction((e) -> {
            primaryStage.setScene(mainScene);
            primaryStage.show();
        });

        HBox hBox = new HBox(menuButton, titleField, priceField, addBtn);
        hBox.setSpacing(20);
        GridPane gridPane = new GridPane();
        gridPane.add(hBox,1,1);
        gridPane.add(backBtn,1,2);
        gridPane.setVgap(20);

        Scene scene = new Scene(gridPane,650,100);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void addProduct(String title, String price, String type){
        double convertedPrice;

        if(title.isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Title field cannot be empty!").showAndWait();
            return;
        }
        try{
            if(price.isEmpty()){
                new Alert(Alert.AlertType.ERROR, "Price field cannot be empty!").showAndWait();
                return;
            }
            convertedPrice = Double.parseDouble(price);
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Price value must be a number!").showAndWait();
            return;
        }

        switch(type) {
            case "CD":
                shop.addProduct(new CD(title, convertedPrice));
                break;
            case "Game":
                shop.addProduct(new Game(title, convertedPrice));
                break;
            case ("Movie"):
                shop.addProduct(new Movie(title, convertedPrice));
                break;
            default:
                new Alert(Alert.AlertType.ERROR, "Product type must be selected!").showAndWait();
                return;
        }

        new Alert(Alert.AlertType.INFORMATION, "Product has been successfully added!").showAndWait();
    }
}
