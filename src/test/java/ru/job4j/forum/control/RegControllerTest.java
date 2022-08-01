package ru.job4j.forum.control;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.UserService;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class RegControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService users;

    @Test
    @WithMockUser
    public void shouldReturnRegPage() throws Exception {
        this.mockMvc.perform(get("/reg"))
                .andDo(print())
                .andExpect(view().name("reg"))
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser
    public void shouldRedirectToRegPage() throws Exception {
        this.mockMvc.perform(post("/reg")
                .param("username", "asd")
                .param("password", "123")
        )
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
        ArgumentCaptor<User> argUser = ArgumentCaptor.forClass(User.class);
        ArgumentCaptor<String> argRole = ArgumentCaptor.forClass(String.class);
        verify(users).save(argUser.capture(), argRole.capture());
        assertThat(argUser.getValue().getUsername(), is("asd"));
        assertThat(argRole.getValue(), is("ROLE_USER"));
    }

}