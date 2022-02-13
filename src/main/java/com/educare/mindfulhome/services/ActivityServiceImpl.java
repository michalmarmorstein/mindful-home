package com.educare.mindfulhome.services;

import com.educare.mindfulhome.model.ActivityEntity;
import com.educare.mindfulhome.repositories.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService{

    private final ActivityRepository repo;

    public ActivityEntity createActivity(ActivityEntity activity){
        ActivityEntity savedActivity = repo.save(activity);
        repo.flush();
        return savedActivity;
    }


}
