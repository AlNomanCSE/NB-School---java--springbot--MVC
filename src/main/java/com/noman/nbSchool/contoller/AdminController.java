package com.noman.nbSchool.contoller;

import com.noman.nbSchool.model.NbClass;
import com.noman.nbSchool.model.Person;
import com.noman.nbSchool.repository.NbClassRepository;
import com.noman.nbSchool.repository.PersonRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final NbClassRepository nbClassRepository;
    private final PersonRepository personRepository;
    @RequestMapping("/displayClasses")
    public ModelAndView displayClasses(Model model, HttpServletRequest req) {
        model.addAttribute("request", req);
        List<NbClass> nbClasses = nbClassRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("classes.html");
        modelAndView.addObject("nbClasses",nbClasses);
        modelAndView.addObject("nbClass", new NbClass());
        return modelAndView;
    }

    @PostMapping("/addNewClass")
    public ModelAndView addNewClass(Model model, @ModelAttribute("nbClass") NbClass nbClass,HttpServletRequest req) {
        model.addAttribute("request", req);
        nbClassRepository.save(nbClass);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;
    }

    @RequestMapping("/deleteClass")
    public ModelAndView deleteClass(Model model, @RequestParam int id,HttpServletRequest req) {
        model.addAttribute("request", req);
        Optional<NbClass> nbClass = nbClassRepository.findById(id);
        for(Person person : nbClass.get().getPersons()){
            person.setNbClass(null);
            personRepository.save(person);
        }
        nbClassRepository.deleteById(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;
    }
}
