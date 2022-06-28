package com.org.dumper.repository;

import com.org.dumper.model.Property;
import com.org.dumper.model.PropertyImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface PropertyImagesRepository extends JpaRepository<PropertyImages, Long> {

    List<PropertyImages> findAllByProperty(Property property);
}
