package com.aipixel.api.component.image.repository;

import com.aipixel.api.component.image.Image;
import com.aipixel.api.component.image.ImageMapper;
import com.aipixel.api.component.image.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;



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
    public List<Image> findAll() {
        return this.imageDao.findAll()
                .stream().map(ImageMapper::modelToEntity)
                .collect(Collectors.toList());
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
