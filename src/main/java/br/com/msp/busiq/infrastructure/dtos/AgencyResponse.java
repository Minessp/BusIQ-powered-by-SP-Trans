package br.com.msp.busiq.infrastructure.dtos;

import java.net.URL;

public record AgencyResponse(String agencyId,
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

        public AgencyResponse build() {
            return new AgencyResponse(agencyId,
                                      agencyName,
                                      agencyUrl,
                                      agencyTimezone,
                                      agencyLang);
        }
    }
}
