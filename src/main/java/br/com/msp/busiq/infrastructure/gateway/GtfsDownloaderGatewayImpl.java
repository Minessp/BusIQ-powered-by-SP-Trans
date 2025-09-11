package br.com.msp.busiq.infrastructure.gateway;

import br.com.msp.busiq.core.gateway.GtfsDownloaderGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class GtfsDownloaderGatewayImpl implements GtfsDownloaderGateway {
    @Value("${gtfs.url}")
    private String GTFS_URL;

    @Value("${gtfs.destination-path}")
    private String DESTINATION_PATH;

    @Override
    public Path execute() {
        try {
            Files.createDirectories(Paths.get(DESTINATION_PATH));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
            LocalDateTime dateTimeNow = LocalDateTime.now();
            String formattedDateTime = dateTimeNow.format(formatter);

            Path filePath =  Paths.get(DESTINATION_PATH, "gtfs_" + formattedDateTime + ".zip");

            try (InputStream in = new URI(GTFS_URL).toURL().openStream()) {
                Files.copy(in, filePath, StandardCopyOption.REPLACE_EXISTING);
            }
            return filePath;
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Erro ao baixar o arquivo GTFS", e);
        }
    }
}
