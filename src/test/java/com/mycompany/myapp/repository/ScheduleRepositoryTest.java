package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Presentation;
import com.mycompany.myapp.domain.Room;
import com.mycompany.myapp.domain.Schedule;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.dto.ScheduleDTOService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScheduleRepositoryTest {

    @Inject
    private ScheduleRepository scheduleRepository;
    @Inject
    private UserRepository userRepository;

    @Test
    public void shouldAddSchedule() {
        User alice = userRepository.getByUsername("alice");
        Assert.assertNotNull(alice);
        Schedule schedule = new Schedule();
        schedule.setRoom(new Room().roomNumber(200));
        schedule.setPresentation(new Presentation()
                .title("presentation1")
                .addPresenter(alice));
        schedule.setEventTime(LocalDateTime.now());
        schedule.setEventDurationMin(30);
        Schedule saved = scheduleRepository.save(schedule);
        Assert.assertNotNull(saved);
    }
}
