package practice.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import practice.post.domain.Member;

import practice.post.repository.MemberRepository;
import practice.post.dto.request.LoginRequest;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{

    private final MemberRepository memberRepository;

    /**
     * @return null -> 실패
     */
    public Optional<Member> login(LoginRequest request) {
        return memberRepository.findByNickName(request.getNickname())
                .filter(m -> m.getPassword().equals(request.getPassword()));
    }

}
