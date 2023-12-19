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

    @Size(min=8, max=20)
    @Column(name="username")
    private String userName;

    @Password
    @Column(name="password")
    private String password;

    @NotEmpty
    @Column(name="messages")
    private String messages;

    @Column(name="status")
    private Integer status;

    @NotEmpty
    @Column(name="created_on")
    private String created_on;

}
