package com.pcoundia.transactions.application.commandHandler;

import com.pcoundia.transactions.application.command.UpdateTransactionCommand;
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
public class UpdateTransactionCommandHandler {

private final TransactionRepository repository;
private final TransactionPublisher publisher;

public Mono<Void> handle(UpdateTransactionCommand command) {

	Transaction entity = Transaction.builder()
		.id(command.getId().value())
		.reference(command.getReference().value())
		.amount(command.getAmount().value())
	.id(command.getId().value())
	.build();
	return repository.save(entity)
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
