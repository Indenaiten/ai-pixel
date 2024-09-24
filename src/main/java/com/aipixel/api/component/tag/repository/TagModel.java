package com.aipixel.api.component.tag.repository;

import com.aipixel.api.common.entity.Entityable;
import com.aipixel.api.component.image.repository.ImageModel;
import com.aipixel.api.component.tag.Tag;
import com.aipixel.api.component.tag.vo.TagName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table( name = "tags" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagModel implements Entityable<Tag> {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id", updatable = false, nullable = false )
    private long id;

    @Column( name = "name", nullable = false, length = TagName.MAX_SIZE, unique = true )
    private String name;

    @ManyToMany( mappedBy = "tags", fetch = FetchType.LAZY )
    private Set<ImageModel> images;

    @Column( name = "created_at", nullable = false )
    private Timestamp createdAt;

    @Column( name = "updated_at", nullable = false )
    private Timestamp updatedAt;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public TagModel( final Tag tag ){
        this.id = tag.getId().value();
        this.name = tag.getName().value();
    }



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| OVERRIDE METHODS |------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public Tag toEntity() {
        return Tag.of(
                this.id,
                this.name,
                this.createdAt.toLocalDateTime(),
                this.updatedAt.toLocalDateTime()
        );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
