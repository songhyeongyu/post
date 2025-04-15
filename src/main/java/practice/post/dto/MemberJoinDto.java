package practice.post.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class MemberJoinDto {
    @NotBlank(message = "이름을 입력해주세요")
    private String username;

    @NotBlank(message = "아이디를 입력해주세요")
    @Size(min = 3,max = 8,message = "3~8자로 입력해주세요")
    private String nickname;

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Size(min = 3,max = 8,message = "3~8자로 입력해주세요")
    private String password;

    @NotBlank(message = "이메일을 입력해주세요")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "핸드폰 번호를 입력해주세요")
    @Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message = "올바른 휴대폰 번호(번호만 입력)를 입력해주세요.")
    private String phoneNumber;

}
