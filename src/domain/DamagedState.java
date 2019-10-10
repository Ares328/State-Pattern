package domain;

import java.io.Serializable;

public class DamagedState extends RequestState implements Serializable {
    // Damaged goes to lendable and removed

    public DamagedState(Product product) {
        super(product);
    }

    @Override
    public void remove() {
        product.setCurrentState(product.getDamagedState());
    }

    @Override
    public void repair() {
        product.setCurrentState(product.getLendableState());
    }
}
