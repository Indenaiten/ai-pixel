package com.aipixel.api.common.entity;



/**
 * Interfaz que representa un objeto que puede ser convertido en una entidad de la aplicación.
 *
 * @param <T> Tipo de la entidad a la que se puede convertir.
 */
public interface Entityable<T>{

    /**
     * Convierte el objeto en una entidad de la aplicación.
     *
     * @return La entidad correspondiente.
     */
    T toEntity();

}
