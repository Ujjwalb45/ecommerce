package com.nextstep.ecomerce.service;

import com.nextstep.ecomerce.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryService {
    public void saveImage(MultipartFile file, Image image) throws IOException;
}
