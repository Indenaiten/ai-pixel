package com.aipixel.api.common.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Getter
@Component
public class ApplicationProperties {

    @Value("${images.url.base}")
    private String imagesUrlBase;

}
