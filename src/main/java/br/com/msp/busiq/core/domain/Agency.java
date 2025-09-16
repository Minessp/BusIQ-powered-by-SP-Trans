package br.com.msp.busiq.core.domain;

import java.net.URL;

public record Agency(int agencyId,
                     String agencyName,
                     URL agencyUrl,
                     String agencyTimezone,
                     String agencyLang) {
}
