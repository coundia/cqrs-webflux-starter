package com.pcoundia.transactions.application.commandHandler;

import com.pcoundia.transactions.application.command.CreateTransactionCommand;
import com.pcoundia.transactions.infrastructure.repository.TransactionRepository;
import com.pcoundia.transactions.infrastructure.entity.Transaction;
import com.pcoundia.transactions.presentation.TransactionPublisher;
import com.pcoundia.transactions.application.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateTransactionCommandHandler {

private final TransactionRepository repository;
private final TransactionPublisher publisher;

public Mono<Void> handle(CreateTransactionCommand command) {

	return repository.insert(
 command.getId().value(),
 command.getReference().value(),
 command.getAmount().value()
	)
	.doOnSuccess(
	saved -> {
	log.info("Transaction entity: {}", saved);
	publisher.publish(TransactionMapper.toResponse(saved));

	}
	)
	.doOnError(error -> log.error("Error  Transaction: {}", error.getMessage(), error))
	.then();
	}
}
