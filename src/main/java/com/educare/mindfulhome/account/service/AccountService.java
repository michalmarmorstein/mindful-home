package com.educare.mindfulhome.account.service;

import com.educare.mindfulhome.account.model.FamilyEntity;
import com.educare.mindfulhome.activities.model.ActivityEntity;

import java.util.List;

public interface AccountService {

    public FamilyEntity createFamily(FamilyEntity family);

    public List<FamilyEntity> getAllFamilies();

}
