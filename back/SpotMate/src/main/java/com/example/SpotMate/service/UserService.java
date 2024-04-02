package com.example.SpotMate.service;

import org.springframework.stereotype.Service;
import com.example.SpotMate.configure.PasswordEncoderSHA512;
import com.example.SpotMate.dto.UserDTO;
import com.example.SpotMate.mapper.UserMapper;

@Service
public class UserService {
    private final UserMapper userMapper;
    private final PasswordEncoderSHA512 passwordEncoderSHA512;

    public UserService(UserMapper userMapper , PasswordEncoderSHA512 passwordEncoderSHA512) {
        this.userMapper = userMapper;
        this.passwordEncoderSHA512 = passwordEncoderSHA512;
    }

    public void signup(UserDTO user){
        String plaintext = user.getEmail() + user.getPassword();
        String hashpassword = passwordEncoderSHA512.encode(plaintext);
        user.setPassword(hashpassword);
        userMapper.signup(user);
    }

}
