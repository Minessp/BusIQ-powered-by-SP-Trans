package br.com.msp.busiq.infrastructure.controllers.calendar;

import br.com.msp.busiq.core.domain.Calendar;
import br.com.msp.busiq.core.usecases.calendars.GetCalendarByServiceId;
import br.com.msp.busiq.core.usecases.calendars.GetCalendarsCase;
import br.com.msp.busiq.infrastructure.controllers.CalendarController;
import br.com.msp.busiq.infrastructure.dtos.CalendarResponse;
import br.com.msp.busiq.infrastructure.exceptions.GlobalExceptionHandler;
import br.com.msp.busiq.infrastructure.mappers.calendar.CalendarDtoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CalendarController.class)
@Import(GlobalExceptionHandler.class)
public class CalendarControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    private GetCalendarsCase getCalendarsCase;

    @MockitoBean
    private GetCalendarByServiceId getCalendarByServiceId;

    @MockitoBean
    private CalendarDtoMapper calendarDtoMapper;

    Calendar calendar1 = Calendar.builder()
            .serviceId("1")
            .monday(true)
            .tuesday(true)
            .wednesday(true)
            .thursday(true)
            .friday(true)
            .saturday(false)
            .sunday(false)
            .startDate(LocalDate.parse("20251105", DateTimeFormatter.ofPattern("yyyyMMdd")))
            .endDate(LocalDate.parse("20251106", DateTimeFormatter.ofPattern("yyyyMMdd")))
            .build();

    CalendarResponse response1 = CalendarResponse.builder()
            .serviceId("1")
            .monday(true)
            .tuesday(true)
            .wednesday(true)
            .thursday(true)
            .friday(true)
            .saturday(false)
            .sunday(false)
            .startDate(LocalDate.parse("20251105", DateTimeFormatter.ofPattern("yyyyMMdd")))
            .endDate(LocalDate.parse("20251106", DateTimeFormatter.ofPattern("yyyyMMdd")))
            .build();

    @Test
    void shouldReturnAllCalendarsWithCorrectTypeAndValuesSuccess() throws Exception {
        Calendar calendar2 = Calendar.builder()
                .serviceId("2")
                .monday(false)
                .tuesday(false)
                .wednesday(false)
                .thursday(false)
                .friday(false)
                .saturday(true)
                .sunday(true)
                .startDate(LocalDate.parse("20251105", DateTimeFormatter.ofPattern("yyyyMMdd")))
                .endDate(LocalDate.parse("20251108", DateTimeFormatter.ofPattern("yyyyMMdd")))
                .build();

        when(getCalendarsCase.execute()).thenReturn(List.of(calendar1, calendar2));

        CalendarResponse response2 = CalendarResponse.builder()
                .serviceId("2")
                .monday(false)
                .tuesday(false)
                .wednesday(false)
                .thursday(false)
                .friday(false)
                .saturday(true)
                .sunday(true)
                .startDate(LocalDate.parse("20251105", DateTimeFormatter.ofPattern("yyyyMMdd")))
                .endDate(LocalDate.parse("20251108", DateTimeFormatter.ofPattern("yyyyMMdd")))
                .build();

        when(calendarDtoMapper.toResponse(calendar1)).thenReturn(response1);
        when(calendarDtoMapper.toResponse(calendar2)).thenReturn(response2);

        mockMvc.perform(get("/calendar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].serviceId").value("1"))
                .andExpect(jsonPath("$[0].monday").value(true))
                .andExpect(jsonPath("$[0].tuesday").value(true))
                .andExpect(jsonPath("$[0].wednesday").value(true))
                .andExpect(jsonPath("$[0].thursday").value(true))
                .andExpect(jsonPath("$[0].friday").value(true))
                .andExpect(jsonPath("$[0].saturday").value(false))
                .andExpect(jsonPath("$[0].sunday").value(false))
                .andExpect(jsonPath("$[0].startDate").value("2025-11-05"))
                .andExpect(jsonPath("$[0].endDate").value("2025-11-06"))
                .andExpect(jsonPath("$[1].serviceId").value("2"))
                .andExpect(jsonPath("$[1].monday").value(false))
                .andExpect(jsonPath("$[1].tuesday").value(false))
                .andExpect(jsonPath("$[1].wednesday").value(false))
                .andExpect(jsonPath("$[1].thursday").value(false))
                .andExpect(jsonPath("$[1].friday").value(false))
                .andExpect(jsonPath("$[1].saturday").value(true))
                .andExpect(jsonPath("$[1].sunday").value(true))
                .andExpect(jsonPath("$[1].startDate").value("2025-11-05"))
                .andExpect(jsonPath("$[1].endDate").value("2025-11-08"));
    }

    @Test
    void shouldReturnExactlyOneCalendarByServiceIdWithCorrectTypeAndValuesSuccess() throws Exception {
        when(getCalendarByServiceId.execute("1")).thenReturn(calendar1);
        when(calendarDtoMapper.toResponse(calendar1)).thenReturn(response1);

        mockMvc.perform(get("/calendar/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.serviceId").value("1"))
                .andExpect(jsonPath("$.monday").value(true))
                .andExpect(jsonPath("$.tuesday").value(true))
                .andExpect(jsonPath("$.wednesday").value(true))
                .andExpect(jsonPath("$.thursday").value(true))
                .andExpect(jsonPath("$.friday").value(true))
                .andExpect(jsonPath("$.saturday").value(false))
                .andExpect(jsonPath("$.sunday").value(false))
                .andExpect(jsonPath("$.startDate").value("2025-11-05"))
                .andExpect(jsonPath("$.endDate").value("2025-11-06"));
    }

    @Test
    void shouldReturnBadRequestStatusWhenServiceIdIsInvalid() throws Exception {
        when(getCalendarByServiceId.execute("2")).thenThrow(new IllegalArgumentException("Service ID inválido"));

        mockMvc.perform(get("/calendar/2"))
                .andExpect(status().isBadRequest());
    }
}
