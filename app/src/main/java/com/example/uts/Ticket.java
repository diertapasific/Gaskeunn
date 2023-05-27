package com.example.uts;

public class Ticket {
    String name, price;
    int image;

    public Ticket(String name, String price, int image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }
    public String getName() {
        return name;
    }
    public String getPrice() {
        return price;
    }
    public int getImage() {
        return image;
    }
}
