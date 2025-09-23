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
public record Calendar(String serviceId,
                       boolean monday,
                       boolean tuesday,
                       boolean wednesday,
                       boolean thursday,
                       boolean friday,
                       boolean saturday,
                       boolean sunday,
                       LocalDate startDate,
                       LocalDate endDate) {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String serviceId;
        private boolean monday;
        private boolean tuesday;
        private boolean wednesday;
        private boolean thursday;
        private boolean friday;
        private boolean saturday;
        private boolean sunday;
        private LocalDate startDate;
        private LocalDate endDate;

        public Builder builder() {
            return this;
        }

        public Builder serviceId(String serviceId) {
            this.serviceId = serviceId;
            return this;
        }

        public Builder monday(boolean monday) {
            this.monday = monday;
            return this;
        }

        public Builder tuesday(boolean tuesday) {
            this.tuesday = tuesday;
            return this;
        }

        public Builder wednesday(boolean wednesday) {
            this.wednesday = wednesday;
            return this;
        }

        public Builder thursday(boolean thursday) {
            this.thursday = thursday;
            return this;
        }

        public Builder friday(boolean friday) {
            this.friday = friday;
            return this;
        }

        public Builder saturday(boolean saturday) {
            this.saturday = saturday;
            return this;
        }

        public Builder sunday(boolean sunday) {
            this.sunday = sunday;
            return this;
        }

        public Builder startDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public Calendar build() {
            return new Calendar(serviceId,
                                monday,
                                tuesday,
                                wednesday,
                                thursday,
                                friday,
                                saturday,
                                sunday,
                                startDate,
                                endDate);
        }
    }
}

