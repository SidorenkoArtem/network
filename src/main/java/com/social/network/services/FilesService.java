package com.social.network.services;

import com.social.network.model.dao.UploadFile;
import com.social.network.repositories.FilesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class FilesService {

    private final FilesRepository filesRepository;

    private static final String FILE_PATH = new File("src\\main\\webapp\\images\\").getAbsolutePath().replace("\\", "//");
    private static final String DELETE_PATH = new File("src" + '/' + "main" + '/' + "webapp").getAbsolutePath();
    private static final String SYMBOL = "//";

    public UploadFile uploadFile(final MultipartFile file) {
        if (file.isEmpty()) {
            return null;
        }
        final UploadFile newUploadFile = new UploadFile();
        try {
            byte[] bytes = file.getBytes();
            filesRepository.save(newUploadFile);
            Path path = Paths.get(FILE_PATH + SYMBOL + newUploadFile.getId() + SYMBOL + file.getOriginalFilename());
            Files.createDirectories(path.getParent());
            Files.write(path, bytes);
            newUploadFile.setPath(path.toString().replace(DELETE_PATH, ""));
            filesRepository.save(newUploadFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newUploadFile;
    }
}
