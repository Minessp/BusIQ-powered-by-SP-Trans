package br.com.msp.busiq.data.parser;

import br.com.msp.busiq.core.domain.Agency;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Component
public class TxtParser {
    @Value("${gtfs.destination-path}")
    private Path path;

    public List<Agency> toAgency() {
        List<Agency> agencies = new ArrayList<>();

        try(Stream<Path> files = Files.walk(getExtractedGtfsFile())) {
            Stream<Path> matchedFile = files.filter(p -> p.getFileName().toString().equals("agency.txt"));
            matchedFile.findFirst().ifPresent(p -> {
                try(BufferedReader reader = new BufferedReader(new FileReader(p.toFile()))) {
                    String line;
                    boolean firstLine = true;

                    while ((line = reader.readLine()) != null) {
                        if(firstLine) {
                            firstLine = false;
                            continue;
                        }
                        if (line.isBlank()) continue;

                        String[] colsValues = line.split(",", -1);

                        colsValues = Arrays.stream(colsValues).map(c -> c.replace("\"", ""))
                                .toArray(String[]::new);

                        String agencyId = colsValues[0];
                        String agencyName = colsValues[1];
                        String agencyUrlStr = colsValues[2];
                        String agencyTimezone = colsValues[3];
                        String agencyLang = colsValues[4];
                        URL agencyUrl;

                        try {
                            if (agencyUrlStr != null && !agencyUrlStr.isBlank()) {
                                agencyUrl = new URI(agencyUrlStr).toURL();
                                agencies.add(Agency
                                        .builder()
                                        .agencyId(agencyId)
                                        .agencyName(agencyName)
                                        .agencyUrl(agencyUrl)
                                        .agencyTimezone(agencyTimezone)
                                        .agencyLang(agencyLang)
                                        .build(
                                ));
                            }
                        } catch (URISyntaxException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            return agencies;
        } catch (IOException e) {
            throw new RuntimeException("Arquivo agency.txt não encontrado");
        }
    }

    private Path getExtractedGtfsFile() {
        try (Stream<Path> stream = Files.list(path)) {
            Path file = stream
                    .filter(p -> p.getFileName().toString().endsWith("_extracted"))
                    .findFirst()
                    .orElse(null);

            if(file != null) {
                return file;
            } else {
                throw new RuntimeException("Nenhum arquivo de GTFS extraído encontrado");
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao entrar tentar encontrar o arquivo");
            }
    }
}
