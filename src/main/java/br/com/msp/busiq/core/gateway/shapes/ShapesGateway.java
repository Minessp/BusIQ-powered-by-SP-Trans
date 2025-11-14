package br.com.msp.busiq.core.gateway.shapes;

import br.com.msp.busiq.core.domain.Shapes;

import java.util.List;

public interface ShapesGateway {
    List<Shapes> getAllShapes();

    List<Shapes> getShapesByShapeId(String shapeId);

    Shapes getShapeByIdAndSequence(String shapeId, int sequence);

    void saveShapesData();
}
