package br.com.msp.busiq.core.domain;

public record Routes(String routeId,
                     String agencyId,
                     String routeShortName,
                     String routeLongName,
                     String routeType,
                     String routeColor,
                     String routeTextColor) {

    public Routes {
        if (!routeColor.matches("^#?[0-9A-Fa-f]{6}$") && !routeTextColor.matches("^#?[0-9A-Fa-f]{6}$")) {
            throw new IllegalArgumentException("routeColor e routeTextColor precisam ser valores hexadecimais");
        }
    }
}
