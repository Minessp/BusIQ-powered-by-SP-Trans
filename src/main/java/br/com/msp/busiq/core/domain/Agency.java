package br.com.msp.busiq.core.domain;

import java.net.URL;

/**
 * Representa uma agência de transporte público conforme o padrão GTFS.
 * @param agencyId Identificador único da agência.
 * @param agencyName Nome da agência.
 * @param agencyUrl URL do site da agência.
 * @param agencyTimezone Fuso horário da agência.
 * @param agencyLang Idioma principal da agência.
 */
public record Agency(int agencyId,
                     String agencyName,
                     URL agencyUrl,
                     String agencyTimezone,
                     String agencyLang) {
}
