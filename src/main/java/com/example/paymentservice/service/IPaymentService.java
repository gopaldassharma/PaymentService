package com.example.paymentservice.service;

import com.razorpay.RazorpayException;
import org.springframework.stereotype.Service;

@Service
public interface IPaymentService {
    String getPaymentLink(String name, String phone, String email, String orderId) throws RazorpayException;

}
