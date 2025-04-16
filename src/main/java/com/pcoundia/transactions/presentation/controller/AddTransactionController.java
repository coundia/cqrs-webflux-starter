package com.pcoundia.transactions.presentation.controller;
	import com.pcoundia.transactions.application.commandHandler.*;
	import com.pcoundia.transactions.application.command.*;
	import com.pcoundia.transactions.application.dto.*;
	import com.pcoundia.transactions.application.mapper.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/commands/transaction")
@Tag(name = "Transaction commands", description = "Endpoints for managing transactions")
@Slf4j
public class AddTransactionController {

private final CreateTransactionCommandHandler handler;

public AddTransactionController(CreateTransactionCommandHandler handler) {
this.handler = handler;
}

@PostMapping
@Operation(
summary = "Create a new transaction",
description = "Creates a new transaction and returns the created entity",
requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
description = "Transaction request payload",
required = true,
content = @Content(schema = @Schema(implementation = TransactionRequest.class))
)
)
@ApiResponses(value = {
@ApiResponse(responseCode = "200", description = "Successfully created",
content = @Content(schema = @Schema(implementation = TransactionResponse.class))),
@ApiResponse(responseCode = "500", description = "Internal server error",
content = @Content(schema = @Schema()))
})
public Mono<ResponseEntity<TransactionResponse>> addTransaction(@RequestBody TransactionRequest transactionRequest) {
	CreateTransactionCommand command = TransactionMapper.toCommand(transactionRequest);

	return handler.handle(command)
	.doOnSuccess(v -> log.info("Transaction created successfully"))
	.thenReturn(ResponseEntity.ok(
	new TransactionResponse(
		command.getId().value(),
		command.getReference().value(),
		command.getAmount().value()
	)))
	.onErrorResume(ex -> {
	log.error("Failed to create transaction: {}", ex.getMessage(), ex);
	return Mono.just(ResponseEntity.internalServerError().build());
	});
	}
}
