package in.codefarm.spring.resilience.features.controller;

public class RetryableException extends RuntimeException{
    public RetryableException(String msg) {
        super(msg);
    }
}
