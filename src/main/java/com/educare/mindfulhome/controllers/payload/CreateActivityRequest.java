package com.educare.mindfulhome.controllers.payload;


import lombok.AllArgsConstructor;
import lombok.Getter;
import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class CreateActivityRequest {

    @NotBlank(message = "Activity name can't be blank")
    private String name;
    private String link;

}
