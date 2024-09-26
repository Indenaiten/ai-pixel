package com.aipixel.api.component.image.repository;

import com.aipixel.api.component.category.repository.CategoryModel;
import com.aipixel.api.component.image.Image;
import com.aipixel.api.component.image.vo.ImageDescription;
import com.aipixel.api.component.image.vo.ImageName;
import com.aipixel.api.component.image.vo.ImageValoration;
import com.aipixel.api.component.tag.repository.TagModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


/**
 * Clase que representa una imagen en la Base de Datos.
 */
@Entity
@Table( name = "images" )
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageModel {

    @Id
    @Column( name = "id", updatable = false, nullable = false )
    private String id;

    @Column( name = "favorite", nullable = false )
    private Boolean favorite;

    @Column( name = "date" )
    private Date date;

    @Column( name = "name", nullable = false, length = ImageName.MAX_SIZE )
    private String name;

    @Column( name = "file_name", nullable = false )
    private String fileName;

    @Column( name = "description", length = ImageDescription.MAX_SIZE )
    private String description;

    @Column( name = "valoration", length = ImageValoration.MAX_SIZE )
    private Short valoration;

    @ManyToMany( fetch = FetchType.LAZY )
    @JoinTable(
            name = "images_categories",
            joinColumns = @JoinColumn( name = "image_id" ),
            inverseJoinColumns = @JoinColumn( name = "category_id" )
    )
    private List<CategoryModel> categories;

    @ManyToMany( fetch = FetchType.LAZY )
    @JoinTable(
            name = "images_tags",
            joinColumns = @JoinColumn( name = "image_id" ),
            inverseJoinColumns = @JoinColumn( name = "tag_id" )
    )
    private List<TagModel> tags;

    @CreationTimestamp
    @Column( name = "created_at", nullable = false, updatable = false )
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column( name = "updated_at", nullable = false )
    private Timestamp updatedAt;



// ------------------------------------------------------------------------------------------------------------------ \\
// ---| GETTERS |---------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public Optional<String> getId(){
        return Optional.ofNullable( this.id );
    }

    public Boolean isFavorite(){
        return this.favorite != null && this.favorite;
    }

    public Optional<Date> getDate(){
        return Optional.ofNullable( this.date );
    }

    public Optional<String> getName(){
        return Optional.ofNullable( this.name );
    }

    public Optional<String> getFileName(){
        return Optional.ofNullable( this.fileName );
    }

    public Optional<String> getDescription(){
        return Optional.ofNullable( this.description );
    }

    public Optional<Short> getValoration(){
        return Optional.ofNullable( this.valoration );
    }

    public List<CategoryModel> getCategories(){
        return this.categories != null ? List.copyOf( this.categories ) : List.of();
    }

    public List<TagModel> getTags(){
        return this.tags != null ? List.copyOf( this.tags ) : List.of();
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
