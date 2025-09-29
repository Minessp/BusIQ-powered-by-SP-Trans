package br.com.msp.busiq.core.domain;

/**
 * Representa uma linha ou rota de transporte público conforme o padrão GTFS.
 * @param routeId Identificador único da rota.
 * @param agencyId Identificador da agência responsável pela rota.
 * @param routeShortName Nome curto da rota.
 * @param routeLongName Nome longo da rota.
 * @param routeType Tipo da rota (ex: ônibus, metrô).
 * @param routeColor Cor da rota em hexadecimal.
 * @param routeTextColor Cor do texto da rota em hexadecimal.
 */
public record Routes(String routeId,
                     String agencyId,
                     String routeShortName,
                     String routeLongName,
                     String routeType,
                     String routeColor,
                     String routeTextColor) {

    /**
     * @throws IllegalArgumentException se routeColor ou routeTextColor não forem hexadecimais válidos.
     */
    public Routes {
        if (!routeColor.matches("^#?[0-9A-Fa-f]{6}$") && !routeTextColor.matches("^#?[0-9A-Fa-f]{6}$")) {
            throw new IllegalArgumentException("routeColor e routeTextColor precisam ser valores hexadecimais");
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String routeId;
        private String agencyId;
        private String routeShortName;
        private String routeLongName;
        private String routeType;
        private String routeColor;
        private String routeTextColor;

        public Builder builder() {
            return this;
        }

        public Builder routeId(String routeId) {
            this.routeId = routeId;
            return this;
        }

        public Builder agencyId(String agencyId) {
            this.agencyId = agencyId;
            return this;
        }

        public Builder routeShortName(String routeShortName) {
            this.routeShortName = routeShortName;
            return this;
        }

        public Builder routeLongName(String routeLongName) {
            this.routeLongName = routeLongName;
            return this;
        }

        public Builder routeType(String routeType) {
            this.routeType = routeType;
            return this;
        }

        public Builder routeColor(String routeColor) {
            this.routeColor = routeColor;
            return this;
        }

        public Builder routeTextColor(String routeTextColor) {
            this.routeTextColor = routeTextColor;
            return this;
        }

        public Routes build() {
            return new Routes(routeId,
                              agencyId,
                              routeShortName,
                              routeLongName,
                              routeType,
                              routeColor,
                              routeTextColor);
        }
    }
}