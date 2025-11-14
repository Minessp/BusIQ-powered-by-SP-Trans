package br.com.msp.busiq.infrastructure.gateway.calendar;

import br.com.msp.busiq.core.domain.Calendar;
import br.com.msp.busiq.core.gateway.calendar.CalendarGateway;
import br.com.msp.busiq.data.parser.TxtParser;
import br.com.msp.busiq.infrastructure.mappers.calendar.CalendarDtoMapper;
import br.com.msp.busiq.infrastructure.persistence.entities.CalendarEntity;
import br.com.msp.busiq.infrastructure.persistence.repositories.CalendarRepository;

import java.util.List;

public class CalendarGatewayImpl implements CalendarGateway {
    private final CalendarRepository calendarRepository;
    private final CalendarDtoMapper calendarDtoMapper;
    private final TxtParser txtParser;

    public CalendarGatewayImpl(CalendarRepository calendarRepository, CalendarDtoMapper calendarDtoMapper, TxtParser txtParser) {
        this.calendarRepository = calendarRepository;
        this.calendarDtoMapper = calendarDtoMapper;
        this.txtParser = txtParser;
    }

    @Override
    public List<Calendar> getAllCalendars() {
        return calendarRepository.findAll().stream().map(calendarDtoMapper::toDomain).toList();
    }

    @Override
    public Calendar getCalendarByServiceId(String serviceId) {
        if (serviceId == null) {
            throw new IllegalArgumentException("ServiceId não pode ser nulo");
        }

        return calendarRepository.findById(serviceId).map(calendarDtoMapper::toDomain).orElseThrow(
                () -> new IllegalArgumentException("Calendário não encontrado"));
    }

    @Override
    public void saveCalendarData() {
        List<CalendarEntity> calendars = txtParser.toCalendar().stream().map(calendarDtoMapper::toEntity).toList();
        calendarRepository.saveAll(calendars);
    }
}
