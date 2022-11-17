package com.educare.mindfulhome.account.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Setter
@Getter

public class FamilyListDTO {

    private List<FamilyDTO> families;

    public FamilyListDTO() {
        families = new ArrayList<>();
    }
}
