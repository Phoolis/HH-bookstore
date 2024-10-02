package paul24.bookstore;

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
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD) // needed if the tests change data in the database 
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // needed for not changing the real database
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void findByNameShouldReturnCategory() {
        Category category = categoryRepository.findByName("Comic").get();
        assertThat(category.getName()).isEqualTo("Comic");
    }

    @Test
    public void editCategoryShouldShowInBook() {
        Category category = categoryRepository.findByName("Comic").get();
        Book book = bookRepository.findByCategory(category).get(0);
        Long bookId = book.getId();
        String previousCategoryName = book.getCategory().getName();
        category.setName("Different Category");
        categoryRepository.save(category);
        book = bookRepository.findById(bookId).get();

        assertThat(previousCategoryName).isEqualTo("Comic");
        assertThat(book.getCategory().getName()).isEqualTo("Different Category");
    }
}
