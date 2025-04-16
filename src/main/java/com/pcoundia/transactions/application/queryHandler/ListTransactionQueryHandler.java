package com.pcoundia.transactions.application.queryHandler;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import com.pcoundia.transactions.application.dto.*;
import com.pcoundia.transactions.infrastructure.repository.*;
import com.pcoundia.transactions.application.query.*;
import com.pcoundia.transactions.infrastructure.entity.*;
import com.pcoundia.transactions.application.mapper.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ListTransactionQueryHandler {

private final TransactionRepository transactionRepository;

public ListTransactionQueryHandler(TransactionRepository transactionRepository) {
this.transactionRepository = transactionRepository;
}

public CompletableFuture<TransactionPagedResponse> handle(ListTransactionQuery query) {
	int limit = query.getLimit();
	int offset = query.getPage() * query.getLimit();

	Mono<Long> totalElements = transactionRepository.findAll().count();
		Mono<List<Transaction>> items = transactionRepository.findAllByPage(limit, offset).collectList();

		return Mono.zip(items, totalElements)
		.map(tuple -> {
		List<TransactionResponse> responses = tuple.getT1().stream()
			.map(TransactionMapper::toResponse)
			.toList();

			return TransactionPagedResponse.from(
			responses,
			query.getPage(),
			query.getLimit(),
			tuple.getT2(),
			tuple.getT2() / query.getLimit()
			);
			})
			.doOnSuccess(result -> log.info("List Transaction query handled: page={}, size={}, total={}",
			query.getPage(), query.getLimit(), result.getTotalElements()))
			.doOnError(error -> log.error("Error during list Transaction query: {}", error.getMessage(), error))
			.toFuture();
			}
}
