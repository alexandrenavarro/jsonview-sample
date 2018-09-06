package com.github.anavarro.jsonviewsample;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.base.Optional;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class JsonViewResponseOperationReader implements OperationBuilderPlugin {

    @Override
    public void apply(OperationContext context) {

        final Optional<JsonView> annotation = context.findAnnotation(JsonView.class);
        if (annotation.isPresent()) {
            context.operationBuilder()
                    .notes("<b> !!! Caution, only fields with " + annotation.get().value()[0].getSimpleName() + " jsonView attribute will be returned !!! <b>");
        }
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return true;
    }
}
