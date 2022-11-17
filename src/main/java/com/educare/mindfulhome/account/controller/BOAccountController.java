package com.educare.mindfulhome.account.controller;

import com.educare.mindfulhome.account.controller.dto.FamilyDTO;
import com.educare.mindfulhome.account.controller.dto.FamilyListDTO;
import com.educare.mindfulhome.account.model.FamilyEntity;
import com.educare.mindfulhome.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/families")
    public ResponseEntity<FamilyListDTO> listFamilies(){
        log.info("BOAccountController:listFamilies() - req");
        List<FamilyEntity> families = service.getAllFamilies();
        if(families == null || families.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<FamilyDTO> familiesDTO = families.stream()
                .map(entity -> modelMapper.map(entity, FamilyDTO.class))
                .collect(Collectors.toList());
        FamilyListDTO familyList = new FamilyListDTO(familiesDTO);
        log.info("BOAccountController:listFamilies() - res: " + Arrays.toString(familyList.getFamilies().toArray()));
        return new ResponseEntity<>(familyList, HttpStatus.OK);
    }

}

