package com.aipixel.api.component.image.service;

import com.aipixel.api.component.image.Image;
import com.aipixel.api.component.image.ImageRepository;
import com.aipixel.api.component.image.ImageService;
import com.aipixel.api.component.image.exception.ImageNotFoundException;
import com.aipixel.api.component.image.vo.ImageId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



/**
 * Implementación del servicio {@link ImageService}.
 */
@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Constructor al que se le inyectan las dependencias necesarias.
     *
     * @param imageRepository El repositorio de imágenes.
     */
    @Autowired
    public ImageServiceImpl( final ImageRepository imageRepository ) {
        this.imageRepository = imageRepository;
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| IMPLEMENT METHODS |------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public List<Image> findAll() {
        return this.imageRepository.findAll();
    }


    @Override
    public Image findById( final ImageId id ) throws ImageNotFoundException {
        return this.imageRepository.findById( id ).orElseThrow( () -> new ImageNotFoundException(
                String.format( "No se ha encontrado ninguna imagen con el identificador \"%s\"", id.toString() )
        ));
    }

// ------------------------------------------------------------------------------------------------------------------ \\
}
