package edu.modul_7;

public class Fruit {

    private Type type;
    private Integer shelfLife;
    private String date;
    private Float price;
    private int count;

    private enum Type {Strawberry, Apple, Pear, Banana, Peach, Grapes, Plum, Melon, Watermelon, Currant};

    public Fruit(String type, int count) {

        this.type = Type.valueOf(type);
        this.count = count;

    }

    public Fruit(String type, int shelfLife, String date, Float price, int count) {

        this.type = Type.valueOf(type);
        this.shelfLife = shelfLife;
        this.date = date;
        this.price = price;
        this.count = count;

    }

    public int getCount() {

        return count;

    }

    public String getDate() {

        return date;

    }

    public Type getType() {

        return type;

    }

    public String getTypeAsString() {

        return type.toString();

    }

 }
