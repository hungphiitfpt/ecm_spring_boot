package com.ecommer.spring.services;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface IStorageService {
    public String storeFile(MultipartFile file);
    public Stream<Path> loadAll(); //load all file inside a folder
    public byte[] readFileContent(String fileName);
    public void deleteAllFiles();
}
