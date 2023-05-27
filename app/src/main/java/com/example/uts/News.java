package com.example.uts;

public class News {
    String title, date;
    int image;
    public News(String title, String date, int image) {
        this.title = title;
        this.date = date;
        this.image = image;
    }
    public String getTitle() {
        return title;
    }
    public String getDate() {
        return date;
    }
    public int getImage() {
        return image;
    }

}
