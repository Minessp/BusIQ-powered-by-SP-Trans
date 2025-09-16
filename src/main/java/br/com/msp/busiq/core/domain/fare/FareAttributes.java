package br.com.msp.busiq.core.domain.fare;

import java.math.BigDecimal;

public record FareAttributes(String fareId,
                             BigDecimal price,
                             String currencyType,
                             int paymentMethod,
                             Integer transfers,
                             Integer transferDuration
) {
}