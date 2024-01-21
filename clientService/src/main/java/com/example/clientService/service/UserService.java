package com.example.clientService.service;

import com.example.clientService.FeignClient.ProductAPI;
import com.example.clientService.modal.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    ProductAPI userAPI;

    public List<UserDTO> getAllUser() {
        return userAPI.getUser();
    }
    public UserDTO getUserById(Long userId) {
        return userAPI.getUserById(userId);
    }

    public void updateUser(Long id, UserDTO updatedUser) {
        UserDTO staffUpdate = userAPI.getUserById(updatedUser.getId());
        if (staffUpdate != null && staffUpdate.getId().equals(updatedUser.getId())) {
            staffUpdate.setUsername(updatedUser.getUsername());
            staffUpdate.setEmail(updatedUser.getEmail());
            userAPI.updateUser(updatedUser.getId(), staffUpdate);
        }
        userAPI.updateUser(id, updatedUser);
    }

    public void addUser(UserDTO user) {
        userAPI.addUser(user);
    }

}
