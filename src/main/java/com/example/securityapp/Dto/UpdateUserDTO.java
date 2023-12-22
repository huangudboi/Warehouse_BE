package com.example.securityapp.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateUserDTO extends RegisterDTO{

    @NotNull(message = "Need userId to identify user to update")
    private int userId;

}
