package in.codefarm.spring.resilience.features.client;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class FakePaymentGateway {

    private final Random random = new Random();

    public void call() {
        if (random.nextInt(3) != 0) { // ~66% failure
            throw new RuntimeException("Temporary gateway failure");
        }
    }
}
