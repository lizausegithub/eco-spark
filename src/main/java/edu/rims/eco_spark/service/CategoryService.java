package edu.rims.eco_spark.service;

import java.io.FileOutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CategoryService {
    
    public String ImageUpload(MultipartFile file) throws Exception{

        String fileName = file.getOriginalFilename();
        String extName = fileName.substring(fileName.lastIndexOf("."));

        String newFileName = "upload_images/" + UUID.randomUUID() +extName;

        FileOutputStream fos = new FileOutputStream(newFileName);

        fos.write(file.getBytes());
        fos.close();
        return newFileName;
    }
}
