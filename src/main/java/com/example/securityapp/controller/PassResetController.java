package com.example.securityapp.controller;

import com.example.securityapp.Dto.response.ChangePassResponse;
import com.example.securityapp.service.PassResetService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/passReset")
public class PassResetController {

    private static final Logger logger = LogManager.getLogger(PassResetController.class);

    @Autowired
    private PassResetService passResetService;

    @GetMapping("/resetPassword")
    //Param thì cần truyền params vào khi callAPI không được sử dụng được file RAW JSON
    public ResponseEntity<ChangePassResponse> resetPassword(@RequestParam("userName") String userName) {
        logger.info("=== Start call api reset password for username: "+userName+" ===");
        ResponseEntity<ChangePassResponse> response;
        try {
            response = passResetService.resetPassword(userName);
        }catch (Exception ex){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("=== End call api reset password for username: "+userName+" ===");

        return response;
    }

    @PostMapping("/creatNewPass")
    public ResponseEntity<ChangePassResponse> creatNewPass(@RequestParam("userName") String userName,
                                               @RequestParam("token") String token,
                                               @RequestParam("newPassword") String newPassword) {
        logger.info("=== Start call api create new password for username: "+userName+" ===");
        ResponseEntity<ChangePassResponse> response;
        try {
            response = passResetService.creatNewPass(userName, token, newPassword);
        }catch (Exception ex){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("=== End call api create new password for username: "+userName+" ===");

        return response;
    }

}
