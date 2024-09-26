package com.aipixel.api.common.manager.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



/**
 * Implementación de la clase {@link FileManager}.
 */
@Component
public class FileManagerImpl implements FileManager{

    private final String directory;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public FileManagerImpl(
            @Value( "${manager.file.directory}" ) final String directory
    ) {
        this.directory = directory;
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| IMPLEMENTED METHODS |---------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public File saveFile( final String fileName, final String contentTpe, final byte[] fileContent ) throws IOException {
        final Path path = Paths.get( this.directory, fileName );
        Files.createDirectories( path.getParent() );
        Files.write( path, fileContent );
        return path.toAbsolutePath().toFile();
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
