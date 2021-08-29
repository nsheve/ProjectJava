package main.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class JournalNotFoundExeption extends RuntimeException{
    public JournalNotFoundExeption(String message) {
        super(message);
    }
}
