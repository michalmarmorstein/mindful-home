package com.educare.mindfulhome.controller;

import com.educare.mindfulhome.controller.payload.CreateActivityRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/bo")
public class BOController {

    @PostMapping("/activity")
    public String createActivity(@Valid @RequestBody CreateActivityRequest payload){
        return payload.getName();

    }
}
