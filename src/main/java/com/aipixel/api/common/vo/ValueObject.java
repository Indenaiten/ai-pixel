package com.aipixel.api.common.vo;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.Supplier;



/**
 * Clase abstracta que representa un ValueObject.
 * Los objetos de valor son objetos que comparamos no por identidad, sino por valor. Los valores mismos son inmutables y
 * serializables, lo que hace que esta clase sea adecuada para su uso en dominios donde la igualdad de objetos se
 * determina por los datos del objeto, en lugar de su identidad en memoria.
 *
 * @param <T> El tipo de valor Serializable para el ValueObject.
 */
public abstract class ValueObject<T extends Serializable> implements Serializable{

// ------------------------------------------------------------------------------------------------------------------ \\
// ---| ABSTRACT METHODS |------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Crea y devuelve una copia profunda de este ValueObject.
     * El método de copia proporciona una forma de duplicar la instancia actual de ValueObject, asegurando que la nueva
     * instancia sea completamente independiente de la original. Esto es particularmente útil para objetos inmutables,
     * permitiendo la creación de una nueva instancia que coincida con el estado actual de este ValueObject pero que sea
     * en sí misma una entidad separada en memoria.
     *
     * @return Una nueva instancia de ValueObject con el mismo valor que este objeto.
     */
    public abstract ValueObject<T> copy();


    /**
     * Obtiene el valor de este ValueObject.
     * Las clases que implementan proporcionarán el valor específico almacenado dentro del ValueObject.
     *
     * @return El valor de este ValueObject.
     */
    public abstract T value();



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| LOCAL METHODS |---------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Compara este ValueObject con otro objeto para determinar si NO son iguales.
     * Dos ValueObjects son considerados diferentes si son de distinto tipo o si sus valores son diferentes.
     *
     * @param object El objeto con el que se comparará este ValueObject para determinar si son diferentes.
     *
     * @return {@code true} si el valor del objeto especificado no es igual al valor de este ValueObject; {@code false}
     * en caso contrario.
     */
    public boolean notEquals( final Object object ){
        return !this.equals( object );
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| OVERRIDE METHODS |------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Determina si otro objeto es igual a este ValueObject.
     * Dos ValueObjects se consideran iguales si son del mismo tipo y sus valores son iguales.
     *
     * @param object El objeto con el que se comparará este ValueObject para determinar si son iguales.
     *
     * @return {@code true} si el valor del objeto especificado es igual al valor de este ValueObject; {@code false} en
     * caso contrario.
     */
    @Override
    public boolean equals( final Object object ){
        if( this == object ) return true;
        if( object == null || getClass() != object.getClass() ) return false;
        final ValueObject<?> valueObject = (ValueObject<?>) object;
        return Objects.equals( this.value(), valueObject.value() );
    }


    /**
     * Calcula el código hash para este ValueObject basado en su valor.
     * Esto asegura que los ValueObjects con el mismo valor tendrán el mismo código hash.
     *
     * @return El código hash del valor de este ValueObject.
     */
    @Override
    public int hashCode(){
        return Objects.hash( this.value() );
    }


    /**
     * Devuelve una representación en cadena de este ValueObject, que es la representación en cadena de su valor.
     *
     * @return Una representación en cadena del valor de este ValueObject.
     */
    @Override
    public String toString(){
        return this.value().toString();
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ----| HELPERS |--------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Valida el resultado proporcionado por un {@link Supplier}.
     * Este método intenta recuperar el resultado del {@code Supplier} suministrado y captura cualquier
     * {@link IllegalArgumentException} lanzada durante su ejecución. El objetivo es determinar si la ejecución del
     * {@code Supplier} se completa con éxito sin lanzar una excepción de {@link IllegalArgumentException}, lo que se
     * considera una indicación de que el valor o condición suministrados son válidos.
     *
     * @param supplier El {@code Supplier} a validar. Se espera que este {@code Supplier} realice una operación que
     *                 pueda lanzar una {@link IllegalArgumentException} para indicar que el valor o la condición evaluados
     *                 no son válidos.
     *
     * @return {@code true} si la ejecución del {@code Supplier} no lanza una {@link IllegalArgumentException},
     *         indicando que el valor o la condición suministrados son válidos; {@code false} si se lanza una
     *         {@link IllegalArgumentException}, indicando que el valor o la condición suministrados no son válidos.
     */
    protected static boolean helperValidate( final Supplier<?> supplier ){
        boolean result = true;

        try{
            supplier.get();
        }
        catch ( final IllegalArgumentException e ){
            result = false;
        }

        return result;
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
