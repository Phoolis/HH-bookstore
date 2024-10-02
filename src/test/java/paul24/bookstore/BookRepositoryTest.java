package paul24.bookstore;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import paul24.bookstore.model.Book;
import paul24.bookstore.model.BookRepository;
import paul24.bookstore.model.Category;
import paul24.bookstore.model.CategoryRepository;

@SpringBootTest(classes = BookstoreApplication.class)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD) // needed if the tests change data
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // needed for real database
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void findByCategoryShouldReturnBook() {
        Category category = categoryRepository.findByName("Romance").get();
        List<Book> books = bookRepository.findByCategory(category);

        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("Leijat");
    }

    @Test
    public void editBookReturnsEditedBook() {
        List<Book> books = bookRepository.findByTitle("Leijat");
        Book book = books.get(0);
        Long previousId = book.getId();
        book.setAuthor("Different Author");
        bookRepository.save(book);
        assertThat(book.getId()).isNotNull();
        assertThat(book.getId()).isEqualTo(previousId);
        assertThat(book.getAuthor()).isEqualTo("Different Author");
    }

}
