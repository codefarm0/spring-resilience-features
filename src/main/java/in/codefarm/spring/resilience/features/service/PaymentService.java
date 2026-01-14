package in.codefarm.spring.resilience.features.service;

import in.codefarm.spring.resilience.features.client.FakePaymentGateway;
import in.codefarm.spring.resilience.features.controller.RetryableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.retry.RetryTemplate;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    private final FakePaymentGateway gateway;

    public PaymentService(FakePaymentGateway gateway) {
        this.gateway = gateway;
    }

    @Retryable(
            includes = RetryableException.class,
            maxRetries = 5,
            delay = 100,
            jitter = 10,
            multiplier = 2,
            maxDelay = 1000)
    public void process(String id) {
        log.info(" Attempt payment for {}", id);
        gateway.call();
        log.info(" Payment successful for {}", id);
    }

}
