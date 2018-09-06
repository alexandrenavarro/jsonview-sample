package com.github.anavarro.jsonviewsample;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel
public class DerivatedItem extends Item {

    @JsonView(Views.Public.class)
    private String publicDerivatedItem;
}
