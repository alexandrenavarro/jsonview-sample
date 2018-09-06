package com.github.anavarro.jsonviewsample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2 // Need to activae swagger
@Import({BeanValidatorPluginsConfiguration.class}) // Need to active documentation of @NotNull in
public class JsonviewSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsonviewSampleApplication.class, args);
	}
}
