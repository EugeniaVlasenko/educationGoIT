package edu.modul_7;

import java.util.List;

public class Sale {

    private List<Client> clients;

    public Sale(List<Client> clients) {

        this.clients = clients;

    }

    public List<Client> getClients() {

        return clients;
    }

}
