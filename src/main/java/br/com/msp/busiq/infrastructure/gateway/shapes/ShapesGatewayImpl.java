package br.com.msp.busiq.infrastructure.gateway.shapes;

import br.com.msp.busiq.core.domain.Shapes;
import br.com.msp.busiq.core.gateway.shapes.ShapesGateway;
import br.com.msp.busiq.infrastructure.mappers.shapes.ShapesDtoMapper;
import br.com.msp.busiq.infrastructure.persistence.repositories.ShapesRepository;

import java.util.List;

public class ShapesGatewayImpl implements ShapesGateway {
    private final ShapesRepository shapesRepository;
    private final ShapesDtoMapper shapesDtoMapper;

    public ShapesGatewayImpl(ShapesRepository shapesRepository, ShapesDtoMapper shapesDtoMapper) {
        this.shapesRepository = shapesRepository;
        this.shapesDtoMapper = shapesDtoMapper;
    }

    @Override
    public List<Shapes> getAllShapes() {
        return shapesRepository.findAll().stream().map(shapesDtoMapper::toDomain).toList();
    }

    @Override
    public List<Shapes> getShapesByShapeId(String shapeId) {
        if (shapeId == null) {
            throw new IllegalArgumentException("Shape ID não pode ser nulo");
        }

        return shapesRepository.findAllByShapeId(shapeId).stream().map(shapesDtoMapper::toDomain).toList();
    }

    @Override
    public Shapes getShapeByIdAndSequence(String shapeId, int sequence) {
        return shapesRepository.findByShapeIdAndSequence(shapeId, sequence).map(shapesDtoMapper::toDomain).orElseThrow(
                () -> new IllegalArgumentException("Não encontrado Shape específico com esses parametros.")
        );
    }
}
