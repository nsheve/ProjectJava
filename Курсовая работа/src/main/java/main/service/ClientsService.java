package main.service;

import main.entity.Clients;

import java.util.List;

public interface ClientsService {
    List<Clients> listClients();
    Clients findClient(Long id);
    Clients addClient(Clients client);
    void deleteClient(Long id);
}

