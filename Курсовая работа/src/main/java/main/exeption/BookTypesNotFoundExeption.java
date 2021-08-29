package main.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BookTypesNotFoundExeption extends RuntimeException{
    public BookTypesNotFoundExeption(String message) {
        super(message);
    }
}
