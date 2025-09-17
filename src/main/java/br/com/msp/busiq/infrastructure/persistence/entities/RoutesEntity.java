package br.com.msp.busiq.infrastructure.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "routes")
public class RoutesEntity {
    @Id
    @Column(name = "route_id", nullable = false, length = 64)
    private String routeId;

    @Column(name = "agency_id", length = 64)
    private String agencyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agency_id", referencedColumnName = "agency_id", insertable = false, updatable = false)
    private AgencyEntity agency;

    @Column(name = "route_short_name", length = 64)
    private String routeShortName;

    @Column(name = "route_long_name", length = 255)
    private String routeLongName;

    @Column(name = "route_type", length = 32)
    private String routeType;

    @Column(name = "route_color", length = 7)
    private String routeColor;

    @Column(name = "route_text_color", length = 7)
    private String routeTextColor;

    public RoutesEntity() {}

    public RoutesEntity(String routeId, String agencyId, AgencyEntity agency, String routeShortName,
                        String routeLongName, String routeType, String routeColor, String routeTextColor) {
        this.routeId = routeId;
        this.agencyId = agencyId;
        this.agency = agency;
        this.routeShortName = routeShortName;
        this.routeLongName = routeLongName;
        this.routeType = routeType;
        this.routeColor = routeColor;
        this.routeTextColor = routeTextColor;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    public AgencyEntity getAgency() {
        return agency;
    }

    public void setAgency(AgencyEntity agency) {
        this.agency = agency;
    }

    public String getRouteShortName() {
        return routeShortName;
    }

    public void setRouteShortName(String routeShortName) {
        this.routeShortName = routeShortName;
    }

    public String getRouteLongName() {
        return routeLongName;
    }

    public void setRouteLongName(String routeLongName) {
        this.routeLongName = routeLongName;
    }

    public String getRouteType() {
        return routeType;
    }

    public void setRouteType(String routeType) {
        this.routeType = routeType;
    }

    public String getRouteColor() {
        return routeColor;
    }

    public void setRouteColor(String routeColor) {
        this.routeColor = routeColor;
    }

    public String getRouteTextColor() {
        return routeTextColor;
    }

    public void setRouteTextColor(String routeTextColor) {
        this.routeTextColor = routeTextColor;
    }
}
