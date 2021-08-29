package main.service;

import main.entity.Books;
import main.exeption.BooksNotFoundExeption;
import main.repos.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksServiceImpl implements BooksService{

    @Autowired
    private BooksRepository bookRepository;

    @Override
    public List<Books> listBooks() {
        return (List<Books>) bookRepository.findAll();
    }

    @Override
    public Books findBook(Long id) {
        Optional<Books> optionalBook = bookRepository.findById((long)id);
        if (optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            throw new BooksNotFoundExeption("Books not found");
        }
    }

    @Override
    public Books addBook(Books book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        Optional<Books> optionalBook = bookRepository.findById((long)id);
        if (optionalBook.isPresent()) {
            bookRepository.delete(optionalBook.get());
        } else {
            throw new BooksNotFoundExeption("Books not found");
        }
    }

}
