package edu.modul_7;

import java.util.List;

public class Delivery {

    private List<Fruit> fruits;
    private String dateDelivery;

    public Delivery(List<Fruit> fruits, String dateDelivery) {

        this.fruits = fruits;
        this.dateDelivery = dateDelivery;

    }

    public List<Fruit> getFruits() {

        return fruits;

    }

    public String getDateDelivery() {

        return dateDelivery;

    }

}
