package pl.dreilt.springhibernatetest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringHibernateTestApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringHibernateTestApplication.class, args);
        PostService postService = context.getBean(PostService.class);

        // dodanie postu z komentarzami
        PostDTO post = new PostDTO("Post content 1");
        List<CommentDTO> comments = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            comments.add(new CommentDTO("Comment content " + i));
        }
        post.setComments(comments);
        Post savedPost = postService.savePost(post);

        // aktualizacja komentarzy
        List<CommentDTO> comments2 = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            comments2.add(new CommentDTO("New comment content"));
        }
        post.setComments(comments2);
        Post updatedPost = postService.updatePost(savedPost.getId(), post);
    }

}
