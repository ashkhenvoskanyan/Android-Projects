package com.example.idramproject;

public class ButtonClass {

    private int image1;
    private int image2;
    private String text;

    public ButtonClass(int image1, int image2, String text){
        this.image1 = image1;
        this.image2 = image2;
        this.text = text;
    }

    public int getImage1() {
        return image1;
    }

    public int getImage2() {
        return image2;
    }

    public String getText() {
        return text;
    }
}
