package ru.job4j.forum.control;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class LoginControlTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void shouldReturnLoginPage() throws Exception {
        this.mockMvc.perform(get("/login"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    @WithMockUser
    public void shouldReturnLoginPageWithError() throws Exception {
        this.mockMvc.perform(
                get("/login")
                        .param("error", "true"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("errorMessge"));
    }

    @Test
    @WithMockUser
    public void shouldReturnLogoutPage() throws Exception {
        this.mockMvc.perform(
                get("/logout"))
                .andDo(print())
                .andExpect(redirectedUrl("/login?logout=true"));

    }

    @Test
    @WithMockUser
    public void shouldReturnLoginPageWithLogoutAttribute() throws Exception {
        this.mockMvc.perform(
                get("/login")
                        .param("logout", "true"))
                .andDo(print())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("errorMessge"));
    }
}