package br.com.msp.busiq.config.calendar;

import br.com.msp.busiq.core.gateway.calendar.CalendarGateway;
import br.com.msp.busiq.core.usecases.calendar.SaveCalendarDataCase;
import br.com.msp.busiq.core.usecases.calendar.SaveCalendarDataInteractor;
import br.com.msp.busiq.core.usecases.calendar.GetCalendarByServiceId;
import br.com.msp.busiq.core.usecases.calendar.GetCalendarByServiceIdInteractor;
import br.com.msp.busiq.core.usecases.calendar.GetCalendarsCase;
import br.com.msp.busiq.core.usecases.calendar.GetCalendarsInteractor;
import br.com.msp.busiq.data.parser.TxtParser;
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
    public CalendarGateway calendarGateway(CalendarRepository calendarRepository, CalendarDtoMapper calendarDtoMapper,
                                           TxtParser txtParser) {
        return new CalendarGatewayImpl(calendarRepository, calendarDtoMapper, txtParser);
    }

    @Bean
    public CalendarDtoMapper calendarDtoMapper() {
        return new CalendarDtoMapper();
    }

    @Bean
    SaveCalendarDataCase saveCalendarDataCase(CalendarGateway calendarGateway) {
        return new SaveCalendarDataInteractor(calendarGateway);
    }
}
