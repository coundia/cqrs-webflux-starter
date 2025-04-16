package com.pcoundia.transactions.presentation.controller;

	import com.pcoundia.transactions.application.command.*;
	import com.pcoundia.transactions.application.mapper.*;
	import com.pcoundia.transactions.domain.valueObject.*;
	import com.pcoundia.transactions.domain.exception.*;
	import com.pcoundia.transactions.application.dto.*;
	import com.pcoundia.transactions.application.query.*;
	import com.pcoundia.transactions.application.commandHandler.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/commands/transaction")
@Tag(name = "Transaction commands", description = "Endpoints for managing transactions")
@Slf4j
public class UpdateTransactionController {

private final UpdateTransactionCommandHandler handler;

public UpdateTransactionController(UpdateTransactionCommandHandler handler) {
this.handler = handler;
}

@PutMapping("/{id}")
@Operation(
summary = "Update an existing transaction",
description = "Updates an existing transaction by ID and returns the updated entity"
)
@ApiResponses(value = {
@ApiResponse(
responseCode = "200",
description = "Transaction updated successfully",
content = @Content(mediaType = "application/json", schema = @Schema(implementation = TransactionResponse.class))
),
@ApiResponse(
responseCode = "400",
description = "Invalid input data",
content = @Content
),
@ApiResponse(
responseCode = "500",
description = "Internal server error",
content = @Content
)
})
public Mono<ResponseEntity<TransactionResponse>> updateTransaction(
	@Parameter(description = "The ID of the transaction to update", required = true)
	@PathVariable String id,

	@io.swagger.v3.oas.annotations.parameters.RequestBody(
	description = "Transaction data to update",
	required = true,
	content = @Content(schema = @Schema(implementation = TransactionRequest.class))
	)
	@RequestBody TransactionRequest transactionRequest
	) {
	UpdateTransactionCommand command = TransactionMapper.toUpdateCommand(TransactionId.create(id),
	transactionRequest);
	command.setId(TransactionId.create(id));

	return handler.handle(command)
	.doOnSuccess(v -> log.info("Transaction updated successfully with ID: {}", id))
	.thenReturn(ResponseEntity.ok(
	new TransactionResponse(
		command.getId().value(),
		command.getReference().value(),
		command.getAmount().value()
	)
	))
	.onErrorResume(ex -> {
	log.error("Failed to update transaction: {}", ex.getMessage(), ex);
	return Mono.just(ResponseEntity.internalServerError().build());
	});
	}
}
