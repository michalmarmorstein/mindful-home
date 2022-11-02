package com.educare.mindfulhome.account.controller;

import com.educare.mindfulhome.account.controller.dto.FamilyDTO;
import com.educare.mindfulhome.account.model.FamilyEntity;
import com.educare.mindfulhome.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/bo/account")
@RequiredArgsConstructor
public class BOAccountController {

    private final AccountService service;
    private final ModelMapper modelMapper;

    @PostMapping("/family")
    public FamilyDTO createFamily(@Valid @RequestBody FamilyDTO family) {
        log.info("BOAccountController:createFamily() - req: " + family);
        FamilyEntity savedFamily = service.createFamily(modelMapper.map(family, FamilyEntity.class));
        log.info("BOAccountController:createFamily() - res: " + savedFamily);
        return modelMapper.map(savedFamily, FamilyDTO.class);
    }

}

