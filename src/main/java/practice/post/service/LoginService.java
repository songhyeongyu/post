package practice.post.service;

import practice.post.domain.Member;
import practice.post.dto.request.LoginRequest;

import java.util.Optional;

public interface LoginService {
    Optional<Member> login(LoginRequest request);
}
