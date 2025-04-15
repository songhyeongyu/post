package practice.post.service;

import practice.post.domain.Member;
import practice.post.dto.MemberJoinDto;

public interface MemberService {
    void join(MemberJoinDto memberJoinDto);

    Member login(String username, String password);
    
}
