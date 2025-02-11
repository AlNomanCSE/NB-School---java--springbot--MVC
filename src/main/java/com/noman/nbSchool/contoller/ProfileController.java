package com.noman.nbSchool.contoller;

import com.noman.nbSchool.model.Person;
import com.noman.nbSchool.model.Profile;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class ProfileController {
    @RequestMapping("/displayProfile")
    public ModelAndView displayProfile(HttpServletRequest request, Model model, HttpSession session) {
        Person loggedInPerson = (Person) session.getAttribute("loggedInPerson");
        ModelAndView modelAndView = new ModelAndView("profile.html");
        Profile profile = new Profile();
        profile.setName(loggedInPerson.getName());
        profile.setMobileNumber(loggedInPerson.getMobileNumber());
        profile.setEmail(loggedInPerson.getEmail());
        if(loggedInPerson.getAddress() !=null && loggedInPerson.getAddress().getAddressId()>0){
            profile.setAddress1(loggedInPerson.getAddress().getAddress1());
            profile.setAddress2(loggedInPerson.getAddress().getAddress2());
            profile.setCity(loggedInPerson.getAddress().getCity());
            profile.setState(loggedInPerson.getAddress().getState());
            profile.setZipCode(loggedInPerson.getAddress().getZipCode());
        }
        modelAndView.addObject("profile", profile);
        model.addAttribute("request",request);
        return modelAndView;
    }
}
