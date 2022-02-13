package com.educare.mindfulhome.controller.dto;


import com.educare.mindfulhome.model.MediaTypeEnum;
import com.educare.mindfulhome.model.ParticipantsEnum;
import com.educare.mindfulhome.model.TimeOfDayEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class FullActivityDTO extends BasicActivityDTO{

    private UUID id;

    public FullActivityDTO(String name, String data, MediaTypeEnum mediaType, ParticipantsEnum participantsType,
                           String trainer, Integer durationInSeconds, Set<TimeOfDayEnum> recommendedTimeOfDay, UUID id) {
        super(name, data, mediaType, participantsType, trainer, durationInSeconds, recommendedTimeOfDay);
        this.id = id;
    }
}
