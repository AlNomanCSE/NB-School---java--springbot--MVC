package com.noman.nbSchool.contoller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class GlobalExceptionController {
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e, Model model, HttpServletRequest req) {
        model.addAttribute("request", req);
        log.error(e.getMessage(), e);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("errormsg", e.getMessage());
        return modelAndView;
    }
}
