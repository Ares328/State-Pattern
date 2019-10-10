package domain;

import java.io.Serializable;

public class RemovedState extends RequestState implements Serializable {

    public RemovedState(Product product) {
        super(product);
    }
}
