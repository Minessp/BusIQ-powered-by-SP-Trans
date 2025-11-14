package br.com.msp.busiq.config.data;

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
import br.com.msp.busiq.infrastructure.gateway.data.SaveDataGatewayImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SaveDataGatewayConfig {

    @Bean
    SaveDataGateway saveDataGateway(SaveAgencyDataCase saveAgencyDataCase,
                                    SaveCalendarDataCase saveCalendarDataCase,
                                    SaveFareAttributesDataCase saveFareAttributesDataCase,
                                    SaveFareRulesDataCase saveFareRulesDataCase,
                                    SaveFrequenciesDataCase saveFrequenciesDataCase,
                                    SaveRoutesDataCase saveRoutesDataCase,
                                    SaveShapesDataCase saveShapesDataCase,
                                    SaveStopsDataCase saveStopsDataCase,
                                    SaveStopTimesDataCase saveStopTimesDataCase,
                                    SaveTripsDataCase saveTripsDataCase) {
        return new SaveDataGatewayImpl(saveAgencyDataCase,
                                       saveCalendarDataCase,
                                       saveFareAttributesDataCase,
                                       saveFareRulesDataCase,
                                       saveFrequenciesDataCase,
                                       saveRoutesDataCase,
                                       saveShapesDataCase,
                                       saveStopsDataCase,
                                       saveStopTimesDataCase,
                                       saveTripsDataCase);
    }
}
