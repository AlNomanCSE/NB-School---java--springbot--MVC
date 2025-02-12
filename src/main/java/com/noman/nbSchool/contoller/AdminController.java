package com.noman.nbSchool.contoller;

import com.noman.nbSchool.model.Courses;
import com.noman.nbSchool.model.NbClass;
import com.noman.nbSchool.model.Person;
import com.noman.nbSchool.repository.CoursesRepository;
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
    private final CoursesRepository coursesRepository;
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

    @GetMapping("deleteStudent")
    public ModelAndView deleteStudent(@RequestParam int personId, HttpSession session) {
        NbClass nbClass = (NbClass)session.getAttribute("nbClass");
        Optional<Person> person = personRepository.findById(personId);
        person.get().setNbClass(null);
        nbClass.getPersons().remove(person.get());
        nbClassRepository.save(nbClass);
        session.setAttribute("nbClass",nbClass);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayStudents?classId="+nbClass.getClassId());
        return modelAndView;
    }

    @GetMapping("/displayCourses")
    public ModelAndView displayCourses(Model model,HttpServletRequest req) {
        model.addAttribute("request", req);
        List<Courses> courses = coursesRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("courses_secure.html");
        modelAndView.addObject("courses",courses);
        modelAndView.addObject("course", new Courses());
        return modelAndView;
    }

    @PostMapping("/addNewCourse")
    public ModelAndView addNewCourse(Model model, @ModelAttribute("course") Courses course,HttpServletRequest req) {
        model.addAttribute("request", req);
        ModelAndView modelAndView = new ModelAndView();
        coursesRepository.save(course);
        modelAndView.setViewName("redirect:/admin/displayCourses");
        return modelAndView;
    }

    @GetMapping("/viewStudents")
    public ModelAndView viewStudents(Model model, @RequestParam int id
            ,HttpSession session,@RequestParam(required = false) String error,HttpServletRequest req) {
        model.addAttribute("request", req);
        String errorMessage = null;
        ModelAndView modelAndView = new ModelAndView("course_students.html");
        Optional<Courses> courses = coursesRepository.findById(id);
        modelAndView.addObject("courses",courses.get());
        modelAndView.addObject("person",new Person());
        session.setAttribute("courses",courses.get());
        if(error != null) {
            errorMessage = "Invalid Email entered!!";
            modelAndView.addObject("errorMessage", errorMessage);
        }
        return modelAndView;
    }
    @PostMapping("/addStudentToCourse")
    public ModelAndView addStudentToCourse(Model model, @ModelAttribute("person") Person person,
                                           HttpSession session,HttpServletRequest req) {
        model.addAttribute("request", req);
        ModelAndView modelAndView = new ModelAndView();
        Courses courses = (Courses) session.getAttribute("courses");
        Person personEntity = personRepository.readByEmail(person.getEmail());
        if(personEntity==null || !(personEntity.getPersonId()>0)){
            modelAndView.setViewName("redirect:/admin/viewStudents?id="+courses.getCourseId()
                    +"&error=true");
            return modelAndView;
        }
        personEntity.getCourses().add(courses);
        courses.getPersons().add(personEntity);
        personRepository.save(personEntity);
        session.setAttribute("courses",courses);
        modelAndView.setViewName("redirect:/admin/viewStudents?id="+courses.getCourseId());
        return modelAndView;
    }

    @GetMapping("/deleteStudentFromCourse")
    public ModelAndView deleteStudentFromCourse(Model model, @RequestParam int personId,
                                                HttpSession session) {
        Courses courses = (Courses) session.getAttribute("courses");
        Optional<Person> person = personRepository.findById(personId);
        person.get().getCourses().remove(courses);
        courses.getPersons().remove(person);
        personRepository.save(person.get());
        session.setAttribute("courses",courses);
        ModelAndView modelAndView = new
                ModelAndView("redirect:/admin/viewStudents?id="+courses.getCourseId());
        return modelAndView;
    }
}
