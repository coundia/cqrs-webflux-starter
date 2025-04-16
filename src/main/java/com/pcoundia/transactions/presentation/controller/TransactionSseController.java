package com.pcoundia.transactions.presentation.controller;
	import org.springframework.http.MediaType;
	import org.springframework.web.bind.annotation.*;
	import reactor.core.publisher.Flux;
	import java.time.Duration;
	import org.springframework.http.codec.ServerSentEvent;
	import com.pcoundia.transactions.application.dto.*;
	import com.pcoundia.transactions.application.mapper.*;
	import com.pcoundia.transactions.infrastructure.repository.*;
	import com.pcoundia.transactions.presentation.TransactionPublisher;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@RestController
@RequestMapping("/api/v1/queries/transaction")
@Tag(name = "Transaction queries SSE", description = "SSE endpoint for streaming transaction updates")
@Slf4j
public class TransactionSseController {

private final TransactionRepository repository;
private final TransactionPublisher publisher;

public TransactionSseController(TransactionRepository repository, TransactionPublisher publisher) {
this.repository = repository;
this.publisher = publisher;
}

@GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
public Flux<ServerSentEvent<TransactionResponse>> stream() {
	Flux<ServerSentEvent<TransactionResponse>> existing = repository.findAll()
		.map(TransactionMapper::toResponse)
		.map(data -> ServerSentEvent.<TransactionResponse>builder()
			.event("transaction-init")
			.data(data)
			.build());

			Flux<ServerSentEvent<TransactionResponse>> updates = publisher.stream();

		return existing.concatWith(updates);
	}
}