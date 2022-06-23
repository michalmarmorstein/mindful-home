package com.educare.mindfulhome.activities.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Setter
@Getter
public class ActivityListDTO {
    private List<FullActivityDTO> activities;

    public ActivityListDTO() {
        activities = new ArrayList<>();
    }
}
