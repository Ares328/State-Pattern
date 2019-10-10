package handlers;

import domain.Game;
import domain.Movie;
import domain.Shop;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AddProductHandler implements EventHandler<ActionEvent> {

    private Shop shop;
    private Stage primaryStage;

    public AddProductHandler(Stage primaryStage, Shop shop){
        this.primaryStage = primaryStage;
        this.shop = shop;
    }

    @Override
    public void handle(ActionEvent event) {
        TextField textField = new TextField();
        Button addBtn = new Button("Add");
        textField.setText("Set title");

        MenuItem cd = new MenuItem("CD");
        MenuItem game = new MenuItem("Game");
        MenuItem movie = new MenuItem("Movie");

        MenuButton menuButton = new MenuButton("Product type",null,cd,game,movie);
        HBox hBox = new HBox(menuButton, textField,addBtn);
        hBox.setSpacing(20);

        Button backBtn = new Button("Back");

        GridPane gridPane = new GridPane();
        gridPane.add(hBox,1,1);
        gridPane.add(backBtn,1,2);
        gridPane.setVgap(20);

        Scene scene = new Scene(gridPane,650,100);

        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
