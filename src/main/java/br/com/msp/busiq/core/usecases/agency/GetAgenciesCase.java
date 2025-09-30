package br.com.msp.busiq.core.usecases.agency;

import br.com.msp.busiq.core.domain.Agency;

import java.util.List;

public interface GetAgenciesCase {
    List<Agency> execute();
}
