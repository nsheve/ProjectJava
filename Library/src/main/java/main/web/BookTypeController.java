package main.web;

import main.entity.BookTypes;
import main.exeption.BookTypesNotFoundExeption;
import main.service.BookTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/library")
public class BookTypeController {
    @Autowired
    private BookTypesService bookTypesService;

    public BookTypeController(BookTypesService bookTypesService){
        this.bookTypesService = bookTypesService;
    }

    @GetMapping("/bookTypes")
    public ResponseEntity<List<BookTypes>> getAllBookTypes() {
        List<BookTypes> list = bookTypesService.listBookTypes();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/bookType/{id}")
    public ResponseEntity<BookTypes> getBookTypes(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(bookTypesService.findBookType(id), HttpStatus.OK);
        } catch (BookTypesNotFoundExeption exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book types not found");
        }
    }

    @PostMapping(value = "/addBookType", consumes = "application/json", produces = "application/json")
    public BookTypes addBookTypes(@RequestBody BookTypes newBookTypes){
        return bookTypesService.addBookType(newBookTypes);
    }

    @DeleteMapping("/deleteBookType/{id}")
    public void deleteBookTypes(@PathVariable("id") Long id) {
        try {
            bookTypesService.deleteBookType(id);
        } catch (BookTypesNotFoundExeption exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book types not found");
        }
    }

    @PutMapping(value = "/updateBookType/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<BookTypes> updateBookTypes(@PathVariable("id") Long id, @RequestBody BookTypes newBookType) {
        try {
            BookTypes updatedBookType = bookTypesService.findBookType(id);
            String name = newBookType.getName();
            Long cnt  = newBookType.getCnt();
            Long fine = newBookType.getFine();
            Long dayCount = newBookType.getDayCount();

            if (name != null)
                updatedBookType.setName(name);
            if (cnt != null)
                updatedBookType.setCnt(cnt);
            if (fine != null)
                updatedBookType.setFine(fine);
            if (dayCount != null)
                updatedBookType.setDayCount(dayCount);

            return ResponseEntity.ok(bookTypesService.addBookType(updatedBookType));
        } catch (BookTypesNotFoundExeption exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book types not found");
        }
    }
}
