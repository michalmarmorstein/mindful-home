package com.educare.mindfulhome.service;

import com.educare.mindfulhome.model.ActivityEntity;
import com.educare.mindfulhome.model.MediaTypeEnum;
import com.educare.mindfulhome.model.ParticipantsEnum;
import com.educare.mindfulhome.model.TimeOfDayEnum;
import com.educare.mindfulhome.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
public class ActivityServiceImplTest {

    @Mock
    private ActivityRepository mockRepo;
    @InjectMocks
    private ActivityServiceImpl service;

    private static ActivityEntity sampleActivity;
    private static ActivityEntity inactiveActivity;

    @BeforeAll
    public static void init(){

        sampleActivity = new ActivityEntity();
        sampleActivity.setId(UUID.randomUUID());//TODO find a better way to test this (app uuid is created on repo.save())
        sampleActivity.setName("Test name");
        sampleActivity.setData("Test data");
        sampleActivity.setDescription("Test description");
        sampleActivity.setTrainer("Test trainer");
        sampleActivity.setActive(true);
        sampleActivity.setDurationInSeconds(30);
        sampleActivity.setMediaType(MediaTypeEnum.AUDIO);
        sampleActivity.setParticipantsType(ParticipantsEnum.ENTIRE_FAMILY);
        sampleActivity.setRecommendedTimeOfDay(EnumSet.allOf(TimeOfDayEnum.class));

        //Inactive activity
        inactiveActivity = new ActivityEntity();
        inactiveActivity.setId(UUID.randomUUID());
        inactiveActivity.setName("Inactive activity");
        inactiveActivity.setData("Inactive data");
        inactiveActivity.setDescription("Inactive description");
        inactiveActivity.setTrainer("Inactive trainer");
        inactiveActivity.setActive(false);
        inactiveActivity.setDurationInSeconds(90);
        inactiveActivity.setMediaType(MediaTypeEnum.VIDEO);
        inactiveActivity.setParticipantsType(ParticipantsEnum.ADULTS);
        inactiveActivity.setRecommendedTimeOfDay(EnumSet.allOf(TimeOfDayEnum.class));
    }

    @Test
    public void whenCreateActivity_shouldReturnActivity() {

        when(mockRepo.save(any(ActivityEntity.class))).thenReturn(sampleActivity);
        ActivityEntity created = service.createActivity(sampleActivity);

        assertThat(created).isEqualTo(sampleActivity);
        verify(mockRepo).save(sampleActivity);
    }

    @Test
    public void whenCreateActivityWithNull_throwException() {

        NullPointerException exception;
        exception = assertThrows(NullPointerException.class, () -> {
            service.createActivity(null);
        });

        String expectedMessage = "Activity must not be null";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void whenGetActivityById_shouldReturnActivity_ifFound() {

        when(mockRepo.findById(sampleActivity.getId())).thenReturn(Optional.of(sampleActivity));
        ActivityEntity expected = service.getActivityById(sampleActivity.getId());

        assertThat(expected).isEqualTo(sampleActivity);
        verify(mockRepo).findById(sampleActivity.getId());
    }

    @Test
    public void whenGetActivityById_idDoesNotExist_shouldThrowException() {

        when(mockRepo.findById(any(UUID.class))).thenReturn(Optional.ofNullable(null));

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            service.getActivityById(UUID.randomUUID());
        });

        String expectedMessage = "Activity Not Found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void whenGetAllActivities_activeOnlyFalse_shouldReturnAllActivities() {

        List<ActivityEntity> allActivities = new ArrayList<>(Arrays.asList(sampleActivity, inactiveActivity));
        when(mockRepo.findAll()).thenReturn(allActivities);
        List<ActivityEntity> expected = service.getAllActivities(false);

        assertThat(expected).isEqualTo(allActivities);

    }

    @Test
    public void whenGetAllActivities_activeOnlyTrue_shouldReturnActiveActivities() {

        List<ActivityEntity> activeActivities =  new ArrayList<>(Arrays.asList(sampleActivity));
        when(mockRepo.findByActiveTrue()).thenReturn(activeActivities);
        List<ActivityEntity> expected = service.getAllActivities(true);

        assertThat(expected).isEqualTo(activeActivities);

    }

}
