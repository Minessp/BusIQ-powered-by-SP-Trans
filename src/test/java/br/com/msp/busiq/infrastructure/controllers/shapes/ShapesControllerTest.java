package br.com.msp.busiq.infrastructure.controllers.shapes;

import br.com.msp.busiq.core.domain.Shapes;
import br.com.msp.busiq.core.usecases.shapes.GetShapeByIdAndSequenceCase;
import br.com.msp.busiq.core.usecases.shapes.GetShapesByIdCase;
import br.com.msp.busiq.core.usecases.shapes.GetShapesCase;
import br.com.msp.busiq.infrastructure.controllers.ShapesController;
import br.com.msp.busiq.infrastructure.dtos.ShapesResponse;
import br.com.msp.busiq.infrastructure.exceptions.GlobalExceptionHandler;
import br.com.msp.busiq.infrastructure.mappers.shapes.ShapesDtoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ShapesController.class)
@Import(GlobalExceptionHandler.class)
public class ShapesControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    private GetShapesCase getShapesCase;

    @MockitoBean
    private GetShapesByIdCase getShapesByIdCase;

    @MockitoBean
    private GetShapeByIdAndSequenceCase getShapeByIdAndSequenceCase;

    @MockitoBean
    private ShapesDtoMapper shapesDtoMapper;

    Shapes shapes1 = Shapes.builder()
            .shapeId("84609")
            .lat(-21.3232)
            .lon(-47.8232)
            .sequence(1)
            .distTraveled(123.45)
            .build();

    Shapes shapes2 = Shapes.builder()
            .shapeId("84609")
            .lat(-21.3476)
            .lon(-47.8266)
            .sequence(2)
            .distTraveled(124.77)
            .build();

    Shapes shapes3 = Shapes.builder()
            .shapeId("84609")
            .lat(-22.9756)
            .lon(-49.2355)
            .sequence(3)
            .distTraveled(144.21)
            .build();

    ShapesResponse response1 = ShapesResponse.builder()
            .shapeId("84609")
            .lat(-21.3232)
            .lon(-47.8232)
            .sequence(1)
            .distTraveled(123.45)
            .build();

    ShapesResponse response2 = ShapesResponse.builder()
            .shapeId("84609")
            .lat(-21.3476)
            .lon(-47.8266)
            .sequence(2)
            .distTraveled(124.77)
            .build();

    ShapesResponse response3 = ShapesResponse.builder()
            .shapeId("84609")
            .lat(-22.9756)
            .lon(-49.2355)
            .sequence(3)
            .distTraveled(144.21)
            .build();

    @Test
    void shouldReturnAllShapesWithSuccess() throws Exception {
        when(getShapesCase.execute()).thenReturn(List.of(shapes1, shapes2, shapes3));

        when(shapesDtoMapper.toResponse(shapes1)).thenReturn(response1);
        when(shapesDtoMapper.toResponse(shapes2)).thenReturn(response2);
        when(shapesDtoMapper.toResponse(shapes3)).thenReturn(response3);

        mockMvc.perform(get("/shapes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].shapeId").value("84609"))
                .andExpect(jsonPath("$[0].lat").value(-21.3232))
                .andExpect(jsonPath("$[0].lon").value(-47.8232))
                .andExpect(jsonPath("$[0].sequence").value(1))
                .andExpect(jsonPath("$[0].distTraveled").value(123.45))
                .andExpect(jsonPath("$[1].shapeId").value("84609"))
                .andExpect(jsonPath("$[1].lat").value(-21.3476))
                .andExpect(jsonPath("$[1].lon").value(-47.8266))
                .andExpect(jsonPath("$[1].sequence").value(2))
                .andExpect(jsonPath("$[1].distTraveled").value(124.77))
                .andExpect(jsonPath("$[2].shapeId").value("84609"))
                .andExpect(jsonPath("$[2].lat").value(-22.9756))
                .andExpect(jsonPath("$[2].lon").value(-49.2355))
                .andExpect(jsonPath("$[2].sequence").value(3))
                .andExpect(jsonPath("$[2].distTraveled").value(144.21));
    }

    @Test
    void shouldReturnAllShapesByIdWithSuccess() throws Exception {
        when(getShapesByIdCase.execute("84609")).thenReturn(List.of(shapes1, shapes2, shapes3));

        when(shapesDtoMapper.toResponse(shapes1)).thenReturn(response1);
        when(shapesDtoMapper.toResponse(shapes2)).thenReturn(response2);
        when(shapesDtoMapper.toResponse(shapes3)).thenReturn(response3);

        mockMvc.perform(get("/shapes/shape-id/84609"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].shapeId").value("84609"))
                .andExpect(jsonPath("$[1].shapeId").value("84609"))
                .andExpect(jsonPath("$[2].shapeId").value("84609"));
    }

    @Test
    void shouldReturnBadRequestStatusWhenShapeIdIsInvalid() throws Exception {
        when(getShapesByIdCase.execute("1")).thenThrow(new IllegalArgumentException("Invalid shape ID"));

        mockMvc.perform(get("/shapes/shape-id/1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnSpecificShapeByIdAndSequenceWithSuccess() throws Exception {
        when(getShapeByIdAndSequenceCase.execute("84609", 1)).thenReturn(shapes1);
        when(shapesDtoMapper.toResponse(shapes1)).thenReturn(response1);

        mockMvc.perform(get("/shapes/shape-id-sequence/84609?sequence=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("shapeId").value("84609"))
                .andExpect(jsonPath("lat").value(-21.3232))
                .andExpect(jsonPath("lon").value(-47.8232))
                .andExpect(jsonPath("sequence").value(1))
                .andExpect(jsonPath("distTraveled").value(123.45));
    }
}
