package br.com.msp.busiq.infrastructure.controllers.frequencies;

import br.com.msp.busiq.core.domain.Frequencies;
import br.com.msp.busiq.core.usecases.frequencies.GetFrequenciesCase;
import br.com.msp.busiq.core.usecases.frequencies.GetFrequencyByIdCase;
import br.com.msp.busiq.infrastructure.controllers.FrequenciesController;
import br.com.msp.busiq.infrastructure.dtos.FrequenciesResponse;
import br.com.msp.busiq.infrastructure.exceptions.GlobalExceptionHandler;
import br.com.msp.busiq.infrastructure.mappers.frequencies.FrequenciesDtoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

@WebMvcTest(FrequenciesController.class)
@Import(GlobalExceptionHandler.class)
public class FrequenciesControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GetFrequenciesCase getFrequenciesCase;

    @MockitoBean
    private GetFrequencyByIdCase getFrequencyByIdCase;

    @MockitoBean
    private FrequenciesDtoMapper frequenciesDtoMapper;

    Frequencies frequencies1 = Frequencies.builder()
            .tripId("1")
            .startTime(LocalTime.of(8, 15, 30))
            .endTime(LocalTime.of(8, 20, 0))
            .headwaySecs(600)
            .build();

    FrequenciesResponse response1 = FrequenciesResponse.builder()
            .tripId("1")
            .startTime(LocalTime.of(8, 15, 30))
            .endTime(LocalTime.of(8, 20, 0))
            .headwaySecs(600)
            .build();

    @Test
    void shouldReturnAllFrequenciesWithSuccess() throws Exception {
        Frequencies frequencies2 = Frequencies.builder()
                .tripId("2")
                .startTime(LocalTime.of(8, 20, 30))
                .endTime(LocalTime.of(8, 25, 0))
                .headwaySecs(600)
                .build();

        when(getFrequenciesCase.execute()).thenReturn((List.of(frequencies1, frequencies2)));

        FrequenciesResponse response2 = FrequenciesResponse.builder()
                .tripId("2")
                .startTime(LocalTime.of(8, 20, 30))
                .endTime(LocalTime.of(8, 25, 0))
                .headwaySecs(600)
                .build();

        when(frequenciesDtoMapper.toResponse(frequencies1)).thenReturn(response1);
        when(frequenciesDtoMapper.toResponse(frequencies2)).thenReturn(response2);

        mockMvc.perform(get("/frequencies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].tripId").value("1"))
                .andExpect(jsonPath("$[0].startTime").value("08:15:30"))
                .andExpect(jsonPath("$[0].endTime").value("08:20:00"))
                .andExpect(jsonPath("$[0].headwaySecs").value(600))
                .andExpect(jsonPath("$[1].tripId").value("2"))
                .andExpect(jsonPath("$[1].startTime").value("08:20:30"))
                .andExpect(jsonPath("$[1].endTime").value("08:25:00"))
                .andExpect(jsonPath("$[1].headwaySecs").value(600));
    }

    @Test
    void shouldReturnExactlyOneFrequencyByIdWithCorrectTypeAndValuesSuccess() throws Exception {
        when(getFrequencyByIdCase.execute("1")).thenReturn(frequencies1);
        when(frequenciesDtoMapper.toResponse(frequencies1)).thenReturn(response1);

        mockMvc.perform(get("/frequencies/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("tripId").value("1"))
                .andExpect(jsonPath("startTime").value("08:15:30"))
                .andExpect(jsonPath("endTime").value("08:20:00"))
                .andExpect(jsonPath("headwaySecs").value(600));
    }

    @Test
    void shouldReturnBadRequestStatusWhenTripIdIsInvalid() throws Exception {
        when(getFrequencyByIdCase.execute("1")).thenThrow(new IllegalArgumentException("Invalid trip ID"));

        mockMvc.perform(get("/frequencies/1"))
                .andExpect(status().isBadRequest());
    }
}
