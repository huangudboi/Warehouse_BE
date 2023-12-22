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

    @Size(min=8, max=20, message = "Username has from 8 to 20 characters")
    private String userName;

    @Password(message = "Password is not in the correct format")
    private String password;

    @NotNull(message = "Code cannot be null")
    private String code;
}
