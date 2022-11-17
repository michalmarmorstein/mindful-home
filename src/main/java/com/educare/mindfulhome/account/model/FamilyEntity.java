package com.educare.mindfulhome.account.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="family")
public class FamilyEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long familyId;

    @NotNull
    private String primaryEmail;
    @NotNull
    private String name;

    private String imageUrl;
    private String password; //TODO delete this after demo

    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL)
    private Set<MemberEntity> members;

    public void linkMembers(){
        if(members != null){
            for(MemberEntity member : members){
                member.setFamily(this);
            }
        }
    }

}
