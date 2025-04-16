package practice.post.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import practice.post.domain.Member;
import practice.post.dto.MemberJoinDto;
import practice.post.repository.MemberRepository;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/add")
    public String addForm(@ModelAttribute("memberJoinDto") MemberJoinDto memberJoinDto) {
        return "members/addMemberForm";
    }

    /**
     * String username,String nickname, String encryptedPassword, String email, String phoneNumber
     */
    @PostMapping("/add")
    public String join(@Valid @ModelAttribute("memberJoinDto") MemberJoinDto dto) {
        Member member = Member.create(
                dto.getUsername(),
                dto.getNickname(),
                dto.getPassword(),
                dto.getEmail(),
                dto.getPhoneNumber()
        );
        memberRepository.save(member);
        return "redirect:/";
    }

}
