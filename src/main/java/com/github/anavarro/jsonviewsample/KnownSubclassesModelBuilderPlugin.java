package com.github.anavarro.jsonviewsample;

import io.swagger.annotations.ApiModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelContext;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class KnownSubclassesModelBuilderPlugin implements ModelBuilderPlugin {

    @Override
    public void apply(ModelContext context) {
        try {
            final ApiModel[] annotationsByType = Class.forName(context.getType().getTypeName()).getAnnotationsByType(ApiModel.class);
            if (annotationsByType != null && annotationsByType.length == 1 && annotationsByType[0].subTypes().length > 0) {
                final String knownSubclasses = Arrays.stream(annotationsByType[0].subTypes()).map(Class::getSimpleName).filter(e -> !context.getType().getTypeName().equals(e)).collect(Collectors.joining(","));
                context.getBuilder().description("Known Subclasses with discriminator " + annotationsByType[0].discriminator() + knownSubclasses);
            }
        } catch (ClassNotFoundException e) {
            log.warn("Fail to add descrition of Subclasses context.getType().getTypeName():{}, e:{}", context.getType().getTypeName(), e);
        }
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return true;
    }
}
