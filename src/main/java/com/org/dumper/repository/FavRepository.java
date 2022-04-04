package com.org.dumper.repository;

import com.org.dumper.model.FavProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface FavRepository extends JpaRepository<FavProperties, Long> {

    Optional<FavProperties> findByUsersIdAndPropertyId(Long userId, Long propertyId);

    List<FavProperties> findAllByUsersId(Long userId);
}
