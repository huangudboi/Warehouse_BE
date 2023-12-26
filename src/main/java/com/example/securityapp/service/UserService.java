package com.example.securityapp.service;

import com.example.securityapp.Dto.ChangePasswordDTO;
import com.example.securityapp.Dto.LoginDTO;
import com.example.securityapp.Dto.RegisterDTO;
import com.example.securityapp.Dto.UpdateUserDTO;
import com.example.securityapp.Dto.response.AuthenticationResponse;
import com.example.securityapp.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    AuthenticationResponse register(RegisterDTO registerDTO);

    AuthenticationResponse login(LoginDTO loginDTO);

    ResponseEntity<String> updateUser(UpdateUserDTO updateUserDTO);

    ResponseEntity<String> deleteUser(int userId);

    ResponseEntity<String> updatePassword(ChangePasswordDTO changePasswordDTO);

    List<User> findAll();

    User findByUserId(int userId);

    boolean exists(User user);
}
