package main.service;

import main.entity.BookTypes;
import main.exeption.BookTypesNotFoundExeption;
import main.repos.BookTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookTypesServiceImpl implements BookTypesService {

    @Autowired
    private BookTypesRepository bookTypeRepository;

    @Override
    public List<BookTypes> listBookTypes() {
        return (List<BookTypes>) bookTypeRepository.findAll();
    }

    @Override
    public BookTypes findBookType(Long id) {
        Optional<BookTypes> optionalBookType = bookTypeRepository.findById((long)id);
        if (optionalBookType.isPresent()) {
            return optionalBookType.get();
        } else {
            throw new BookTypesNotFoundExeption("Book type  not found");
        }
    }

    @Override
    public BookTypes addBookType(BookTypes bookType ) {
        return bookTypeRepository.save(bookType);
    }

    @Override
    public void deleteBookType(Long id) {
        Optional<BookTypes> optionalBookType = bookTypeRepository.findById((long) id);
        if (optionalBookType.isPresent()) {
            bookTypeRepository.delete(optionalBookType.get());
        } else {
            throw new BookTypesNotFoundExeption("Book type not found");
        }
    }
}
