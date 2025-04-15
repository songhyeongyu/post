package practice.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import practice.post.domain.Member;
import practice.post.domain.UserStatus;
import practice.post.dto.MemberJoinDto;
import practice.post.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;


    @Override
    public void join(MemberJoinDto dto) {
        validateDuplicate(dto);
        Member member = Member.create(
                dto.getNickname(),
                dto.getPassword(),
                dto.getEmail(),
                dto.getPhoneNumber()
        );
        memberRepository.save(member);

    }





    private void validateDuplicate(MemberJoinDto dto) {
        validateDuplicateNickName(dto.getNickname());
        validateDuplicateEmail(dto.getEmail());
        validateDuplicatePhoneNumber(dto.getPhoneNumber());
    }

    private void validateDuplicateNickName(String nickname) {
        if (memberRepository.findByNickName(nickname).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 닉네임 입니다.");
        }

    }

    private void validateDuplicateEmail(String email) {
        if (memberRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일 입니다.");
        }
    }

    private void validateDuplicatePhoneNumber(String phoneNumber) {
        if (memberRepository.findByPhoneNumber(phoneNumber).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 핸드폰 번호입니다.");
        }
    }



    @Override
    public Member login(String username, String password) {
        return null;
    }
}
