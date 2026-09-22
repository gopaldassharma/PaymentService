package com.example.paymentservice.controllers;

import com.example.paymentservice.dtos.InitializePaymemntDto;
import com.example.paymentservice.service.IPaymentService;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private IPaymentService paymentService;

    @PostMapping
    public String initializePayment(@RequestBody InitializePaymemntDto initializePaymemntDto) throws RazorpayException {
        return paymentService.getPaymentLink(initializePaymemntDto.getName(), initializePaymemntDto.getPhoneNumber(), initializePaymemntDto.getEmail(), initializePaymemntDto.getOrderId());
    }
}
