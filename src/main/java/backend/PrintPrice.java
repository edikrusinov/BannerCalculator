package main.java.backend;

public class PrintPrice {

    private double weight;
    private double height;
    private double price;
    private double s;

    public PrintPrice() {
    }

    public PrintPrice(double weight, double height) {
        this.weight = weight;
        this.height = height;
    }

    private double s(){
        double s = weight * height;
        s = (Math.round(s * 100.0) / 100.0);
        return s;
    }

    public double getS(){
        return s();
    }

    public double priceCalculation(){
        s = getS();
        if (s < 1.5){
            price = 3000 * s;
        } else if (s < 5) {
            price = 2000 * s;
        } else if ((weight > 0.9 || height > 0.9) && (weight < 1.05 || height < 1.05)) {
            price = 1500 * s;
        } else if ((weight > 1.2 || height > 1.2) && (weight < 1.35 || height < 1.35)) {
            price = 1500 * s;
        } else if ((weight > 1.5 || height > 1.5) && (weight < 1.65 || height < 1.65)) {
            price = 1500 * s;
        } else if ((weight > 2 || height > 2) && (weight < 2.3 || height < 2.3)) {
            price = 1500 * s;
        } else if ((weight > 2.8 || height > 2.8) && (weight < 3.1 || height < 3.1)) {
            price = 1500 * s;
        } else if (weight > 3.1 && height > 3.1) {
            if (weight > height){
                price = 1500 * s + (weight * 300);
            } else {
                price = 1500 * s + (height * 300);
            }
        } else {
            price = 1800 * s;
        }


        return price;



    }
}
