package br.com.msp.busiq.core.usecases.calendar;

import br.com.msp.busiq.core.domain.Calendar;

public interface GetCalendarByServiceId {
    Calendar execute(String serviceId);
}
