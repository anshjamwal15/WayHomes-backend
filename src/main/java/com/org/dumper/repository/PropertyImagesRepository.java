package com.org.dumper.repository;

import com.org.dumper.model.PropertyImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface PropertyImagesRepository extends JpaRepository<PropertyImages, Long> {
}
