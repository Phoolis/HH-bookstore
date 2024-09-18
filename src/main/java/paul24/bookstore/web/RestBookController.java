package paul24.bookstore.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import paul24.bookstore.model.Book;
import paul24.bookstore.model.BookRepository;
import paul24.bookstore.model.CategoryRepository;

@RestController
public class RestBookController {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/books")
    public Iterable<Book> getBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/book/{id}")
    public Optional<Book> getOneBook(@PathVariable("id") Long bookId) {
        return bookRepository.findById(bookId);
    }
}
