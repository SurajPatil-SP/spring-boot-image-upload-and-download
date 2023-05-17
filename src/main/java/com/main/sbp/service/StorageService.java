package com.main.sbp.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.main.sbp.entity.ImageData;
import com.main.sbp.repository.ImageStorageRepository;
import com.main.sbp.util.ImageUtils;

@Service
public class StorageService {

	@Autowired
	private ImageStorageRepository imageStorageRepository;

	public String uploadImage(MultipartFile file) throws IOException {

		ImageData imageData = imageStorageRepository.save(ImageData.builder()
				.name(file.getOriginalFilename())
				.type(file.getContentType())
				.imageData(ImageUtils.compressImage(file.getBytes()))
				.build());
		if (imageData != null) {
			return "file uploaded successfully : " + file.getOriginalFilename();
		}
		return null;
	}

	public byte[] downloadImage(String fileName) {
		Optional<ImageData> dbImageData = imageStorageRepository.findByName(fileName);
		if (dbImageData.isPresent()) {
			byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
			return images;
		}
		return null;
		
		
	}

}
