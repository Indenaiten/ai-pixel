package com.aipixel.api.component.image.repository;

import com.aipixel.api.component.image.Image;
import com.aipixel.api.component.image.ImageMapper;
import com.aipixel.api.component.image.ImageRepository;
import com.aipixel.api.component.image.exception.ImageNotFoundException;
import com.aipixel.api.component.image.vo.ImageId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Optional;



/**
 * Implementación del repositorio {@link ImageRepository}.
 */
@Repository
public class ImageRepositoryImpl implements ImageRepository {

    private final ImageDao imageDao;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Constructor al que se le inyectan las dependencias necesarias.
     *
     * @param imageDao El DAO de imágenes.
     */
    @Autowired
    public ImageRepositoryImpl( final ImageDao imageDao ) {
        this.imageDao = imageDao;
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| IMPLEMENT METHODS |------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public List<Image> findAll( final ImageId lastId, final int limit ) throws ImageNotFoundException {
        List<Image> result;
        final Sort sort = Sort.by( Sort.Order.desc( ImageModel.FIELD_CREATED_AT ) );
        final Pageable pageable = PageRequest.of( 0, limit, sort );

        if( Optional.ofNullable( lastId ).isPresent() ) {
            final ImageModel image = this.imageDao.findById( lastId.toString() ).orElseThrow( () -> new ImageNotFoundException(
                    String.format( "No se ha encontrado la imagen con identificador %s", lastId )
            ));
            result = this.imageDao.findByCreatedAtLessThan( image.getCreatedAt(), pageable )
                    .stream().map( ImageMapper::modelToEntity ).toList();
        }
        else {
            result = this.imageDao.findAll( pageable ).stream().map( ImageMapper::modelToEntity ).toList();
        }

         return result;
    }


    @Override
    public Optional<Image> findById( final ImageId id ) {
        return this.imageDao.findById( id.toString() ).map( ImageMapper::modelToEntity );
    }


    @Override
    public void save( final Image image ) {
        this.imageDao.save( ImageMapper.entityToModel( image ));
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
