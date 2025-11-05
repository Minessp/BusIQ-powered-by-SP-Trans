package br.com.msp.busiq.core.usecases.calendars;

import br.com.msp.busiq.core.domain.Calendar;

public interface GetCalendarByServiceId {
    Calendar execute(String serviceId);
}
