# Payment Service

Spring Boot API that asks a payment gateway to create a payment link. The current active strategy selects Razorpay; Stripe code is present but is not wired into the strategy.

## Architecture and classes

`PaymentController` accepts the payment-link request and delegates to `IPaymentService` / `PaymentService`. `PaymentGatewayChoserStrategy` chooses an `IPaymentGateway` implementation. `RazorpayPaymentGateway` creates a Razorpay payment link using the `RazorpayClient` bean from `RazorPayConfig`. `StripePaymentGateway` is an incomplete alternative.

## API

| Method | Path | Purpose |
|---|---|---|
| POST | `/payment` | Initialize a gateway payment link |

Request fields are `name`, `phoneNumber`, `email`, and `orderId` (`InitializePaymemntDto`). The current response is a string containing the link returned by the chosen gateway.

## Local run

Requires Java 17, Maven (or `mvnw.cmd`), valid Razorpay test credentials supplied as environment-backed Spring properties `razorpay.key` and `razorpay.secret`, and outbound network access to the gateway. No service port or Eureka registration is set in the checked-in `application.properties`; Spring Boot's default web port is therefore used unless overridden.

```powershell
.\mvnw.cmd spring-boot:run
```

## Architecture review and production gaps

- Keep all payment credentials in a secret manager; the checked-in project files contain credential material and must be treated as compromised. Rotate exposed keys before use.
- The Razorpay request currently uses a fixed amount and fixed metadata, and its customer name/contact mapping appears reversed. Validate and correct these from an authenticated order record before processing real payments.
- The strategy always returns Razorpay. The Stripe adapter has a placeholder price identifier and embedded key, and is not registered as a Spring component.
- Add amount/currency validation, authenticated ownership checks, idempotency, signature-verified webhooks, persistence/audit records, safe error mapping, and integration tests. Do not treat link creation as payment confirmation.
