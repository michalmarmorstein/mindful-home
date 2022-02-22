package com.educare.mindfulhome.service;

import com.educare.mindfulhome.model.ActivityEntity;

import java.util.List;
import java.util.UUID;

public interface ActivityService {
    public ActivityEntity createActivity(ActivityEntity activity);
    public ActivityEntity getActivityById(UUID id);
    public List<ActivityEntity> getAllActivities(boolean activeOnly);
}
