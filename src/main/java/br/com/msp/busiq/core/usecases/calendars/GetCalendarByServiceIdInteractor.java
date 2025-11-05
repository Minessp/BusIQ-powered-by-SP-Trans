package br.com.msp.busiq.core.usecases.calendars;

import br.com.msp.busiq.core.domain.Calendar;
import br.com.msp.busiq.core.gateway.calendar.CalendarGateway;

public class GetCalendarByServiceIdInteractor implements GetCalendarByServiceId {
    private final CalendarGateway calendarGateway;

    public GetCalendarByServiceIdInteractor(CalendarGateway calendarGateway) {
        this.calendarGateway = calendarGateway;
    }

    @Override
    public Calendar execute(String serviceId) {
        return calendarGateway.getCalendarByServiceId(serviceId);
    }
}
