package edu.modul_7;

import com.alibaba.fastjson.JSON;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Shop {

    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private List<Delivery> listDeliveries = new ArrayList<>();
    private HashMap<String, Integer> stock = new HashMap<>();

    public List<Delivery> getListDeliveries()  {

        return listDeliveries;

    }

    public void addFruits(String pathToJsonFile) throws FileNotFoundException {

        try {

            String json = new Scanner(new File(pathToJsonFile)).useDelimiter("\\Z").next();
            Delivery delivery = JSON.parseObject(json, Delivery.class);

            listDeliveries.add(delivery);
            addToStock(delivery);

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    public HashMap<String, Integer> getStock() {

        return stock;

    }

    private void addToStock(Delivery delivery) {

        for (Fruit fruit : delivery.getFruits()) {

            if (stock.containsKey(fruit.getTypeAsString()))
                stock.put(fruit.getTypeAsString(), stock.get(fruit.getTypeAsString()) + fruit.getCount());
            else
                stock.put(fruit.getTypeAsString(), fruit.getCount());

        }

    }

    public void save(String pathToFile) throws IOException {

        String json = JSON.toJSONString(listDeliveries);
        BufferedWriter bw = new BufferedWriter(new FileWriter(pathToFile));
        bw.write(json);
        bw.close();

    }

    public void load (String pathToFile) throws FileNotFoundException {

        listDeliveries.clear();
        stock.clear();
        String json = new Scanner(new File(pathToFile)).useDelimiter("\\Z").next();
        listDeliveries = (JSON.parseArray(json, Delivery.class));

        for (Delivery delivery : listDeliveries)
            addToStock(delivery);

    }

    public ArrayList<Fruit> getSpoiledFruits(Date date) throws ParseException {

        ArrayList<Fruit> listSpoiledFruits = new ArrayList<Fruit>();
        for (Delivery delivery : listDeliveries) {

            for (Fruit fruit : delivery.getFruits()) {

                Date dateSpoiledFruit = dateFormat.parse(fruit.getDate());
                if (date.getTime() > dateSpoiledFruit.getTime())
                    listSpoiledFruits.add(fruit);

            }

        }

        return listSpoiledFruits;

    }

    public ArrayList<Fruit> getSpoiledFruits(Date date, String typeOfFruits) throws ParseException {

        ArrayList<Fruit> listSpoiledFruits = new ArrayList<Fruit>();
        for (Delivery delivery : listDeliveries) {

            for (Fruit fruit : delivery.getFruits()) {

                Date dateSpoiledFruit = dateFormat.parse(fruit.getDate());
                if (date.getTime() > dateSpoiledFruit.getTime() && fruit.getTypeAsString() == typeOfFruits)
                    listSpoiledFruits.add(fruit);

            }

        }

        return listSpoiledFruits;

    }

    public ArrayList<Fruit> getAvailableFruits(Date date) throws ParseException {

        ArrayList<Fruit> listAvailableFruits = new ArrayList<Fruit>();
        for (Delivery delivery : listDeliveries) {

            for (Fruit fruit : delivery.getFruits()) {

                Date dateSpoiledFruit = dateFormat.parse(fruit.getDate());
                if (date.getTime() < dateSpoiledFruit.getTime())
                    listAvailableFruits.add(fruit);

            }

        }

        return listAvailableFruits;

    }

    public ArrayList<Fruit> getAvailableFruits(Date date, String typeOfFruits) throws ParseException {

        ArrayList<Fruit> listAvailableFruits = new ArrayList<Fruit>();
        for (Delivery delivery : listDeliveries) {

            for (Fruit fruit : delivery.getFruits()) {

                Date dateSpoiledFruit = dateFormat.parse(fruit.getDate());
                if (date.getTime() < dateSpoiledFruit.getTime() && fruit.getTypeAsString() == typeOfFruits)
                    listAvailableFruits.add(fruit);

            }

        }

        return listAvailableFruits;

    }

    public ArrayList<Fruit> getAddedFruits(Date date) throws ParseException {

        ArrayList<Fruit> addedFruits = new ArrayList<>();

        for (Delivery delivery : listDeliveries) {

            Date dateDelivery = dateFormat.parse(delivery.getDateDelivery());
            if (date.getTime() == dateDelivery.getTime()) {

                for (Fruit fruit : delivery.getFruits())
                    addedFruits.add(fruit);

            }

        }

        return addedFruits;
    }

    public ArrayList<Fruit> getAddedFruits(Date date, String typeOfFruits) throws ParseException {

        ArrayList<Fruit> addedFruits = new ArrayList<>();

        for (Delivery delivery : listDeliveries) {

            Date dateDelivery = dateFormat.parse(delivery.getDateDelivery());
            if (date.getTime() == dateDelivery.getTime()) {

                for (Fruit fruit : delivery.getFruits())
                    if (fruit.getTypeAsString() == typeOfFruits) addedFruits.add(fruit);

            }

        }

        return addedFruits;
    }

    public void sell(String pathToJsonFile) {

        try {

            String json = new Scanner(new File(pathToJsonFile)).useDelimiter("\\Z").next();
            Sale sale = JSON.parseObject(json, Sale.class);

            HashMap<String, Integer> fruitSale = new HashMap<>();
            for (Client client : sale.getClients()) {

                if (fruitSale.containsKey(client.getType()))
                    fruitSale.put(client.getType(), fruitSale.get(client.getType()) + client.getCount());
                else
                    fruitSale.put(client.getType(), client.getCount());

            }

            HashMap<String, Integer> prevStock = stock;
            Boolean missingFruits = true;
            for(Map.Entry<String, Integer> entry : fruitSale.entrySet()) {

                 if (stock.containsKey(entry.getKey())) {

                    if (stock.get(entry.getKey()) >= entry.getValue()) {

                        stock.put(entry.getKey(), stock.get(entry.getKey()) - entry.getValue());

                    } else {

                        missingFruits = false;
                        stock = prevStock;
                        break;
                    }

                 } else {

                    missingFruits = false;
                    stock = prevStock;
                    break;

                 }

            }

            if (missingFruits)
                System.out.println("Фруктов хватает! Сделка состоялась :)");
            else
                System.out.println("Не хватает фруктов на складе! Сделка не состоялась :(");


        } catch (IOException e) {

            e.printStackTrace();

        }


    }

}
