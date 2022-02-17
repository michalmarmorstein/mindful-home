package com.educare.mindfulhome.service;

import com.educare.mindfulhome.model.ActivityEntity;

import java.util.UUID;

public interface ActivityService {
    public ActivityEntity createActivity(ActivityEntity activity);
    public ActivityEntity getActivityById(UUID id);
}
