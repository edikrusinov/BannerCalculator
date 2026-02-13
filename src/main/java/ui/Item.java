package main.java.ui;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Item {
    private final SimpleStringProperty position;
    private final SimpleDoubleProperty value;

    public Item(String position, double value) {
        this.position = new SimpleStringProperty(position);
        this.value = new SimpleDoubleProperty(value);
    }

    public String getPosition(){
        return position.get();
    }

    public void setPosition(String position){
        this.position.set(position);
    }

    public double getValue(){
        return value.get();
    }
    public void setValue(double value){
        this.value.set(value);
    }
}
