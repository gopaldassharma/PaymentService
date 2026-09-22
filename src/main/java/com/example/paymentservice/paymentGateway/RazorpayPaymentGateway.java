package com.example.paymentservice.paymentGateway;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RazorpayPaymentGateway implements IPaymentGateway {

        @Autowired
        RazorpayClient razorpayClient;

        @Override
        public String getPaymentLink(String name, String phone, String email, String orderId ) throws RazorpayException {
            try {
                RazorpayClient razorpay = razorpayClient;
                JSONObject paymentLinkRequest = new JSONObject();
                paymentLinkRequest.put("amount", 1000);
                paymentLinkRequest.put("currency", "INR");
                paymentLinkRequest.put("accept_partial", true);
                paymentLinkRequest.put("first_min_partial_amount", 100);
                paymentLinkRequest.put("expire_by", 1790812799);
                paymentLinkRequest.put("reference_id", orderId);
                paymentLinkRequest.put("description", "Payment for policy no #23456");
                JSONObject customer = new JSONObject();
                customer.put("name", phone);
                customer.put("contact", name);
                customer.put("email",email);
                paymentLinkRequest.put("customer", customer);
                JSONObject notify = new JSONObject();
                notify.put("sms", true);
                notify.put("email", true);
                paymentLinkRequest.put("notify", notify);
                paymentLinkRequest.put("reminder_enable", true);
                JSONObject notes = new JSONObject();
                notes.put("policy_name", "Life Insurance Policy");
                paymentLinkRequest.put("notes", notes);
                paymentLinkRequest.put("callback_url", "https://example-callback-url.com/");
                paymentLinkRequest.put("callback_method", "get");

                PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);
                return payment.get("short_url").toString();
            }catch (RazorpayException razorpayException){
                throw new RuntimeException( razorpayException.getMessage());
            }
    }
}
