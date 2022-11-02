package com.educare.mindfulhome.account.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="member")
public class MemberEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long memberId;

    @NotNull
    private String name;
    private String email;
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private FamilyRoleEnum role;
    private int yearOfBirth;
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_id")
    FamilyEntity family;
}
