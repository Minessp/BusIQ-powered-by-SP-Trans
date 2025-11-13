package br.com.msp.busiq.infrastructure.controllers;

import br.com.msp.busiq.core.usecases.frequencies.GetFrequenciesCase;
import br.com.msp.busiq.core.usecases.frequencies.GetFrequenciesByIdCase;
import br.com.msp.busiq.infrastructure.dtos.FrequenciesResponse;
import br.com.msp.busiq.infrastructure.mappers.frequencies.FrequenciesDtoMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/frequencies")
public class FrequenciesController {
    private final GetFrequenciesCase getFrequenciesCase;
    private final GetFrequenciesByIdCase getFrequenciesByIdCase;
    private final FrequenciesDtoMapper frequenciesDtoMapper;

    public FrequenciesController(GetFrequenciesCase getFrequenciesCase, GetFrequenciesByIdCase getFrequenciesByIdCase,
                                 FrequenciesDtoMapper frequenciesDtoMapper) {
        this.getFrequenciesCase = getFrequenciesCase;
        this.getFrequenciesByIdCase = getFrequenciesByIdCase;
        this.frequenciesDtoMapper = frequenciesDtoMapper;
    }

    @GetMapping
    public List<FrequenciesResponse> getFrequencies() {
        return getFrequenciesCase.execute().stream().map(frequenciesDtoMapper::toResponse).toList();
    }

    @GetMapping("/{trip-id}")
    public List<FrequenciesResponse> getAllFrequenciesFromTripId(@PathVariable("trip-id") String tripId) {
        return getFrequenciesByIdCase.execute(tripId).stream().map(frequenciesDtoMapper::toResponse).toList();
    }
}
