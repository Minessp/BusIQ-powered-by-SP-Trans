package br.com.msp.busiq.infrastructure.gateway;

import br.com.msp.busiq.core.gateway.GtfsGateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class GtfsGatewayImplTest {
    @Autowired
    private GtfsGateway gtfsGateway;

    @Value("${gtfs.destination-path}")
    private String TEST_GTFS_PATH;

    @BeforeEach
    @AfterEach
    void cleanTestGtfsDir() {
        Path dir = Paths.get(TEST_GTFS_PATH);
        if (Files.exists(dir)) {
            try (Stream<Path> files = Files.walk(dir)) {
                files
                        .sorted(Comparator.reverseOrder())
                        .forEach(p -> {
                            try { Files.delete(p); } catch (IOException e) {
                                throw new RuntimeException("Erro ao deletar o arquivo " + p, e);
                            }
                        });
            } catch (IOException e) {
                throw new RuntimeException("Erro ao tentar entrar no diretório " + TEST_GTFS_PATH, e);
            }
        }
    }

    @Test
    void downloadGtfsSuccess() {
        assertTrue(Files.exists(gtfsGateway.download()));
    }

    @Test
    void extractGtfsSuccess() {
        gtfsGateway.download();
        assertTrue(Files.exists(gtfsGateway.extract()));
    }

    @Test
    void extractGtfsErrorExpected() {
        assertThrows(RuntimeException.class, () -> {
            gtfsGateway.extract();
        });
    }
}