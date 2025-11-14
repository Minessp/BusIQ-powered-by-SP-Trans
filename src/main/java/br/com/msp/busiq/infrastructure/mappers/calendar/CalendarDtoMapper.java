package br.com.msp.busiq.infrastructure.mappers.calendar;

import br.com.msp.busiq.core.domain.Calendar;
import br.com.msp.busiq.infrastructure.dtos.CalendarResponse;
import br.com.msp.busiq.infrastructure.persistence.entities.CalendarEntity;

public class CalendarDtoMapper {
    public Calendar toDomain(CalendarEntity calendarEntity) {
        return Calendar
                .builder()
                .serviceId(calendarEntity.getServiceId())
                .monday(calendarEntity.isMonday())
                .tuesday(calendarEntity.isTuesday())
                .wednesday(calendarEntity.isWednesday())
                .thursday(calendarEntity.isThursday())
                .friday(calendarEntity.isFriday())
                .saturday(calendarEntity.isSaturday())
                .sunday(calendarEntity.isSunday())
                .startDate(calendarEntity.getStartDate())
                .endDate(calendarEntity.getEndDate())
                .build();
    }

    public CalendarResponse toResponse(Calendar calendar) {
        return CalendarResponse
                .builder()
                .serviceId(calendar.serviceId())
                .monday(calendar.monday())
                .tuesday(calendar.tuesday())
                .wednesday(calendar.wednesday())
                .thursday(calendar.thursday())
                .friday(calendar.friday())
                .saturday(calendar.saturday())
                .sunday(calendar.sunday())
                .startDate(calendar.startDate())
                .endDate(calendar.endDate())
                .build();
    }

    public CalendarEntity toEntity(Calendar calendar) {
        return new CalendarEntity(calendar.serviceId(),
                                  calendar.monday(),
                                  calendar.tuesday(),
                                  calendar.wednesday(),
                                  calendar.thursday(),
                                  calendar.friday(),
                                  calendar.saturday(),
                                  calendar.sunday(),
                                  calendar.startDate(),
                                  calendar.endDate());
    }
}
