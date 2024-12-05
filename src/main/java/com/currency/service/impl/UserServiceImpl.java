package com.currency.service.impl;

import com.currency.dto.UserDTO;
import com.currency.entity.User;
import com.currency.repository.UserRepository;
import com.currency.service.UserService;
import com.currency.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MapperUtil mapperUtil;

    public UserServiceImpl(UserRepository userRepository, MapperUtil mapperUtil) {
        this.userRepository = userRepository;
        this.mapperUtil = mapperUtil;
    }



    @Override
    public List<UserDTO> findAll() {

        return userRepository.findAll().stream().map(user-> mapperUtil.convert(user, new UserDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO create(UserDTO userDTO) {
        User user = userRepository.save(mapperUtil.convert(userDTO, new User()));
        return mapperUtil.convert(user,new UserDTO());
    }

    @Override
    public UserDTO findByUsername(String username) {
       User user=userRepository.findByUsername(username)
               .orElseThrow(()->new RuntimeException("User not found: "+username));

        return mapperUtil.convert(user,new UserDTO());
    }
}
