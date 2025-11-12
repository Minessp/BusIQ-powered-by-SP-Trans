package br.com.msp.busiq.core.usecases.shapes;

import br.com.msp.busiq.core.domain.Shapes;
import br.com.msp.busiq.core.gateway.shapes.ShapesGateway;

import java.util.List;

public class GetShapesInteractor implements GetShapesCase {
    private final ShapesGateway shapesGateway;

    public GetShapesInteractor(ShapesGateway shapesGateway) {
        this.shapesGateway = shapesGateway;
    }

    @Override
    public List<Shapes> execute() {
        return shapesGateway.getAllShapes();
    }
}
