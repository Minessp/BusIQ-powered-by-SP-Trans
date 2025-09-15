package br.com.msp.busiq.core.gateway;

import java.nio.file.Path;

public interface GtfsGateway {
    Path download();

    Path extract();
}
