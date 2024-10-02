package paul24.bookstore.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import paul24.bookstore.model.Book;
import paul24.bookstore.model.BookRepository;
import paul24.bookstore.model.Category;
import paul24.bookstore.model.CategoryRepository;

@RestController
@RequestMapping("/api")
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

    @PostMapping("/book")
    Book newBook(@RequestBody Book newBook) {
        return bookRepository.save(newBook);
    }

    @PutMapping("/book/{id}")
    Book editBook(@RequestBody Book editedBook, @PathVariable("id") Long bookId) {
        editedBook.setId(bookId);
        return bookRepository.save(editedBook);
    }

    @DeleteMapping("/book/{id}")
    public Iterable<Book> deleteStudent(@PathVariable("id") Long bookId) {
        bookRepository.deleteById(bookId);
        return bookRepository.findAll();
    }

    @GetMapping("/category/{categoryName}")
    public Iterable<Book> getBooksByCategory(@PathVariable("categoryName") String categoryName) {
        Optional<Category> category = categoryRepository.findByName(categoryName);
        if (category.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Category not found"
            );
        }
        return bookRepository.findByCategory(category.get());
    }

}
