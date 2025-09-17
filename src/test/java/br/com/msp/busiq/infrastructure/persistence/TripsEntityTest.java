package br.com.msp.busiq.infrastructure.persistence;

import br.com.msp.busiq.infrastructure.persistence.entities.TripsEntity;
import br.com.msp.busiq.infrastructure.persistence.repositories.TripsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TripsEntityTest extends DatabaseConnectionTest {

    @Autowired
    private TripsRepository tripsRepository;

    @Test
    void createTripAndCompareWithDatabaseSuccess() {
        TripsEntity trip = new TripsEntity(
                "1012-10",
                null,
                "USD",
                null,
                "1012-10-0",
                "Jd. Monte Belo",
                0,
                "81072"
        );

        tripsRepository.save(trip);

        assertThat(tripsRepository.findById("1012-10-0"))
                .hasValueSatisfying(foundTrip -> assertThat(foundTrip)
                        .extracting(
                                TripsEntity::getRouteId,
                                TripsEntity::getServiceId,
                                TripsEntity::getTripId,
                                TripsEntity::getTripHeadsign,
                                TripsEntity::getDirectionId,
                                TripsEntity::getShapeId
                        )
                        .containsExactly("1012-10", "USD", "1012-10-0", "Jd. Monte Belo", 0, "81072")
                );
    }
}
