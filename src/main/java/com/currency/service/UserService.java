package com.currency.service;

import com.currency.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> findAll();
    UserDTO create(UserDTO userDTO);
    UserDTO findByUsername(String username);

}
