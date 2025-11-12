package br.com.msp.busiq.core.usecases.shapes;

import br.com.msp.busiq.core.domain.Shapes;
import br.com.msp.busiq.core.gateway.shapes.ShapesGateway;

import java.util.List;

public class GetShapesByIdInteractor implements GetShapesByIdCase {
    private final ShapesGateway shapesGateway;

    public GetShapesByIdInteractor(ShapesGateway shapesGateway) {
        this.shapesGateway = shapesGateway;
    }

    @Override
    public List<Shapes> execute(String shapeId) {
        return shapesGateway.getShapesByShapeId(shapeId);
    }
}
