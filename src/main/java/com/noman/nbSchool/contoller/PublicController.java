package com.noman.nbSchool.contoller;


import com.noman.nbSchool.model.Person;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
@RequestMapping("/public")
public class PublicController {

    @RequestMapping(value = "/register", method = {RequestMethod.GET})
    public String displayRegisterPage(Model model, HttpServletRequest req) {
        model.addAttribute("request", req);
        model.addAttribute("person", new Person());
        return "register.html";
    }

    @RequestMapping(value = "/createUser",method = {RequestMethod.POST})
    public String createUser(@Valid @ModelAttribute("person") Person person, Errors errors, Model model, HttpServletRequest req) {
        model.addAttribute("request", req);
        if (errors.hasErrors()) {
            return "register.html";
        }
        return "redirect:/login?register=true";
    }


}
