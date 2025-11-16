package br.com.msp.busiq.infrastructure.controllers.stoptimes;

import br.com.msp.busiq.core.domain.StopTimes;
import br.com.msp.busiq.core.usecases.stoptimes.GetStopTimeByTripIdAndStopIdCase;
import br.com.msp.busiq.core.usecases.stoptimes.GetStopTimeByTripIdCase;
import br.com.msp.busiq.core.usecases.stoptimes.GetStopTimesCase;
import br.com.msp.busiq.infrastructure.controllers.StopTimesController;
import br.com.msp.busiq.infrastructure.dtos.StopTimesResponse;
import br.com.msp.busiq.infrastructure.exceptions.GlobalExceptionHandler;
import br.com.msp.busiq.infrastructure.mappers.stoptimes.StopTimesDtoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StopTimesController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(GlobalExceptionHandler.class)
public class StopTimesControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    private GetStopTimesCase getStopTimesCase;

    @MockitoBean
    private GetStopTimeByTripIdCase getStopTimeByTripIdCase;

    @MockitoBean
    private GetStopTimeByTripIdAndStopIdCase getStopTimeByTripIdAndStopIdCase;

    @MockitoBean
    private StopTimesDtoMapper stopTimesDtoMapper;

    StopTimes stopTimes1 = StopTimes.builder()
            .tripId("1")
            .arrivalTime(LocalTime.parse("20:15:10"))
            .departureTime(LocalTime.parse("20:15:10"))
            .stopId("100")
            .stopSequence(1)
            .build();

    StopTimesResponse response1 = StopTimesResponse.builder()
            .tripId("1")
            .arrivalTime(LocalTime.parse("20:15:10"))
            .departureTime(LocalTime.parse("20:15:10"))
            .stopId("100")
            .stopSequence(1)
            .build();

    @Test
    void shouldReturnAllStopTimesWithSuccess() throws Exception {
        StopTimes stopTimes2 = StopTimes.builder()
                .tripId("2")
                .arrivalTime(LocalTime.parse("20:35:20"))
                .departureTime(LocalTime.parse("20:35:20"))
                .stopId("100")
                .stopSequence(23)
                .build();

        when(getStopTimesCase.execute()).thenReturn(List.of(stopTimes1, stopTimes2));

        StopTimesResponse response2 = StopTimesResponse.builder()
                .tripId("2")
                .arrivalTime(LocalTime.parse("20:35:20"))
                .departureTime(LocalTime.parse("20:35:20"))
                .stopId("100")
                .stopSequence(23)
                .build();

        when(stopTimesDtoMapper.toResponse(stopTimes1)).thenReturn(response1);
        when(stopTimesDtoMapper.toResponse(stopTimes2)).thenReturn(response2);

        mockMvc.perform(get("/stop-times"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].tripId").value("1"))
                .andExpect(jsonPath("$[0].stopId").value("100"))
                .andExpect(jsonPath("$[0].stopSequence").value(1))
                .andExpect(jsonPath("$[1].tripId").value("2"))
                .andExpect(jsonPath("$[1].stopId").value("100"))
                .andExpect(jsonPath("$[1].stopSequence").value(23));
    }

    @Test
    void shouldReturnAllStopTimesByTripIdWithSuccess() throws Exception {
        when(getStopTimeByTripIdCase.execute("1")).thenReturn(List.of(stopTimes1));
        when(stopTimesDtoMapper.toResponse(stopTimes1)).thenReturn(response1);

        mockMvc.perform(get("/stop-times/trip-id/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].tripId").value("1"))
                .andExpect(jsonPath("$[0].stopId").value("100"))
                .andExpect(jsonPath("$[0].stopSequence").value(1));
    }

    @Test
    void shouldReturnExactlyOneStopTimeByTripIdAndStopIdWithSuccess() throws Exception {
        when(getStopTimeByTripIdAndStopIdCase.execute("1", "100")).thenReturn(stopTimes1);
        when(stopTimesDtoMapper.toResponse(stopTimes1)).thenReturn(response1);

        mockMvc.perform(get("/stop-times/trip-id/1/100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("tripId").value("1"))
                .andExpect(jsonPath("stopId").value("100"))
                .andExpect(jsonPath("stopSequence").value(1));
    }
}
