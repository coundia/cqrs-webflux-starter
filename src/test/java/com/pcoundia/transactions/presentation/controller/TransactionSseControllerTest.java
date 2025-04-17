package com.pcoundia.transactions.presentation.controller;

import com.pcoundia.shared.*;
import com.pcoundia.transactions.application.dto.*;
import com.pcoundia.transactions.infrastructure.entity.*;
import com.pcoundia.transactions.infrastructure.repository.*;
import com.pcoundia.transactions.application.command.*;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import java.time.Duration;

public class TransactionSseControllerTest extends BaseIntegrationTests {

@Autowired
private TransactionRepository repository;

@Test
void it_should_stream_transactions_as_sse() {
TransactionFixtures.deleteAll(repository).block();
TransactionFixtures.randomMany(repository, 1).block();

Flux<String> stream = this.webTestClient.get()
	.uri("/api/v1/queries/transaction/stream")
	.accept(MediaType.TEXT_EVENT_STREAM)
	.exchange()
	.returnResult(String.class)
	.getResponseBody();

	StepVerifier.create(stream)
	.expectNextMatches(json ->
			json.contains("reference")  &&
			json.contains("amount")
	)
	.thenCancel()
	.verify(Duration.ofSeconds(5));
	}
}
