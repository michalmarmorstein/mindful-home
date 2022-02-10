package com.educare.mindfulhome.controllers;

import com.educare.mindfulhome.controllers.payload.CreateActivityRequest;
import com.educare.mindfulhome.services.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/bo")
@RequiredArgsConstructor
public class BOController {

    private final ActivityService activityService;

    @PostMapping("/activity")
    public String createActivity(@Valid @RequestBody CreateActivityRequest payload){

        Long id = activityService.createActivity(payload);
        return id.toString();
    }
}
