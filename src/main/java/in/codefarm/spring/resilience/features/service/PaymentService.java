package in.codefarm.spring.resilience.features.service;

import in.codefarm.spring.resilience.features.client.FakePaymentGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    private final FakePaymentGateway gateway;

    public PaymentService(FakePaymentGateway gateway) {
        this.gateway = gateway;
    }

    public void process(String id) {
        log.info(" Attempt payment for {}", id);
        gateway.call();
        log.info(" Payment successful for {}", id);
    }
}
