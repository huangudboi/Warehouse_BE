package com.example.securityapp.model;

import com.example.securityapp.validator.Phone;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="`order`")
@Entity
public class Order{
    @Id
    @Column(name="order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Size(min=10, max=30, message = "Name has from 10 to 30 characters")
    @Column(name="nameSender")
    private String nameSender;

    @Phone(message = "Phone is not in the correct format")
    @Column(name="phoneSender")
    private String phoneSender;

    @NotEmpty(message = "Address can't be empty")
    @Column(name="addressSender")
    private String addressSender;

    @NotEmpty(message = "Email can't be empty") @Email
    @Column(name="emailSender")
    private String emailSender;

    @Size(min=10, max=30, message = "Name has from 10 to 30 characters")
    @Column(name="nameReceiver")
    private String nameReceiver;

    @Phone(message = "Phone is not in the correct format")
    @Column(name="phoneReceiver")
    private String phoneReceiver;

    @NotEmpty(message = "Address can't be empty")
    @Column(name="addressReceiver")
    private String addressReceiver;

    @NotEmpty(message = "Email can't be empty") @Email
    @Column(name="emailReceiver")
    private String emailReceiver;

    @NotEmpty(message = "Longtitude can't be empty") @Min(0) @Max(360)
    @Column(name="longitude")
    private Integer longitude;

    @NotEmpty(message = "Latitude can't be empty") @Min(0) @Max(360)
    @Column(name="latitude")
    private Integer latitude;
}
