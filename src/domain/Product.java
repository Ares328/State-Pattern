package domain;

import exceptions.OperationNotAvailable;

import java.io.Serializable;
import java.util.UUID;

public abstract class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String title;
    private double purchasePrice;

    private DamagedState damagedState;
    private LendableState lendableState;
    private RemovedState removedState;
    private LoanedState loanedState;

    private RequestState currentState;


    public Product(String title){
        id = UUID.randomUUID().hashCode();
        this.title = title;

        damagedState = new DamagedState(this);
        lendableState = new LendableState(this);
        removedState = new RemovedState(this);
        loanedState = new LoanedState(this);

        currentState = lendableState;
    }

    public Product(String title, double price){
        this(title);
        this.purchasePrice = price;
    }
    public abstract double getPrice(int days);

    public double getRentPrice(){
        return purchasePrice/5;
    }

    public double getFeePrice(){
        return purchasePrice/3;
    }

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

    public void rent() throws OperationNotAvailable {
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

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
}
