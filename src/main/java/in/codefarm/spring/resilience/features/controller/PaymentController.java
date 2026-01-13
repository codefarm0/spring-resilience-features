package in.codefarm.spring.resilience.features.controller;

import in.codefarm.spring.resilience.features.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> pay(@PathVariable String id) {
        log.info("➡️ HTTP request received for payment {}", id);

        paymentService.process(id);

        log.info("⬅️ HTTP request completed for payment {}", id);
        return ResponseEntity.ok("OK");
    }
}
