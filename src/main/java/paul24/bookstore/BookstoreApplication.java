package paul24.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import paul24.bookstore.model.Book;
import paul24.bookstore.model.BookRepository;
import paul24.bookstore.model.Category;
import paul24.bookstore.model.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner demoData(BookRepository bookRepository, CategoryRepository categoryRepository) {
        return (args) -> {

            categoryRepository.save(new Category("Non-fiction"));
            categoryRepository.save(new Category("Sci-fi"));
            categoryRepository.save(new Category("Romance"));
            categoryRepository.save(new Category("Comic"));

            bookRepository.save(new Book("Taisteluni", "Hullu Miäs", 1933, "897A-F98AWF-986A89F", 30.99,
                    categoryRepository.findByName("Comic").get()));
            bookRepository.save(new Book("Roopen Elämä ja Teot", "Don Rosa", 1998, "597A-F423AWF-986A89F", 53.80,
                    categoryRepository.findByName("Comic").get()));
            bookRepository.save(new Book("Leijat", "Bron Bronskoviski", 1833, "1237A-F98AWF-18669E", 19.50,
                    categoryRepository.findByName("Romance").get()));

        };
    }

}
