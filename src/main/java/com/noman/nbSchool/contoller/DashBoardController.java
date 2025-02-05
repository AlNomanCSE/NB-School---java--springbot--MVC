package com.noman.nbSchool.contoller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashBoardController {
    @RequestMapping("/dashboard")
    public String displayDashboard(Model model, Authentication authentication, HttpServletRequest req) {
        model.addAttribute("request", req);
        model.addAttribute("username", authentication.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        return "dashboard.html";
    }
}
