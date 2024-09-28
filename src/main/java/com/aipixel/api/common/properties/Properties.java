package com.aipixel.api.common.properties;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;



/**
 * Clase base para las clases que representan propiedades de la aplicación.
 */
public abstract class Properties {

    /**
     * Convierte una cadena de texto en una lista de cadenas de texto.
     * Ejemplos:
     * - "a,b,c,d" -> ["a", "b", "c", "d"]
     * - "a, b, c, d" -> ["a", "b", "c", "d"]
     * - "a, 'b c', d" -> ["a", "b", "c", "d"]
     *
     * @param value Cadena de texto a convertir.
     *
     * @return Lista de cadenas de texto.
     */
    protected List<String> convertToList( final String value ) {
        return Arrays.stream( value.split( "," ) )
                .map( item -> item.trim().replace("'", "") )
                .toList();
    }


    /**
     * Obtiene un {@link Optional} a partir de un valor.
     *
     * @param <T> Tipo del valor.
     * @param value Valor a convertir en {@link Optional}.
     *
     * @return Valor {@link Optional}.
     */
    protected <T> Optional<T> getOptional( final T value ) {
        Optional<T> result = Optional.empty();
        if( value instanceof String valueString && !valueString.isBlank() ) {
            result = Optional.of( value );
        }
        return result;
    }
}
