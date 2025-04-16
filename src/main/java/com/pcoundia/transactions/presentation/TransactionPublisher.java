package com.pcoundia.transactions.presentation;
	import com.pcoundia.transactions.application.dto.*;

import org.springframework.stereotype.Component;
import org.springframework.http.codec.ServerSentEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Component
public class TransactionPublisher {

private final Sinks.Many<ServerSentEvent<TransactionResponse>> sink =
	Sinks.many().multicast().onBackpressureBuffer();

	public void publish(TransactionResponse response) {
	ServerSentEvent<TransactionResponse> event = ServerSentEvent.<TransactionResponse>builder()
		.event("transaction-update")
		.data(response)
		.build();
		sink.tryEmitNext(event);
		}

		public Flux<ServerSentEvent<TransactionResponse>> stream() {
			return sink.asFlux();
			}
}
