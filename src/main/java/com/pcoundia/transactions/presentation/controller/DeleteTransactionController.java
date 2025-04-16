package com.pcoundia.transactions.presentation.controller;

import com.pcoundia.transactions.application.mapper.*;
import com.pcoundia.transactions.domain.valueObject.*;
import com.pcoundia.transactions.domain.exception.*;
import com.pcoundia.transactions.application.dto.*;
import com.pcoundia.transactions.application.query.*;
import com.pcoundia.transactions.application.command.*;
import com.pcoundia.transactions.application.commandHandler.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/commands/transaction")
@Tag(name = "Transaction commands", description = "Endpoints for managing transactions")
@Slf4j
public class DeleteTransactionController {

private final DeleteTransactionCommandHandler handler;

public DeleteTransactionController(DeleteTransactionCommandHandler handler) {
this.handler = handler;
}

@DeleteMapping("/{id}")
@Operation(
summary = "Delete a transaction",
description = "Deletes a transaction based on the provided identifier"
)
@ApiResponses(value = {
@ApiResponse(responseCode = "200", description = "Transaction deleted successfully"),
@ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
@ApiResponse(responseCode = "404", description = "Transaction not found", content = @Content),
@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
})
public Mono<ResponseEntity<String>> deleteTransaction(
	@Parameter(description = "ID of the transaction to delete", required = true)
	@PathVariable String id
	) {
	if (id == null || id.isEmpty()) {
	return Mono.just(ResponseEntity.badRequest().body("Invalid id"));
	}

	log.info("Deleting Transaction with id: {}", id);

	DeleteTransactionCommand command = DeleteTransactionCommand.builder()
	.id(TransactionId.create(id))
	.build();

	return handler.handle(command)
	.doOnSuccess(v -> log.info("Transaction deleted successfully"))
	.thenReturn(ResponseEntity.ok("Transaction deleted successfully"))
	.onErrorResume(ex -> {
	log.error("Error deleting Transaction: {}", ex.getMessage(), ex);
	return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	.body("Error deleting Transaction: " + ex.getMessage()));
	});
	}
}
