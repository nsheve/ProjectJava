package main.service;

import main.entity.BookTypes;

import java.util.List;

public interface BookTypesService {
    List<BookTypes> listBookTypes();
    BookTypes findBookType(Long id);
    BookTypes addBookType(BookTypes bookType);
    void deleteBookType(Long id);
}
