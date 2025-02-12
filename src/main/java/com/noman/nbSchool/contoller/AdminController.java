package com.noman.nbSchool.contoller;

import com.noman.nbSchool.model.NbClass;
import com.noman.nbSchool.model.Person;
import com.noman.nbSchool.repository.NbClassRepository;
import com.noman.nbSchool.repository.PersonRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Slf4j
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
    @GetMapping("/displayStudents")
    public ModelAndView displayStudents(Model model, @RequestParam int classId, HttpSession session,
                                        @RequestParam(value = "error", required = false) String error,HttpServletRequest req) {
        String errorMessage = null;
        model.addAttribute("request", req);
        ModelAndView modelAndView = new ModelAndView("students.html");
        Optional<NbClass> nbClass = nbClassRepository.findById(classId);
        modelAndView.addObject("nbClass",nbClass.get());
        modelAndView.addObject("person",new Person());
        session.setAttribute("nbClass",nbClass.get());
        if(error != null) {
            errorMessage = "Invalid Email entered!!";
            modelAndView.addObject("errorMessage", errorMessage);
        }
        return modelAndView;
    }

    @PostMapping("/addStudent")
    public ModelAndView addStudent(Model model, @ModelAttribute("person") Person person, HttpSession session,HttpServletRequest req) {
        model.addAttribute("request", req);
        ModelAndView modelAndView = new ModelAndView();
        NbClass nbClass = (NbClass) session.getAttribute("nbClass");
        Person personEntity = personRepository.readByEmail(person.getEmail());
        if(personEntity==null || !(personEntity.getPersonId()>0)){
            modelAndView.setViewName("redirect:/admin/displayStudents?classId="+nbClass.getClassId()
                    +"&error=true");
            return modelAndView;
        }
        personEntity.setNbClass(nbClass);
        personRepository.save(personEntity);
        nbClass.getPersons().add(personEntity);
        nbClassRepository.save(nbClass);
        modelAndView.setViewName("redirect:/admin/displayStudents?classId="+nbClass.getClassId());
        return modelAndView;
    }

}
