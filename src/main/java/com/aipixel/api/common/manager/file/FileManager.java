package com.aipixel.api.common.manager.file;

import java.io.File;
import java.io.IOException;
import java.util.Optional;


/**
 * Clase que proporciona operaciones para la gestión de archivos.
 */
public interface FileManager {

    /**
     * Guarda un archivo en el sistema de archivos.
     *
     * @param fileName   El nombre del archivo.
     * @param contentTpe El tipo de contenido del archivo.
     * @param content    El contenido del archivo.
     *
     * @return Un {@link File} con el archivo guardado.
     *
     * @throws IOException Si ocurre un error al guardar el archivo.
     */
    File saveFile( String fileName, String contentTpe, byte[] content ) throws IOException;


    /**
     *
     * Obtiene la extensión de un archivo a partir de su nombre si dispone de ella.
     *
     * @param fileName El nombre del archivo.
     *
     * @return La extensión del archivo si dispone de ella en un {@link Optional}.
     */
    Optional<String> getExtension( String fileName );


    /**
     * Obtiene la extensión de un archivo a partir de un objeto {@link File} si dispone de ella.
     *
     * @param file El objeto {@link File} del archivo que se quiere obtener la extensión.
     *
     * @return La extensión del archivo si dispone de ella en un {@link Optional}.
     */
    Optional<String> getExtension( File file );
}
