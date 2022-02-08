package com.educare.mindfulhome.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="activity")
public class ActivityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="activity_id", nullable = false)
    private Long id;

    private String name;

    private String link;

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
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActivityEntity that = (ActivityEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (link != null ? !link.equals(that.link) : that.link != null) return false;
        if (mediaType != that.mediaType) return false;
        if (participantsType != that.participantsType) return false;
        if (trainer != null ? !trainer.equals(that.trainer) : that.trainer != null) return false;
        if (durationInSeconds != null ? !durationInSeconds.equals(that.durationInSeconds) : that.durationInSeconds != null) return false;
        return recommendedTimeOfDay != null ? recommendedTimeOfDay.equals(that.recommendedTimeOfDay) : that.recommendedTimeOfDay == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (mediaType != null ? mediaType.hashCode() : 0);
        result = 31 * result + (participantsType != null ? participantsType.hashCode() : 0);
        result = 31 * result + (trainer != null ? trainer.hashCode() : 0);
        result = 31 * result + (durationInSeconds != null ? durationInSeconds.hashCode() : 0);
        result = 31 * result + (recommendedTimeOfDay != null ? recommendedTimeOfDay.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ActivityEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", mediaType=" + mediaType +
                ", participantsType=" + participantsType +
                ", trainer='" + trainer + '\'' +
                ", durationInSeconds=" + durationInSeconds +
                ", recommendedTimeOfDay=" + recommendedTimeOfDay +
                '}';
    }
}
