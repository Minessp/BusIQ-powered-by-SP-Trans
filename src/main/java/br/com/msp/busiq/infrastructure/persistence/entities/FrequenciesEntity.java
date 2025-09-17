package br.com.msp.busiq.infrastructure.persistence.entities;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "frequencies")
public class FrequenciesEntity {
    @Id
    @Column(name = "trip_id", nullable = false, length = 64)
    private String tripId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id", referencedColumnName = "trip_id", insertable = false, updatable = false)
    private TripsEntity trip;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "headway_secs", nullable = false)
    private int headwaySecs;

    public FrequenciesEntity() {}

    public FrequenciesEntity(String tripId, TripsEntity trip, LocalTime startTime, LocalTime endTime, int headwaySecs) {
        this.tripId = tripId;
        this.trip = trip;
        this.startTime = startTime;
        this.endTime = endTime;
        this.headwaySecs = headwaySecs;
    }

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public TripsEntity getTrip() {
        return trip;
    }

    public void setTrip(TripsEntity trip) {
        this.trip = trip;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public int getHeadwaySecs() {
        return headwaySecs;
    }

    public void setHeadwaySecs(int headwaySecs) {
        this.headwaySecs = headwaySecs;
    }
}
