package com.example.securityapp.model;

import com.example.securityapp.validator.Password;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "messages")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min=8, max=20, message = "Username has from 8 to 20 characters")
    @Column(name="username")
    private String userName;

    @Password(message = "Password is not in the correct format")
    @Column(name="password")
    private String password;

    @NotEmpty(message = "Message has not been entered")
    @Column(name="messages")
    private String messages;

    @Column(name="created_on")
    private String created_on;

}
