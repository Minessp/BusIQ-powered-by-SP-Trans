package br.com.msp.busiq.infrastructure.persistence;

import br.com.msp.busiq.infrastructure.persistence.entities.RoutesEntity;
import br.com.msp.busiq.infrastructure.persistence.repositories.RoutesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class RoutesEntityTest extends DatabaseConnectionTest {

    @Autowired
    private RoutesRepository routesRepository;

    @Test
    void createRouteAndCompareWithDatabaseSuccess() {
        RoutesEntity route = new RoutesEntity(
                "1012-10-0",
                "1",
                null,
                "1012-10",
                "Term. Jd. Britania - Jd. Monte Belo",
                "3",
                "509E2F",
                "FFFFFF"
        );

        routesRepository.save(route);

        assertThat(routesRepository.findById("1012-10-0"))
                .hasValueSatisfying(foundRoute -> assertThat(foundRoute)
                        .extracting(
                                RoutesEntity::getRouteId,
                                RoutesEntity::getAgencyId,
                                RoutesEntity::getRouteShortName,
                                RoutesEntity::getRouteLongName,
                                RoutesEntity::getRouteType,
                                RoutesEntity::getRouteColor,
                                RoutesEntity::getRouteTextColor
                        )
                        .containsExactly("1012-10-0", "1", "1012-10", "Term. Jd. Britania - Jd. Monte Belo",
                                "3", "509E2F", "FFFFFF")
                );
    }
}
