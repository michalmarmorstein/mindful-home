package com.educare.mindfulhome.model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Collection;
import java.util.EnumSet;
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
    @Type(type="uuid-char")//Needed for MySQL
    private UUID id;

    private String name = "N/A";

    @Lob
    private String data;

    private String imageUrl;

    private String description;

    private boolean active = true;

    @Enumerated(value = EnumType.STRING)
    private MediaTypeEnum mediaType = MediaTypeEnum.TEXT;

    @Enumerated(value = EnumType.STRING)
    private ParticipantsEnum participantsType = ParticipantsEnum.ENTIRE_FAMILY;

    private String trainer;

    private Integer durationInSeconds;

    @ElementCollection
    @CollectionTable(name="ACTIVITY_TIME_OF_DAY")
    @Enumerated(EnumType.STRING)
    private Collection<TimeOfDayEnum> recommendedTimeOfDay = EnumSet.allOf(TimeOfDayEnum.class);;

}
