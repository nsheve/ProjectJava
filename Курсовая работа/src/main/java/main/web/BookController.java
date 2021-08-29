package main.web;

import main.entity.Books;
import main.exeption.BooksNotFoundExeption;
import main.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/library")
public class BookController {
    @Autowired
    private BooksService booksService;

    public BookController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping("/books")
    public ResponseEntity<List<Books>> getAllBooks() {
        return new ResponseEntity<>(booksService.listBooks(), HttpStatus.OK);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Books> getBook(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(booksService.findBook(id), HttpStatus.OK);
        } catch (BooksNotFoundExeption exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
    }

    @PostMapping(value = "/addBook", consumes = "application/json", produces = "application/json")
    public Books addBook(@RequestBody Books newBook){
        return booksService.addBook(newBook);
    }

    @DeleteMapping("/deleteBook/{id}")
    public void deleteBook(@PathVariable("id") Long id) {
        try {
            booksService.deleteBook(id);
        } catch (BooksNotFoundExeption exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
    }

    @PutMapping(value = "/updateBook/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Books> updateBook(@PathVariable("id") Long id, @RequestBody Books newBook) {
        try {
            Books updatedBook = booksService.findBook(id);
            String name = newBook.getName();
            Long cnt = newBook.getCnt();
            Long typeId = newBook.getTypeId();

            if (name != null)
                updatedBook.setName(name);
            if (cnt != null)
                updatedBook.setCnt(cnt);
            if (typeId != null)
                updatedBook.setTypeId(typeId);

            return ResponseEntity.ok(booksService.addBook(updatedBook));
        } catch (BooksNotFoundExeption exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
    }
}
