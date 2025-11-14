package br.com.msp.busiq.core.usecases.calendar;

import br.com.msp.busiq.core.domain.Calendar;
import br.com.msp.busiq.core.gateway.calendar.CalendarGateway;

import java.util.List;

public class GetCalendarsInteractor implements GetCalendarsCase {
    private final CalendarGateway calendarGateway;

    public GetCalendarsInteractor(CalendarGateway calendarGateway) {
        this.calendarGateway = calendarGateway;
    }

    @Override
    public List<Calendar> execute() {
        return calendarGateway.getAllCalendars();
    }
}
