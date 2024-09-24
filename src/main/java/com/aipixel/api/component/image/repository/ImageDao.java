package com.aipixel.api.component.image.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageDao extends JpaRepository<ImageModel, String> {

}
