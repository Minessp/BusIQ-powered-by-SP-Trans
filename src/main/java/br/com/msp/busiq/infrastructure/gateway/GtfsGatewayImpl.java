package br.com.msp.busiq.infrastructure.gateway;

import br.com.msp.busiq.core.gateway.GtfsGateway;
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
import java.util.Comparator;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Component
public class GtfsGatewayImpl implements GtfsGateway {
    @Value("${gtfs.url}")
    private String GTFS_URL;

    @Value("${gtfs.destination-path}")
    private String DESTINATION_PATH;

    @Override
    public Path download() {
        try {
            Path dir = Paths.get(DESTINATION_PATH);
            Files.createDirectories(dir);

            try (Stream<Path> files = Files.list(dir)) {
                files.forEach(path -> {
                    String name = path.getFileName().toString();
                    try {
                        if (name.endsWith(".zip") || (Files.isDirectory(path) && name.endsWith("_extracted"))) {
                            if (Files.isDirectory(path)) {
                                try (Stream<Path> walk = Files.walk(path)) {
                                    walk.sorted(Comparator.reverseOrder()).forEach(p -> {
                                        try { Files.deleteIfExists(p); } catch (IOException ignored) {}
                                    });
                                }
                            } else {
                                Files.deleteIfExists(path);
                            }
                        }
                    } catch (IOException e) {
                        throw new RuntimeException("Erro ao deletar o arquivo " + path, e);
                    }
                });
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
            LocalDateTime dateTimeNow = LocalDateTime.now();
            String formattedDateTime = dateTimeNow.format(formatter);

            Path filePath = Paths.get(DESTINATION_PATH, "gtfs_" + formattedDateTime + ".zip");

            try (InputStream in = new URI(GTFS_URL).toURL().openStream()) {
                Files.copy(in, filePath, StandardCopyOption.REPLACE_EXISTING);
            }
            return filePath;
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Erro ao baixar o arquivo GTFS", e);
        }
    }

    @Override
    public Path extract() {
        try {
            Path dir = Paths.get(DESTINATION_PATH);
            if (!Files.exists(dir)) {
                throw new RuntimeException("Diretório de destino não existe: " + DESTINATION_PATH);
            }

            Path latestZip;
            try (Stream<Path> files = Files.list(dir)) {
                latestZip = files
                    .filter(p -> p.getFileName().toString().endsWith(".zip"))
                    .max(Comparator.comparingLong(p -> p.toFile().lastModified()))
                    .orElseThrow(() -> new RuntimeException(
                            "Nenhum arquivo GTFS .zip encontrado em " + DESTINATION_PATH));
            }

            String zipName = latestZip.getFileName().toString().replace(".zip", "");
            Path extractDir = dir.resolve(zipName + "_extracted");
            Files.createDirectories(extractDir);

            try (ZipInputStream zis = new ZipInputStream(Files.newInputStream(latestZip))) {
                ZipEntry entry;
                while ((entry = zis.getNextEntry()) != null) {
                    Path newFilePath = extractDir.resolve(entry.getName());
                    if (entry.isDirectory()) {
                        Files.createDirectories(newFilePath);
                    } else {
                        Files.createDirectories(newFilePath.getParent());
                        Files.copy(zis, newFilePath, StandardCopyOption.REPLACE_EXISTING);
                    }
                    zis.closeEntry();
                }
            }
            return extractDir;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao extrair o arquivo GTFS", e);
        }
    }
}
