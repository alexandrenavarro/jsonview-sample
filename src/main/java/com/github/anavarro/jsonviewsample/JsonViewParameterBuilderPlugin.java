package com.github.anavarro.jsonviewsample;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.base.Optional;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import io.swagger.annotations.ApiModel;
import net.bytebuddy.ByteBuddy;
import org.springframework.stereotype.Component;
import springfox.bean.validators.plugins.Validators;
import springfox.documentation.schema.ModelReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ParameterBuilderPlugin;
import springfox.documentation.spi.service.contexts.ParameterContext;

import java.util.Arrays;

@Component
public class JsonViewParameterBuilderPlugin implements ParameterBuilderPlugin {


    @Override
    public void apply(ParameterContext parameterContext) {
        final Optional<JsonView> annotation = parameterContext.resolvedMethodParameter().findAnnotation(JsonView.class);
        if (annotation.isPresent()) {
            parameterContext.parameterBuilder()
                    .description("<b>!!! Caution, only fields with " + annotation.get().value()[0].getSimpleName() + " jsonView attribute are taken !!! </b>" );
        }
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return true;
    }
}
