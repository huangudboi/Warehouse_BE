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

    @Size(min=2, max=30)
    @Column(name="nameSender")
    private String nameSender;

    @Phone
    @Column(name="phoneSender")
    private String phoneSender;

    @NotEmpty
    @Column(name="addressSender")
    private String addressSender;

    @NotEmpty @Email
    @Column(name="emailSender")
    private String emailSender;

    @Size(min=2, max=30)
    @Column(name="nameReceiver")
    private String nameReceiver;

    @Phone
    @Column(name="phoneReceiver")
    private String phoneReceiver;

    @NotEmpty
    @Column(name="addressReceiver")
    private String addressReceiver;

    @NotEmpty @Email
    @Column(name="emailReceiver")
    private String emailReceiver;

    @NotEmpty @Min(0) @Max(360)
    @Column(name="longitude")
    private Long longitude;

    @NotEmpty @Min(0) @Max(360)
    @Column(name="latitude")
    private Long latitude;
}
