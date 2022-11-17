package com.educare.mindfulhome.account.service;

import com.educare.mindfulhome.account.model.FamilyEntity;
import com.educare.mindfulhome.account.repository.FamilyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final FamilyRepository repo;

    @Override
    public FamilyEntity createFamily(FamilyEntity family) {
        if(family == null){
            log.error("Family must not be null");
            throw new NullPointerException("Family must not be null");
        }
        family.linkMembers();
        FamilyEntity savedFamily = repo.save(family);
        repo.flush();
        return savedFamily;
    }

    @Override
    public List<FamilyEntity> getAllFamilies() {
        List<FamilyEntity> families = repo.findAll();
        return families;
    }

}

