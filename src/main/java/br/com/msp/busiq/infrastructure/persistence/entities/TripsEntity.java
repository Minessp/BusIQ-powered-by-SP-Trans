package br.com.msp.busiq.infrastructure.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "trips")
public class TripsEntity {
    @Id
    @Column(name = "trip_id", nullable = false, length = 64)
    private String tripId;

    @Column(name = "route_id", nullable = false, length = 64)
    private String routeId;

    @Column(name = "service_id", nullable = false, length = 64)
    private String serviceId;

    @Column(name = "trip_headsign", length = 255)
    private String tripHeadsign;

    @Column(name = "direction_id", nullable = false)
    private int directionId;

    @Column(name = "shape_id", length = 64)
    private String shapeId;

    public TripsEntity() {}

    public TripsEntity(String routeId, String serviceId, String tripId,
                       String tripHeadsign, int directionId, String shapeId) {
        this.tripId = tripId;
        this.routeId = routeId;
        this.serviceId = serviceId;
        this.tripHeadsign = tripHeadsign;
        this.directionId = directionId;
        this.shapeId = shapeId;
    }

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getTripHeadsign() {
        return tripHeadsign;
    }

    public void setTripHeadsign(String tripHeadsign) {
        this.tripHeadsign = tripHeadsign;
    }

    public int getDirectionId() {
        return directionId;
    }

    public void setDirectionId(int directionId) {
        this.directionId = directionId;
    }

    public String getShapeId() {
        return shapeId;
    }

    public void setShapeId(String shapeId) {
        this.shapeId = shapeId;
    }
}
