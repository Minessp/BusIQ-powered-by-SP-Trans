package br.com.msp.busiq.infrastructure.controllers.routes;

import br.com.msp.busiq.core.domain.Routes;
import br.com.msp.busiq.core.usecases.routes.*;
import br.com.msp.busiq.infrastructure.controllers.RoutesController;
import br.com.msp.busiq.infrastructure.dtos.RoutesResponse;
import br.com.msp.busiq.infrastructure.exceptions.GlobalExceptionHandler;
import br.com.msp.busiq.infrastructure.mappers.routes.RoutesDtoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RoutesController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(GlobalExceptionHandler.class)
public class RoutesControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    private GetRoutesCase getRoutesCase;

    @MockitoBean
    private GetRouteByIdCase getRouteByIdCase;

    @MockitoBean
    private GetRoutesByAgencyIdCase getRoutesByAgencyId;

    @MockitoBean
    private GetRouteByRouteShortNameCase getRouteByRouteShortNameCase;

    @MockitoBean
    private GetRoutesByContainsRouteLongNameCase getRoutesByContainsRouteLongName;

    @MockitoBean
    private GetRoutesByRouteColorCase getRoutesByRouteColorCase;

    @MockitoBean
    private RoutesDtoMapper routesDtoMapper;

    Routes routes1 = Routes.builder()
            .routeId("1012-10")
            .agencyId("1")
            .routeShortName("1012-10")
            .routeLongName("Term. Jd. Britania - Jd. Monte Belo")
            .routeType("3")
            .routeColor("509E2F")
            .routeTextColor("FFFFFF")
            .build();

    RoutesResponse response1 = RoutesResponse.builder()
            .routeId("1012-10")
            .agencyId("1")
            .routeShortName("1012-10")
            .routeLongName("Term. Jd. Britania - Jd. Monte Belo")
            .routeType("3")
            .routeColor("509E2F")
            .routeTextColor("FFFFFF")
            .build();


    @Test
    void shouldReturnAllRoutesWithSuccess() throws Exception {
        Routes routes2 = Routes.builder()
                .routeId("1012-21")
                .agencyId("1")
                .routeShortName("1012-21")
                .routeLongName("Term. Jd. Britânia - Jd. Rosinha")
                .routeType("3")
                .routeColor("509E2F")
                .routeTextColor("FFFFFF")
                .build();

        when(getRoutesCase.execute()).thenReturn(List.of(routes1, routes2));

        RoutesResponse response2 = RoutesResponse.builder()
                .routeId("1012-21")
                .agencyId("1")
                .routeShortName("1012-21")
                .routeLongName("Term. Jd. Britânia - Jd. Rosinha")
                .routeType("3")
                .routeColor("509E2F")
                .routeTextColor("FFFFFF")
                .build();

        when(routesDtoMapper.toResponse(routes1)).thenReturn(response1);
        when(routesDtoMapper.toResponse(routes2)).thenReturn(response2);

        mockMvc.perform(get("/routes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].routeId").value("1012-10"))
                .andExpect(jsonPath("$[0].agencyId").value("1"))
                .andExpect(jsonPath("$[0].routeShortName").value("1012-10"))
                .andExpect(jsonPath("$[0].routeLongName").value("Term. Jd. Britania - Jd. Monte Belo"))
                .andExpect(jsonPath("$[0].routeType").value("3"))
                .andExpect(jsonPath("$[0].routeColor").value("509E2F"))
                .andExpect(jsonPath("$[0].routeTextColor").value("FFFFFF"))
                .andExpect(jsonPath("$[1].routeId").value("1012-21"))
                .andExpect(jsonPath("$[1].agencyId").value("1"))
                .andExpect(jsonPath("$[1].routeShortName").value("1012-21"))
                .andExpect(jsonPath("$[1].routeLongName").value("Term. Jd. Britânia - Jd. Rosinha"))
                .andExpect(jsonPath("$[1].routeType").value("3"))
                .andExpect(jsonPath("$[1].routeColor").value("509E2F"))
                .andExpect(jsonPath("$[1].routeTextColor").value("FFFFFF"));
    }

    @Test
    void shouldReturnExactlyOneRouteByIdWithSuccess() throws Exception {
        when(getRouteByIdCase.execute("1012-10")).thenReturn(routes1);
        when(routesDtoMapper.toResponse(routes1)).thenReturn(response1);

        mockMvc.perform(get("/routes/id/1012-10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("routeId").value("1012-10"))
                .andExpect(jsonPath("agencyId").value("1"))
                .andExpect(jsonPath("routeShortName").value("1012-10"))
                .andExpect(jsonPath("routeLongName").value("Term. Jd. Britania - Jd. Monte Belo"))
                .andExpect(jsonPath("routeType").value("3"))
                .andExpect(jsonPath("routeColor").value("509E2F"))
                .andExpect(jsonPath("routeTextColor").value("FFFFFF"));
    }

    @Test
    void shouldReturnBadRequestStatusWhenRouteIdIsInvalid() throws Exception {
        when(getRouteByIdCase.execute("1")).thenThrow(new IllegalArgumentException("Rota não encontrada"));

        mockMvc.perform(get("/routes/id/1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnRoutesByAgencyIdWithSuccess() throws Exception {
        Routes routes2 = Routes.builder()
                .routeId("1012-21")
                .agencyId("1")
                .routeShortName("1012-21")
                .routeLongName("Term. Jd. Britânia - Jd. Rosinha")
                .routeType("3")
                .routeColor("509E2F")
                .routeTextColor("FFFFFF")
                .build();

        when(getRoutesByAgencyId.execute("1")).thenReturn(List.of(routes1, routes2));

        RoutesResponse response2 = RoutesResponse.builder()
                .routeId("1012-21")
                .agencyId("1")
                .routeShortName("1012-21")
                .routeLongName("Term. Jd. Britânia - Jd. Rosinha")
                .routeType("3")
                .routeColor("509E2F")
                .routeTextColor("FFFFFF")
                .build();

        when(routesDtoMapper.toResponse(routes1)).thenReturn(response1);
        when(routesDtoMapper.toResponse(routes2)).thenReturn(response2);

        mockMvc.perform(get("/routes/agency-id/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].routeId").value("1012-10"))
                .andExpect(jsonPath("$[0].agencyId").value("1"))
                .andExpect(jsonPath("$[0].routeShortName").value("1012-10"))
                .andExpect(jsonPath("$[0].routeLongName").value("Term. Jd. Britania - Jd. Monte Belo"))
                .andExpect(jsonPath("$[0].routeType").value("3"))
                .andExpect(jsonPath("$[0].routeColor").value("509E2F"))
                .andExpect(jsonPath("$[0].routeTextColor").value("FFFFFF"))
                .andExpect(jsonPath("$[1].routeId").value("1012-21"))
                .andExpect(jsonPath("$[1].agencyId").value("1"))
                .andExpect(jsonPath("$[1].routeShortName").value("1012-21"))
                .andExpect(jsonPath("$[1].routeLongName").value("Term. Jd. Britânia - Jd. Rosinha"))
                .andExpect(jsonPath("$[1].routeType").value("3"))
                .andExpect(jsonPath("$[1].routeColor").value("509E2F"))
                .andExpect(jsonPath("$[1].routeTextColor").value("FFFFFF"));
    }

    @Test
    void shouldReturnRoutesByRouteShortNameWithSuccess() throws Exception {
        when(getRouteByRouteShortNameCase.execute("1012-10")).thenReturn(routes1);
        when(routesDtoMapper.toResponse(routes1)).thenReturn(response1);

        mockMvc.perform(get("/routes/route-short-name/1012-10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("routeId").value("1012-10"))
                .andExpect(jsonPath("agencyId").value("1"))
                .andExpect(jsonPath("routeShortName").value("1012-10"))
                .andExpect(jsonPath("routeLongName").value("Term. Jd. Britania - Jd. Monte Belo"))
                .andExpect(jsonPath("routeType").value("3"))
                .andExpect(jsonPath("routeColor").value("509E2F"))
                .andExpect(jsonPath("routeTextColor").value("FFFFFF"));
    }

    @Test
    void shouldReturnRoutesByContainsRouteLongNameWithSuccess() throws Exception {
        Routes routes2 = Routes.builder()
                .routeId("1012-21")
                .agencyId("1")
                .routeShortName("1012-21")
                .routeLongName("Term. Jd. Britânia - Jd. Rosinha")
                .routeType("3")
                .routeColor("509E2F")
                .routeTextColor("FFFFFF")
                .build();

        when(getRoutesByContainsRouteLongName.execute("Term. Jd. Britania")).thenReturn(List.of(routes1, routes2));

        RoutesResponse response2 = RoutesResponse.builder()
                .routeId("1012-21")
                .agencyId("1")
                .routeShortName("1012-21")
                .routeLongName("Term. Jd. Britânia - Jd. Rosinha")
                .routeType("3")
                .routeColor("509E2F")
                .routeTextColor("FFFFFF")
                .build();

        when(routesDtoMapper.toResponse(routes1)).thenReturn(response1);
        when(routesDtoMapper.toResponse(routes2)).thenReturn(response2);

        mockMvc.perform(get("/routes/route-long-name?q=Term. Jd. Britania"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].routeId").value("1012-10"))
                .andExpect(jsonPath("$[0].agencyId").value("1"))
                .andExpect(jsonPath("$[0].routeShortName").value("1012-10"))
                .andExpect(jsonPath("$[0].routeLongName").value("Term. Jd. Britania - Jd. Monte Belo"))
                .andExpect(jsonPath("$[0].routeType").value("3"))
                .andExpect(jsonPath("$[0].routeColor").value("509E2F"))
                .andExpect(jsonPath("$[0].routeTextColor").value("FFFFFF"))
                .andExpect(jsonPath("$[1].routeId").value("1012-21"))
                .andExpect(jsonPath("$[1].agencyId").value("1"))
                .andExpect(jsonPath("$[1].routeShortName").value("1012-21"))
                .andExpect(jsonPath("$[1].routeLongName").value("Term. Jd. Britânia - Jd. Rosinha"))
                .andExpect(jsonPath("$[1].routeType").value("3"))
                .andExpect(jsonPath("$[1].routeColor").value("509E2F"))
                .andExpect(jsonPath("$[1].routeTextColor").value("FFFFFF"));
    }

    @Test
    void shouldReturnRoutesByRouteColorWithSuccess() throws Exception {
        when(getRoutesByRouteColorCase.execute("509E2F")).thenReturn(List.of(routes1));
        when(routesDtoMapper.toResponse(routes1)).thenReturn(response1);

        mockMvc.perform(get("/routes/route-color/509E2F"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].routeId").value("1012-10"))
                .andExpect(jsonPath("$[0].agencyId").value("1"))
                .andExpect(jsonPath("$[0].routeShortName").value("1012-10"))
                .andExpect(jsonPath("$[0].routeLongName").value("Term. Jd. Britania - Jd. Monte Belo"))
                .andExpect(jsonPath("$[0].routeType").value("3"))
                .andExpect(jsonPath("$[0].routeColor").value("509E2F"))
                .andExpect(jsonPath("$[0].routeTextColor").value("FFFFFF"));
    }
}
