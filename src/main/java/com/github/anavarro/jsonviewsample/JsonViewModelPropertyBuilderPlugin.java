package com.github.anavarro.jsonviewsample;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Optional;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.bean.validators.plugins.Validators;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;

import javax.validation.constraints.NotNull;

import java.util.Arrays;
import java.util.stream.Collectors;

import static springfox.bean.validators.plugins.Validators.annotationFromBean;
import static springfox.bean.validators.plugins.Validators.annotationFromField;

@Order(Validators.BEAN_VALIDATOR_PLUGIN_ORDER)
@Component
public class JsonViewModelPropertyBuilderPlugin implements ModelPropertyBuilderPlugin {

    /**
     * support all documentationTypes
     */
    @Override
    public boolean supports(DocumentationType delimiter) {
        // we simply support all documentationTypes!
        return true;
    }

    /**
     * read NotNull annotation
     */
    @Override
    public void apply(ModelPropertyContext context) {
        Optional<JsonView> jsonViewOptional = extractAnnotation(context);
        if (jsonViewOptional.isPresent()) {
            final String jsonViews = Arrays.stream(jsonViewOptional.get().value()).map(Class::getSimpleName).collect(Collectors.joining(","));
            context.getBuilder().description("@JsonView:[" + jsonViews + "]");
        }
    }

    @VisibleForTesting
    Optional<JsonView> extractAnnotation(ModelPropertyContext context) {
        return annotationFromBean(context, JsonView.class).or(annotationFromField(context, JsonView.class));
    }
}
