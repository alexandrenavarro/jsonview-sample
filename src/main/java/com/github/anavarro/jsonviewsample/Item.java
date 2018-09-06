package com.github.anavarro.jsonviewsample;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(discriminator = "test", subTypes = DerivatedItem.class)
public class Item {

    @NotNull(groups = {Views.Public.class, Views.Light.class})
    @Min(value = 1)
    @JsonView({Views.Public.class, Views.Light.class})
    private int publicAndLightId;

    @JsonView(Views.Public.class)
    private String publicItem;

    @JsonView(Views.Internal.class)
    private String internalOwnerName;

}
