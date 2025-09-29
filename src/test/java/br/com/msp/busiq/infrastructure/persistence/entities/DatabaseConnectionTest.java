package br.com.msp.busiq.infrastructure.persistence.entities;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS;

@Testcontainers
@DirtiesContext(classMode = AFTER_CLASS)
public abstract class DatabaseConnectionTest {
    private static final String DATABASE_NAME = "testdb";
    private static final String DATABASE_USER = "testuser";
    private static final String DATABASE_PASSWORD = "testpass";

    @SuppressWarnings("resource")
    @Container
    public static final PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:17")
                    .withDatabaseName(DATABASE_NAME)
                    .withUsername(DATABASE_USER)
                    .withPassword(DATABASE_PASSWORD);

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }
}
