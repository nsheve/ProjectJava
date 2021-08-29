package main.web;

import lombok.RequiredArgsConstructor;
import main.entity.BookTypes;
import main.repos.BookTypesRepository;
import main.repos.BooksRepository;
import main.repos.ClientsRepository;
import main.repos.JournalRepository;
import main.service.BookTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TemplatesController {

    private final BookTypesRepository bookTypeRepository;
    private final ClientsRepository clientRepository;
    private final BooksRepository bookRepository;
    private final JournalRepository journalRepository;

     @GetMapping("/journal")
     public String journalPage(Model model) {
        model.addAttribute("journalList", journalRepository.findAll());
        return "journal";
     }

    @GetMapping("/books")
    public String booksPage(Model model) {
        model.addAttribute("bookList", bookRepository.findAll());
        return "books";
    }

    @GetMapping("/clients")
    public String clientsPage(Model model) {
        model.addAttribute("clientList", clientRepository.findAll());
        return "clients";
    }

    @GetMapping("/bookTypes")
    public String bookTypesPage(Model model) {
         model.addAttribute("bookTypesList", bookTypeRepository.findAll());
         return "bookTypes";
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "registration";
    }
}
