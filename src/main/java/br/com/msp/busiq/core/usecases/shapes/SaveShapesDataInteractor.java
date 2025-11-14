package br.com.msp.busiq.core.usecases.shapes;

import br.com.msp.busiq.core.gateway.shapes.ShapesGateway;

public class SaveShapesDataInteractor implements SaveShapesDataCase {
    private final ShapesGateway shapesGateway;

    public SaveShapesDataInteractor(ShapesGateway shapesGateway) {
        this.shapesGateway = shapesGateway;
    }

    @Override
    public void execute() {
        shapesGateway.saveShapesData();
    }
}
