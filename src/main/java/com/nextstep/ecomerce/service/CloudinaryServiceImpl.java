package com.nextstep.ecomerce.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nextstep.ecomerce.model.Admin;
import com.nextstep.ecomerce.model.Image;
import com.nextstep.ecomerce.repository.AdminRepository;
import com.nextstep.ecomerce.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    Cloudinary cloudinary;
    @Autowired
    AdminRepository adminRepository;

    @Override
    public void saveImage(MultipartFile file,Image image)  throws IOException{

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        Admin admin=adminRepository.findByUsername(authentication.getName());

        Map cloudinaryImage=cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String imageUrl=cloudinaryImage.get("url").toString();
        String publicId=cloudinaryImage.get("public_id").toString();
        image.setImageUrl(imageUrl);
        image.setPublicId(publicId);

        image.setAdmin(admin);
        admin.getImages().add(image);

       adminRepository.save(admin);


    }
}
