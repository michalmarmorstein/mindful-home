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
import java.util.EnumSet;
import java.util.UUID;

@RestController
@RequestMapping("/bo")
@RequiredArgsConstructor
public class BOController {

    private final ActivityService activityService;
    private final ModelMapper modelMapper;

    //TODO test happy scenario
    //TODO test validation exception
    //TODO test bad mapping (should be unit test?)
    @PostMapping("/activity")
    public FullActivityDTO createActivity(@Valid @RequestBody BasicActivityDTO activityDTO) {
        ActivityEntity savedActivity = activityService.createActivity(modelMapper.map(activityDTO, ActivityEntity.class));
        return modelMapper.map(savedActivity, FullActivityDTO.class);
    }

    @GetMapping("/activity")
    public BasicActivityDTO hi() {
        BasicActivityDTO dto = new BasicActivityDTO("activity1", "http://blabla", "description",
                false, MediaTypeEnum.TEXT, ParticipantsEnum.ENTIRE_FAMILY, "Danni", 52,
                EnumSet.allOf(TimeOfDayEnum.class));
        return dto;
    }

    //TODO test IllegalArgumentException - invalid UUID format
    //TODO test happy scenario
    @GetMapping("/activity/{id}")
    public FullActivityDTO getActivityById(@PathVariable String id) {
        ActivityEntity activity = activityService.getActivityById(UUID.fromString(id));
        return modelMapper.map(activity, FullActivityDTO.class);
    }

}
