package main.java.backend;

public class InstallationPrice {
    private int numberOfBeams;
    private double s;
    private int price;
    private int priceOfBeams = 820;
    FramePrice round = new FramePrice();
    PrintPrice printPrice = new PrintPrice();




    public InstallationPrice(int numberOfBeams, double s) {
        this.numberOfBeams = numberOfBeams;
        this.s = s;
    }



    public int installationPriceSealing(){
        price = (int)(Math.round(s * 1000));
        return price > 10_000 ? price : 10_000;
    }

    public int installationPriceFrame(){
        FramePrice beams = new FramePrice();
        double s = printPrice.getS();
        int getBeams = beams.getNumberOfBeams();
        price = getBeams * 500;
        if (s < 4){
            price = 10_000 - (7_000 + getBeams * priceOfBeams);
            return price;
        } else {
        return price > 10_000 ? price : 10_000;
        }
    }

    public int installationPriceFrameAndSealing(){
        FramePrice framePrice = new FramePrice();
        int numberBeams = framePrice.getNumberOfBeamsSealing();
        price = numberBeams * 2600;
        return price > (10_000 + (numberBeams * 820)) ? price : 10_000 + (numberBeams * 820);
    }
}
