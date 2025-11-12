package br.com.msp.busiq.infrastructure.mappers.shapes;

import br.com.msp.busiq.core.domain.Shapes;
import br.com.msp.busiq.infrastructure.dtos.ShapesResponse;
import br.com.msp.busiq.infrastructure.persistence.entities.ShapesEntity;

public class ShapesDtoMapper {
    public Shapes toDomain(ShapesEntity shapesEntity) {
        return Shapes.builder()
                .shapeId(shapesEntity.getShapeId())
                .lat(shapesEntity.getLat())
                .lon(shapesEntity.getLon())
                .sequence(shapesEntity.getSequence())
                .distTraveled(shapesEntity.getDistTraveled())
                .build();
    }

    public ShapesResponse toResponse(Shapes shapes) {
        return ShapesResponse.builder()
                .shapeId(shapes.shapeId())
                .lat(shapes.lat())
                .lon(shapes.lon())
                .sequence(shapes.sequence())
                .distTraveled(shapes.distTraveled())
                .build();
    }
}
