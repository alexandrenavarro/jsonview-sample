package com.github.anavarro.jsonviewsample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class ItemTest {


    @Test
    public void whenUsePublicView_thenOnlyPublicSerialized()
            throws JsonProcessingException {

        Item item = new Item(2, "book", "John");

        ObjectMapper mapper = new ObjectMapper();
        String result = mapper
                .writerWithView(Views.Public.class)
                .writeValueAsString(item);

        Assertions.assertThat(result).contains("book");
        Assertions.assertThat(result).contains("2");
        Assertions.assertThat(result).doesNotContain("John");
    }
}