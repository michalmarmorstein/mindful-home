package com.educare.mindfulhome.repositories;

import com.educare.mindfulhome.model.TimeOfDayEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeOfDayRepository extends JpaRepository<TimeOfDayEntity, Long> {
}
