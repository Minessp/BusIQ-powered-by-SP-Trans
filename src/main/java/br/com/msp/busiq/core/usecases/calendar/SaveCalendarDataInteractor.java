package br.com.msp.busiq.core.usecases.calendar;

import br.com.msp.busiq.core.gateway.calendar.CalendarGateway;

public class SaveCalendarDataInteractor implements SaveCalendarDataCase {
    private final CalendarGateway calendarGateway;

    public SaveCalendarDataInteractor(CalendarGateway calendarGateway) {
        this.calendarGateway = calendarGateway;
    }

    @Override
    public void execute() {
        calendarGateway.saveCalendarData();
    }
}
