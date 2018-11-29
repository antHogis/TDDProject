package com.github.anthogis.tddproject;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException() {
        super("The school has insufficient funds for this course. It needs more students.");
    }
}
