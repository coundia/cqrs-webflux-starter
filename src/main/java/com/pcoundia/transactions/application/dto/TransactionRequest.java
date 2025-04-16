package com.pcoundia.transactions.application.dto;

	import com.pcoundia.transactions.domain.valueObject.*;
import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;
import io.swagger.v3.oas.annotations.media.Schema;

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
return request;
}
}
