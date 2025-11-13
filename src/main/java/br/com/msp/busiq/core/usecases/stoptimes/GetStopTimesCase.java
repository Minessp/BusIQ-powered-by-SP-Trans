package br.com.msp.busiq.core.usecases.stoptimes;

import br.com.msp.busiq.core.domain.StopTimes;

import java.util.List;

public interface GetStopTimesCase {
    List<StopTimes> execute();
}
