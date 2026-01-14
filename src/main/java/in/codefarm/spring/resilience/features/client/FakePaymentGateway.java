package in.codefarm.spring.resilience.features.client;

import in.codefarm.spring.resilience.features.controller.NonRetryableException;
import in.codefarm.spring.resilience.features.controller.RetryableException;
import in.codefarm.spring.resilience.features.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class FakePaymentGateway {
    private static final Logger log = LoggerFactory.getLogger(FakePaymentGateway.class);
    private final Random random = new Random();

    public void call() {
        if (random.nextInt(3) != 0) { // ~66% failure
            log.error("Error occured..");
            throw new RetryableException("Temporary gateway failure");
        }
    }
}
