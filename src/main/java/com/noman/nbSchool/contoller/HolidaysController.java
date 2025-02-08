package com.noman.nbSchool.contoller;

import com.noman.nbSchool.model.Holiday;
import com.noman.nbSchool.repository.HolidaysRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HolidaysController {
    private final HolidaysRepository holidaysRepository;

    @GetMapping("holidays/{display}")
    public String displayHolidays(@PathVariable String display, Model model, HttpServletRequest req) {
        model.addAttribute("request", req);
        if (display.equals("all")) {
            model.addAttribute("festival", true);
            model.addAttribute("federal", true);
            model.addAttribute("FESTIVAL", holidaysRepository.findByType(Holiday.Type.FESTIVAL));
            model.addAttribute("FEDERAL", holidaysRepository.findByType(Holiday.Type.FEDERAL));
        } else if (display.equals("festival")) {
            model.addAttribute("festival", true);
            model.addAttribute("FESTIVAL", holidaysRepository.findByType(Holiday.Type.FESTIVAL));
        } else if (display.equals("federal")) {
            model.addAttribute("federal", true);
            model.addAttribute("FEDERAL", holidaysRepository.findByType(Holiday.Type.FEDERAL));
        }
        return "holidays.html";
    }


}
