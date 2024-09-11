package paul24.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import paul24.bookstore.model.Book;
import paul24.bookstore.model.BookRepository;
import paul24.bookstore.model.CategoryRepository;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/index")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }

    @GetMapping("/bookList")
    public String showBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "bookList";
    }

    @GetMapping("/addBook")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addBook";
    }

    @PostMapping("/saveBook")
    public String saveBook(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("editBook", book);
            model.addAttribute("categories", categoryRepository.findAll());
            return "addBook";
        }
        bookRepository.save(book);
        return "redirect:/bookList";
    }

    @GetMapping("delete/{id}")
    public String deleteBook(@PathVariable("id") Long id, Model model) {
        bookRepository.deleteById(id);
        return "redirect:/bookList";
    }

    @GetMapping("editBook/{id}")
    public String editBook(@PathVariable("id") Long id, Model model) {
        model.addAttribute("editBook", bookRepository.findById(id));
        model.addAttribute("categories", categoryRepository.findAll());
        return "editBook";
    }

    @PostMapping("/saveEditedBook")
    public String saveEditedBook(Book book, Model model) {
        bookRepository.save(book);
        return "redirect:/bookList";
    }

}
