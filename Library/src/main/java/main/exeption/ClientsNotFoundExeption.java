package main.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ClientsNotFoundExeption extends RuntimeException{
    public ClientsNotFoundExeption(String message) {
        super(message);
    }
}
