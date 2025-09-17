package br.com.msp.busiq.infrastructure.persistence;

import br.com.msp.busiq.infrastructure.persistence.entities.FrequenciesEntity;
import br.com.msp.busiq.infrastructure.persistence.repositories.FrequenciesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class FrequenciesEntityTest extends DatabaseConnectionTest {

    @Autowired
    private FrequenciesRepository frequenciesRepository;

    @Test
    void createFrequenciesAndCompareWithDatabaseSuccess() {
        FrequenciesEntity frequencies = new FrequenciesEntity(
                "1012-10-0",
                null,
                LocalTime.of(6, 0, 20),
                LocalTime.of(7, 0, 0),
                1200
        );

        frequenciesRepository.save(frequencies);

        assertThat(frequenciesRepository.findById("1012-10-0"))
                .hasValueSatisfying(found -> assertThat(found)
                        .extracting(
                                FrequenciesEntity::getTripId,
                                FrequenciesEntity::getStartTime,
                                FrequenciesEntity::getEndTime,
                                FrequenciesEntity::getHeadwaySecs
                        )
                        .containsExactly("1012-10-0", LocalTime.of(6, 0, 20),
                                LocalTime.of(7, 0, 0), 1200)
                );
    }
}
