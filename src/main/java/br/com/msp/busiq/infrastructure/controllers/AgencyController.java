package br.com.msp.busiq.infrastructure.controllers;

import br.com.msp.busiq.core.usecases.agency.GetAgencyByIdCase;
import br.com.msp.busiq.core.usecases.agency.GetAgenciesCase;
import br.com.msp.busiq.core.usecases.agency.GetAgencyByNameCase;
import br.com.msp.busiq.infrastructure.dtos.AgencyResponse;
import br.com.msp.busiq.infrastructure.mappers.agency.AgencyDtoMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agency")
public class AgencyController {
    private final GetAgenciesCase getAgenciesCase;
    private final GetAgencyByIdCase getAgencyByIdCase;
    private final GetAgencyByNameCase getAgencyByNameCase;
    private final AgencyDtoMapper agencyDtoMapper;

    public AgencyController(GetAgenciesCase getAgenciesCase, GetAgencyByIdCase getAgencyByIdCase,
                            GetAgencyByNameCase getAgencyByNameCase, AgencyDtoMapper agencyDtoMapper) {
        this.getAgenciesCase = getAgenciesCase;
        this.getAgencyByIdCase = getAgencyByIdCase;
        this.getAgencyByNameCase = getAgencyByNameCase;
        this.agencyDtoMapper = agencyDtoMapper;
    }

    @GetMapping
    public List<AgencyResponse> getAgencies() {
        return getAgenciesCase.execute().stream().map(agencyDtoMapper::toResponse).toList();
    }

    @GetMapping("/id/{id}")
    public AgencyResponse getAgencyById(@PathVariable String id) {
        return agencyDtoMapper.toResponse(getAgencyByIdCase.execute(id));
    }

    @GetMapping("/name/{name}")
    public AgencyResponse getAgencyByName(@PathVariable String name) {
        return agencyDtoMapper.toResponse(getAgencyByNameCase.execute(name));
    }
}
