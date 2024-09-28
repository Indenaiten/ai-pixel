package com.aipixel.api.component.image.service;

import com.aipixel.api.common.manager.file.FileManager;
import com.aipixel.api.component.category.Category;
import com.aipixel.api.component.category.CategoryRepository;
import com.aipixel.api.component.image.Image;
import com.aipixel.api.component.image.ImageRepository;
import com.aipixel.api.component.image.ImageService;
import com.aipixel.api.component.image.exception.ImageNotFoundException;
import com.aipixel.api.component.image.service.request.SaveImageServiceRequest;
import com.aipixel.api.component.image.vo.ImageFileName;
import com.aipixel.api.component.image.vo.ImageId;
import com.aipixel.api.component.tag.Tag;
import com.aipixel.api.component.tag.TagRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Implementación del servicio {@link ImageService}.
 */
@Service
public class ImageServiceImpl implements ImageService {

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
     * @param fileManager El gestor de archivos.
     * @param imageRepository El repositorio de imágenes.
     * @param categoryRepository El repositorio de categorías.
     * @param tagRepository El repositorio de etiquetas.
     */
    @Autowired
    public ImageServiceImpl(
            final FileManager fileManager,
            final ImageRepository imageRepository,
            final CategoryRepository categoryRepository,
            final TagRepository tagRepository
    ) {
        this.fileManager = fileManager;
        this.imageRepository = imageRepository;
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
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


    @SneakyThrows
    @Override
    public ImageId saveImage( final SaveImageServiceRequest request ) {
        final ImageId id = ImageId.random();
        final List<Category> categories = this.categoryRepository.findAllById( request.getCategories().stream().toList());
        final List<Tag> tags = this.tagRepository.findAllById( request.getTags().stream().toList() );

        final String fileName = request.getFileName();
        final byte[] fileContent = request.getFileContent();
        final String fileContentType = request.getFileContentType();

        final String[] fileNameParts = fileName.split( "\\." );
        final String extensionFile = fileNameParts[ fileNameParts.length -1 ];
        final String newFileName = String.format( "%s.%s", id, extensionFile );

        final File file = this.fileManager.saveFile( newFileName, fileContentType, fileContent );

        final Image.ImageBuilder imageBuilder = Image.builder( id, request.getName(), ImageFileName.of( file ))
                .favorite( request.isFavorite() )
                .categories( Set.copyOf( categories ))
                .tags( Set.copyOf( tags ));
        request.getDate().ifPresent( imageBuilder::date );
        request.getDescription().ifPresent( imageBuilder::description );
        request.getImageValoration().ifPresent( imageBuilder::imageValoration );

        this.imageRepository.save( imageBuilder.build() );

        return id;
    }

// ------------------------------------------------------------------------------------------------------------------ \\
}
