package com.velihangozek.payment.registry;

import com.velihangozek.payment.annotation.PaymentMethod;
import com.velihangozek.payment.exception.PaymentException;
import com.velihangozek.payment.processor.PaymentProcessor;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Component
public class PaymentProcessorRegistry {

    private final Map<String, PaymentProcessor> processors = new HashMap<>();

    public PaymentProcessorRegistry() {
        scanAndRegisterProcessors("com.velihangozek.payment");
    }

    public void register(String paymentMethodType, PaymentProcessor processor) {
        if (paymentMethodType == null || paymentMethodType.isBlank()) {
            throw new PaymentException("Payment method type cannot be null or blank.");
        }
        if (processor == null) {
            throw new PaymentException("Payment processor cannot be null.");
        }
        if (processors.containsKey(paymentMethodType)) {
            throw new PaymentException("Duplicate payment method registration: " + paymentMethodType);
        }

        processors.put(paymentMethodType, processor);
    }

    public Optional<PaymentProcessor> findProcessor(String paymentMethodType) {
        return Optional.ofNullable(processors.get(paymentMethodType));
    }

    private void scanAndRegisterProcessors(String basePackage) {
        Reflections reflections = new Reflections(basePackage);
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(PaymentMethod.class);

        for (Class<?> clazz : annotatedClasses) {
            if (!PaymentProcessor.class.isAssignableFrom(clazz)) {
                throw new PaymentException(
                        "Class annotated with @PaymentMethod must implement PaymentProcessor: " + clazz.getName()
                );
            }

            PaymentMethod annotation = clazz.getAnnotation(PaymentMethod.class);
            String paymentMethodType = annotation.value();

            try {
                PaymentProcessor processor = (PaymentProcessor) clazz.getDeclaredConstructor().newInstance();
                register(paymentMethodType, processor);
            } catch (InstantiationException |
                     IllegalAccessException |
                     InvocationTargetException |
                     NoSuchMethodException e) {
                throw new PaymentException("Failed to instantiate payment processor: " + clazz.getName(), e);
            }
        }
    }
}