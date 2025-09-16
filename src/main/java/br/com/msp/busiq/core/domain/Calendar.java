package br.com.msp.busiq.core.domain;

import java.time.LocalDate;

// LocalDate precisa ser convertido usando DateTimeFormatter.BASIC_ISO_DATE (yyyyMMdd)
public record Calendar(String serviceId, boolean monday,
                       boolean tuesday,
                       boolean wednesday,
                       boolean thursday,
                       boolean friday,
                       boolean saturday,
                       boolean sunday,
                       LocalDate startDate,
                       LocalDate endDate) {
}

