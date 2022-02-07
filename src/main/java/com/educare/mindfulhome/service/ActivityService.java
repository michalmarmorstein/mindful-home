package com.educare.mindfulhome.service;

import com.educare.mindfulhome.controller.payload.CreateActivityRequest;
import com.educare.mindfulhome.model.ActivityEntity;
import com.educare.mindfulhome.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository repo;

    public Long createActivity(CreateActivityRequest activity){
        //TODO find elegant way to handle this
        ActivityEntity entity = new ActivityEntity(activity.getName(), activity.getLink());
        entity = repo.save(entity);
        repo.flush();
        return entity.getId();
    }


}
