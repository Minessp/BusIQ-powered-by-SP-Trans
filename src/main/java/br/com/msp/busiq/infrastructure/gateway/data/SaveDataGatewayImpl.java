package br.com.msp.busiq.infrastructure.gateway.data;

import br.com.msp.busiq.core.gateway.data.SaveDataGateway;
import br.com.msp.busiq.core.usecases.agency.SaveAgencyDataCase;
import br.com.msp.busiq.core.usecases.calendar.SaveCalendarDataCase;
import br.com.msp.busiq.core.usecases.fare.SaveFareAttributesDataCase;
import br.com.msp.busiq.core.usecases.fare.SaveFareRulesDataCase;
import br.com.msp.busiq.core.usecases.frequencies.SaveFrequenciesDataCase;
import br.com.msp.busiq.core.usecases.routes.SaveRoutesDataCase;
import br.com.msp.busiq.core.usecases.shapes.SaveShapesDataCase;
import br.com.msp.busiq.core.usecases.stops.SaveStopsDataCase;
import br.com.msp.busiq.core.usecases.stoptimes.SaveStopTimesDataCase;
import br.com.msp.busiq.core.usecases.trips.SaveTripsDataCase;

public class SaveDataGatewayImpl implements SaveDataGateway {
    private final SaveAgencyDataCase saveAgencyDataCase;
    private final SaveCalendarDataCase saveCalendarDataCase;
    private final SaveFareAttributesDataCase saveFareAttributesDataCase;
    private final SaveFareRulesDataCase saveFareRulesDataCase;
    private final SaveFrequenciesDataCase saveFrequenciesDataCase;
    private final SaveRoutesDataCase saveRoutesDataCase;
    private final SaveShapesDataCase saveShapesDataCase;
    private final SaveStopsDataCase saveStopsDataCase;
    private final SaveStopTimesDataCase saveStopTimesDataCase;
    private final SaveTripsDataCase saveTripsDataCase;

    public SaveDataGatewayImpl(SaveAgencyDataCase saveAgencyDataCase, SaveCalendarDataCase saveCalendarDataCase,
                               SaveFareAttributesDataCase saveFareAttributesDataCase,
                               SaveFareRulesDataCase saveFareRulesDataCase,
                               SaveFrequenciesDataCase saveFrequenciesDataCase,
                               SaveRoutesDataCase saveRoutesDataCase,
                               SaveShapesDataCase saveShapesDataCase,
                               SaveStopsDataCase saveStopsDataCase,
                               SaveStopTimesDataCase saveStopTimesDataCase,
                               SaveTripsDataCase saveTripsDataCase) {
        this.saveAgencyDataCase = saveAgencyDataCase;
        this.saveCalendarDataCase = saveCalendarDataCase;
        this.saveFareAttributesDataCase = saveFareAttributesDataCase;
        this.saveFareRulesDataCase = saveFareRulesDataCase;
        this.saveFrequenciesDataCase = saveFrequenciesDataCase;
        this.saveRoutesDataCase = saveRoutesDataCase;
        this.saveShapesDataCase = saveShapesDataCase;
        this.saveStopsDataCase = saveStopsDataCase;
        this.saveStopTimesDataCase = saveStopTimesDataCase;
        this.saveTripsDataCase = saveTripsDataCase;
    }

    @Override
    public void saveAllData() {
        saveAgencyDataCase.execute();
        saveCalendarDataCase.execute();
        saveFareAttributesDataCase.execute();
        saveRoutesDataCase.execute();
        saveFareRulesDataCase.execute();
        saveTripsDataCase.execute();
        saveFrequenciesDataCase.execute();
        saveShapesDataCase.execute();
        saveStopsDataCase.execute();
        saveStopTimesDataCase.execute();
    }
}
