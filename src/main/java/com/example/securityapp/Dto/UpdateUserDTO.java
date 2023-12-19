package com.example.securityapp.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateUserDTO extends RegisterDTO{

    @NotNull
    private int userId;

}
