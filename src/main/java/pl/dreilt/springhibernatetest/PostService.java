package pl.dreilt.springhibernatetest;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
//@Transactional(readOnly = true)
class PostService {
    private final PostRepository postRepository;

    Post savePost(PostDTO newPostData) {
        var post = new Post(newPostData.getContent());
        if (!newPostData.getComments().isEmpty()) {
            List<Comment> comments = new ArrayList<>();
            for (var newCommentData : newPostData.getComments()) {
                comments.add(new Comment(newCommentData.getContent(), post));
            }
            post.setComments(comments);
        }

        return postRepository.save(post);
    }

//    @Transactional
    public Post updatePost(Long id, PostDTO newPostData) {
        Optional<Post> postOpt = postRepository.findById(id);
        if (postOpt.isPresent()) {
            var post = postOpt.get();
            if (!newPostData.getContent().equals(post.getContent())) {
                post.setContent(newPostData.getContent());
            }
            if (!newPostData.getComments().isEmpty()) {
                List<Comment> comments = new ArrayList<>();
                for (var newCommentData : newPostData.getComments()) {
                    comments.add(new Comment(newCommentData.getContent(), post));
                }
                post.setComments(comments);
            }

            return postRepository.save(post);
        }

        throw new ResourceNotFound("Post o ID " + id + " nie istnieje.");
    }
}
