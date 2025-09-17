package br.com.msp.busiq.infrastructure.persistence.entities.fare;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "fare_attributes")
public class FareAttributesEntity{
    @Id
    @Column(name = "fare_id", nullable = false, length = 64)
    private String fareId;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "currency_type", nullable = false, length = 8)
    private String currencyType;

    @Column(name = "payment_method", nullable = false)
    private int paymentMethod;

    @Column(name = "transfers")
    private int transfers;

    @Column(name = "transfer_duration")
    private int transferDuration;

    public FareAttributesEntity() {}

    public FareAttributesEntity(String fareId, BigDecimal price, String currencyType, int paymentMethod,
                                Integer transfers, Integer transferDuration) {
        this.fareId = fareId;
        this.price = price;
        this.currencyType = currencyType;
        this.paymentMethod = paymentMethod;
        this.transfers = transfers;
        this.transferDuration = transferDuration;
    }

    public String getFareId() {
        return fareId;
    }

    public void setFareId(String fareId) {
        this.fareId = fareId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public int getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getTransfers() {
        return transfers;
    }

    public void setTransfers(Integer transfers) {
        this.transfers = transfers;
    }

    public Integer getTransferDuration() {
        return transferDuration;
    }

    public void setTransferDuration(Integer transferDuration) {
        this.transferDuration = transferDuration;
    }
}
