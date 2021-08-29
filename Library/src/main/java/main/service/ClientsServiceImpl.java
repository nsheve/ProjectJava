package main.service;

import main.entity.Clients;
import main.exeption.ClientsNotFoundExeption;
import main.repos.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientsServiceImpl implements ClientsService{
    @Autowired
    private ClientsRepository clientRepository;

    @Override
    public List<Clients> listClients() {
        return (List<Clients>) clientRepository.findAll();
    }

    @Override
    public Clients findClient(Long id) {
        Optional<Clients> optionalClient = clientRepository.findById((long) id);
        if (optionalClient.isPresent()) {
            return optionalClient.get();
        } else {
            throw new ClientsNotFoundExeption("Clients not found");
        }
    }

    @Override
    public Clients addClient(Clients client) {
        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(Long id) {
        Optional<Clients> optionalClient = clientRepository.findById((long) id);
        if (optionalClient.isPresent()) {
            clientRepository.delete(optionalClient.get());
        } else {
            throw new ClientsNotFoundExeption("Clients not found");
        }
    }
}
