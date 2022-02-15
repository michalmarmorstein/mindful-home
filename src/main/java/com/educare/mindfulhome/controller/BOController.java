package com.educare.mindfulhome.controller;

import com.educare.mindfulhome.controller.dto.BasicActivityDTO;
import com.educare.mindfulhome.controller.dto.FullActivityDTO;
import com.educare.mindfulhome.model.ActivityEntity;
import com.educare.mindfulhome.model.MediaTypeEnum;
import com.educare.mindfulhome.model.ParticipantsEnum;
import com.educare.mindfulhome.model.TimeOfDayEnum;
import com.educare.mindfulhome.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/bo")
@RequiredArgsConstructor
public class BOController {

    private final ActivityService activityService;
    private final ModelMapper modelMapper;

    @PostMapping("/activity")
    public FullActivityDTO createActivity(@Valid @RequestBody BasicActivityDTO activityDTO) {
        ActivityEntity savedActivity = activityService.createActivity(modelMapper.map(activityDTO, ActivityEntity.class));
        return modelMapper.map(savedActivity, FullActivityDTO.class);
    }

    @GetMapping("/activity")
    public BasicActivityDTO hi() {
        Set<TimeOfDayEnum> set = new HashSet<>();
        set.add(TimeOfDayEnum.MORNING);
        set.add(TimeOfDayEnum.NOON);
        BasicActivityDTO dto = new BasicActivityDTO("activity1", "http://blabla", MediaTypeEnum.AUDIO,
                ParticipantsEnum.ADULTS, "Danni", 52, set);
        return dto;
    }

}
