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
}
