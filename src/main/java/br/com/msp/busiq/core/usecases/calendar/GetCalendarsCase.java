package br.com.msp.busiq.core.usecases.calendar;

import br.com.msp.busiq.core.domain.Calendar;

import java.util.List;

public interface GetCalendarsCase {
    List<Calendar> execute();
}
