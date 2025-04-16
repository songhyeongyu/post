package practice.post.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import practice.post.domain.Member;
import practice.post.dto.MemberJoinDto;
import practice.post.repository.MemberRepository;

@Slf4j
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
    @PostMapping("/add") @Transactional
    public String join(@Valid @ModelAttribute("memberJoinDto") MemberJoinDto dto, BindingResult br) {


        if (memberRepository.findByNickName(dto.getNickname()).isPresent()) {
            br.rejectValue("nickname", "duplicate.nickname", "이미 존재하는 아이디입니다.");
        }
        if (memberRepository.findByEmail(dto.getEmail()).isPresent()) {
            br.rejectValue("email", "duplicate.email", "이미 존재하는 이메일입니다.");
        }
        if (memberRepository.findByPhoneNumber(dto.getPhoneNumber()).isPresent()) {
            br.rejectValue("phoneNumber", "duplicate.phoneNumber", "이미 존재하는 핸드폰 번호입니다.");
        }
        if (br.hasErrors()) {
            log.info("errors={}",br);
            return "members/addMemberForm";
        }

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
