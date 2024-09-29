package com.aipixel.api.component.image.service;

import com.aipixel.api.common.manager.file.FileManager;
import com.aipixel.api.common.properties.ImagesProperties;
import com.aipixel.api.component.category.Category;
import com.aipixel.api.component.category.CategoryRepository;
import com.aipixel.api.component.image.Image;
import com.aipixel.api.component.image.ImageRepository;
import com.aipixel.api.component.image.ImageService;
import com.aipixel.api.component.image.enumeration.ValidContentType;
import com.aipixel.api.component.image.exception.ImageNotFoundException;
import com.aipixel.api.component.image.service.request.SaveImageServiceRequest;
import com.aipixel.api.component.image.vo.ImageId;
import com.aipixel.api.component.tag.Tag;
import com.aipixel.api.component.tag.TagRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;


/**
 * Implementación del servicio {@link ImageService}.
 */
@Service
public class ImageServiceImpl implements ImageService {

    private final ImagesProperties imagesProperties;
    private final FileManager fileManager;
    private final ImageRepository imageRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Constructor al que se le inyectan las dependencias necesarias.
     *
     * @param imagesProperties Las propiedades de las imágenes.
     * @param fileManager El gestor de archivos.
     * @param imageRepository El repositorio de imágenes.
     * @param categoryRepository El repositorio de categorías.
     * @param tagRepository El repositorio de etiquetas.
     */
    @Autowired
    public ImageServiceImpl(
            final ImagesProperties imagesProperties,
            final FileManager fileManager,
            final ImageRepository imageRepository,
            final CategoryRepository categoryRepository,
            final TagRepository tagRepository
    ) {
        this.imagesProperties = imagesProperties;
        this.fileManager = fileManager;
        this.imageRepository = imageRepository;
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| IMPLEMENT METHODS |------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public List<Image> findAll( final ImageId lastId, Integer limit ) throws ImageNotFoundException {
        limit = Optional.ofNullable( limit ).orElse( this.imagesProperties.getDefaultLimit() );
        return this.imageRepository.findAll( lastId, limit );
    }


    @Override
    public Image findById( final ImageId id ) throws ImageNotFoundException {
        return this.imageRepository.findById( id ).orElseThrow( () -> new ImageNotFoundException(
                String.format( "No se ha encontrado ninguna imagen con el identificador \"%s\"", id.toString() )
        ));
    }


    @SneakyThrows
    @Override
    public ImageId saveImage( final SaveImageServiceRequest request ) {
        final ValidContentType contentType = request.getContentType();
        final List<Category> categories = this.categoryRepository.findAllById( request.getCategories().stream().toList());
        final List<Tag> tags = this.tagRepository.findAllById( request.getTags().stream().toList() );

        final Image.ImageBuilder imageCreation = Image.create( request.getName(), contentType )
                .favorite( request.isFavorite() )
                .categories( Set.copyOf( categories ))
                .tags( Set.copyOf( tags ));

        request.getDate().ifPresent( imageCreation::date );
        request.getDescription().ifPresent( imageCreation::description );
        request.getImageValoration().ifPresent( imageCreation::imageValoration );

        final Image image = imageCreation.build();
        this.imageRepository.save( image );
        this.fileManager.saveFile( image.getFileName().getNameValue(), contentType.getContentType(), request.getFileContent() );

        return image.getId();
    }

// ------------------------------------------------------------------------------------------------------------------ \\
}
