package com.educare.mindfulhome.repository;

import com.educare.mindfulhome.model.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ActivityRepository extends JpaRepository<ActivityEntity, UUID> {

    List<ActivityEntity> findByActiveTrue();


}
