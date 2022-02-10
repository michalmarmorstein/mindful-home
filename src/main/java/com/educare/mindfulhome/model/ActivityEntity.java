package com.educare.mindfulhome.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="activity")
public class ActivityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="activity_id")
    private Long id;

    private String name;

    @Lob
    private String data;

    @Enumerated(value = EnumType.STRING)
    private MediaType mediaType;

    @Enumerated(value = EnumType.STRING)
    private ParticipantsType participantsType;

    private String trainer;

    private Integer durationInSeconds;

    @ManyToMany
    @JoinTable(
            name = "activity_tod",
            joinColumns = @JoinColumn(name = "activity_id"),
            inverseJoinColumns = @JoinColumn(name = "tod_id"))
    private Set<TimeOfDayEntity> recommendedTimeOfDay;

    //TODO remove this constructor
    public ActivityEntity(String name, String link){
        this.name = name;
        this.data = link;
    }

}
