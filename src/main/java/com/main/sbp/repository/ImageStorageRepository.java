package com.main.sbp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.sbp.entity.ImageData;

@Repository
public interface ImageStorageRepository extends JpaRepository<ImageData, Long> {

	Optional<ImageData> findByName(String fileName);

}