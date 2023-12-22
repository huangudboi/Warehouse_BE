package com.example.securityapp.service;


import com.example.securityapp.Dto.response.ChangePassResponse;
import com.example.securityapp.model.PasswordResetToken;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface PassResetService {

    PasswordResetToken saveOrUpdate(PasswordResetToken passwordResetToken);
    PasswordResetToken getLastTokenByUserId(int userId);
    ResponseEntity<ChangePassResponse> resetPassword(String userName);
    ResponseEntity<ChangePassResponse> creatNewPass(String userName, String token, String newPassword);
}
