package br.com.msp.busiq.infrastructure.controllers;

import br.com.msp.busiq.core.usecases.calendars.GetCalendarByServiceId;
import br.com.msp.busiq.core.usecases.calendars.GetCalendarsCase;
import br.com.msp.busiq.infrastructure.dtos.CalendarResponse;
import br.com.msp.busiq.infrastructure.mappers.calendar.CalendarDtoMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calendar")
public class CalendarController {
    private final GetCalendarsCase getCalendarsCase;
    private final GetCalendarByServiceId getCalendarByServiceId;
    private final CalendarDtoMapper calendarDtoMapper;

    public CalendarController(GetCalendarsCase getCalendarsCase, CalendarDtoMapper calendarDtoMapper,
                              GetCalendarByServiceId getCalendarByServiceId) {
        this.getCalendarsCase = getCalendarsCase;
        this.getCalendarByServiceId = getCalendarByServiceId;
        this.calendarDtoMapper = calendarDtoMapper;
    }

    @GetMapping
    public List<CalendarResponse> getCalendars(){
        return getCalendarsCase.execute().stream().map(calendarDtoMapper::toResponse).toList();
    }

    @GetMapping("/{service-id}")
    public CalendarResponse getCalendarByServiceId(@PathVariable("service-id") String serviceId){
        return calendarDtoMapper.toResponse(getCalendarByServiceId.execute(serviceId));
    }
}
