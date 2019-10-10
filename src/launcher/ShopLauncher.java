package launcher;

import domain.Shop;
import helpers.IDataManager;
import helpers.Serializer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.ShopUI;
import ui.ShopUIFx;

public class ShopLauncher extends Application {

    private static IDataManager dataManager;
    private static Shop shop;

    @Override
    public void start(Stage primaryStage) throws Exception{
        dataManager = new Serializer("shop.txt");
        shop = new Shop();
        dataManager.loadProductsFromFile(shop);

        ShopUIFx ui = new ShopUIFx(shop);
        ui.start(primaryStage);

    }

    public static void main(String[] args) {
        launch(args);
        dataManager.saveProductsToFile(shop);
    }
}
