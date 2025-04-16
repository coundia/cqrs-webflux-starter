package com.pcoundia.transactions.presentation.controller;

import com.pcoundia.transactions.domain.valueObject.*;
import com.pcoundia.transactions.application.queryHandler.*;
import com.pcoundia.transactions.application.query.*;
import com.pcoundia.transactions.application.dto.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1/queries/transaction")
@Tag(name = "Transaction Query", description = "Endpoints for querying transactions by id")
@Slf4j
public class FindByIdTransactionController {

private final FindByIdTransactionHandler handler;

public FindByIdTransactionController(FindByIdTransactionHandler handler) {
this.handler = handler;
}

@GetMapping("/by-id")
@Operation(
summary = "Find transaction by id",
description = "Returns a list of transactions that match the given id"
)
@ApiResponses(value = {
@ApiResponse(responseCode = "200", description = "List of matching transactions",
content = @Content(mediaType = "application/json",
schema = @Schema(implementation = TransactionResponse.class))),
@ApiResponse(responseCode = "400", description = "Invalid parameter", content = @Content),
@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
})
public Mono<ResponseEntity<List<TransactionResponse>>> findById(
	@Parameter(description = "Value of the id to filter by", required = true)
	@RequestParam String id) {

	TransactionId idVo = TransactionId.create(id);
	FindByIdTransactionQuery query = new FindByIdTransactionQuery(idVo);

	return Mono.fromFuture(handler.handle(query))
	.map(ResponseEntity::ok)
	.onErrorResume(ex -> {
	log.error("Error retrieving transaction by id: {}", ex.getMessage(), ex);
	return Mono.just(ResponseEntity.internalServerError().build());
	});
	}
}
