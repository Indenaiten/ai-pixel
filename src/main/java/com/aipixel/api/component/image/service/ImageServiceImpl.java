package com.aipixel.api.component.image.service;

import com.aipixel.api.component.image.Image;
import com.aipixel.api.component.image.ImageRepository;
import com.aipixel.api.component.image.ImageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

// ------------------------------------------------------------------------------------------------------------------ \\
// ---| CONSTRUCTOR |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public ImageServiceImpl( final ImageRepository imageRepository ) {
        this.imageRepository = imageRepository;
    }


// ------------------------------------------------------------------------------------------------------------------ \\
// ---| IMPLEMENT METHODS |------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public List<Image> getImages() {
        return this.imageRepository.findAll();
    }

// ------------------------------------------------------------------------------------------------------------------ \\
}
