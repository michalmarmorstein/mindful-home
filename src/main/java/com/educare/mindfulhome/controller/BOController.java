package com.educare.mindfulhome.controller;

import com.educare.mindfulhome.controller.dto.ActivityListDTO;
import com.educare.mindfulhome.controller.dto.BasicActivityDTO;
import com.educare.mindfulhome.controller.dto.FullActivityDTO;
import com.educare.mindfulhome.model.ActivityEntity;
import com.educare.mindfulhome.model.MediaTypeEnum;
import com.educare.mindfulhome.model.ParticipantsEnum;
import com.educare.mindfulhome.model.TimeOfDayEnum;
import com.educare.mindfulhome.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bo")
@RequiredArgsConstructor
public class BOController {

    private final ActivityService activityService;
    private final ModelMapper modelMapper;

    //TODO test happy scenario
    //TODO test validation exception
    //TODO test bad mapping (should be unit test?)
    //TODO test empty ActivityEntity - default values
    @PostMapping("/activities")
    public FullActivityDTO createActivity(@Valid @RequestBody BasicActivityDTO activityDTO) {
        ActivityEntity savedActivity = activityService.createActivity(modelMapper.map(activityDTO, ActivityEntity.class));
        return modelMapper.map(savedActivity, FullActivityDTO.class);
    }

    //TODO delete this
    @GetMapping("/test")
    public BasicActivityDTO hi() {
        BasicActivityDTO dto = new BasicActivityDTO("activity1", "http://blabla", "description",
                false, MediaTypeEnum.TEXT, ParticipantsEnum.ENTIRE_FAMILY, "Danni", 52,
                EnumSet.allOf(TimeOfDayEnum.class));
        return dto;
    }

    //TODO test IllegalArgumentException - invalid UUID format
    //TODO test happy scenario
    @GetMapping("/activities/{id}")
    public FullActivityDTO getActivityById(@PathVariable String id) {
        ActivityEntity activity = activityService.getActivityById(UUID.fromString(id));
        return modelMapper.map(activity, FullActivityDTO.class);
    }

    //TODO test happy scenario active + all
    //TODO test empty list or null value NOTFOUND status code
    //TODO test failed mapping
    //TODO no requestparam - default false
    //TODO invalid requestparam key
    //TODO invalid requestparam value
    @GetMapping("/activities")
    public ResponseEntity<ActivityListDTO> listActivities(@RequestParam(defaultValue = "false") boolean activeOnly){
        List<ActivityEntity> activities = activityService.getAllActivities(activeOnly);
        if(activities == null || activities.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<FullActivityDTO> activitiesDTO = activities.stream()
                .map(entity -> modelMapper.map(entity, FullActivityDTO.class))
                .collect(Collectors.toList());
        ActivityListDTO activityList = new ActivityListDTO(activitiesDTO);
        return new ResponseEntity<>(activityList, HttpStatus.OK);
    }

    //TODO test happy scenario
    //TODO Test dto with no id
    //TODO Test dto with invalid id
    //TODO Test dto with not existing id - NOT found response
    @PutMapping("/activities")
    public FullActivityDTO updateActivity(@Valid @RequestBody FullActivityDTO activityDTO) {
        ActivityEntity updatedActivity = activityService.updateActivity(modelMapper.map(activityDTO, ActivityEntity.class));
        return modelMapper.map(updatedActivity, FullActivityDTO.class);
    }

}
