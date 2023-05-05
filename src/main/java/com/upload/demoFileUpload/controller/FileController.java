

package com.upload.demoFileUpload.controller;

import com.upload.demoFileUpload.playload.FileResponce;
import com.upload.demoFileUpload.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

@PostMapping("/upload")
    public ResponseEntity<FileResponce> fileUpload(@RequestParam("image")MultipartFile image ){
    String fileName = null;
    try {
        fileName = this.fileService.uploadImage(path, image);
    } catch (IOException e) {

        return new ResponseEntity<>(new FileResponce(null,"Image is not uploaded due to some error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<>(new FileResponce(fileName,"Image is successfully upload"), HttpStatus.OK);
}
}
