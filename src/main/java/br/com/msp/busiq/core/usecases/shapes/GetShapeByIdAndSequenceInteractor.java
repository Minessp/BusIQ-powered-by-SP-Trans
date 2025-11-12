package br.com.msp.busiq.core.usecases.shapes;

import br.com.msp.busiq.core.domain.Shapes;
import br.com.msp.busiq.core.gateway.shapes.ShapesGateway;

public class GetShapeByIdAndSequenceInteractor implements GetShapeByIdAndSequenceCase {
    private final ShapesGateway shapesGateway;

    public GetShapeByIdAndSequenceInteractor(ShapesGateway shapesGateway) {
        this.shapesGateway = shapesGateway;
    }

    @Override
    public Shapes execute(String shapeId, int sequence) {
        return shapesGateway.getShapeByIdAndSequence(shapeId, sequence);
    }
}
