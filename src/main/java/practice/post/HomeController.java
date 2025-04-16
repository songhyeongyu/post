package practice.post;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import practice.post.domain.Member;
import practice.post.web.SessionConst;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return "home";
        }

        Member login = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        if (login == null) {
            return "home";
        }

        model.addAttribute("member", login);

        return "loginHome";
    }

}
