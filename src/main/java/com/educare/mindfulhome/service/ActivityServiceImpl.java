package com.educare.mindfulhome.service;

import com.educare.mindfulhome.model.ActivityEntity;
import com.educare.mindfulhome.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService{

    private final ActivityRepository repo;

    public ActivityEntity createActivity(@NotNull ActivityEntity activity){
        ActivityEntity savedActivity = repo.save(activity);
        repo.flush();
        return savedActivity;
    }


}
