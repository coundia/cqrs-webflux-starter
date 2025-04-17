package com.pcoundia.transactions.application;

	import reactor.core.publisher.Mono;
	import org.junit.jupiter.api.Test;
	import org.mockito.Mockito;
	import com.pcoundia.transactions.application.command.*;
	import com.pcoundia.transactions.application.commandHandler.*;
	import com.pcoundia.transactions.infrastructure.repository.*;
	import com.pcoundia.transactions.domain.valueObject.*;
	import com.pcoundia.transactions.presentation.sse.*;
	import com.pcoundia.transactions.infrastructure.entity.*;
	import com.pcoundia.transactions.shared.*;
	import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import reactor.core.publisher.Mono;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

@SpringBootTest
public class CreateTransactionCommandHandlerTest extends BaseUnitTests {

@Mock
private TransactionRepository repository;

@Mock
private TransactionPublisher publisher;

@InjectMocks
private CreateTransactionCommandHandler handler;

private CreateTransactionCommand command;

@BeforeEach
void setUp() {
	command = new CreateTransactionCommand(
	new TransactionId(UUID.randomUUID().toString()), new TransactionReference(UUID.randomUUID().toString()), new TransactionAmount(2480.25)
	);
}

@Test
void it_should_handle_command() {
	when(repository.insert(any(), any(), any())).thenReturn(Mono.just(new Transaction()));

StepVerifier.create(handler.handle(command))
.verifyComplete();

	verify(publisher, times(1)).publish(any());
}
}
