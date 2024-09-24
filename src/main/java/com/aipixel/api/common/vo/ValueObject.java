package com.aipixel.api.common.vo;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.Supplier;



/**
 * Abstract class representing a ValueObject. Value objects are objects that we compare not by identity, but by value.
 * The values themselves are immutable and serializable, making this class suitable for use in domains where object
 * equality is determined by the object's data, rather than its identity in memory.
 *
 * @param <T> The type of the value for the ValueObject must implement Serializable, ensuring it can be serialized.
 */
public abstract class ValueObject<T extends Serializable> implements Serializable{

// ------------------------------------------------------------------------------------------------------------------ \\
// ---| ABSTRACT METHODS |------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Creates and returns a deep copy of this ValueObject. The copy method provides a way to duplicate the current
     * ValueObject instance, ensuring that the new instance is completely independent of the original. This is
     * particularly useful for immutable objects, allowing the creation of a new instance that matches the current state
     * of this ValueObject but is itself a separate entity in memory.
     *
     * @return A new instance of ValueObject that is a deep copy of this ValueObject. The returned object will have the
     *         same value as this object but will be a completely independent copy.
     */
    public abstract ValueObject<T> copy();


    /**
     * Retrieves the value of this ValueObject. Implementing classes will provide the specific value stored within the
     * ValueObject.
     *
     * @return The value of this ValueObject.
     */
    public abstract T value();



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| LOCAL METHODS |---------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Compares this ValueObject with another object for inequality. It is the logical negation of the equals method.
     *
     * @param object The object to compare with this ValueObject.
     *
     * @return True if value of this ValueObject does not equal the other object value; false otherwise.
     */
    public boolean notEquals( final Object object ){
        return !this.equals( object );
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| OVERRIDE METHODS |------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Determines whether another object is equal to this ValueObject. Two ValueObjects are considered equal if they are
     * of the same type and their values are equal.
     *
     * @param object The object to compare with this ValueObject for equality.
     *
     * @return True if value of the specified object is equal to value of this ValueObject; false otherwise.
     */
    @Override
    public boolean equals( final Object object ){
        if( this == object ) return true;
        if( object == null || getClass() != object.getClass() ) return false;
        final ValueObject<?> valueObject = (ValueObject<?>) object;
        return Objects.equals( this.value(), valueObject.value() );
    }


    /**
     * Calculates the hash code for this ValueObject based on its value. This ensures that alueObjects with the same
     * value will have the same hash code.
     *
     * @return The hash code of value of this ValueObject.
     */
    @Override
    public int hashCode(){
        return Objects.hash( this.value() );
    }


    /**
     * Returns a string representation of this ValueObject, which is the string representation of its value.
     *
     * @return A string representation of value of this ValueObject.
     */
    @Override
    public String toString(){
        return this.value().toString();
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ----| HELPERS |--------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Validates the result provided by a {@link Supplier}. This method attempts to retrieve the result from the
     * supplied {@code Supplier} and catches any {@link IllegalArgumentException} thrown during its execution.
     * The intent is to determine whether the execution of the {@code Supplier} completes successfully without throwing
     * an illegal argument exception, which is considered an indication that the supplied value or condition is valid.
     *
     * @param supplier The {@code Supplier} to be validated. This {@code Supplier} is expected to perform an operation
     *                 that may throw an {@link IllegalArgumentException} if the evaluated value or condition is not
     *                 valid.
     *
     * @return {@code true} if the execution of the {@code Supplier} does not throw an {@link IllegalArgumentException},
     *         indicating that the supplied value or condition is valid; {@code false} if an
     *         {@link IllegalArgumentException} is thrown, indicating that the supplied value or condition is not valid.
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
