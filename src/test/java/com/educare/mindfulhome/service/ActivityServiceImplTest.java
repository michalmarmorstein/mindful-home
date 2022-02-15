package com.educare.mindfulhome.service;

import com.educare.mindfulhome.model.ActivityEntity;
import com.educare.mindfulhome.model.MediaTypeEnum;
import com.educare.mindfulhome.model.ParticipantsEnum;
import com.educare.mindfulhome.model.TimeOfDayEnum;
import com.educare.mindfulhome.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.EnumSet;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
public class ActivityServiceImplTest {

    @Mock
    private ActivityRepository repoMock;
    @InjectMocks
    private ActivityServiceImpl service;

    //TODO test entity null

    @Test
    void createActivitySuccess() {

        ActivityEntity activity = new ActivityEntity(UUID.randomUUID(), "my name", "my data",
                "my description", false,  MediaTypeEnum.AUDIO, ParticipantsEnum.ENTIRE_FAMILY,
                "my trainer", 30, EnumSet.allOf(TimeOfDayEnum.class));
        when(repoMock.save(activity)).thenReturn(activity);
        ActivityEntity savedActivity = service.createActivity(activity);
        assertThat(savedActivity.getId()).isNotNull();
        assertThat(savedActivity.getName()).isNotNull();
        assertThat(savedActivity.getData()).isNotNull();
        assertThat(savedActivity.getMediaType()).isNotNull();
        assertThat(savedActivity.getParticipantsType()).isNotNull();
        assertThat(savedActivity.getTrainer()).isNotNull();
        assertThat(savedActivity.getDurationInSeconds()).isNotNull();
        assertThat(savedActivity.getRecommendedTimeOfDay()).isNotNull();

//        System.out.println(activity);
//        System.out.println(savedActivity);

        //Example from pet clinic
//        Collection<Vet> vets = this.clinicService.findAllVets();
//        int found = vets.size();
//
//        Vet vet = new Vet();
//        vet.setFirstName("John");
//        vet.setLastName("Dow");
//
//        this.clinicService.saveVet(vet);
//        assertThat(vet.getId().longValue()).isNotEqualTo(0);
//
//        vets = this.clinicService.findAllVets();
//        assertThat(vets.size()).isEqualTo(found + 1);
    }
}
