package com.example.securityapp.Dto;

import com.example.securityapp.validator.Password;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    @Size(min=8, max=20)
    private String userName;

    @Password
    private String password;

    @NotNull
    private String code;
}
