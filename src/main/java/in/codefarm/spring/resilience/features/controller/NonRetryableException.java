package in.codefarm.spring.resilience.features.controller;

public class NonRetryableException extends RuntimeException{
    public NonRetryableException(String msg) {
        super(msg);
    }
}
