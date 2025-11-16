package br.com.msp.busiq.infrastructure.controllers.trips;

import br.com.msp.busiq.core.domain.Trips;
import br.com.msp.busiq.core.usecases.trips.GetTripByTripIdCase;
import br.com.msp.busiq.core.usecases.trips.GetTripsByRouteIdCase;
import br.com.msp.busiq.core.usecases.trips.GetTripsByTripHeadsignCase;
import br.com.msp.busiq.core.usecases.trips.GetTripsCase;
import br.com.msp.busiq.infrastructure.controllers.TripsController;
import br.com.msp.busiq.infrastructure.dtos.TripsResponse;
import br.com.msp.busiq.infrastructure.exceptions.GlobalExceptionHandler;
import br.com.msp.busiq.infrastructure.mappers.trips.TripsDtoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TripsController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(GlobalExceptionHandler.class)
public class TripsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    private GetTripsCase getTripsCase;

    @MockitoBean
    private GetTripsByRouteIdCase getTripsByRouteIdCase;

    @MockitoBean
    private GetTripByTripIdCase getTripByTripIdCase;

    @MockitoBean
    private GetTripsByTripHeadsignCase getTripsByTripHeadsignCase;

    @MockitoBean
    private TripsDtoMapper tripsDtoMapper;

    Trips trips1 = Trips.builder()
            .routeId("1")
            .serviceId("USD")
            .tripId("1")
            .tripHeadsign("Rua da Casa")
            .directionId(0)
            .shapeId("1")
            .build();

    Trips trips2 = Trips.builder()
            .routeId("2")
            .serviceId("USD")
            .tripId("2")
            .tripHeadsign("Rua do Posto")
            .directionId(0)
            .shapeId("2")
            .build();

    Trips trips3 = Trips.builder()
            .routeId("3")
            .serviceId("USD")
            .tripId("3")
            .tripHeadsign("Rua da Praia")
            .directionId(0)
            .shapeId("3")
            .build();

    TripsResponse response1 = TripsResponse.builder()
            .routeId("1")
            .serviceId("USD")
            .tripId("1")
            .tripHeadsign("Rua da Casa")
            .directionId(0)
            .shapeId("1")
            .build();

    TripsResponse response2 = TripsResponse.builder()
            .routeId("2")
            .serviceId("USD")
            .tripId("2")
            .tripHeadsign("Rua do Posto")
            .directionId(0)
            .shapeId("2")
            .build();

    TripsResponse response3 = TripsResponse.builder()
            .routeId("3")
            .serviceId("USD")
            .tripId("3")
            .tripHeadsign("Rua da Praia")
            .directionId(0)
            .shapeId("3")
            .build();

    @Test
    void shouldReturnAllTripsWithSuccess() throws Exception {
        when(getTripsCase.execute()).thenReturn(List.of(trips1, trips2, trips3));

        when(tripsDtoMapper.toResponse(trips1)).thenReturn(response1);
        when(tripsDtoMapper.toResponse(trips2)).thenReturn(response2);
        when(tripsDtoMapper.toResponse(trips3)).thenReturn(response3);

        mockMvc.perform(get("/trips"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].routeId").value("1"))
                .andExpect(jsonPath("$[0].serviceId").value("USD"))
                .andExpect(jsonPath("$[0].tripId").value("1"))
                .andExpect(jsonPath("$[0].tripHeadsign").value("Rua da Casa"))
                .andExpect(jsonPath("$[0].directionId").value(0))
                .andExpect(jsonPath("$[0].shapeId").value("1"))
                .andExpect(jsonPath("$[1].routeId").value("2"))
                .andExpect(jsonPath("$[1].serviceId").value("USD"))
                .andExpect(jsonPath("$[1].tripId").value("2"))
                .andExpect(jsonPath("$[1].tripHeadsign").value("Rua do Posto"))
                .andExpect(jsonPath("$[1].directionId").value(0))
                .andExpect(jsonPath("$[1].shapeId").value("2"))
                .andExpect(jsonPath("$[2].routeId").value("3"))
                .andExpect(jsonPath("$[2].serviceId").value("USD"))
                .andExpect(jsonPath("$[2].tripId").value("3"))
                .andExpect(jsonPath("$[2].tripHeadsign").value("Rua da Praia"))
                .andExpect(jsonPath("$[2].directionId").value(0))
                .andExpect(jsonPath("$[2].shapeId").value("3"));
    }

    @Test
    void shouldReturnTripsByRouteIdWithSuccess() throws Exception {
        when(getTripsByRouteIdCase.execute("1")).thenReturn(List.of(trips1));
        when(tripsDtoMapper.toResponse(trips1)).thenReturn(response1);

        mockMvc.perform(get("/trips/route-id/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].routeId").value("1"))
                .andExpect(jsonPath("$[0].serviceId").value("USD"))
                .andExpect(jsonPath("$[0].tripId").value("1"))
                .andExpect(jsonPath("$[0].tripHeadsign").value("Rua da Casa"))
                .andExpect(jsonPath("$[0].directionId").value(0))
                .andExpect(jsonPath("$[0].shapeId").value("1"));
    }

    @Test
    void shouldReturnTripsByTripHeadsignWithSuccess() throws Exception {
        when(getTripsByTripHeadsignCase.execute("Rua")).thenReturn(List.of(trips1, trips2, trips3));

        when(tripsDtoMapper.toResponse(trips1)).thenReturn(response1);
        when(tripsDtoMapper.toResponse(trips2)).thenReturn(response2);
        when(tripsDtoMapper.toResponse(trips3)).thenReturn(response3);

        mockMvc.perform(get("/trips/trip-headsign?q=Rua"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].tripId").value("1"))
                .andExpect(jsonPath("$[1].tripId").value("2"))
                .andExpect(jsonPath("$[2].tripId").value("3"));
    }
}
