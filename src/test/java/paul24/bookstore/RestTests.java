package paul24.bookstore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
public class RestTests {

    @Autowired
    private WebApplicationContext webAppContext;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    String jsonString = "{\"title\":\"Taisteluni ja Poika\",\"author\":\"Hullu Miäs\",\"publicationYear\":2233,\"isbn\":\"897A-F98AWF-986A89F\",\"price\":30.99,\"category\":{\"id\":4}}";

    @Test
    public void getBooksReturnsStatusOk() throws Exception {
        mockMvc.perform(get("/api/books")).andExpect(status().isOk());
    }

    @Test
    public void getOneBookReturnsStatusOk() throws Exception {
        mockMvc.perform(get("/api/book/1")).andExpect(status().isOk());
    }

    @Test
    public void newBookReturnsStatusOk() throws Exception {
        mockMvc.perform(post("/api/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void editBookReturnsStatusOk() throws Exception {
        mockMvc.perform(put("/api/book/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteBookReturnsStatusOk() throws Exception {
        mockMvc.perform(delete("/api/book/1")).andExpect(status().isOk());
    }
}
