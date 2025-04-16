package com.pcoundia.transactions.application.queryHandler;

	import com.pcoundia.transactions.application.mapper.*;
	import com.pcoundia.transactions.domain.valueObject.*;
	import com.pcoundia.transactions.application.query.*;
	import com.pcoundia.transactions.infrastructure.repository.*;
	import com.pcoundia.transactions.application.dto.*;
	import reactor.core.publisher.Flux;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.concurrent.CompletableFuture;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindByIdTransactionHandler {

private final TransactionRepository repository;

public CompletableFuture<List<TransactionResponse>> handle(FindByIdTransactionQuery query) {
	return repository
	.getById(query.getId().value())
	.map(TransactionMapper::toResponse)
	.collectList()
	.doOnSuccess(list -> log.info("Found {}  by id", list.size()))
	.doOnError(error -> log.error("Error finding  by id: {}", error.getMessage(), error))
	.toFuture();
	}
}
