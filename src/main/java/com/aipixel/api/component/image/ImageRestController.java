package com.aipixel.api.component.image;

import com.aipixel.api.common.controller.response.ApiResponse;
import com.aipixel.api.component.image.dto.ImageDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping( "/api/image" )
public interface ImageRestController {

    @GetMapping( "/find/all" )
    ApiResponse<List<ImageDto>> findAll();
}
