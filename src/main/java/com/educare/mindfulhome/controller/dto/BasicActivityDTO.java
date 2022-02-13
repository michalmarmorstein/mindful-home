package com.educare.mindfulhome.controller.dto;

import com.educare.mindfulhome.model.MediaTypeEnum;
import com.educare.mindfulhome.model.ParticipantsEnum;
import com.educare.mindfulhome.model.TimeOfDayEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasicActivityDTO {
    private String name;
    private String data;
    private MediaTypeEnum mediaType;
    private ParticipantsEnum participantsType;
    private String trainer;
    private Integer durationInSeconds;
    private Set<TimeOfDayEnum> recommendedTimeOfDay;
}
