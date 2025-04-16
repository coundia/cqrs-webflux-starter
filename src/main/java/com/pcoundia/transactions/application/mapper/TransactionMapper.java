package com.pcoundia.transactions.application.mapper;

import com.pcoundia.transactions.application.dto.*;
import com.pcoundia.transactions.domain.valueObject.*;
import com.pcoundia.transactions.domain.*;
import com.pcoundia.transactions.infrastructure.entity.*;
import com.pcoundia.transactions.application.command.*;
import java.util.UUID;

public class TransactionMapper {

public static TransactionResponse toResponse(Transaction entity) {
return new TransactionResponse(
		entity.getId(), 
		entity.getReference(), 
		entity.getAmount()
);
}

public static TransactionResponse toResponse(CreateTransactionCommand command) {
return new TransactionResponse(
command.getId().value(), command.getReference().value(), command.getAmount().value()
);
}

public static TransactionResponse toResponse(UpdateTransactionCommand command) {
return new TransactionResponse(
command.getId().value(), command.getReference().value(), command.getAmount().value()
);
}


public static CreateTransactionCommand toCommand(
	TransactionRequest request
) {
return new CreateTransactionCommand(
TransactionId.create(UUID.randomUUID().toString()),
TransactionReference.create(request.getReference()), TransactionAmount.create(request.getAmount())
);
}
public static UpdateTransactionCommand toUpdateCommand(TransactionId id, TransactionRequest request) {
return new UpdateTransactionCommand(
id, TransactionReference.create(request.getReference()), TransactionAmount.create(request.getAmount())
);
}


public static DeleteTransactionCommand toDeleteCommand(TransactionId id) {
return new DeleteTransactionCommand(id);
}
}
