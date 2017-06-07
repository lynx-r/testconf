package com.mycompany.myapp.controller;

import com.mycompany.myapp.dto.UserDTO;
import com.mycompany.myapp.dto.UserDTOService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class AuthController {

    private final UserDTOService userDTOService;

    @Inject
    public AuthController(UserDTOService userDTOService) {
        this.userDTOService = userDTOService;
    }

    @GetMapping("/registration")
    String showRegister(Model model) {
        UserDTO userDto = new UserDTO();
        model.addAttribute("user", userDto);
        return "auth/registration";
    }

    @PostMapping(value = "/registration")
    public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid UserDTO accountDto,
                                            BindingResult result) {
        if (!result.hasErrors()) {
            try {
                userDTOService.save(accountDto);
            } catch (DataIntegrityViolationException e) {
                result.rejectValue("username", "username.exists");
            }
        }
        if (result.hasErrors()) {
            return new ModelAndView("auth/registration", "user", accountDto);
        } else {
            return new ModelAndView("auth/successRegister", "user", accountDto);
        }
    }

    @GetMapping("/login")
    public String showLogin(Model model) {
        model.addAttribute("user", new UserDTO());
        return "auth/login";
    }

}
