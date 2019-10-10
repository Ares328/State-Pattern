package launcher;

import domain.Shop;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.ShopUI;
import ui.ShopUIFx;

public class ShopLauncher extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        ShopUIFx ui = new ShopUIFx(new Shop());
        ui.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
