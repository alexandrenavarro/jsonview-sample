package com.github.anavarro.jsonviewsample;


import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ItemController {


    private Item item;

    @GetMapping("/derivateditems/1/public")
    @JsonView(Views.Public.class)
    @Validated(Views.Public.class)
    public DerivatedItem getPublicDerivatedItem() {
        final DerivatedItem derivatedItem = new DerivatedItem();
        derivatedItem.setPublicDerivatedItem("derivateA");
        derivatedItem.setPublicAndLightId(1);
        derivatedItem.setPublicItem("publicItem");
        derivatedItem.setInternalOwnerName("internalOwnerName");
        return derivatedItem;
    }


    @GetMapping("/items/1")
    public Item getItem() {
        return Item.builder()
                .publicAndLightId(1)
                .publicItem("publicItem")
                .internalOwnerName("internalOwnerName")
                .build();
    }

    @GetMapping("/items/1/public")
    @JsonView(Views.Public.class)
    @Validated(Views.Public.class)

    public Item getPublicItem() {
        return Item.builder()
                .publicAndLightId(1)
                .publicItem("publicItem")
                .internalOwnerName("internalOwnerName")
                .build();
    }

    @GetMapping("/items/1/light")
    @JsonView(Views.Light.class)
    @Validated(Views.Light.class)
    public Item getLightItem() {
        return Item.builder()
                .publicAndLightId(1)
                .publicItem("publicItem")
                .internalOwnerName("internalOwnerName")
                .build();
    }

    @GetMapping("/items/1/internal")
    @JsonView(Views.Internal.class)
    @Validated(Views.Internal.class)
    public Item getInternalItem() {
        return Item.builder()
                .publicAndLightId(1)
                .publicItem("publicItem")
                .internalOwnerName("internalOwnerName")
                .build();
    }

    @PutMapping("/items/1/public")
    @JsonView(Views.Public.class)
    @Validated(Views.Public.class)
    @ApiOperation(value = "Api save Public Item", ignoreJsonView = false)
    public Item savePublicItem(
            @ApiParam(value = "Only fields annotated jsonView will be taken in account", examples =
                    @Example(
                            {@ExampleProperty(mediaType = MediaType.APPLICATION_JSON_VALUE, value = "value")}
                            )
                    )
            @RequestBody
            @JsonView(Views.Public.class)
            @Validated(Views.Public.class)

            Item item) {
        this.item = item;
        log.info("item:{}", item);
        return item;
    }

}
