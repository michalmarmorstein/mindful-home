package com.educare.mindfulhome.account.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    //private int notification

//    public void addMember(MemberEntity member){
//        if(members == null){
//            members = new HashSet<>();
//        }
//        members.add(member);
////        member.setFamily(this);
//    }

}
