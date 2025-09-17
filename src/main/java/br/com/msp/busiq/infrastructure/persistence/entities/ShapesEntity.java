package br.com.msp.busiq.infrastructure.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "shapes")
public class ShapesEntity {
    @Id
    @Column(name = "shape_id", nullable = false, length = 64)
    private String shapeId;

    @Column(name = "sequence", nullable = false)
    private int sequence;

    @Column(name = "lat", nullable = false)
    private double lat;

    @Column(name = "lon", nullable = false)
    private double lon;

    @Column(name = "dist_traveled")
    private double distTraveled;

    public ShapesEntity() {}

    public ShapesEntity(String shapeId, double lat, double lon, int sequence, double distTraveled) {
        this.shapeId = shapeId;
        this.sequence = sequence;
        this.lat = lat;
        this.lon = lon;
        this.distTraveled = distTraveled;
    }

    public String getShapeId() {
        return shapeId;
    }

    public void setShapeId(String shapeId) {
        this.shapeId = shapeId;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getDistTraveled() {
        return distTraveled;
    }

    public void setDistTraveled(double distTraveled) {
        this.distTraveled = distTraveled;
    }
}
