package com.example.paymentservice.paymentGateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentGatewayChoserStrategy {
    @Autowired
    private RazorpayPaymentGateway razorpayPaymentGateway;
    public IPaymentGateway getBestPaymentGateway(){
        return  razorpayPaymentGateway;
    }
}
