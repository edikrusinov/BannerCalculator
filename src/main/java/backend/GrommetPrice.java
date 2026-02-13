package main.java.backend;

public class GrommetPrice {
    private int perimeter;
    private int grommetAmount;
    private int grommetAmountSum;

    public GrommetPrice(int perimeter) {
        this.perimeter = perimeter;
    }

    public int getGrommetAmount(){
        grommetAmount = perimeter * 2;
        return grommetAmount;
    }

    public  int getGrommetAmountSum(){
        grommetAmountSum = getGrommetAmount() * 50;
        return grommetAmountSum;
    }

}


