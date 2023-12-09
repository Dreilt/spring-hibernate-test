package pl.dreilt.springhibernatetest;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comments")
@NoArgsConstructor
class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
    private Post post;

    Comment(String content, Post post) {
        this.content = content;
        this.post = post;
    }
}
