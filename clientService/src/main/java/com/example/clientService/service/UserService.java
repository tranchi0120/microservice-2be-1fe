package com.example.clientService.service;

import com.example.clientService.FeignClient.ProductAPI;
import com.example.clientService.modal.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    ProductAPI productAPI;

    public List<UserDTO> getAllUser() {
        return productAPI.getUser();
    }

    public UserDTO getUserById(Long userId) {
        return productAPI.getUserById(userId);
    }

    public UserDTO addUser(UserDTO newUser) {
        return productAPI.addUser(newUser);
    }

}
