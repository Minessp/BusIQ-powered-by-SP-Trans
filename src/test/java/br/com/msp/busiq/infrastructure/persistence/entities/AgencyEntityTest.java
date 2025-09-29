package br.com.msp.busiq.infrastructure.persistence.entities;

import br.com.msp.busiq.infrastructure.persistence.repositories.AgencyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AgencyEntityTest extends DatabaseConnectionTest {

    @Autowired
    private AgencyRepository agencyRepository;

    @Test
    void createAgencyAndCompareWithDatabaseSuccess() {
        AgencyEntity agency = new AgencyEntity(
                "1",
                "Agência Teste",
                "https://agencia.com",
                "America/Sao_Paulo",
                "pt"
        );
        agencyRepository.save(agency);

        assertThat(agencyRepository.findById("1"))
                .hasValueSatisfying(foundAgency -> assertThat(foundAgency)
                        .extracting(
                                AgencyEntity::getAgencyId,
                                AgencyEntity::getAgencyName,
                                AgencyEntity::getAgencyUrl,
                                AgencyEntity::getAgencyTimezone,
                                AgencyEntity::getAgencyLang
                        )
                        .containsExactly("1", "Agência Teste", "https://agencia.com", "America/Sao_Paulo", "pt")
                );
    }
}
