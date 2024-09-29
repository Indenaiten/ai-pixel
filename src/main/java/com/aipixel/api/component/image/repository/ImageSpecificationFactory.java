package com.aipixel.api.component.image.repository;

import org.springframework.data.jpa.domain.Specification;

import java.sql.Timestamp;



/**
 * Clase que se encarga de generar las especificaciones de búsqueda de imágenes.
 */
public class ImageSpecificationFactory {

    private ImageSpecificationFactory() {
        throw new UnsupportedOperationException( "Utility Class" );
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| METHODS |---------------------------------------------------------------------------------------------------- \\

    /**
     * Devuelve una especificación que busca imágenes cuya fecha de creación sea anterior a una marca de tiempo determinada.
     *
     * @param timestamp La marca de tiempo.
     *
     * @return La especificación.
     */
    public static Specification<ImageModel> getCreatedAtLessThan( final Timestamp timestamp ) {
        return Specification.where( ( root, query, cb ) -> cb.lessThan( root.get( ImageModel.FIELD_CREATED_AT ), timestamp ));
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
