package domain;

import java.io.Serializable;

public class LendableState extends RequestState implements Serializable {

    public LendableState(Product product) {
        super(product);
    }

    @Override
    public void remove() {
        product.setCurrentState(product.getRemovedState());
    }

    @Override
    public void loan() {
        product.setCurrentState(product.getLoanedState());
    }

}
