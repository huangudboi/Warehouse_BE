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

    @Size(min=8, max=30)
    private String fullName;

    @Size(min=8, max=20)
    private String userName;

    @NotEmpty
    @Email
    private String email;

    @Password
    private String password;

    @NotNull
    private Set<String> listRoles = new HashSet<>();
}
