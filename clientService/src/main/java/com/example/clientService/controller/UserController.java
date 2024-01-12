package com.example.clientService.controller;

import com.example.clientService.modal.UserDTO;
import com.example.clientService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/product")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping()
    public String listUser(Model model, @ModelAttribute("userForm") UserDTO userForm) {
        List<UserDTO> users = userService.getAllUser();
        model.addAttribute("users", users);
        return "user/listUser";
    }

    @GetMapping("/getUser")
    public String getUser(Model model) {
        UserDTO userForm = new UserDTO();
        model.addAttribute("userForm", userForm);
        return "user/userForm";
    }

//    @PostMapping("/addUser/{id}")
//    public String AddUserForm(@PathVariable("id") Long id, @ModelAttribute("userForm") UserDTO userForm, Model model) {
//        userService.addUser(id, userForm);
//        model.addAttribute("userForm", userForm);
//        return "redirect:/product";
//    }

    @PostMapping("/addUser")
    public String AddUserForm( @ModelAttribute("userForm") UserDTO userForm, Model model) {
        userService.addUser( userForm);
        model.addAttribute("userForm", userForm);
        return "redirect:/product";
    }

    @GetMapping("/edit/{id}")
    public String getUserId(@PathVariable("id") Long id, Model model) {
        UserDTO userForm = userService.getUserById(id);
        model.addAttribute("userForm", userForm);
        return "user/userForm";
    }

    @PostMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, @ModelAttribute("userForm") UserDTO userForm) {
        userService.updateUser(id, userForm);
        return "redirect:/product";
    }


}
