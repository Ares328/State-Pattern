package domain;

public class CD extends Product {

    public CD(String title){super(title);}
    public CD(String title, double price) {
        super(title,price);
    }

    @Override
    public double getPrice(int days) {
        return 1.5*days;
    }

    @Override
    public String toString() {
        return "CD: " + super.toString();
    }
}
