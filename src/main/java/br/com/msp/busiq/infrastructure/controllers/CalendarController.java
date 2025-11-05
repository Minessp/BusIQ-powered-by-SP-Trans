package br.com.msp.busiq.infrastructure.controllers;

import br.com.msp.busiq.core.usecases.calendars.GetCalendarsCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calendar")
public class CalendarController {
//    private final GetCalendarsCase getCalendarsCase;
//
//    public CalendarController(GetCalendarsCase getCalendarsCase) {
//        this.getCalendarsCase = getCalendarsCase;
//    }
//
//    @GetMapping
//    public @ResponseBody List<CalendarResponse> getCalendars(){
//
//    }
}
