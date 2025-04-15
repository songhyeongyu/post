package practice.post.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Table(name = "tb_user")
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @NotEmpty @Column(name = "NAME")
    private String username;

    @NotEmpty @Column(unique = true,name = "NICK_NAME")
    private String nickname;

    @NotEmpty @Column(name = "PASSWORD")
    private String password;

    @NotEmpty @Column(unique = true, name = "EMAIL")
    private String email;

    @NotEmpty @Column(unique = true, name="P_NUMBER")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @OneToMany(mappedBy = "member")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<ReviewLike> reviewLikes = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Review> reviews = new ArrayList<>();

    protected Member () {}

    @Builder
    public static Member create(String nickname, String encryptedPassword, String email, String phoneNumber) {
        Member member = new Member();
        member.nickname = nickname;
        member.password = encryptedPassword;
        member.email = email;
        member.phoneNumber = phoneNumber;
        member.status = UserStatus.NORMAL;
        return member;
    }

}
