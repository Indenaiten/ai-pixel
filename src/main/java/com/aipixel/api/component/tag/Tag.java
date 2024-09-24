package com.aipixel.api.component.tag;

import com.aipixel.api.common.entity.Entity;
import com.aipixel.api.component.tag.vo.TagId;
import com.aipixel.api.component.tag.vo.TagName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
public class Tag implements Entity {

    /**
     * A unique version identifier for the Serializable class. This ID is used during the deserialization process to
     * verify that the sender and receiver of a serialized object maintain compatible versions of the class, ensuring
     * that the object can be correctly deserialized.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    private final TagId id;
    private TagName name;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| BUILDER METHODS |-------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public static Tag of(
            final long id,
            final String name,
            final LocalDateTime createdAt,
            final LocalDateTime updatedAt
    ){
        return new Tag(
                TagId.of( id ),
                TagName.of( name ),
                createdAt,
                updatedAt
        );
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| OVERRIDE METHODS |------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public boolean equals( final Object o ) {
        if( this == o ) return true;
        if( o == null || getClass() != o.getClass() ) return false;
        final Tag tag = (Tag) o;
        return Objects.equals( this.id, tag.id );
    }

    @Override
    public int hashCode() {
        return Objects.hash( this.id );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
