package br.com.msp.busiq.core.usecases.shapes;

import br.com.msp.busiq.core.domain.Shapes;

import java.util.List;

public interface GetShapesByIdCase {
    List<Shapes> execute(String shapeId);
}
