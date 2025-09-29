package br.com.msp.busiq.infrastructure.persistence.entities;

import br.com.msp.busiq.infrastructure.persistence.repositories.CalendarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CalendarEntityTest extends DatabaseConnectionTest {

    @Autowired
    private CalendarRepository calendarRepository;

    @Test
    void createCalendarAndCompareWithDatabaseSuccess() {
        CalendarEntity calendar = new CalendarEntity(
                "U__",
                true,
                true,
                true,
                true,
                true,
                false,
                false,
                LocalDate.of(2025, 1, 1),
                LocalDate.of(2025, 12, 31)
        );

        calendarRepository.save(calendar);

        assertThat(calendarRepository.findById("U__"))
                .hasValueSatisfying(foundCalendar -> assertThat(foundCalendar)
                        .extracting(
                                CalendarEntity::getServiceId,
                                CalendarEntity::isMonday,
                                CalendarEntity::isTuesday,
                                CalendarEntity::isWednesday,
                                CalendarEntity::isThursday,
                                CalendarEntity::isFriday,
                                CalendarEntity::isSaturday,
                                CalendarEntity::isSunday,
                                CalendarEntity::getStartDate,
                                CalendarEntity::getEndDate
                        )
                        .containsExactly("U__", true, true, true, true, true, false, false,
                                LocalDate.of(2025, 1, 1), LocalDate.of(2025, 12, 31))
                );
    }
}
