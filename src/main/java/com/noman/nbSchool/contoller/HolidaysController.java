package com.noman.nbSchool.contoller;

import com.noman.nbSchool.model.Holiday;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class HolidaysController {

    @GetMapping("/holidays/{display}")
    public String displayHolidays(@PathVariable String display, Model model) {

        List<Holiday> holidays = Arrays.asList(
                // National Holidays
                new Holiday("Mar 26", "Independence Day", Holiday.Type.FEDERAL),
                new Holiday("Dec 16", "Victory Day", Holiday.Type.FEDERAL),
                new Holiday("Aug 15", "National Mourning Day", Holiday.Type.FEDERAL),
                // Religious Festivals
                new Holiday("Mar 22", "Eid-ul-Fitr", Holiday.Type.FESTIVAL),
                new Holiday("May 16", "Eid-ul-Adha", Holiday.Type.FESTIVAL),
                // Other Significant Days
                new Holiday("Feb 21", "International Mother Language Day", Holiday.Type.FEDERAL),
                new Holiday("Dec 14", "Martyred Intellectuals Day", Holiday.Type.FEDERAL)
        );
        List<Holiday> federalHolidays = holidays.stream().filter(h -> h.getType() == Holiday.Type.FEDERAL).collect(Collectors.toList());
        List<Holiday> festivalHolidays = holidays.stream().filter(h -> h.getType() == Holiday.Type.FESTIVAL).collect(Collectors.toList());
        if(display!=null && display.equals("all")){
            model.addAttribute("festival", true);
            model.addAttribute("federal", true);
        } else if (display!=null && display.equals("federal")) {
            model.addAttribute("federal", true);
        }else if (display!=null && display.equals("festival")) {
            model.addAttribute("festival", true);
        }
        model.addAttribute("FESTIVAL", festivalHolidays);
        model.addAttribute("FEDERAL", federalHolidays);

        return "holidays.html";
    }


}
