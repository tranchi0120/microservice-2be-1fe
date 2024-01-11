package com.example.clientService.FeignClient;

import com.example.clientService.modal.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "product", url = "http://localhost:8080/api/")
public interface ProductAPI {
    @GetMapping("/users")
    List<UserDTO> getUser();

    @GetMapping("/users/{id}")
    UserDTO getUserById(@PathVariable Long id);

    @PostMapping("/users/addUser")
    UserDTO addUser(UserDTO newUser);
}
