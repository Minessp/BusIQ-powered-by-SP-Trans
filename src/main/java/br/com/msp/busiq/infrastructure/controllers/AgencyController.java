package br.com.msp.busiq.infrastructure.controllers;

import br.com.msp.busiq.core.usecases.agency.GetAgenciesCase;
import br.com.msp.busiq.infrastructure.dtos.AgencyResponse;
import br.com.msp.busiq.infrastructure.mappers.agency.AgencyDtoMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/agencies")
public class AgencyController {
    private final GetAgenciesCase getAgenciesCase;
    private final AgencyDtoMapper agencyDTOMapper;

    public AgencyController(GetAgenciesCase getAgenciesCase, AgencyDtoMapper agencyDTOMapper) {
        this.getAgenciesCase = getAgenciesCase;
        this.agencyDTOMapper = agencyDTOMapper;
    }

    @GetMapping()
    public @ResponseBody List<AgencyResponse> getAgencies() {
        return getAgenciesCase.execute().stream().map(agencyDTOMapper::toResponse).toList();
    }
}
