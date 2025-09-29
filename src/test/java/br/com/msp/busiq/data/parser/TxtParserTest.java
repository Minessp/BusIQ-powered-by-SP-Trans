package br.com.msp.busiq.data.parser;

import br.com.msp.busiq.core.domain.*;
import br.com.msp.busiq.core.domain.fare.FareAttributes;
import br.com.msp.busiq.core.domain.fare.FareRules;
import br.com.msp.busiq.core.gateway.GtfsGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class TxtParserTest {

    @Autowired
    private GtfsGateway gtfsGateway;

    @Autowired
    private TxtParser txtParser;

    @BeforeEach
    void setup() {
        gtfsGateway.download();
        gtfsGateway.extract();
    }

    @Test
    void convertTxtToAgencySuccess() {
        List<Agency> agencies = txtParser.toAgency();
        verifyDomainFields(agencies);
    }

    @Test
    void convertTxtToCalendarSuccess() {
        List<Calendar> calendars = txtParser.toCalendar();
        verifyDomainFields(calendars);
    }

    @Test
    void convertTxtToFareAttributesSuccess() {
        List<FareAttributes> fareAttributes = txtParser.toFareAttributes();
        assertFalse(fareAttributes.isEmpty(), "Lista de FareAttributes deve vir preenchida");

        for (FareAttributes obj : fareAttributes) {
            assertNotNull(obj.fareId(), "fareId deve ser preenchido");
            assertNotNull(obj.price(), "price deve ser preenchido");
            assertNotNull(obj.currencyType(), "currencyType deve ser preenchido");
        }
    }

    @Test
    void convertTxtToFareRulesSuccess() {
        List<FareRules> fareRules = txtParser.toFareRules();
        assertFalse(fareRules.isEmpty(), "Lista de FareRules deve vir preenchida");

        for (FareRules obj : fareRules) {
            assertNotNull(obj.fareId(), "fareId deve ser preenchido");
            assertNotNull(obj.routeId(), "routeId deve ser preenchido");
        }
    }

    @Test
    void convertTxtToFrequenciesSuccess() {
        List<Frequencies> frequencies = txtParser.toFrequencies();
        verifyDomainFields(frequencies);
    }

    @Test
    void convertTxtToRoutesSuccess() {
        List<Routes> routes = txtParser.toRoutes();
        verifyDomainFields(routes);
    }

    @Test
    void convertTxtToShapesSuccess() {
        List<Shapes> shapes = txtParser.toShapes();
        verifyDomainFields(shapes);
    }

    @Test
    void convertTxtToStopsSuccess() {
        List<Stops> stops = txtParser.toStops();
        assertFalse(stops.isEmpty(), "Lista de Stops deve vir preenchida");

        for (Stops obj : stops) {
            assertNotNull(obj.stopId(), "stopId deve ser preenchido");
            assertNotNull(obj.stopName(), "stopName deve ser preenchido");
            assertInstanceOf(Double.class, obj.stopLat(), "stopLatitude deve ser preenchido e do tipo Double");
            assertInstanceOf(Double.class, obj.stopLon(), "stopLongitude deve ser preenchido e do tipo Double");
        }
    }

    @Test
    void convertTxtToStopTimesSuccess() {
        List<StopTimes> stopTimes = txtParser.toStopTimes();
        verifyDomainFields(stopTimes);
    }

    @Test
    void convertTxtToTripsSuccess() {
        List<Trips> trips = txtParser.toTrips();
        verifyDomainFields(trips);
    }

    private <T> void verifyDomainFields(List<T> domains) {
        for (T domain : domains) {
            for (String field : domain.toString().split(",")) {
                assertNotNull(field, "Os campos não devem ser nulos");
                assertFalse(field.isBlank(), "Os campos não devem ser vazios" );
            }
        }
    }

}