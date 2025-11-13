package br.com.msp.busiq.infrastructure.controllers;

import br.com.msp.busiq.core.usecases.shapes.GetShapeByIdAndSequenceCase;
import br.com.msp.busiq.core.usecases.shapes.GetShapesByIdCase;
import br.com.msp.busiq.core.usecases.shapes.GetShapesCase;
import br.com.msp.busiq.infrastructure.dtos.ShapesResponse;
import br.com.msp.busiq.infrastructure.mappers.shapes.ShapesDtoMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shapes")
public class ShapesController {
    private final GetShapesCase getShapesCase;
    private final GetShapesByIdCase getShapesByIdCase;
    private final GetShapeByIdAndSequenceCase getShapeByIdAndSequenceCase;
    private final ShapesDtoMapper shapesDtoMapper;

    public ShapesController(GetShapesCase getShapesCase, GetShapesByIdCase getShapesByIdCase,
                            GetShapeByIdAndSequenceCase getShapeByIdAndSequenceCase,
                            ShapesDtoMapper shapesDtoMapper) {
        this.getShapesCase = getShapesCase;
        this.getShapesByIdCase = getShapesByIdCase;
        this.getShapeByIdAndSequenceCase = getShapeByIdAndSequenceCase;
        this.shapesDtoMapper = shapesDtoMapper;
    }

    @GetMapping
    public List<ShapesResponse> getShapes() {
        return getShapesCase.execute().stream().map(shapesDtoMapper::toResponse).toList();
    }

    @GetMapping("/shape-id/{shape-id}")
    public List<ShapesResponse> getShapesByShapeId(@PathVariable("shape-id") String shapeId) {
        return getShapesByIdCase.execute(shapeId).stream().map(shapesDtoMapper::toResponse).toList();
    }

    @GetMapping("/shape-id-sequence/{shape-id}")
    public ShapesResponse getShapeByIdAndSequence(@PathVariable("shape-id") String shapeId,
                                                  @RequestParam("sequence") int sequence) {
        return shapesDtoMapper.toResponse(getShapeByIdAndSequenceCase.execute(shapeId, sequence));
    }
}
