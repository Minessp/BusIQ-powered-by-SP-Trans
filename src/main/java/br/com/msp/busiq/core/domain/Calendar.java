package br.com.msp.busiq.core.domain;

import java.time.LocalDate;

/**
 * Representa o calendário de operação de um serviço GTFS.
 * Cada campo booleano indica se o serviço opera naquele dia da semana.
 * As datas indicam o período de validade do serviço.
 * @param serviceId Identificador do serviço.
 * @param monday Opera na segunda-feira.
 * @param tuesday Opera na terça-feira.
 * @param wednesday Opera na quarta-feira.
 * @param thursday Opera na quinta-feira.
 * @param friday Opera na sexta-feira.
 * @param saturday Opera no sábado.
 * @param sunday Opera no domingo.
 * @param startDate Data de início de operação (yyyyMMdd).
 * @param endDate Data de término de operação (yyyyMMdd).
 */
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

