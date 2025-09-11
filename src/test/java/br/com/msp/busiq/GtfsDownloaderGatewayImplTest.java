package br.com.msp.busiq;

import br.com.msp.busiq.infrastructure.gateway.GtfsDownloaderGatewayImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GtfsDownloaderGatewayImplTest {

    @Autowired
    private GtfsDownloaderGatewayImpl downloader;

    @Test
    void deveBaixarArquivoGtfsComSucesso() {
        Path arquivo = downloader.execute();
        assertTrue(Files.exists(arquivo));
    }
}

