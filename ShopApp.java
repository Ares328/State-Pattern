import domain.*;
import helpers.*;
import ui.*;

public class ShopApp {
    public static void main(String[] args) {
        Shop shop = new Shop();
        ShopUI shopUI = new ShopUI(shop);
        IDataManager dataManager = new Serializer("shop.txt");

        dataManager.loadProductsFromFile(shop);
        shopUI.showMenu();

        dataManager.saveProductsToFile(shop);
    }
}
