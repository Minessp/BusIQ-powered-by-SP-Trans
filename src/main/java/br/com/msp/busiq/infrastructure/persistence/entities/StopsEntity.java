package br.com.msp.busiq.infrastructure.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "stops")
public class StopsEntity {

    @Id
    @Column(name = "stop_id", nullable = false, length = 64)
    private String stopId;

    @Column(name = "stop_name", nullable = false)
    private String stopName;

    @Column(name = "stop_desc")
    private String stopDesc;

    @Column(name = "stop_lat", nullable = false)
    private double stopLat;

    @Column(name = "stop_lon", nullable = false)
    private double stopLon;

    public StopsEntity() {
    }

    public StopsEntity(String stopId, String stopName, String stopDesc, double stopLat, double stopLon) {
        this.stopId = stopId;
        this.stopName = stopName;
        this.stopDesc = stopDesc;
        this.stopLat = stopLat;
        this.stopLon = stopLon;
    }

    public String getStopId() {
        return stopId;
    }

    public void setStopId(String stopId) {
        this.stopId = stopId;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public String getStopDesc() {
        return stopDesc;
    }

    public void setStopDesc(String stopDesc) {
        this.stopDesc = stopDesc;
    }

    public double getStopLat() {
        return stopLat;
    }

    public void setStopLat(double stopLat) {
        this.stopLat = stopLat;
    }

    public double getStopLon() {
        return stopLon;
    }

    public void setStopLon(double stopLon) {
        this.stopLon = stopLon;
    }
}