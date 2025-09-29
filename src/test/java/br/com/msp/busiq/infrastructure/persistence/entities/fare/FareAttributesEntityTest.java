package br.com.msp.busiq.infrastructure.persistence.entities.fare;

import br.com.msp.busiq.infrastructure.persistence.entities.DatabaseConnectionTest;
import br.com.msp.busiq.infrastructure.persistence.repositories.fare.FareAttributesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class FareAttributesEntityTest extends DatabaseConnectionTest {

    @Autowired
    private FareAttributesRepository fareAttributesRepository;

    @Test
    void createFareAttributesAndCompareWithDatabaseSuccess() {
        FareAttributesEntity fareAttributes = new FareAttributesEntity(
                "CPTM",
                new BigDecimal("5.200000"),
                "BRL",
                0,
                0,
                10800
        );
        fareAttributesRepository.save(fareAttributes);

        assertThat(fareAttributesRepository.findById("CPTM"))
                .hasValueSatisfying(foundFareAttribute -> assertThat(foundFareAttribute)
                        .extracting(
                                FareAttributesEntity::getFareId,
                                FareAttributesEntity::getPrice,
                                FareAttributesEntity::getCurrencyType,
                                FareAttributesEntity::getPaymentMethod,
                                FareAttributesEntity::getTransfers,
                                FareAttributesEntity::getTransferDuration
                        )
                        .containsExactly("CPTM", new BigDecimal("5.200000"), "BRL", 0, 0, 10800)
                );
    }
}