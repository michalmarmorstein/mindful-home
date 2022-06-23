package com.educare.mindfulhome.activities.service;

import com.educare.mindfulhome.activities.model.ActivityEntity;
import com.educare.mindfulhome.activities.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService{

    private final ActivityRepository repo;
    //TODO should we save activities in memory for better performance (cache) - more reads than writes

    public ActivityEntity createActivity(ActivityEntity activity){
        if(activity == null){
            log.error("Activity must not be null");
            throw new NullPointerException("Activity must not be null");
        }
        ActivityEntity savedActivity = repo.save(activity);
        repo.flush();
        return savedActivity;
    }

    @Override
    public ActivityEntity getActivityById(UUID id) {
        if(id == null){
            throw new IllegalArgumentException("The given id must not be null");
        }
        Optional<ActivityEntity> activityOptional = repo.findById(id);
        return activityOptional.orElseThrow(()-> new EntityNotFoundException("Activity Not Found"));
    }

    @Override
    public List<ActivityEntity> getAllActivities(boolean activeOnly) {
        List<ActivityEntity> activities;
        if(activeOnly){
            activities = repo.findByActiveTrue();
        }else{
            activities = repo.findAll();
        }
        return activities;
    }

    @Override
    public ActivityEntity updateActivity(ActivityEntity activity) {
        if(activity == null){
            log.error("Activity must not be null");
            throw new NullPointerException("Activity must not be null");
        }
        //Check the activity exists
        getActivityById(activity.getId());
        ActivityEntity savedActivity = repo.save(activity);
        repo.flush();
        return savedActivity;
    }


}
