package br.com.msp.busiq.config.domain.shapes;

import br.com.msp.busiq.core.gateway.shapes.ShapesGateway;
import br.com.msp.busiq.core.usecases.shapes.*;
import br.com.msp.busiq.data.parser.TxtParser;
import br.com.msp.busiq.infrastructure.gateway.shapes.ShapesGatewayImpl;
import br.com.msp.busiq.infrastructure.mappers.shapes.ShapesDtoMapper;
import br.com.msp.busiq.infrastructure.persistence.repositories.ShapesRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShapesConfig {

    @Bean
    GetShapesCase getShapesCase(ShapesGateway shapesGateway) {
        return new GetShapesInteractor(shapesGateway);
    }

    @Bean
    GetShapesByIdCase getShapeByIdCase(ShapesGateway shapesGateway) {
        return new GetShapesByIdInteractor(shapesGateway);
    }

    @Bean
    GetShapeByIdAndSequenceCase getShapeByIdAndSequenceCase(ShapesGateway shapesGateway) {
        return new GetShapeByIdAndSequenceInteractor(shapesGateway);
    }

    @Bean
    ShapesGateway shapesGateway(ShapesRepository shapesRepository, ShapesDtoMapper shapesDtoMapper,
                                TxtParser txtParser) {
        return new ShapesGatewayImpl(shapesRepository, shapesDtoMapper, txtParser);
    }

    @Bean
    ShapesDtoMapper shapesDtoMapper() {
        return new ShapesDtoMapper();
    }

    @Bean
    SaveShapesDataCase saveShapesDataCase(ShapesGateway shapesGateway) {
        return new SaveShapesDataInteractor(shapesGateway);
    }
}
