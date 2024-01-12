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


    public void updateUser(Long id, UserDTO updatedUser) {
        UserDTO staffUpdate = productAPI.getUserById(updatedUser.getId());
        if (staffUpdate != null && staffUpdate.getId().equals(updatedUser.getId())) {
            staffUpdate.setUsername(updatedUser.getUsername());
            staffUpdate.setEmail(updatedUser.getEmail());
            productAPI.updateUser(updatedUser.getId(), staffUpdate);
        }
        productAPI.updateUser(id, updatedUser);
    }


    public void addUser(UserDTO user) {
        productAPI.addUser(user);
    }


//        public void addUser(Long id, UserDTO user) {
//        if(user.getId() == null){
//            productAPI.addUser(user);
//        }else{
//            UserDTO staffUpdate = productAPI.getUserById(user.getId());
//            if (staffUpdate != null) {
//                staffUpdate.setUsername(user.getUsername());
//                staffUpdate.setEmail(user.getEmail());
//                productAPI.updateUser(id, staffUpdate);
//            }
//        }
//    }


}
