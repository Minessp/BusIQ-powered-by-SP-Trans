package br.com.msp.busiq.infrastructure.persistence.entities;

import br.com.msp.busiq.infrastructure.persistence.repositories.StopTimesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class StopTimesEntityTest extends DatabaseConnectionTest {

    @Autowired
    private StopTimesRepository stopTimesRepository;

    @Test
    void createStopTimeAndCompareWithDatabaseSuccess() {
        StopTimesEntity stopTime = new StopTimesEntity(
                "1012-10-0",
                LocalTime.of(18, 0, 0),
                LocalTime.of(18, 0, 0),
                "301790",
                1
        );

        stopTimesRepository.save(stopTime);

        assertThat(stopTimesRepository.findById("1012-10-0"))
                .hasValueSatisfying(found -> assertThat(found)
                        .extracting(
                                StopTimesEntity::getTripId,
                                StopTimesEntity::getArrivalTime,
                                StopTimesEntity::getDepartureTime,
                                StopTimesEntity::getStopId,
                                StopTimesEntity::getStopSequence
                        )
                        .containsExactly("1012-10-0", LocalTime.of(18, 0, 0),
                                LocalTime.of(18, 0, 0), "301790", 1)
                );
    }
}
