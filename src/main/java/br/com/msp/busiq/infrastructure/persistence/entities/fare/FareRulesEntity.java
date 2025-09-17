package br.com.msp.busiq.infrastructure.persistence.entities.fare;

import jakarta.persistence.*;
import br.com.msp.busiq.infrastructure.persistence.entities.RoutesEntity;

@Entity
@Table(name = "fare_rules")
public class FareRulesEntity {
    @Id
    @Column(name = "fare_id", nullable = false, length = 64)
    private String fareId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fare_id", referencedColumnName = "fare_id", insertable = false, updatable = false)
    private FareAttributesEntity fare;

    @Column(name = "route_id", length = 64)
    private String routeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id", referencedColumnName = "route_id", insertable = false, updatable = false)
    private RoutesEntity route;

    @Column(name = "origin_id", length = 64)
    private String originId;

    @Column(name = "destination_id", length = 64)
    private String destinationId;

    @Column(name = "contains_id", length = 64)
    private String containsId;

    public FareRulesEntity() {}

    public FareRulesEntity(String fareId, FareAttributesEntity fare, String routeId, RoutesEntity route,
                           String originId, String destinationId, String containsId) {
        this.fareId = fareId;
        this.fare = fare;
        this.routeId = routeId;
        this.route = route;
        this.originId = originId;
        this.destinationId = destinationId;
        this.containsId = containsId;
    }

    public String getFareId() {
        return fareId;
    }

    public void setFareId(String fareId) {
        this.fareId = fareId;
    }

    public FareAttributesEntity getFare() {
        return fare;
    }

    public void setFare(FareAttributesEntity fare) {
        this.fare = fare;
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

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }

    public String getContainsId() {
        return containsId;
    }

    public void setContainsId(String containsId) {
        this.containsId = containsId;
    }
}
