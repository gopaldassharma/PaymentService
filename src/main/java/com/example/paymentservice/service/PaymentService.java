package com.example.paymentservice.service;

import com.example.paymentservice.paymentGateway.IPaymentGateway;
import com.example.paymentservice.paymentGateway.PaymentGatewayChoserStrategy;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService implements IPaymentService {

    @Autowired
    private PaymentGatewayChoserStrategy paymentGatewayChoserStrategy;

    @Override
    public String getPaymentLink(String name, String phone, String email, String orderId) throws RazorpayException {
        IPaymentGateway paymentGateway = paymentGatewayChoserStrategy.getBestPaymentGateway();
        return paymentGateway.getPaymentLink(name, phone, email, orderId);
    }
}
