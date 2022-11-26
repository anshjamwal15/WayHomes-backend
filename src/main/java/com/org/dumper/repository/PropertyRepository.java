package com.org.dumper.repository;

import com.org.dumper.model.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface PropertyRepository extends JpaRepository<Property, Long>, JpaSpecificationExecutor<Property> {
//    Page<Property> findAll(Pageable pageable);
}
