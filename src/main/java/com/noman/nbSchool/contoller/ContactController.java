package com.noman.nbSchool.contoller;


import com.noman.nbSchool.model.Contact;
import com.noman.nbSchool.repository.ContactRepository;
import com.noman.nbSchool.service.ContactService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ContactController {

    private final ContactRepository contactRepository;
    private final ContactService contactService;

    @RequestMapping(value = "contact")
    public String contactPage(HttpServletRequest req, Model model) {
        model.addAttribute("request", req);
        model.addAttribute("contact", new Contact());
        return "contact.html";
    }

    @RequestMapping(value = "/saveMsg", method = POST)
    public String saveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors, Model model,HttpServletRequest req) {
        model.addAttribute("request", req);
        log.info("save contact {}", contact);

        // If validation fails, return to the contact page with errors
        if (errors.hasErrors()) {
            log.error("Contact form validation failed due to : " + errors.toString());
            model.addAttribute("errors", errors); // Add errors to the model
            return "contact"; // Return to the contact page (without .html extension)
        }

        // Save the message and redirect to the contact page
        try {
            contactService.saveMessageDetails(contact);
        } catch (Exception e) {
            log.error("Failed to save contact message: " + e.getMessage(), e);
            model.addAttribute("errorMessage", "An unexpected error occurred while saving your message.");
            return "contact"; // Return to the contact page with an error message
        }

        return "redirect:/contact";
    }

    @RequestMapping(value = "/displayMessages", method = GET)
    public void displayMessages(Model model,HttpServletRequest req) {
        model.addAttribute("request", req);
        List<Contact> allMessages = contactService.findAllMessages();
        model.addAttribute("contactMsgs", allMessages);
    }

}
