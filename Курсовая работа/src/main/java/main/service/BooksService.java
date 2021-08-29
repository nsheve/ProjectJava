package main.service;

import main.entity.Books;

import java.util.List;

public interface BooksService {
    List<Books> listBooks();
    Books findBook(Long id);
    Books addBook(Books book);
    void deleteBook(Long id);
}
