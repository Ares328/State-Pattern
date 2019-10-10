package domain;

public class Game extends Product {

    public Game(String title){super(title);}
    public Game(String title,double price) {
        super(title,price);
    }

    @Override
    public double getPrice(int days) {
        return days*3;
    }

    @Override
    public String toString() {
        return "Game: " + super.toString();
    }
}
