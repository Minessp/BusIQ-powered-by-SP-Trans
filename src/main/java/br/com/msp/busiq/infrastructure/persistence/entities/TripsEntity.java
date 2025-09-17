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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id", referencedColumnName = "route_id", insertable = false, updatable = false)
    private RoutesEntity route;

    @Column(name = "service_id", nullable = false, length = 64)
    private String serviceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", referencedColumnName = "service_id", insertable = false, updatable = false)
    private CalendarEntity calendar;

    @Column(name = "trip_headsign", length = 255)
    private String tripHeadsign;

    @Column(name = "direction_id", nullable = false)
    private int directionId;

    @Column(name = "shape_id", length = 64)
    private String shapeId;

    public TripsEntity() {}

    public TripsEntity(String routeId, RoutesEntity route, String serviceId, CalendarEntity calendar, String tripId,
                       String tripHeadsign, int directionId, String shapeId) {
        this.tripId = tripId;
        this.routeId = routeId;
        this.route = route;
        this.serviceId = serviceId;
        this.calendar = calendar;
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

    public RoutesEntity getRoute() {
        return route;
    }

    public void setRoute(RoutesEntity route) {
        this.route = route;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public CalendarEntity getCalendar() {
        return calendar;
    }

    public void setCalendar(CalendarEntity calendar) {
        this.calendar = calendar;
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
