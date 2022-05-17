package com.educare.mindfulhome.controller.dto;


import com.educare.mindfulhome.model.MediaTypeEnum;
import com.educare.mindfulhome.model.ParticipantsEnum;
import com.educare.mindfulhome.model.TimeOfDayEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class FullActivityDTO extends BasicActivityDTO{

    @NotNull
    private UUID id;

    public FullActivityDTO(String name, String data, String imgUrl,
                           String description, boolean active, MediaTypeEnum mediaType, ParticipantsEnum participantsType,
                           String trainer, Integer durationInSeconds, Set<TimeOfDayEnum> recommendedTimeOfDay, UUID id) {
        super(name, data, imgUrl, description, active, mediaType, participantsType, trainer, durationInSeconds, recommendedTimeOfDay);
        this.id = id;
    }
}
