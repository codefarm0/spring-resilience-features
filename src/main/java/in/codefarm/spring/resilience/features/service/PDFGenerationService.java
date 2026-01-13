package in.codefarm.spring.resilience.features.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.resilience.annotation.ConcurrencyLimit;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;


@Service
public class PDFGenerationService {

    private static final Logger log =
            LoggerFactory.getLogger(PDFGenerationService.class);

    // Total processed per user
    private final ConcurrentHashMap<String, AtomicLong> processedCount =
            new ConcurrentHashMap<>();

    // Active executions (global for this instance)
    private final AtomicInteger activeCalls = new AtomicInteger(0);

    @ConcurrencyLimit(3)
    public void generatePdf(String userId) {

        int active = activeCalls.incrementAndGet();

        long totalSoFar = processedCount
                .computeIfAbsent(userId, u -> new AtomicLong(0))
                .incrementAndGet();

        log.info(
                "user={} | totalProcessed={} | activeCalls={}",
                userId, totalSoFar, active
        );

        try {
            // Simulate slow downstream
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            int remaining = activeCalls.decrementAndGet();
            log.info(
                    "user={} | activeCalls={}",
                    userId, remaining
            );
        }
    }
}
