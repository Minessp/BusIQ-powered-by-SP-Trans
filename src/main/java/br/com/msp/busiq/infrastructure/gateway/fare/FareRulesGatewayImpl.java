package br.com.msp.busiq.infrastructure.gateway.fare;

import br.com.msp.busiq.core.gateway.fare.FareRulesGateway;
import br.com.msp.busiq.data.parser.TxtParser;
import br.com.msp.busiq.infrastructure.mappers.fare.FareRulesDtoMapper;
import br.com.msp.busiq.infrastructure.persistence.entities.fare.FareRulesEntity;
import br.com.msp.busiq.infrastructure.persistence.repositories.fare.FareRulesRepository;

import java.util.List;

public class FareRulesGatewayImpl implements FareRulesGateway {
    private final FareRulesRepository fareRulesRepository;
    private final FareRulesDtoMapper fareRulesDtoMapper;
    private final TxtParser txtParser;

    public FareRulesGatewayImpl(FareRulesRepository fareRulesRepository, FareRulesDtoMapper fareRulesDtoMapper,
                                TxtParser txtParser) {
        this.fareRulesRepository = fareRulesRepository;
        this.fareRulesDtoMapper = fareRulesDtoMapper;
        this.txtParser = txtParser;
    }

    @Override
    public void saveFareRulesData() {
        List<FareRulesEntity> fareRules = txtParser.toFareRules().stream().map(fareRulesDtoMapper::toEntity).toList();
        fareRulesRepository.saveAll(fareRules);
    }
}
