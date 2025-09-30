package br.com.msp.busiq.infrastructure.controllers;

import br.com.msp.busiq.core.domain.Agency;
import br.com.msp.busiq.core.usecases.agency.GetAgenciesCase;
import br.com.msp.busiq.infrastructure.dtos.AgencyResponse;
import br.com.msp.busiq.infrastructure.mappers.agency.AgencyDtoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AgencyController.class)
class AgencyControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    private GetAgenciesCase getAgenciesCase;

    @MockitoBean
    private AgencyDtoMapper agencyDTOMapper;

    @Test
    void returnAgenciesWithCorrectTypeAndValuesSuccess() throws Exception {
        Agency agency1 = Agency.builder()
                .agencyId("1")
                .agencyName("Agencia 1")
                .agencyUrl(URI.create("https://agencia1.com.br").toURL())
                .agencyTimezone("America/Sao_Paulo")
                .agencyLang("pt/br")
                .build();

        Agency agency2 = Agency.builder()
                .agencyId("2")
                .agencyName("Agencia 2")
                .agencyUrl(URI.create("https://agencia2.com.br").toURL())
                .agencyTimezone("America/Sao_Paulo")
                .agencyLang("pt/br")
                .build();

        when(getAgenciesCase.execute()).thenReturn(List.of(agency1, agency2));

        AgencyResponse response1 = AgencyResponse.builder()
                .agencyId("1")
                .agencyName("Agencia 1")
                .agencyUrl(URI.create("https://agencia1.com.br").toURL())
                .agencyTimezone("America/Sao_Paulo")
                .agencyLang("pt/br")
                .build();

        AgencyResponse response2 = AgencyResponse.builder()
                .agencyId("2")
                .agencyName("Agencia 2")
                .agencyUrl(URI.create("https://agencia2.com.br").toURL())
                .agencyTimezone("America/Sao_Paulo")
                .agencyLang("pt/br")
                .build();

        when(agencyDTOMapper.toResponse(agency1)).thenReturn(response1);
        when(agencyDTOMapper.toResponse(agency2)).thenReturn(response2);

        mockMvc.perform(get("/agencies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].agencyId").value("1"))
                .andExpect(jsonPath("$[0].agencyName").value("Agencia 1"))
                .andExpect(jsonPath("$[0].agencyUrl").value("https://agencia1.com.br"))
                .andExpect(jsonPath("$[0].agencyTimezone").value("America/Sao_Paulo"))
                .andExpect(jsonPath("$[0].agencyLang").value("pt/br"))
                .andExpect(jsonPath("$[1].agencyId").value("2"))
                .andExpect(jsonPath("$[1].agencyName").value("Agencia 2"))
                .andExpect(jsonPath("$[1].agencyUrl").value("https://agencia2.com.br"))
                .andExpect(jsonPath("$[1].agencyTimezone").value("America/Sao_Paulo"))
                .andExpect(jsonPath("$[1].agencyLang").value("pt/br"));
    }
}