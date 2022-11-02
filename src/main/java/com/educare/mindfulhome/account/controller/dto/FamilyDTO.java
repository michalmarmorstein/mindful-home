package com.educare.mindfulhome.account.controller.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FamilyDTO {

    @NotBlank
    private String primaryEmail;
    @NotBlank
    private String name;

    private String imageUrl;
    private String password; //TODO delete this after demo
    private Long familyId;
    private List<MemberDTO> members;

}
