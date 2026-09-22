package com.example.paymentservice.paymentGateway;

import com.razorpay.RazorpayException;
import org.springframework.stereotype.Service;

@Service
public interface IPaymentGateway {
    String getPaymentLink(String name, String phone, String email, String orderId) throws RazorpayException;
}
