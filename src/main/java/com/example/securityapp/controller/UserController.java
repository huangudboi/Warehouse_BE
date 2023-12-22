package com.example.securityapp.controller;

import com.example.securityapp.Dto.ChangePasswordDTO;
import com.example.securityapp.Dto.LoginDTO;
import com.example.securityapp.Dto.RegisterDTO;
import com.example.securityapp.Dto.UpdateUserDTO;
import com.example.securityapp.Dto.response.AuthenticationResponse;
import com.example.securityapp.service.UserService;
import lombok.RequiredArgsConstructor;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping(path = "/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterDTO registerDTO
    ){
        return ResponseEntity.ok(userService.register(registerDTO));
    }

    @PostMapping(path = "/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody LoginDTO loginDTO
    ){
        return ResponseEntity.ok(userService.login(loginDTO));
    }

    @PutMapping("/updateUser")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<String> updateUser(@RequestBody UpdateUserDTO updateUserDTO) {
        logger.info("Start update user id:"+updateUserDTO.getUserId());
        return userService.updateUser(updateUserDTO);
    }

    @DeleteMapping("/deleteUser")
    @PreAuthorize(" hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<String> deleteUser(Integer userId){
        return userService.deleteUser(userId);
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestBody ChangePasswordDTO changePasswordDTO){
        return userService.updatePassword(changePasswordDTO);
    }

}
