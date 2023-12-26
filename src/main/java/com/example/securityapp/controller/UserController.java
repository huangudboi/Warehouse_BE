package com.example.securityapp.controller;

import com.example.securityapp.Dto.ChangePasswordDTO;
import com.example.securityapp.Dto.LoginDTO;
import com.example.securityapp.Dto.RegisterDTO;
import com.example.securityapp.Dto.UpdateUserDTO;
import com.example.securityapp.Dto.response.AuthenticationResponse;
import com.example.securityapp.model.Message;
import com.example.securityapp.model.User;
import com.example.securityapp.service.UserService;
import lombok.RequiredArgsConstructor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping(path = "/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterDTO registerDTO){
        logger.info("=== Start call api register ===");
        ResponseEntity<AuthenticationResponse> response;
        try {
            response = new ResponseEntity<>(userService.register(registerDTO), HttpStatus.OK);
        }catch (Exception ex){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("=== End call api register ===");
        return response;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginDTO loginDTO){
        logger.info("=== Start call api login ===");
        ResponseEntity<AuthenticationResponse> response;
        try {
            response = new ResponseEntity<>(userService.login(loginDTO), HttpStatus.OK);
        }catch (Exception ex){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("=== End call api login ===");
        return response;
    }

    @PutMapping("/updateUser")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<String> updateUser(@RequestBody UpdateUserDTO updateUserDTO) {
        logger.info("Start update user id:"+updateUserDTO.getUserId());
        ResponseEntity<String> response;
        try {
            response = userService.updateUser(updateUserDTO);
        }catch (Exception ex){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("End update user id:"+updateUserDTO.getUserId());
        return response;
    }

    @DeleteMapping("/deleteUser")
    @PreAuthorize(" hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<String> deleteUser(Integer userId){
        logger.info("Start delete user id:"+userId);
        ResponseEntity<String> response;
        try{
            response = userService.deleteUser(userId);
        }catch (Exception ex){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestBody ChangePasswordDTO changePasswordDTO){
        logger.info("Start update password for userName:"+changePasswordDTO.getUserName());
        ResponseEntity<String> response;
        try{
            response = userService.updatePassword(changePasswordDTO);
        }catch (Exception ex){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<List<User>> findAllUser() {
        logger.info("=== Start call api get all users ===");
        List<User> users = userService.findAll();
        ResponseEntity<List<User>> response;
        try {
            response = new ResponseEntity<>(users, HttpStatus.OK);
        }catch (IndexOutOfBoundsException ex) {
            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception ex){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("=== End call api get all users ===");
        return response;
    }

}
