package com.sumit.knowledgeRepo.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sumit.knowledgeRepo.config.FileStorageProperties;
import com.sumit.knowledgeRepo.exception.FileStorageException;
import com.sumit.knowledgeRepo.exception.ResourceNotFoundException;
import com.sumit.knowledgeRepo.model.UploadFile;
import com.sumit.knowledgeRepo.pojo.UploadFilePojo;
import com.sumit.knowledgeRepo.repository.UploadFileRepository;
import com.sumit.knowledgeRepo.service.FileStorageService;

@Service
public class FileStorageServiceImpl implements FileStorageService{

	@Autowired
	UploadFileRepository uploadFileRepo;
	
	private final Path fileStorageLocation;

    @Autowired
    public FileStorageServiceImpl(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }
    
    @Override
	public UploadFilePojo uploadFile(MultipartFile file) {
    	String fileName = storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/downloadFile/")
                .path(fileName)
                .toUriString();
        
        UploadFilePojo uploadFilePojo = new UploadFilePojo(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
        UploadFile uploadFile = new UploadFile(uploadFilePojo);
        uploadFileRepo.save(uploadFile);
        
		return uploadFilePojo;
	}
    
    private String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    @Override
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new ResourceNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new ResourceNotFoundException("File not found " + fileName, ex);
        }
    }
    
}
