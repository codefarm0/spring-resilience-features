package in.codefarm.spring.resilience.features.controller;

import in.codefarm.spring.resilience.features.service.PDFGenerationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

@RestController
public class ResiliencyController {
    private final Logger log = LoggerFactory.getLogger(ResiliencyController.class);
    private final PDFGenerationService PDFGenerationService;

    public ResiliencyController(PDFGenerationService PDFGenerationService) {
        this.PDFGenerationService = PDFGenerationService;
    }

    @GetMapping("/test")
    public String testConcurrency() {
        IntStream.range(0, 10).forEach(i ->
                CompletableFuture.runAsync(
                        () -> PDFGenerationService.generatePdf("U" + new Random().nextInt(1,10))
                )
        );
        return "succeeded..";
    }
}
