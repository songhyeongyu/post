package practice.post.service;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import practice.post.domain.Member;
import practice.post.dto.MemberJoinDto;
import practice.post.dto.request.LoginRequest;
import practice.post.repository.MemberRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest

class LoginServiceImplTest {


    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private LoginService loginService;

    @Autowired
    private MemberService memberService;

    @BeforeEach
    void tearDown() {
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("로그인 성공")
    void 로그인_성공() {
        //given
        MemberJoinDto dto = new MemberJoinDto();
        dto.setUsername("홍길동");
        dto.setNickname("hong123");
        dto.setPassword("abcd1234");
        dto.setEmail("hong@example.com");
        dto.setPhoneNumber("01011112222");

        memberService.join(dto);
        // when
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setNickname("hong123");
        loginRequest.setPassword("abcd1234");

        //then
        Optional<Member> result = loginService.login(loginRequest);
        assertThat(result).isPresent();
        assertThat(result.get().getNickname()).isEqualTo("hong123");

    }

    @Test
    @DisplayName("로그인 실패 비밀번호 틀림")
    void 로그인_실패_비밀번호_틀림() {
        //given
        MemberJoinDto dto = new MemberJoinDto();
        dto.setUsername("홍길동");
        dto.setNickname("hong123");
        dto.setPassword("abcd1234");
        dto.setEmail("hong@example.com");
        dto.setPhoneNumber("01011112222");

        memberService.join(dto);
        // when
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setNickname("hong123");
        loginRequest.setPassword("abcd123");

        //then
        Optional<Member> result = loginService.login(loginRequest);
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("로그인 실패 아이디 틀림")
    void 로그인_실패_아이디_틀림() {
        //given
        MemberJoinDto dto = new MemberJoinDto();
        dto.setUsername("홍길동");
        dto.setNickname("hong123");
        dto.setPassword("abcd1234");
        dto.setEmail("hong@example.com");
        dto.setPhoneNumber("01011112222");

        memberService.join(dto);
        // when
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setNickname("hong12");
        loginRequest.setPassword("abcd1234");

        //then
        Optional<Member> result = loginService.login(loginRequest);
        assertThat(result).isEmpty();
    }



}