package com.educare.mindfulhome.controller.dto;

import com.educare.mindfulhome.model.MediaTypeEnum;
import com.educare.mindfulhome.model.ParticipantsEnum;
import com.educare.mindfulhome.model.TimeOfDayEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasicActivityDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String data;
    private String description;
    private boolean active;
    private MediaTypeEnum mediaType;
    private ParticipantsEnum participantsType;
    private String trainer;
    @Min(value = 0)
    private Integer durationInSeconds;
    @NotEmpty
    private Set<TimeOfDayEnum> recommendedTimeOfDay;
}
