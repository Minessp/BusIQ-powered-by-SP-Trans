package br.com.msp.busiq.infrastructure.dtos;

public record RoutesResponse(String routeId,
                              String agencyId,
                              String routeShortName,
                              String routeLongName,
                              String routeType,
                              String routeColor,
                              String routeTextColor) {

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

        public RoutesResponse build() {
            return new RoutesResponse(routeId,
                                      agencyId,
                                      routeShortName,
                                      routeLongName,
                                      routeType,
                                      routeColor,
                                      routeTextColor);
        }
    }
}
