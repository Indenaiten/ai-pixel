package com.aipixel.api.component.image.repository;

import com.aipixel.api.component.image.Image;
import com.aipixel.api.component.image.ImageMapper;
import com.aipixel.api.component.image.ImageRepository;
import com.aipixel.api.component.image.vo.ImageId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
    public List<Image> findAll( final LocalDateTime createdAt, final int limit ) {
        final Pageable pageable = this.getPageable( limit );
        Specification<ImageModel> specification = Specification.where( null );
        if( createdAt != null ) {
            final Timestamp timestamp = Timestamp.valueOf( createdAt );
            specification = ImageSpecificationFactory.getCreatedAtLessThan( timestamp );
        }

        return this.imageDao.findAll( specification, pageable ).map( ImageMapper::modelToEntity ).stream().toList();
    }


    @Override
    public Long countAll() {
        return this.imageDao.count();
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
// ---| UTILITY METHODS |-------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    private Pageable getPageable( final int limit ) {
        final Sort sort = this.getDefaulSort();
        return PageRequest.of( 0, limit, sort );
    }

    private Sort getDefaulSort() {
        return Sort.by( Sort.Order.desc( ImageModel.FIELD_CREATED_AT ));
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
