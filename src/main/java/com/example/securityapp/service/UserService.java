package com.example.securityapp.service;

import com.example.securityapp.Dto.ChangePasswordDTO;
import com.example.securityapp.Dto.LoginDTO;
import com.example.securityapp.Dto.RegisterDTO;
import com.example.securityapp.Dto.UpdateUserDTO;
import com.example.securityapp.Dto.response.AuthenticationResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {

    AuthenticationResponse register(RegisterDTO registerDTO);

    AuthenticationResponse login(LoginDTO loginDTO);

    ResponseEntity<String> updateUser(UpdateUserDTO updateUserDTO);

    ResponseEntity<String> deleteUser(int userId);

    ResponseEntity<String> updatePassword(ChangePasswordDTO changePasswordDTO);
}
