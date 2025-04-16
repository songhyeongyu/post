package practice.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter @Setter
public class LoginRequest {
    @NotBlank(message = "아이디를 입력해주세요")
    @Size(min = 3,max = 8,message = "3~8자로 입력해주세요")
    private String nickname;

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Size(min = 3,max = 8,message = "3~8자로 입력해주세요")
    private String password;
}
