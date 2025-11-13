package br.com.msp.busiq.core.usecases.stoptimes;

import br.com.msp.busiq.core.domain.StopTimes;

public interface GetStopTimeByTripIdAndStopIdCase {
    StopTimes execute(String tripId, String stopId);
}
