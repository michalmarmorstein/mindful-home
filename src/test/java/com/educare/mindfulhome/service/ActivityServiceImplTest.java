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
    public void whenCreateActivity_withNullArg_shouldThrowNullPointerException() {

        NullPointerException exception;
        exception = assertThrows(NullPointerException.class, () -> {
            service.createActivity(null);
        });

        String expectedMessage = "Activity must not be null";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void whenCreateActivity_emptyActivity_shouldReturnDefaultActivity() {

        when(mockRepo.save(any(ActivityEntity.class))).thenReturn(new ActivityEntity());
        ActivityEntity created = service.createActivity(new ActivityEntity());

        assertThat(created.getRecommendedTimeOfDay()).isEqualTo(EnumSet.allOf(TimeOfDayEnum.class));
        assertThat(created.getParticipantsType()).isEqualTo(ParticipantsEnum.ENTIRE_FAMILY);
        assertThat(created.getMediaType()).isEqualTo(MediaTypeEnum.TEXT);
        assertThat(created.getName()).isEqualTo("N/A");
        assertThat(created.isActive()).isEqualTo(true);
        verify(mockRepo).save(new ActivityEntity());
    }

    @Test
    public void whenGetActivityById_shouldReturnActivity_ifFound() {

        when(mockRepo.findById(sampleActivity.getId())).thenReturn(Optional.of(sampleActivity));
        ActivityEntity expected = service.getActivityById(sampleActivity.getId());

        assertThat(expected).isEqualTo(sampleActivity);
        verify(mockRepo).findById(sampleActivity.getId());
    }

    @Test
    public void whenGetActivityById_idDoesNotExist_shouldThrowEntityNotFoundException() {

        when(mockRepo.findById(any(UUID.class))).thenReturn(Optional.ofNullable(null));

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            service.getActivityById(UUID.randomUUID());
        });

        String expectedMessage = "Activity Not Found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void whenGetActivityById_idIsNull_shouldThrowIllegalArgumentException() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.getActivityById(null);
        });

        String expectedMessage = "The given id must not be null";
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

    @Test
    public void whenUpdateActivity_shouldReturnUpdatedActivity_ifFound() {

        ActivityEntity updatedActivity = new ActivityEntity();
        updatedActivity.setId(sampleActivity.getId());
        updatedActivity.setName("New name");
        updatedActivity.setData("New data");
        updatedActivity.setDescription("New description");
        updatedActivity.setTrainer("New trainer");
        updatedActivity.setActive(false);
        updatedActivity.setDurationInSeconds(22);
        updatedActivity.setMediaType(MediaTypeEnum.VIDEO);
        updatedActivity.setParticipantsType(ParticipantsEnum.KIDS);
        updatedActivity.setRecommendedTimeOfDay(new ArrayList<>(Arrays.asList(TimeOfDayEnum.MORNING)));

        when(mockRepo.findById(sampleActivity.getId())).thenReturn(Optional.of(sampleActivity));
        when(mockRepo.save(any(ActivityEntity.class))).thenReturn(updatedActivity);

        ActivityEntity created = service.updateActivity(updatedActivity);

        assertThat(created).isEqualTo(updatedActivity);
        assertThat(created.getId()).isEqualTo(sampleActivity.getId());
        verify(mockRepo).save(updatedActivity);
    }

    @Test
    public void whenUpdateActivity_withNullArg_shouldThrowNullPointerException() {

        NullPointerException exception;
        exception = assertThrows(NullPointerException.class, () -> {
            service.updateActivity(null);
        });

        String expectedMessage = "Activity must not be null";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void whenUpdateActivity_idDoesNotExist_shouldThrowEntityNotFoundException() {

        ActivityEntity invalidIdActivity = new ActivityEntity();
        invalidIdActivity.setId(UUID.randomUUID());
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            service.updateActivity(invalidIdActivity);
        });

        String expectedMessage = "Activity Not Found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void whenUpdateActivity_idIsNull_shouldThrowIllegalArgumentException() {

        ActivityEntity emptyActivity = new ActivityEntity();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.updateActivity(emptyActivity);
        });

        String expectedMessage = "The given id must not be null";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void whenUpdateActivity_emptyActivity_shouldReturnDefaultActivity() {

        UUID id = UUID.randomUUID();
        ActivityEntity emptyActivity = new ActivityEntity();
        emptyActivity.setId(id);
        ActivityEntity returnedActivity = new ActivityEntity();
        returnedActivity.setId(id);

        when(mockRepo.findById(id)).thenReturn(Optional.of(returnedActivity));
        when(mockRepo.save(any(ActivityEntity.class))).thenReturn(returnedActivity);

        ActivityEntity created = service.updateActivity(emptyActivity);

        assertThat(created.getRecommendedTimeOfDay()).isEqualTo(EnumSet.allOf(TimeOfDayEnum.class));
        assertThat(created.getParticipantsType()).isEqualTo(ParticipantsEnum.ENTIRE_FAMILY);
        assertThat(created.getMediaType()).isEqualTo(MediaTypeEnum.TEXT);
        assertThat(created.getName()).isEqualTo("N/A");
        assertThat(created.isActive()).isEqualTo(true);
        verify(mockRepo).save(emptyActivity);
    }
}
