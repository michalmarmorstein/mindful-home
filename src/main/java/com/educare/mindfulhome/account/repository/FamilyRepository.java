package com.educare.mindfulhome.account.repository;

import com.educare.mindfulhome.account.model.FamilyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyRepository extends JpaRepository<FamilyEntity, Long> {
}
