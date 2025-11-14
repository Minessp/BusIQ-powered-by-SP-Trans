package br.com.msp.busiq.infrastructure.controllers.fare;

import br.com.msp.busiq.core.usecases.fare.GetFareAttributesCase;
import br.com.msp.busiq.infrastructure.dtos.fare.FareAttributesResponse;
import br.com.msp.busiq.infrastructure.mappers.fare.FareAttributesDtoMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fare-attributes")
public class FareAttributesController {
    private final GetFareAttributesCase getFareAttributesCase;
    private final FareAttributesDtoMapper fareAttributesDtoMapper;

    public FareAttributesController(GetFareAttributesCase getFareAttributesCase,
                                    FareAttributesDtoMapper fareAttributesDtoMapper) {
        this.getFareAttributesCase = getFareAttributesCase;
        this.fareAttributesDtoMapper = fareAttributesDtoMapper;
    }

    @GetMapping
    public List<FareAttributesResponse> getAllFareAttributes() {
        return getFareAttributesCase.execute().stream().map(fareAttributesDtoMapper::toResponse).toList();
    }
}
