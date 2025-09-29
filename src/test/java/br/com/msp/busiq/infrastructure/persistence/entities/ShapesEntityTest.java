package br.com.msp.busiq.infrastructure.persistence.entities;

import br.com.msp.busiq.infrastructure.persistence.repositories.ShapesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ShapesEntityTest extends DatabaseConnectionTest {

    @Autowired
    private ShapesRepository shapesRepository;

    @Test
    void createShapeAndCompareWithDatabaseSuccess() {
        ShapesEntity shape = new ShapesEntity(
                "81072",
                -23.432024,
                -46.787121,
                1,
                0.0
        );

        shapesRepository.save(shape);

        assertThat(shapesRepository.findById("81072"))
                .hasValueSatisfying(found -> assertThat(found)
                        .extracting(
                                ShapesEntity::getShapeId,
                                ShapesEntity::getLat,
                                ShapesEntity::getLon,
                                ShapesEntity::getSequence,
                                ShapesEntity::getDistTraveled
                        )
                        .containsExactly("81072",-23.432024, -46.787121, 1, 0.0)
                );
    }
}
