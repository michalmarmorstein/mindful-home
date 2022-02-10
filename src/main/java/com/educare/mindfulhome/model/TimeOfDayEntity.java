package com.educare.mindfulhome.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "time_of_day")
public class TimeOfDayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tod_id")
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "recommendedTimeOfDay")
    private Set<ActivityEntity> activities;


}
