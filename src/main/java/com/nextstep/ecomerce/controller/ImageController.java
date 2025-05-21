package com.nextstep.ecomerce.controller;

import com.nextstep.ecomerce.model.Image;
import com.nextstep.ecomerce.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@RestController
public class ImageController {

    @Autowired
    CloudinaryService cloudinaryService;
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping(value = "/saveImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Image saveImage(
            @RequestPart("imageUrl") MultipartFile file,
            @RequestPart("imageType") String imageType
                          ) throws IOException {
        Image image= new Image();
        image.setImageType(imageType);
        cloudinaryService.saveImage(file,image);
        return image;

    }
}
