package com.pcoundia.transactions.application.dto;

	import com.pcoundia.transactions.domain.valueObject.*;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "TransactionRequest", description = "Request payload for Transaction operations")
public class TransactionRequest implements Serializable {

			@Schema(description = "", example = "")
			private String reference;
			@Schema(description = "", example = "")
			private Double amount;

	public static TransactionRequest random() {
	TransactionRequest request = new TransactionRequest();
	request.setReference(UUID.randomUUID().toString());
	request.setAmount(5055.44);
	return request;
}
}
