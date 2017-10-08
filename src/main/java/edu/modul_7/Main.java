package edu.modul_7;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Admin on 07.09.2017.
 */
public class Main {

    public static void main( String[] args ) throws IOException, ParseException {

        Shop shop = new Shop();
        shop.addFruits("files\\delivery_1.txt");
        shop.addFruits("files\\delivery_2.txt");
        shop.addFruits("files\\delivery_3.txt");

        HashMap<String, Integer> stock = shop.getStock();

        shop.save("files\\shop.txt");
        shop.load("files\\shop.txt");

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date currentDateSpoiled = dateFormat.parse("15/06/2017");
        ArrayList<Fruit>listSpoiledFruits = shop.getSpoiledFruits(currentDateSpoiled);
        ArrayList<Fruit>listSpoiledFruitsByType = shop.getSpoiledFruits(currentDateSpoiled, "Banana");

        Date currentDateReadyToSell = dateFormat.parse("10/07/2017");
        ArrayList<Fruit>listReadyToSellFruits = shop.getAvailableFruits(currentDateReadyToSell);
        ArrayList<Fruit>listReadyToSellFruitsByType = shop.getAvailableFruits(currentDateReadyToSell, "Apple");

        Date currentDateDelivery = dateFormat.parse("19/07/2017");
        ArrayList<Fruit>listFruitsOnDate = shop.getAddedFruits(currentDateDelivery);
        ArrayList<Fruit>listFruitsOnDateByType = shop.getAddedFruits(currentDateReadyToSell, "Apple");


        shop.sell("files\\sale.txt");
        int test = 0;

    }
}
