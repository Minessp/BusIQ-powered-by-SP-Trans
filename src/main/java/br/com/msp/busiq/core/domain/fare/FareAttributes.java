package br.com.msp.busiq.core.domain.fare;

import java.math.BigDecimal;

/**
 * Representa os atributos de tarifa do GTFS.
 * @param fareId Identificador da tarifa.
 * @param price Valor da tarifa (BigDecimal para precisão).
 * @param currencyType Código da moeda (ex: "BRL").
 * @param paymentMethod Método de pagamento (0 = embarque, 1 = pré-pago).
 * @param transfers Número de transferências permitidas (pode ser nulo).
 * @param transferDuration Duração máxima da transferência em segundos (pode ser nulo).
 */
public record FareAttributes(String fareId,
                             BigDecimal price,
                             String currencyType,
                             int paymentMethod,
                             Integer transfers,
                             Integer transferDuration
) {
}