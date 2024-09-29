package com.aipixel.api.common.manager.file;

import com.aipixel.api.common.properties.ImagesProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;


/**
 * Implementación de la clase {@link FileManager}.
 */
@Component
public class FileManagerImpl implements FileManager{

    private final ImagesProperties imagesProperties;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Constructor al que se le inyectan las dependencias necesarias.
     *
     * @param imagesProperties Las propiedades de las imágenes.
     */
    @Autowired
    public FileManagerImpl(
            final ImagesProperties imagesProperties
    ) {
        this.imagesProperties = imagesProperties;
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| IMPLEMENTED METHODS |---------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public File saveFile( final String fileName, final String contentTpe, final byte[] fileContent ) throws IOException {
        final Path path = Paths.get( this.imagesProperties.getImagesDirectory(), fileName );
        Files.createDirectories( path.getParent() );
        Files.write( path, fileContent );
        return path.toAbsolutePath().toFile();
    }


    @Override
    public Optional<String> getExtension( final String fileName ) {
        Optional<String> result = Optional.empty();

        final int index = fileName.lastIndexOf( '.' );
        if (index > 0 && index < fileName.length() - 1) {
            result = Optional.of( fileName.substring( index + 1 ));
        }

        return result;
    }


    @Override
    public Optional<String> getExtension( final File file ) {
        return this.getExtension( file.getName() );
    }


    // ------------------------------------------------------------------------------------------------------------------ \\

}
