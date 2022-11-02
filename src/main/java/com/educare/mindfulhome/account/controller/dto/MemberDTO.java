package com.educare.mindfulhome.account.controller.dto;

import com.educare.mindfulhome.account.model.FamilyRoleEnum;
import lombok.*;

import javax.validation.constraints.NotBlank;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

    @NotBlank
    private String name;
    private String email;
    private FamilyRoleEnum role;
    private int yearOfBirth;
    private String imageUrl;
    private Long memberId;

}
