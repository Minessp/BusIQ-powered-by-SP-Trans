package br.com.msp.busiq.config.calendar;

import br.com.msp.busiq.core.gateway.calendar.CalendarGateway;
import br.com.msp.busiq.core.usecases.calendars.GetCalendarByServiceId;
import br.com.msp.busiq.core.usecases.calendars.GetCalendarByServiceIdInteractor;
import br.com.msp.busiq.core.usecases.calendars.GetCalendarsCase;
import br.com.msp.busiq.core.usecases.calendars.GetCalendarsInteractor;
import br.com.msp.busiq.infrastructure.gateway.calendar.CalendarGatewayImpl;
import br.com.msp.busiq.infrastructure.mappers.calendar.CalendarDtoMapper;
import br.com.msp.busiq.infrastructure.persistence.repositories.CalendarRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalendarConfig {

    @Bean
    public GetCalendarsCase getCalendarsCase(CalendarGateway calendarGateway) {
        return new GetCalendarsInteractor(calendarGateway);
    }

    @Bean
    public GetCalendarByServiceId getCalendarByServiceId(CalendarGateway calendarGateway) {
        return new GetCalendarByServiceIdInteractor(calendarGateway);
    }

    @Bean
    public CalendarGateway calendarGateway(CalendarRepository calendarRepository, CalendarDtoMapper calendarDtoMapper) {
        return new CalendarGatewayImpl(calendarRepository, calendarDtoMapper);
    }

    @Bean
    public CalendarDtoMapper calendarDtoMapper() {
        return new CalendarDtoMapper();
    }
}
