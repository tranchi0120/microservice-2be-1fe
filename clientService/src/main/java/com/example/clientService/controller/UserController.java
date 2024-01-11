package com.example.clientService.controller;
import com.example.clientService.modal.UserDTO;
import com.example.clientService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/product")
public class UserController {

    @Autowired
    UserService userService;
    @GetMapping()
    public String listUser(Model model, @ModelAttribute("newUser") UserDTO user) {
        List<UserDTO> users = userService.getAllUser();
        model.addAttribute("users", users);
        return "user/listUser";
    }
    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("newUser") UserDTO user, Model model) {
        UserDTO newUser = userService.addUser(user);
        model.addAttribute("newUser", newUser);
        return "redirect:/product";
    }

}
