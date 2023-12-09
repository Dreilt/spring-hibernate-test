package pl.dreilt.springhibernatetest;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
class PostDTO {
    private String content;
    private List<CommentDTO> comments = new ArrayList<>();

    PostDTO(String content) {
        this.content = content;
    }

    void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }
}
