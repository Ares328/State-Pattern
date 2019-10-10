package helpers;

import domain.Shop;

public interface IDataManager {
    void loadProductsFromFile(Shop shop);
    void saveProductsToFile(Shop shop);
}
