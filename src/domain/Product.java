package domain;

import exceptions.OperationNotAvailable;

import java.io.Serializable;

public abstract class Product implements Serializable {
    private static int COUNT;
    private int id;
    private String title;

    private DamagedState damagedState;
    private LendableState lendableState;
    private RemovedState removedState;
    private LoanedState loanedState;

    private RequestState currentState;


    public Product(String title){
        id = ++COUNT;
        setTitle(title);

        damagedState = new DamagedState(this);
        lendableState = new LendableState(this);
        removedState = new RemovedState(this);
        loanedState = new LoanedState(this);

        currentState = lendableState;
    }

    public abstract double getPrice(int days);

    public void setCurrentState(RequestState state){ currentState = state;}

    public RequestState getCurrentState(){return currentState;}

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void remove() throws OperationNotAvailable {
        currentState.remove();
    }

    public void reinstate(boolean isDamaged) throws OperationNotAvailable {
        currentState.reinstate(isDamaged);
    }

    public void loan() throws OperationNotAvailable {
        currentState.loan();
    }

    public void repair() throws OperationNotAvailable {
        currentState.repair();
    }

    @Override
    public String toString() {
        return "ID: "+ id +
                ", Title: " + title +
                " , State: " + currentState.getClass().getSimpleName();
    }

    public DamagedState getDamagedState() {
        return damagedState;
    }

    public RemovedState getRemovedState() {
        return removedState;
    }

    public LoanedState getLoanedState() {
        return loanedState;
    }

    public LendableState getLendableState(){
        return lendableState;
    }
}
