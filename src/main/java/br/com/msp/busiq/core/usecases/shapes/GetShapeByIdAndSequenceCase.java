package br.com.msp.busiq.core.usecases.shapes;

import br.com.msp.busiq.core.domain.Shapes;

public interface GetShapeByIdAndSequenceCase {
    Shapes execute(String shapeId, int sequence);
}
