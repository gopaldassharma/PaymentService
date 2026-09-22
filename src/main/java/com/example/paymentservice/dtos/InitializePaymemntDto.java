package com.example.paymentservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitializePaymemntDto {
    String phoneNumber;
    String email;
    String orderId;
    String name;
}
