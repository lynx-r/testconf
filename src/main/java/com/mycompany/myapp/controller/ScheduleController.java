package com.mycompany.myapp.controller;

import com.mycompany.myapp.dto.*;
import com.mycompany.myapp.exception.PresentationNotFoundException;
import com.mycompany.myapp.exception.TimeOccupiedException;
import com.mycompany.myapp.repository.RoomRepository;
import com.mycompany.myapp.security.UserWithId;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/presentations")
public class ScheduleController {

    private final ScheduleDTOService scheduleService;
    private final UserDTOService userService;
    @Inject
    private RoomRepository roomRepository;

    @Inject
    public ScheduleController(ScheduleDTOService scheduleDTOService,
                              UserDTOService userService) {
        this.scheduleService = scheduleDTOService;
        this.userService = userService;
    }

    @GetMapping("/show")
    public String showSchedule(Principal principal, Model model) {
        UserWithId userWithId = ((UserWithId) ((UsernamePasswordAuthenticationToken) principal).getPrincipal());
        List<ScheduleDTO> allByPresenterId = scheduleService.findAllByPresenterId(userWithId.getId());
        model.addAttribute("schedules", allByPresenterId);
        return "presentations/show";
    }

    @GetMapping("/add")
    public String showEditSchedule(Model model) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.presentation = new PresentationDTO();
        scheduleDTO.room = new RoomDTO();
        model.addAttribute("schedule", scheduleDTO);
        model.addAttribute("rooms", roomRepository.findAll());
        return "presentations/edit";
    }

    @GetMapping("/edit/{id}")
    public String showEditSchedule(@PathVariable("id") int id, Model model) {
        ScheduleDTO presentation = scheduleService.findOne(id);
        if (presentation == null) {
            throw new PresentationNotFoundException();
        }
        model.addAttribute("schedule", presentation);
        model.addAttribute("rooms", roomRepository.findAll());
        return "presentations/edit";
    }

    @PostMapping("/edit")
    public String editSchedule(Principal principal,
                               Model model,
                               @ModelAttribute("schedule") @Valid ScheduleDTO scheduleDto,
                               BindingResult result) {
        ScheduleDTO edited = null;
        if (!result.hasErrors()) {
            try {
                UserWithId userWithId = ((UserWithId) ((UsernamePasswordAuthenticationToken) principal).getPrincipal());
                edited = scheduleService.saveWithPresenter(scheduleDto, userWithId.getId());
            } catch (TimeOccupiedException e) {
                edited = null;
                result.rejectValue("eventTime", "schedule.eventTimeOccupied", new Object[] {e.getMessage()}, e.getLocalizedMessage());
                result.rejectValue("eventDurationMin", "schedule.eventTimeOccupied", e.getLocalizedMessage());
            }
        }
        if (edited == null) {
            model.addAttribute("rooms", roomRepository.findAll());
            return "presentations/edit";
        }
        return "redirect:/presentations/show";
    }

}
