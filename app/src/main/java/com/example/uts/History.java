package com.example.uts;

public class History {
    String name, quantity, date;

    public History(String name, String quantity, String date) {
        this.name = name;
        this.quantity = quantity;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getDate() {
        return date;
    }
}
