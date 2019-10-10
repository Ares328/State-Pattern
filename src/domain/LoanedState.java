package domain;

import java.io.Serializable;

public class LoanedState extends RequestState implements Serializable {

    public LoanedState(Product product) {
        super(product);
    }

    //TODO: show window with fine in UI 1/3 price
    @Override
    public void reinstate(boolean isDamaged) {
        if(isDamaged){
            product.setCurrentState(product.getDamagedState());
        }else{
            product.setCurrentState(product.getLendableState());
        }
    }

}
