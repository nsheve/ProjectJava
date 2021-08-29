package main.service;

import main.entity.Journal;

import java.util.List;

public interface JournalService {
    List<Journal> journalList();
    Journal findJournal(Long id);
    Journal addJournal(Journal journal);
    void deleteJournal(Long id);
}
