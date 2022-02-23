package com.educare.mindfulhome.service;

import com.educare.mindfulhome.model.ActivityEntity;
import com.educare.mindfulhome.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService{

    private final ActivityRepository repo;

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

    //TODO test null activity
    //TODO Test dto with no id
    //TODO Test dto with invalid id
    //TODO Test dto with not existing id - EntityNotFoundException

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
