package practice.post.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Entity
@Getter
@Table(name = "tb_post")
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @NotEmpty @Column(name = "post_title")
    private String title;

    @NotEmpty @Column(name = "post_content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @NotEmpty @Column(name ="create_post_date")
    private LocalDateTime createPostDate;

    @NotEmpty @Column(name = "like_count")
    private Long likeCount;

    @OneToMany(mappedBy = "post")
    private List<Review> reviews = new ArrayList<>();
}
