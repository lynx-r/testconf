package com.mycompany.myapp.controller;

import com.mycompany.myapp.domain.Role;
import com.mycompany.myapp.dto.UserDTO;
import com.mycompany.myapp.dto.UserDTOService;
import com.mycompany.myapp.dto.support.PageRequestByExample;
import com.mycompany.myapp.exception.UserNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserDTOService userService;

    @Inject
    public UserController(UserDTOService userDTOService) {
        this.userService = userDTOService;
    }

    @GetMapping("/show")
    public String showUser(Model model) {
        PageRequestByExample<UserDTO> requestByExample = new PageRequestByExample<>();
        requestByExample.example = new UserDTO();
        model.addAttribute("users", userService.findAll(requestByExample));
        return "users/show";
    }

    @GetMapping("/add")
    public String showEditUser(Model model) {
        UserDTO user = new UserDTO();
        user.role = Role.ROLE_LISTENER;
        model.addAttribute("user", user);
        return "users/add";
    }

    @GetMapping("/edit/{id}")
    public String showEditUser(@PathVariable("id") int id, Model model) {
        UserDTO user = userService.findOne(id);
        if (user == null) {
            throw new UserNotFoundException();
        }
        model.addAttribute("user", user);
        return "users/edit";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") @Valid UserDTO accountDto,
                           BindingResult result) {
        UserDTO edited = null;
        if (!result.hasErrors()) {
            try {
                edited = userService.save(accountDto);
            } catch (DataIntegrityViolationException e) {
                edited = null;
                result.rejectValue("username",
                        "msg.usernameMustBeUnique",
                        "Имя пользователя должно быть уникальным");
            }
        }
        if (edited == null) {
            if (accountDto.id != null) {
                result.reject("mayNotBeEmpty");
                return "users/edit";
            } else {
                return "users/add";
            }
        }
        return "redirect:/users/show";
    }

}
