package com.noman.nbSchool.contoller;


import com.noman.nbSchool.model.Contact;
import com.noman.nbSchool.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class ContactController {


    private static Logger log = LoggerFactory.getLogger(ContactController.class);
    private ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping(value = "/contact")
    public String contactPage() {
        return "contact.html";
    }

    @RequestMapping(value = "/saveMsg", method = POST)
    public ModelAndView saveMessaage(Contact contact) {
         contactService.saveMessageDetails(contact);
        return new ModelAndView("redirect:/contact");
    }

}
