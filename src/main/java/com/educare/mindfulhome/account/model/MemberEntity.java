package com.educare.mindfulhome.account.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@EqualsAndHashCode(exclude = "family")
@ToString(exclude = "family")
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
    private long phoneNumber; //TODO add support for this
    private int notification; //TODO add support for this


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_id")
    FamilyEntity family;
}
