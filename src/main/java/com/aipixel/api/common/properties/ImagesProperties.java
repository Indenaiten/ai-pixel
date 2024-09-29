package com.aipixel.api.common.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



/**
 * Propiedades relacionadas con las imágenes de la aplicación.
 */
@Getter
@Component
public class ImagesProperties extends Properties {

    @Value("${app.images.url}")
    private String imagesUrlBase;

    @Value("${app.images.directory}")
    private String imagesDirectory;

    @Value("${app.images.default-limit}")
    private Integer defaultLimit;

// ------------------------------------------------------------------------------------------------------------------ \\

}
