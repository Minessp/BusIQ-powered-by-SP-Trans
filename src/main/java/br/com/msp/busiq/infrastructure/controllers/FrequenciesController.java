package br.com.msp.busiq.infrastructure.controllers;

import br.com.msp.busiq.core.usecases.frequencies.GetFrequenciesCase;
import br.com.msp.busiq.core.usecases.frequencies.GetFrequencyByIdCase;
import br.com.msp.busiq.infrastructure.dtos.FrequenciesResponse;
import br.com.msp.busiq.infrastructure.mappers.frequencies.FrequenciesDtoMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/frequencies")
public class FrequenciesController {
    private final GetFrequenciesCase getFrequenciesCase;
    private final GetFrequencyByIdCase getFrequencyByIdCase;
    private final FrequenciesDtoMapper frequenciesDtoMapper;

    public FrequenciesController(GetFrequenciesCase getFrequenciesCase, GetFrequencyByIdCase getFrequencyByIdCase,
                                 FrequenciesDtoMapper frequenciesDtoMapper) {
        this.getFrequenciesCase = getFrequenciesCase;
        this.getFrequencyByIdCase = getFrequencyByIdCase;
        this.frequenciesDtoMapper = frequenciesDtoMapper;
    }

    @GetMapping
    public List<FrequenciesResponse> getFrequencies() {
        return getFrequenciesCase.execute().stream().map(frequenciesDtoMapper::toResponse).toList();
    }

    @GetMapping("/{tripId}")
    public FrequenciesResponse getAllFrequenciesFromTripId(@PathVariable String tripId) {
        return frequenciesDtoMapper.toResponse(getFrequencyByIdCase.execute(tripId));
    }
}
