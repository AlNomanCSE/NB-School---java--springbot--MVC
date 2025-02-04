package com.noman.nbSchool.contoller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CoursesController {
    @RequestMapping(value = {"/courses"})
    public String home(HttpServletRequest req, Model model) {
        model.addAttribute("request", req);
        return "courses.html";
    }
}
