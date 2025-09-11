package br.com.msp.busiq.infrastructure.controller;

import br.com.msp.busiq.core.usecases.DownloadGtfsCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gtfs")
public class DownloadGtfsController {
    private final DownloadGtfsCase downloadGtfsCase;

    public DownloadGtfsController(DownloadGtfsCase downloadGtfsCase) {
        this.downloadGtfsCase = downloadGtfsCase;
    }

    @PostMapping("/download")
    public ResponseEntity<String> download() {
        downloadGtfsCase.execute();
        return ResponseEntity.ok().build();
    }
}
