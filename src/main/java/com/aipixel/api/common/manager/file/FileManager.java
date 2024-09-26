package com.aipixel.api.common.manager.file;

import java.io.File;
import java.io.IOException;



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
}
