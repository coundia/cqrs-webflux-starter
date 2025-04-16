package com.pcoundia.transactions.presentation.controller;

import com.pcoundia.transactions.application.dto.TransactionResponse;
import com.pcoundia.transactions.application.query.ListTransactionQuery;
import com.pcoundia.transactions.application.dto.*;
import com.pcoundia.transactions.application.queryHandler.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/queries/list-transaction")
@Tag(name = "Transaction Query", description = "Endpoints for listing paginated transactions")
public class TransactionListController {

private final ListTransactionQueryHandler handler;

public TransactionListController(ListTransactionQueryHandler handler) {
this.handler = handler;
}

@GetMapping
@Operation(
summary = "List paginated transactions",
description = "Returns a paginated list of transactions based on page and limit parameters"
)
@ApiResponses(value = {
@ApiResponse(
responseCode = "200",
description = "Successfully retrieved list of transactions",
content = @Content(mediaType = "application/json", schema = @Schema(implementation = TransactionPagedResponse.class))
),
@ApiResponse(
responseCode = "500",
description = "Internal server error",
content = @Content
)
})
public Mono<TransactionPagedResponse> list(
	@Parameter(description = "Page number (zero-based index)", example = "0")
	@RequestParam(defaultValue = "0") int page,

	@Parameter(description = "Number of items per page", example = "10")
	@RequestParam(defaultValue = "10") int limit
	) {
	return Mono.fromFuture(handler.handle(new ListTransactionQuery(page, limit)));
	}
	}
