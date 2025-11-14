package br.com.msp.busiq.infrastructure.controllers.stops;

import br.com.msp.busiq.core.domain.Stops;
import br.com.msp.busiq.core.usecases.stops.FindResultsStopsByStopNameCase;
import br.com.msp.busiq.core.usecases.stops.GetStopByIdCase;
import br.com.msp.busiq.core.usecases.stops.GetStopsCase;
import br.com.msp.busiq.infrastructure.controllers.StopsController;
import br.com.msp.busiq.infrastructure.dtos.StopsResponse;
import br.com.msp.busiq.infrastructure.exceptions.GlobalExceptionHandler;
import br.com.msp.busiq.infrastructure.mappers.stops.StopsDtoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StopsController.class)
@Import(GlobalExceptionHandler.class)
public class StopsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    private GetStopsCase getStopsCase;

    @MockitoBean
    private GetStopByIdCase getStopByIdCase;

    @MockitoBean
    private FindResultsStopsByStopNameCase findResultsStopsByStopNameCase;

    @MockitoBean
    private StopsDtoMapper stopsDtoMapper;

    Stops stops1 = Stops.builder()
            .stopId("18848")
            .stopName("Clínicas")
            .stopDesc("")
            .stopLat(-23.554022)
            .stopLon(-46.671108)
            .build();

    StopsResponse response1 = StopsResponse.builder()
            .stopId("18848")
            .stopName("Clínicas")
            .stopDesc("")
            .stopLat(-23.554022)
            .stopLon(-46.671108)
            .build();

    @Test
    void shouldReturnAllStopsWithSuccess() throws Exception {
        Stops stops2 = Stops.builder()
                .stopId("18849")
                .stopName("Vila Madalena")
                .stopDesc("")
                .stopLat(-23.546498)
                .stopLon(-46.691141)
                .build();

        when(getStopsCase.execute()).thenReturn(List.of(stops1, stops2));

        StopsResponse response2 = StopsResponse.builder()
                .stopId("18849")
                .stopName("Vila Madalena")
                .stopDesc("")
                .stopLat(-23.546498)
                .stopLon(-46.691141)
                .build();

        when(stopsDtoMapper.toResponse(stops1)).thenReturn(response1);
        when(stopsDtoMapper.toResponse(stops2)).thenReturn(response2);

        mockMvc.perform(get("/stops"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].stopId").value("18848"))
                .andExpect(jsonPath("$[0].stopName").value("Clínicas"))
                .andExpect(jsonPath("$[0].stopDesc").value(""))
                .andExpect(jsonPath("$[0].stopLat").value(-23.554022))
                .andExpect(jsonPath("$[0].stopLon").value(-46.671108))
                .andExpect(jsonPath("$[1].stopId").value("18849"))
                .andExpect(jsonPath("$[1].stopName").value("Vila Madalena"))
                .andExpect(jsonPath("$[1].stopDesc").value(""))
                .andExpect(jsonPath("$[1].stopLat").value(-23.546498))
                .andExpect(jsonPath("$[1].stopLon").value(-46.691141));
    }

    @Test
    void shouldReturnStopByIdWithSuccess() throws Exception {
        when(getStopByIdCase.execute("18848")).thenReturn(stops1);
        when(stopsDtoMapper.toResponse(stops1)).thenReturn(response1);

        mockMvc.perform(get("/stops/18848"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("stopId").value("18848"))
                .andExpect(jsonPath("stopName").value("Clínicas"))
                .andExpect(jsonPath("stopDesc").value(""))
                .andExpect(jsonPath("stopLat").value(-23.554022))
                .andExpect(jsonPath("stopLon").value(-46.671108));
    }

    @Test
    void shouldReturnResultsByNameWithSuccess() throws Exception {
        when(findResultsStopsByStopNameCase.execute("Cl")).thenReturn(List.of(stops1));
        when(stopsDtoMapper.toResponse(stops1)).thenReturn(response1);

        mockMvc.perform(get("/stops/stop-name?q=Cl"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].stopId").value("18848"))
                .andExpect(jsonPath("$[0].stopName").value("Clínicas"))
                .andExpect(jsonPath("$[0].stopDesc").value(""))
                .andExpect(jsonPath("$[0].stopLat").value(-23.554022))
                .andExpect(jsonPath("$[0].stopLon").value(-46.671108));
    }
}
