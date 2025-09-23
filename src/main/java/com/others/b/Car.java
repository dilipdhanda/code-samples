package com.others.b;

public class Car {

    private String color;
    private String type;
    
    // needed first, in case you have a custom constructor, for ObjectMapper to parse JSON and create Java object
    public Car() {
    }
    
    public Car(String color, String type) {
        this.color = color;
        this.type = type;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String toString() { return color + " " + type; } 
}
