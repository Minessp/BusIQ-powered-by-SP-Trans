package br.com.msp.busiq.infrastructure.controllers.fare;

import br.com.msp.busiq.core.usecases.fare.GetFareRulesCase;
import br.com.msp.busiq.infrastructure.dtos.fare.FareRulesResponse;
import br.com.msp.busiq.infrastructure.mappers.fare.FareRulesDtoMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fare-rules")
public class FareRulesController {
    private final GetFareRulesCase getFareRulesCase;
    private final FareRulesDtoMapper fareRulesDtoMapper;

    public FareRulesController(GetFareRulesCase getFareRulesCase, FareRulesDtoMapper fareRulesDtoMapper) {
        this.getFareRulesCase = getFareRulesCase;
        this.fareRulesDtoMapper = fareRulesDtoMapper;
    }

    @GetMapping
    public List<FareRulesResponse> getAllFareRules() {
        return getFareRulesCase.execute().stream().map(fareRulesDtoMapper::toResponse).toList();
    }
}
