package com.aipixel.api.config;

import com.aipixel.api.common.properties.WebProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;
import java.util.Optional;



/**
 * Clase de configuración para la configuración web de la aplicación.
 */
@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final WebProperties webProperties;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Constructor al que se le inyectan las dependencias necesarias.
     *
     * @param webProperties Las propiedades de la configuración web.
     */
    @Autowired
    public WebConfig( final WebProperties webProperties ) {
        this.webProperties = webProperties;
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| IMPLEMENTED METHODS |---------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public void addCorsMappings( final CorsRegistry registry ) {
        final List<String> corsAllowedOrigins = this.webProperties.getCorsAllowedOrigins();
        final List<String> corsAllowedMethods = this.webProperties.getCorsAllowedMethods();
        final List<String> corsAllowedHeaders = this.webProperties.getCorsAllowedHeaders();
        final Optional<Boolean> corsAllowCredentials = this.webProperties.getCorsAllowCredentials();

        if( !corsAllowedOrigins.isEmpty() ||
                !corsAllowedMethods.isEmpty() ||
                !corsAllowedHeaders.isEmpty() ||
                corsAllowCredentials.isPresent()
        ) {

            final CorsRegistration globalCorsRegistration = registry.addMapping("/**");
            if( !corsAllowedOrigins.isEmpty() ) {
                globalCorsRegistration.allowedOrigins( corsAllowedOrigins.toArray( String[]::new ));
            }
            if( !corsAllowedOrigins.isEmpty() ) {
                globalCorsRegistration.allowedOrigins( corsAllowedOrigins.toArray( String[]::new ));
            }
            if( !corsAllowedMethods.isEmpty() ) {
                globalCorsRegistration.allowedMethods( corsAllowedMethods.toArray( String[]::new ));
            }
            corsAllowCredentials.ifPresent( globalCorsRegistration::allowCredentials );
        }
    }


    @Override
    public void addResourceHandlers( final ResourceHandlerRegistry registry ) {
        final List<String> resourcesPaths = this.webProperties.getResourcesPaths();
        if( !resourcesPaths.isEmpty() ) {
            final ResourceHandlerRegistration globalResourceRegistry = registry.addResourceHandler("/**");
            globalResourceRegistry.addResourceLocations( resourcesPaths.toArray( String[]::new ));
        }
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
