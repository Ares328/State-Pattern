package domain;

import exceptions.OperationNotAvailable;

public abstract class RequestState {

    protected Product product;

    public RequestState(Product product){
        this.product = product;
    }

    void remove() throws OperationNotAvailable {
        throw new OperationNotAvailable("");
    };
    void loan() throws OperationNotAvailable{
        throw new OperationNotAvailable();
    };
    void reinstate(boolean isDamaged) throws OperationNotAvailable {
        throw new OperationNotAvailable();
    };
    void repair() throws OperationNotAvailable {
        throw new OperationNotAvailable();
    };
}
