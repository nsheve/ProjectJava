package main.web;

import main.entity.Clients;
import main.exeption.ClientsNotFoundExeption;
import main.service.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/library")
public class ClientController {
    @Autowired
    private ClientsService clientsService;

    public ClientController(ClientsService clientsService){
        this.clientsService = clientsService;
    }

    @GetMapping("/clients")
    public ResponseEntity<List<Clients>> getAllClients() {
        List<Clients> list = clientsService.listClients();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<Clients> getClient(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(clientsService.findClient(id), HttpStatus.OK);
        } catch (ClientsNotFoundExeption exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found");
        }
    }

    @PostMapping(value = "/addClient", consumes = "application/json", produces = "application/json")
    public Clients addClients(@RequestBody Clients newClients){
        return clientsService.addClient(newClients);
    }

    @DeleteMapping("/deleteClient/{id}")
    public void deleteClient(@PathVariable("id") Long id) {
        try {
            clientsService.deleteClient(id);
        } catch (ClientsNotFoundExeption exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found");
        }
    }

    @PutMapping(value = "/updateClient/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Clients> updateClient(@PathVariable("id") Long id, @RequestBody Clients newClient) {
        try {
            Clients updatedClient = clientsService.findClient(id);
            String firstName = newClient.getFirstName();
            String lastName = newClient.getLastName();
            String patherName = newClient.getPatherName();
            String passportSeria = newClient.getPassportSeria();
            String passportNum = newClient.getPassportNum();

            if (firstName != null)
                updatedClient.setFirstName(firstName);
            if (lastName != null)
                updatedClient.setLastName(lastName);
            if (patherName != null)
                updatedClient.setPatherName(patherName);
            if (passportSeria != null)
                updatedClient.setPassportSeria(passportSeria);
            if (passportNum != null)
                updatedClient.setPassportNum(passportNum);

            return ResponseEntity.ok(clientsService.addClient(updatedClient));
        } catch (ClientsNotFoundExeption exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found");
        }
    }
}
