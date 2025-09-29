package br.com.msp.busiq.infrastructure.persistence.entities;

import br.com.msp.busiq.core.domain.Stops;
import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "stop_times")
public class StopTimesEntity {
    @Id
    @Column(name = "trip_id", nullable = false, length = 64)
    private String tripId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id", referencedColumnName = "trip_id", insertable = false, updatable = false)
    private TripsEntity trip;

    @Column(name = "stop_sequence", nullable = false)
    private int stopSequence;

    @Column(name = "arrival_time", nullable = false)
    private LocalTime arrivalTime;

    @Column(name = "departure_time", nullable = false)
    private LocalTime departureTime;

    @Column(name = "stop_id", nullable = false, length = 64)
    private String stopId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stop_id", referencedColumnName = "stop_id", insertable = false, updatable = false)
    private StopsEntity stops;

    public StopTimesEntity() {}

    public StopTimesEntity(String tripId, LocalTime arrivalTime,
                           LocalTime departureTime, String stopId, StopsEntity stops, int stopSequence) {
        this.tripId = tripId;
        this.stopSequence = stopSequence;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.stopId = stopId;
        this.stops = stops;
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

    public int getStopSequence() {
        return stopSequence;
    }

    public void setStopSequence(int stopSequence) {
        this.stopSequence = stopSequence;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public String getStopId() {
        return stopId;
    }

    public void setStopId(String stopId) {
        this.stopId = stopId;
    }
}
