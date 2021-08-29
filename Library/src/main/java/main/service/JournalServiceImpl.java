package main.service;

import main.entity.Journal;
import main.exeption.JournalNotFoundExeption;
import main.repos.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalServiceImpl implements JournalService {

    @Autowired
    private JournalRepository journalRepository;

    @Override
    public List<Journal> journalList() {
        return (List<Journal>) journalRepository.findAll();
    }

    @Override
    public Journal findJournal(Long id) {
        Optional<Journal> optionalJournal = journalRepository.findById((long) id);
        if (optionalJournal.isPresent()) {
            return optionalJournal.get();
        } else {
            throw new JournalNotFoundExeption("Journal not found");
        }
    }

    @Override
    public Journal addJournal(Journal journal) {
        return journalRepository.save(journal);
    }

    @Override
    public void deleteJournal(Long id) {
        Optional<Journal> optionalJournal = journalRepository.findById((long) id);
        if (optionalJournal.isPresent()) {
            journalRepository.delete(optionalJournal.get());
        } else {
            throw new JournalNotFoundExeption("Journal not found");
        }
    }
}
