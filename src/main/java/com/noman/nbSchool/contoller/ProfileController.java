package com.noman.nbSchool.contoller;

import com.noman.nbSchool.model.Profile;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class ProfileController {
    @RequestMapping("/displayProfile")
    public ModelAndView displayProfile(HttpServletRequest request, Model model) {
        ModelAndView modelAndView = new ModelAndView("profile.html");
        Profile profile = new Profile();
        modelAndView.addObject("profile", profile);
        model.addAttribute("request",request);
        return modelAndView;
    }
}
