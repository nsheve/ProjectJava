package main.web;

import main.entity.Journal;
import main.exeption.JournalNotFoundExeption;
import main.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/library")
public class JournalController {
    @Autowired
    private JournalService journalService;

    public JournalController(JournalService journalService){
        this.journalService = journalService;
    }

    @GetMapping("/journal")
    public ResponseEntity<List<Journal>> getAllJournal() {
        List<Journal> list = journalService.journalList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/journal/{id}")
    public ResponseEntity<Journal> getJournal(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(journalService.findJournal(id), HttpStatus.OK);
        } catch (JournalNotFoundExeption exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Journal record not found");
        }
    }

    @PostMapping(value = "/addJournal", consumes = "application/json", produces = "application/json")
    public Journal addJournal(@RequestBody Journal newJournal){
        return journalService.addJournal(newJournal);
    }

    @DeleteMapping("/deleteJournal/{id}")
    public void deleteJournal(@PathVariable("id") Long id) {
        try {
            journalService.deleteJournal(id);
        } catch (JournalNotFoundExeption exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Journal not found");
        }
    }

    @PutMapping(value = "/updateJournal/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Journal> updateJournal(@PathVariable("id") Long id, @RequestBody Journal newJournal) {
        try {
            Journal updatedJournal = journalService.findJournal(id);
            Long newBookId = newJournal.getBookId();
            Long newClientId = newJournal.getClientId();
            Timestamp newDateBeg = newJournal.getDateBeg();
            Timestamp newDateEnd = newJournal.getDateEnd();
            Timestamp newDateRet = newJournal.getDateRet();

            if (newBookId != null)
                updatedJournal.setBookId(newBookId);
            if (newClientId != null)
                updatedJournal.setClientId(newClientId);
            if (newDateBeg != null)
                updatedJournal.setDateBeg(newDateBeg);
            if (newDateEnd != null)
                updatedJournal.setDateEnd(newDateEnd);
            if (newDateRet != null)
                updatedJournal.setDateRet(newDateRet);

            return ResponseEntity.ok(journalService.addJournal(updatedJournal));
        } catch (JournalNotFoundExeption exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Journal not found");
        }
    }
}
