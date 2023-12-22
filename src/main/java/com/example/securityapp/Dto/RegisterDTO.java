package com.example.securityapp.Dto;

import com.example.securityapp.validator.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {

    @Size(min=10, max=30, message = "Fullname has from 10 to 30 characters")
    private String fullName;

    @Size(min=8, max=20, message = "Username has from 8 to 20 characters")
    private String userName;

    @NotEmpty(message = "Email can't be empty")
    @Email
    private String email;

    @Password(message = "Password is not in the correct format")
    private String password;

    @NotNull(message = "listRoles has not been entered")
    private Set<String> listRoles = new HashSet<>();
}
