package com.educare.mindfulhome.service;

import com.educare.mindfulhome.model.ActivityEntity;
import com.educare.mindfulhome.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService{

    private final ActivityRepository repo;

    public ActivityEntity createActivity(@NotNull ActivityEntity activity){
        ActivityEntity savedActivity = repo.save(activity);
        repo.flush();
        return savedActivity;
    }

    @Override
    public ActivityEntity getActivityById(UUID id) {

        Optional<ActivityEntity> activityOptional = repo.findById(id);
        return activityOptional.orElseThrow(()-> new EntityNotFoundException("Activity Not Found"));
    }


}
