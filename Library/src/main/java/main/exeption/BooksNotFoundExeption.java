package main.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BooksNotFoundExeption extends RuntimeException {
    public BooksNotFoundExeption(String message) {
        super(message);
    }
}
