package br.com.msp.busiq.core.gateway.stoptimes;

import br.com.msp.busiq.core.domain.StopTimes;

import java.util.List;

public interface StopTimesGateway {
    List<StopTimes> getAllStopTimes();

    List<StopTimes> getStopTimeById(String tripId);

    StopTimes getStopTimeByTripIdAndStopId(String tripId, String stopId);

    void saveStopTimesData();
}
