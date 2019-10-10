package handlers;

import domain.Shop;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class ShopEventHandler {
    protected Shop shop;
    protected Stage primaryStage;
    protected Scene mainScene;

    public ShopEventHandler(Stage primaryStage, Scene mainScene, Shop shop){
        this.primaryStage = primaryStage;
        this.mainScene = mainScene;
        this.shop = shop;
    }
}
