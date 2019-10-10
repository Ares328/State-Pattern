package domain;

public class Movie extends Product {

    public Movie(String title){super(title);}
    public Movie(String title,double price) {
        super(title,price);
    }

    @Override
    public double getPrice(int days) {
        double price = 5;
        int daysLeft = days - 3;
        if (daysLeft > 0) {
            price += (daysLeft * 2);
        }
        return price;
    }

    @Override
    public String toString() {
        return "Movie: " + super.toString();
    }
}
