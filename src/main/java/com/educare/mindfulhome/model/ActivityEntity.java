package com.educare.mindfulhome.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="activity")
public class ActivityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="activityId")
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="link")
    private String link;

    public ActivityEntity(String name, String link){
        this.name = name;
        this.link = link;
    }
}
