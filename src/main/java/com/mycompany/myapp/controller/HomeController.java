package com.mycompany.myapp.controller;

import com.mycompany.myapp.dto.RoomDTO;
import com.mycompany.myapp.dto.ScheduleDTO;
import com.mycompany.myapp.dto.ScheduleDTOService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Controller
class HomeController {

    @Inject
    private ScheduleDTOService scheduleService;

    @GetMapping("/")
    String index(Model model) {
        Map<RoomDTO, List<ScheduleDTO>> schedules = scheduleService.findAllGroupedByRoom();
        model.addAttribute("schedule", schedules);
        return "index";
    }

}
