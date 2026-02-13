package main.java.backend;

public class EdgeSealingPrice {
    private double width;
    private double height;
    private int p;
    private int price;

    public EdgeSealingPrice() {
    }

    public EdgeSealingPrice(double width, double height) {
        this.width = width;
        this.height = height;

    }

    private int perimeter(){
        p = (int) Math.ceil((width + height) * 2);
        return p;
    }


    private int edgeSealingPrice(){
        price = getPerimeter() * 250;
        return price;
    }

    public int getEdgeSealingPrice() {
        return edgeSealingPrice();
    }

    public int getPerimeter(){
        return perimeter();
    }
}

