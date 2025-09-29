package br.com.msp.busiq.infrastructure.persistence.entities;

import br.com.msp.busiq.infrastructure.persistence.repositories.StopsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class StopsTest extends DatabaseConnectionTest {

    @Autowired
    private StopsRepository stopsRepository;

    @Test
    void createStopAndCompareWithDatabaseSuccess() {
        StopsEntity stop = new StopsEntity(
                "18848",
                "Clínicas",
                "",
                -23.554022,
                -46.671108
        );

        stopsRepository.save(stop);

        assertThat(stopsRepository.findById("18848"))
                .hasValueSatisfying(found -> assertThat(found)
                        .extracting(
                                StopsEntity::getStopId,
                                StopsEntity::getStopName,
                                StopsEntity::getStopDesc,
                                StopsEntity::getStopLat,
                                StopsEntity::getStopLon
                        )
                        .containsExactly("18848", "Clínicas",
                                "", -23.554022, -46.671108)
                );
    }
}
