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
public class DeleteTransactionCommandHandlerTest extends BaseUnitTests {

@Mock
private TransactionRepository repository;

@Mock
private TransactionPublisher publisher;

@InjectMocks
private DeleteTransactionCommandHandler handler;

private DeleteTransactionCommand command;

@BeforeEach
void setUp() {
	command = new DeleteTransactionCommand(new TransactionId("test-id"));
}

@Test
void it_should_handle_command() {
	when(repository.deleteById("test-id")).thenReturn(Mono.empty());

StepVerifier.create(handler.handle(command))
.verifyComplete();

}
}
