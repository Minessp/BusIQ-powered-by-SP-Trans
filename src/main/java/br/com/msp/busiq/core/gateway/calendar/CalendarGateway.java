package br.com.msp.busiq.core.gateway.calendar;

import br.com.msp.busiq.core.domain.Calendar;

import java.util.List;

public interface CalendarGateway {
    List<Calendar> getAllCalendars();

    Calendar getCalendarByServiceId(String serviceId);

    void saveCalendarData();
}
