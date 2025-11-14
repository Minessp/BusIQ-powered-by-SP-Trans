package br.com.msp.busiq.infrastructure.persistence.entities.fare;

import br.com.msp.busiq.infrastructure.persistence.entities.DatabaseConnectionTest;
import br.com.msp.busiq.infrastructure.persistence.repositories.fare.FareRulesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class FareRulesEntityTest extends DatabaseConnectionTest {

    @Autowired
    private FareRulesRepository fareRulesRepository;

    @Test
    void createFareRulesAndCompareWithDatabaseSuccess() {
        FareRulesEntity fareRules = new FareRulesEntity(
                "CPTM",
                "CPTM L07",
                "",
                "",
                ""
        );
        fareRulesRepository.save(fareRules);

        assertThat(fareRulesRepository.findById("CPTM"))
                .hasValueSatisfying(foundFareRules -> assertThat(foundFareRules)
                        .extracting(
                                FareRulesEntity::getFareId,
                                FareRulesEntity::getRouteId,
                                FareRulesEntity::getOriginId,
                                FareRulesEntity::getDestinationId,
                                FareRulesEntity::getContainsId
                        )
                        .containsExactly("CPTM", "CPTM L07", "", "", "")
                );
    }
}