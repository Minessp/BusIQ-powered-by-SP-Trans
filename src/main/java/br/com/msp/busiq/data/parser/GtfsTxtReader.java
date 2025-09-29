package br.com.msp.busiq.data.parser;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Component
public class GtfsTxtReader {

    private final Path basePath;

    public GtfsTxtReader(@Value("${gtfs.destination-path}") String basePath) {
        this.basePath = Path.of(basePath);
    }

    private static final Pattern COMMA_OUTSIDE_QUOTES =
            Pattern.compile(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

    public <T> List<T> parse(String fileName, Function<String[], T> mapper) {
        List<String[]> rows = readTxt(fileName);
        List<T> result = new ArrayList<>(rows.size());
        for (String[] row : rows) {
            T item = mapper.apply(row);
            if (item != null) {
                result.add(item);
            }
        }
        return result;
    }

    private List<String[]> readTxt(String fileName) {
        Path file = resolveFile(fileName);
        List<String[]> rows = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // skip header
                    continue;
                }
                if (line.isBlank()) continue;
                rows.add(splitString(line));
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler arquivo " + fileName, e);
        }
        return rows;
    }

    private Path resolveFile(String fileName) {
        Path extractedDir = getExtractedGtfsDir();
        try (Stream<Path> files = Files.walk(extractedDir)) {
            return files
                .filter(p -> p.getFileName().toString().equals(fileName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Arquivo " + fileName + " não encontrado em " + extractedDir));
        } catch (IOException e) {
            throw new RuntimeException("Erro ao entrar no diretório " + extractedDir, e);
        }
    }

    private Path getExtractedGtfsDir() {
        try (Stream<Path> stream = Files.list(basePath)) {
            return stream
                .filter(p -> p.getFileName().toString().endsWith("_extracted"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Nenhum arquivo de GTFS extraído encontrado em " + basePath));
        } catch (IOException e) {
            throw new RuntimeException("Erro ao tentar encontrar o diretório de GTFS extraído em " + basePath, e);
        }
    }

    private String[] splitString(String line) {
        String[] cols = COMMA_OUTSIDE_QUOTES.split(line, -1);
        for (int i = 0; i < cols.length; i++) {
            cols[i] = unquote(cols[i]);
        }
        return cols;
    }

    private String unquote(String s) {
        if (s == null) return null;
        s = s.trim();
        if (s.length() >= 2 && s.startsWith("\"") && s.endsWith("\"")) {
            return s.substring(1, s.length() - 1);
        }
        return s;
    }
}
