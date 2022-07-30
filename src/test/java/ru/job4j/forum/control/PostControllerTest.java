package ru.job4j.forum.control;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import ru.job4j.forum.Main;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void shouldReturnPostPage() throws Exception {
        mockMvc.perform(
                get("/post")
                        .param("id", "1"))
                .andDo(print())
                .andExpect(view().name("post"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void shouldReturnEditPageWithoutParams() throws Exception {
        mockMvc.perform(get("/create"))
                .andDo(print())
                .andExpect(view().name("edit"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void shouldReturnEditPage() throws Exception {
        mockMvc.perform(
                get("/edit")
                        .param("id", "1")
        )
                .andDo(print())
                .andExpect(view().name("edit"))
                .andExpect(status().isOk());
    }

}