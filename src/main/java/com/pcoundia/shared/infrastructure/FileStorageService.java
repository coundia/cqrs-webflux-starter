package com.pcoundia.shared.infrastructure;

import org.springframework.web.multipart.MultipartFile;


public interface FileStorageService {

String storeFile(MultipartFile file);
}
