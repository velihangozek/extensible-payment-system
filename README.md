# Extensible Payment System

A Java-based payment system designed to demonstrate how to integrate new payment methods into an existing architecture while following SOLID principles.

---

## Objective

This project aims to design a payment processing system that:

* minimizes modification of existing code
* allows easy addition of new payment methods
* follows SOLID principles, especially OCP and SRP
* uses Chain of Responsibility for workflow management
* uses Reflection for dynamic discovery of payment processors

---

## Architecture Overview

The system is composed of the following core components.

### 1. Payment Chain (Chain of Responsibility)

The payment flow is divided into multiple handlers:

* `PaymentValidationHandler`
* `PaymentMethodResolutionHandler`
* `PaymentExecutionHandler`
* `PaymentLoggingHandler`

Each handler has a single responsibility and passes control to the next handler.

### 2. Payment Processor Layer

Each payment method is implemented as a separate class:

* `CreditCardPaymentProcessor`
* `PaypalPaymentProcessor`
* `ApplePayPaymentProcessor`

All processors implement the following contract:

```java
public interface PaymentProcessor {
    PaymentResult process(PaymentRequest request);
}
```

### 3. Reflection-Based Processor Discovery

Instead of manually registering payment processors in the application entry point, the system uses reflection.

Each processor is annotated like this:

```java
@PaymentMethod("PAYPAL")
public class PaypalPaymentProcessor implements PaymentProcessor {
    @Override
    public PaymentResult process(PaymentRequest request) {
        return new PaymentResult(true, "PayPal payment processed successfully.");
    }
}
```

At runtime, the registry:

* scans the base package
* finds classes annotated with `@PaymentMethod`
* instantiates them automatically
* registers them into the processor map

This makes the system extensible without changing the existing orchestration flow.

---

## Payment Flow

The request goes through the following chain:

```text
Request -> Validation -> Method Resolution -> Execution -> Logging -> Result
```

Step by step:

1. The request is validated.
2. The appropriate payment processor is resolved from the registry.
3. The payment is executed.
4. The result is logged.
5. The final payment result is returned.

---

## How to Add a New Payment Method

To add a new payment method:

1. Create a new class implementing `PaymentProcessor`
2. Annotate it with `@PaymentMethod(...)`

Example:

```java
@PaymentMethod("APPLE_PAY")
public class ApplePayPaymentProcessor implements PaymentProcessor {

    @Override
    public PaymentResult process(PaymentRequest request) {
        return new PaymentResult(true, "Apple Pay payment processed successfully.");
    }
}
```

No changes are required in:

* `Application`
* `PaymentService`
* handler chain classes
* existing processor implementations

This demonstrates the Open/Closed Principle in practice.

---

## SOLID Principles Applied

### Open/Closed Principle (OCP)

The system is open for extension but closed for modification.

A new payment method can be added by introducing a new processor class without modifying the existing payment flow.

### Single Responsibility Principle (SRP)

Each class has a focused responsibility.

Examples:

* validation is handled by `PaymentValidationHandler`
* processor lookup is handled by `PaymentMethodResolutionHandler`
* payment execution is handled by `PaymentExecutionHandler`
* logging is handled by `PaymentLoggingHandler`

---

## Project Structure

```text
com.velihangozek.payment
├── Application.java
├── annotation
│   └── PaymentMethod.java
├── chain
│   ├── PaymentHandler.java
│   ├── AbstractPaymentHandler.java
│   ├── PaymentValidationHandler.java
│   ├── PaymentMethodResolutionHandler.java
│   ├── PaymentExecutionHandler.java
│   └── PaymentLoggingHandler.java
├── exception
│   ├── PaymentException.java
│   ├── InvalidPaymentRequestException.java
│   └── UnsupportedPaymentMethodException.java
├── model
│   ├── PaymentRequest.java
│   ├── PaymentResult.java
│   └── PaymentContext.java
├── processor
│   ├── PaymentProcessor.java
│   ├── CreditCardPaymentProcessor.java
│   ├── PaypalPaymentProcessor.java
│   └── ApplePayPaymentProcessor.java
├── registry
│   └── PaymentProcessorRegistry.java
└── service
    └── PaymentService.java
```

---

## Technologies Used

* Java 21
* Maven
* Reflections library
* SLF4J Simple Logger

---

## How to Run

Build the project:

```bash
mvn clean install
```

Run the application from:

```text
Application.main()
```

---

## Example Output

```text
Payment method: APPLE_PAY
Amount: 150.00
Currency: USD
Result: PaymentResult[success=true, message=Apple Pay payment processed successfully.]
Final result: PaymentResult[success=true, message=Apple Pay payment processed successfully.]
```

---

## Logging Note

The project uses `slf4j-simple` for lightweight logging support.

To suppress reflection library info logs, a logger configuration can be added under:

```text
src/main/resources/simplelogger.properties
```

Example:

```properties
org.slf4j.simpleLogger.log.org.reflections=warn
```

---

## Possible Future Improvements

* Replace payment method string usage with an enum for stronger type safety
* Add unit tests for validation and processor resolution
* Add a Spring Boot REST API layer as a bonus extension
* Add a minimal UI if needed

```
```