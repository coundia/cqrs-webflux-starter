package com.pcoundia.transactions.domain;

import com.pcoundia.transactions.domain.valueObject.*;
import com.pcoundia.transactions.application.command.*;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionAggregate implements Serializable {

private TransactionId id;
    private TransactionReference reference;
    private TransactionAmount amount;

public static TransactionAggregate create(CreateTransactionCommand command) {
return TransactionAggregate.builder()
            .id(command.getId())
            .reference(command.getReference())
            .amount(command.getAmount())	.id(command.getId())
.build();
}

public void applyUpdate(UpdateTransactionCommand command) {
        this.reference = command.getReference();
        this.amount = command.getAmount();
}

public void applyDelete(DeleteTransactionCommand command) {
this.id = command.getId();
}
}
