package com.pcoundia.transactions.application.query;

import com.pcoundia.transactions.domain.valueObject.*;
import reactor.core.publisher.Flux;

public class FindByIdTransactionQuery {

private final TransactionId  id;

public FindByIdTransactionQuery( TransactionId id) {
	this.id = id;
}

public TransactionId  getId() {
return id;
}
}
