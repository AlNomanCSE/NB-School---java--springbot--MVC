package com.noman.nbSchool.contoller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping(value = {"", "/", "/home"})
    public String homeController(Model model) {
        model.addAttribute("username", "Abdullah");
        return "home.html";
    }
}
