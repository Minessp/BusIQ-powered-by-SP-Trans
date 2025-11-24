package br.com.msp.busiq.data.parser;

import br.com.msp.busiq.core.domain.*;
import br.com.msp.busiq.core.domain.fare.FareAttributes;
import br.com.msp.busiq.core.domain.fare.FareRules;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class TxtParser {

    private final GtfsTxtReader reader;

    public TxtParser(GtfsTxtReader reader) {
        this.reader = reader;
    }

    public List<Agency> toAgency() {
        return reader.parse("agency.txt", cols -> {
            String agencyId = cols[0];
            String agencyName = cols[1];
            String agencyUrlStr = cols[2];
            String agencyTimezone = cols[3];
            String agencyLang = cols[4];

            if (agencyUrlStr == null || agencyUrlStr.isBlank()) {
                return null;
            }

            try {
                URL agencyUrl = new URI(agencyUrlStr).toURL();
                return Agency.builder()
                        .agencyId(agencyId)
                        .agencyName(agencyName)
                        .agencyUrl(agencyUrl)
                        .agencyTimezone(agencyTimezone)
                        .agencyLang(agencyLang)
                        .build();
            } catch (URISyntaxException | MalformedURLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public List<Calendar> toCalendar() {
        return reader.parse("calendar.txt", cols -> {
            String serviceId = cols[0];
            boolean monday = Objects.equals(cols[1], "1");
            boolean tuesday = Objects.equals(cols[2], "1");
            boolean wednesday = Objects.equals(cols[3], "1");
            boolean thursday = Objects.equals(cols[4], "1");
            boolean friday = Objects.equals(cols[5], "1");
            boolean saturday = Objects.equals(cols[6], "1");
            boolean sunday = Objects.equals(cols[7], "1");
            String startDateStr = cols[8];
            String endDateStr = cols[9];

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

            return Calendar.builder()
                    .serviceId(serviceId)
                    .monday(monday)
                    .tuesday(tuesday)
                    .wednesday(wednesday)
                    .thursday(thursday)
                    .friday(friday)
                    .saturday(saturday)
                    .sunday(sunday)
                    .startDate(LocalDate.parse(startDateStr, formatter))
                    .endDate(LocalDate.parse(endDateStr, formatter))
                    .build();
        });
    }

    public List<FareAttributes> toFareAttributes() {
        return reader.parse("fare_attributes.txt", cols -> {
            String fareId = cols[0];
            String priceStr = cols[1];
            String currencyType = cols[2];
            String paymentMethodStr = cols[3];
            String transfersStr = cols[4];
            String transferDurationStr = cols[5];

            return FareAttributes.builder()
                    .fareId(fareId)
                    .price(new BigDecimal(priceStr))
                    .currencyType(currencyType)
                    .paymentMethod(Integer.parseInt(paymentMethodStr))
                    .transfers(transfersStr != null && !transfersStr.isBlank() ? Integer.parseInt(transfersStr) : null)
                    .transferDuration(transferDurationStr != null && !transferDurationStr.isBlank() ?
                            Integer.parseInt(transferDurationStr) : null)
                    .build();
        });
    }

    public List<FareRules> toFareRules() {
        return reader.parse("fare_rules.txt", cols -> {
            String fareId = cols[0];
            String routeId = cols[1];
            String originId = cols[2];
            String destinationId = cols[3];
            String containsId = cols[4];

            return FareRules.builder()
                    .fareId(fareId)
                    .routeId(routeId)
                    .originId(originId != null && !originId.isBlank() ? originId : null)
                    .destinationId(destinationId != null && !destinationId.isBlank() ? destinationId : null)
                    .containsId(containsId != null && !containsId.isBlank() ? containsId : null)
                    .build();
        });
    }

    public List<Frequencies> toFrequencies() {
        return reader.parse("frequencies.txt", cols -> {
            String tripId = cols[0];
            String startTime = cols[1];
            String endTime = cols[2];
            String headwaySecsStr = cols[3];

            return Frequencies.builder()
                    .tripId(tripId)
                    .startTime(LocalTime.parse(startTime))
                    .endTime(LocalTime.parse(endTime))
                    .headwaySecs(Integer.parseInt(headwaySecsStr))
                    .build();
        });
    }

    public List<Routes> toRoutes() {
        return reader.parse("routes.txt", cols -> {
            String routeId = cols[0];
            String agencyId = cols[1];
            String routeShortName = cols[2];
            String routeLongName = cols[3];
            String routeTypeStr = cols[4];
            String routeColor = cols[5];
            String routeTextColor = cols[6];

            return Routes.builder()
                    .routeId(routeId)
                    .agencyId(agencyId)
                    .routeShortName(routeShortName)
                    .routeLongName(routeLongName)
                    .routeType(routeTypeStr)
                    .routeColor(routeColor)
                    .routeTextColor(routeTextColor)
                    .build();
        });
    }

    public List<Shapes> toShapes() {
        return reader.parse("shapes.txt", cols -> {
            String shapeId = cols[0];
            String latStr = cols[1];
            String lonStr = cols[2];
            String sequenceStr = cols[3];
            String distTraveledStr = cols[3];

            return Shapes.builder()
                    .shapeId(shapeId)
                    .lat(Double.parseDouble(latStr))
                    .lon(Double.parseDouble(lonStr))
                    .sequence(Integer.parseInt(sequenceStr))
                    .distTraveled(Double.parseDouble(distTraveledStr))
                    .build();
        });
    }

    public List<Stops> toStops() {
        return reader.parse("stops.txt", cols -> {
            String stopId = cols[0];
            String stopName = cols[1];
            String stopDesc = cols[2];
            String latStr = cols[3];
            String lonStr = cols[4];

            return Stops.builder()
                    .stopId(stopId)
                    .stopName(stopName)
                    .stopDesc(stopDesc)
                    .stopLat(Double.parseDouble(latStr))
                    .stopLon(Double.parseDouble(lonStr))
                    .build();
        });
    }

    public List<StopTimes> toStopTimes() {
        return reader.parse("stop_times.txt", cols -> {
            String tripId = cols[0];
            String arrivalTimeStr = Integer.parseInt(cols[1].substring(0, 2)) > 24 ? cols[1] : "00:00:00";
            String departureTimeStr = Integer.parseInt(cols[2].substring(0, 2)) > 24 ? cols[2] : "00:00:00";
            String stopId = cols[3];
            String stopSequenceStr = cols[4];

            return StopTimes.builder()
                    .tripId(tripId)
                    .arrivalTime(LocalTime.parse(arrivalTimeStr))
                    .departureTime(LocalTime.parse(departureTimeStr))
                    .stopId(stopId)
                    .stopSequence(Integer.parseInt(stopSequenceStr))
                    .build();
        });
    }

    public List<Trips> toTrips() {
        return reader.parse("trips.txt", cols -> {
            String routeId = cols[0];
            String serviceId = cols[1];
            String tripId = cols[2];
            String tripHeadsign = cols[3];
            String directionIdStr = cols[4];
            String shapeId = cols[5];

            return Trips.builder()
                    .routeId(routeId)
                    .serviceId(serviceId)
                    .tripId(tripId)
                    .tripHeadsign(tripHeadsign)
                    .directionId(Integer.parseInt(directionIdStr))
                    .shapeId(shapeId)
                    .build();
        });
    }
}
