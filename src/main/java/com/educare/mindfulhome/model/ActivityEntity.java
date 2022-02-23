package com.educare.mindfulhome.model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="activity")
public class ActivityEntity {

    @Id
    @GeneratedValue
    @Column(name="activity_id")
    @Type(type="uuid-char")
    private UUID id;

    private String name;

    @Lob
    private String data;

    private String description;

    private boolean active;

    @Enumerated(value = EnumType.STRING)
    private MediaTypeEnum mediaType;

    @Enumerated(value = EnumType.STRING)
    private ParticipantsEnum participantsType;

    private String trainer;

    private Integer durationInSeconds;

    @ElementCollection
    @CollectionTable(name="ACTIVITY_TIME_OF_DAY")
    @Enumerated(EnumType.STRING)
    private Collection<TimeOfDayEnum> recommendedTimeOfDay;

}
