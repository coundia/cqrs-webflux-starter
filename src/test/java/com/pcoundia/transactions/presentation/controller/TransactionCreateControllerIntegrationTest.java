package com.pcoundia.transactions.presentation.controller;

import com.pcoundia.shared.*;
import com.pcoundia.transactions.application.dto.*;
import com.pcoundia.transactions.infrastructure.entity.*;
import com.pcoundia.transactions.infrastructure.repository.*;
import com.pcoundia.transactions.application.command.*;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TransactionCreateControllerIntegrationTest extends BaseIntegrationTests {

@Test
void it_should_be_able_to_add_transaction() {
TransactionRequest requestDTO = TransactionRequest.random();

	this.post("/v1/commands/transaction", requestDTO)
		.expectStatus().isOk()
		.expectBody()
		.jsonPath("$.code").isEqualTo(1)
		.jsonPath("$.data.id").isNotEmpty()
				.jsonPath("$.data.reference").isEqualTo(requestDTO.getReference())
				.jsonPath("$.data.amount").isEqualTo(requestDTO.getAmount())
;
	}
}
