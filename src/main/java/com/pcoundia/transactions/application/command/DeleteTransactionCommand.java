package com.pcoundia.transactions.application.command;

import com.pcoundia.transactions.domain.valueObject.*;
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
public class DeleteTransactionCommand implements Serializable {

	
	
	

private TransactionId id;

}
