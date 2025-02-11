package com.noman.nbSchool.contoller;

import com.noman.nbSchool.model.Person;
import com.noman.nbSchool.repository.PersonRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class DashBoardController {
    private final PersonRepository personRepository;
    @RequestMapping("/dashboard")
    public String displayDashboard(Model model, Authentication authentication, HttpServletRequest req, HttpSession session) {
        Person person = personRepository.readByEmail(authentication.getName());
        model.addAttribute("request", req);
        model.addAttribute("username", person.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        session.setAttribute("loggedInPerson", person);
        return "dashboard.html";
    }
}
