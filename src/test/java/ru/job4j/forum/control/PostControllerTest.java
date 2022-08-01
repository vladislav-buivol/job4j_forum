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
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

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
class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService posts;

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

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(post("/save")
                .param("name", "Куплю ладу-грант. Дорого."))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(posts).save(argument.capture());
        assertThat(argument.getValue().getName(), is("Куплю ладу-грант. Дорого."));
    }

    @Test
    @WithMockUser
    public void shouldRedirectToPostPage() throws Exception {
        this.mockMvc.perform(post("/comment")
                .param("text", "Куплю ладу-грант. Дорого.")
                .param("id", "1")
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Comment> argComment = ArgumentCaptor.forClass(Comment.class);
        ArgumentCaptor<Integer> postId = ArgumentCaptor.forClass(Integer.class);
        verify(posts).saveComment(postId.capture(), argComment.capture());
        assertThat(argComment.getValue().getText(), is("Куплю ладу-грант. Дорого."));
        assertThat(argComment.getValue().getAuthor(), is("user"));
    }
}