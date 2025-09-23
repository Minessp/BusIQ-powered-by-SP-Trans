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
public record Agency(String agencyId,
                     String agencyName,
                     URL agencyUrl,
                     String agencyTimezone,
                     String agencyLang) {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String agencyId;
        private String agencyName;
        private URL agencyUrl;
        private String agencyTimezone;
        private String agencyLang;

        public Builder builder() {
            return this;
        }

        public Builder agencyId(String agencyId) {
            this.agencyId = agencyId;
            return this;
        }

        public Builder agencyName(String agencyName) {
            this.agencyName = agencyName;
            return this;
        }

        public Builder agencyUrl(URL agencyUrl) {
            this.agencyUrl = agencyUrl;
            return this;
        }

        public Builder agencyTimezone(String agencyTimezone) {
            this.agencyTimezone = agencyTimezone;
            return this;
        }

        public Builder agencyLang(String agencyLang) {
            this.agencyLang = agencyLang;
            return this;
        }

        public Agency build() {
            return new Agency(agencyId,
                              agencyName,
                              agencyUrl,
                              agencyTimezone,
                              agencyLang);
        }
    }
}
