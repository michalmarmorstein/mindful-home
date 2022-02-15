package com.educare.mindfulhome.service;

import com.educare.mindfulhome.model.ActivityEntity;
import com.educare.mindfulhome.model.MediaTypeEnum;
import com.educare.mindfulhome.model.ParticipantsEnum;
import com.educare.mindfulhome.model.TimeOfDayEnum;
import com.educare.mindfulhome.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.EnumSet;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
public class ActivityServiceImplTest {

    @Mock
    private ActivityRepository mockRepo;
    @InjectMocks
    private ActivityServiceImpl service;

    @Test
    public void whenSaveActivity_shouldReturnActivity() {

        ActivityEntity activity = new ActivityEntity();
        activity.setId(UUID.randomUUID());//TODO find a better way to test this (app uuid is created on repo.save())
        activity.setName("Test name");
        activity.setData("Test data");
        activity.setDescription("Test description");
        activity.setTrainer("Test trainer");
        activity.setHidden(false);
        activity.setDurationInSeconds(30);
        activity.setMediaType(MediaTypeEnum.AUDIO);
        activity.setParticipantsType(ParticipantsEnum.ENTIRE_FAMILY);
        activity.setRecommendedTimeOfDay(EnumSet.allOf(TimeOfDayEnum.class));

        when(mockRepo.save(ArgumentMatchers.any(ActivityEntity.class))).thenReturn(activity);

        ActivityEntity created = service.createActivity(activity);

        assertThat(created).isEqualTo(activity);
        verify(mockRepo).save(activity);
    }

    //TODO need context for this test - find a better way to unit test this
//    @Test
//    public void whenSaveNullActivity_throwException() {
//
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
//            service.createActivity(null);
//        });
//
//        String expectedMessage = "Entity must not be null.";
//        String actualMessage = exception.getMessage();
//        assertTrue(actualMessage.contains(expectedMessage));
//    }
}
