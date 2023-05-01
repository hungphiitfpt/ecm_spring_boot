package com.ecommer.spring.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.ecommer.spring.model.Image;
import com.ecommer.spring.model.Product;
import com.ecommer.spring.model.ResponseObject;
import com.ecommer.spring.repository.imageRepository;
import com.ecommer.spring.services.IStorageService;

@Controller
@RequestMapping(path = "/api/v1/FileUpload")
public class FileUploadController {
    //Inject Storage Service here
    @Autowired
    private IStorageService storageService;
    
    @Autowired
    imageRepository imageRepository;
    
    @PostMapping("")
    public ResponseEntity<ResponseObject> uploadFile(@RequestParam("files")MultipartFile file) {
        try {
            //save files to a folder => use a service
            String generatedFileName = storageService.storeFile(file);
            return ResponseEntity.status(HttpStatus.OK).body(
               new ResponseObject("ok", "upload file successfully", generatedFileName)
            );
            //06a290064eb94a02a58bfeef36002483.png => how to open this file in Web Browser ?
        }catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
               new ResponseObject("ok", exception.getMessage(), "")
            );
        }
    }
    
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFiles(@RequestParam("file") MultipartFile[] files) {
        System.out.println(files);
        List<String> fileNames = Arrays.stream(files).map(file -> {
        	return storageService.storeFile(file);
        	}).collect(Collectors.toList());
        return ResponseEntity.ok().body(fileNames);
    }
    
    //get image's url
    @GetMapping("/files/{fileName:.+}")
     // /files/06a290064eb94a02a58bfeef36002483.png
    public ResponseEntity<byte[]> readDetailFile(@PathVariable String fileName) {
        try {
            byte[] bytes = storageService.readFileContent(fileName);
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(bytes);
        }catch (Exception exception) {
            return ResponseEntity.noContent().build();
        }
    }
    //How to load all uploaded files ?
    @GetMapping("")
    public ResponseEntity<ResponseObject> getUploadedFiles() {
        try {
            List<String> urls = storageService.loadAll()
                    .map(path -> {
                        //convert fileName to url(send request "readDetailFile")
                        String urlPath = MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                                "readDetailFile", path.getFileName().toString()).build().toUri().toString();
                        return urlPath;
                    })
                    .collect(Collectors.toList());
            return ResponseEntity.ok(new ResponseObject("ok", "List files successfully", urls));
        }catch (Exception exception) {
            return ResponseEntity.ok(new
                    ResponseObject("failed", "List files failed", new String[] {}));
        }
    }
    
    @PostMapping("/add")
    public ResponseEntity<String> addImages(@RequestParam Product productId, @RequestParam List<String> urls) {
        List<Image> images = new ArrayList<>();
        for (String url : urls) {
        	Image image = new Image();
            image.setProduct(productId);
            image.setUrl(url);
            images.add(image);
        }
        imageRepository.saveAll(images);
        return ResponseEntity.ok("Added " + urls.size() + " images for product " + productId);
    }
}
