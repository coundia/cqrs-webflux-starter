package com.pcoundia.transactions.domain.exception;

import com.pcoundia.transactions.domain.valueObject.TransactionId;

public class TransactionNotFoundException extends RuntimeException {

public TransactionNotFoundException(TransactionId id) {
super("Transaction with ID " + id + " not found");
}
}
