package com.aipixel.api.common.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;



/**
 * Propiedades relacionadas con la configuración de la aplicación web.
 */
@Getter
@Component
public class WebProperties extends Properties {

    @Value( "${app.web.cors.allowed-origins:}" )
    private String corsAllowedOrigins;

    @Value( "${app.web.cors.allowed-methods:}" )
    private String corsAllowedMethods;

    @Value( "${app.web.cors.allowed-headers:}" )
    private String corsAllowedHeaders;

    @Value( "${app.web.cors.allow-credentials:}" )
    private boolean corsAllowCredentials;

    @Value( "${app.web.resources.paths:}" )
    private String resourcesPaths;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| GETTERS |---------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public List<String> getCorsAllowedOrigins() {
        return this.convertToList( this.corsAllowedOrigins );
    }

    public List<String> getCorsAllowedMethods() {
        return this.convertToList( this.corsAllowedMethods );
    }

    public List<String> getCorsAllowedHeaders() {
        return this.convertToList( this.corsAllowedHeaders );
    }

    public Optional<Boolean> getCorsAllowCredentials() {
        return this.getOptional( this.corsAllowCredentials );
    }

    public List<String> getResourcesPaths() {
        return this.convertToList( this.resourcesPaths );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
