package com.pcoundia.transactions.application.dto;

import com.pcoundia.transactions.domain.valueObject.*;
import com.pcoundia.transactions.infrastructure.entity.*;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "TransactionResponse", description = "Response payload for Transaction")
public class TransactionResponse implements Serializable {

	@Schema(description = "", example = "")
	private String id;
	@Schema(description = "", example = "")
	private String reference;
	@Schema(description = "", example = "")
	private Double amount;

}
