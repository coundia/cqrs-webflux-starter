package com.pcoundia.transactions.presentation.controller;

import com.pcoundia.shared.*;
import com.pcoundia.transactions.application.dto.*;
import com.pcoundia.transactions.infrastructure.entity.*;
import com.pcoundia.transactions.infrastructure.repository.*;
import com.pcoundia.transactions.application.command.*;
import java.util.UUID;

import reactor.core.publisher.Mono;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransactionFixtures {

	public static Mono<Transaction> randomOne(TransactionRepository repository) {

	return repository.insert(
	UUID.randomUUID().toString(),
			UUID.randomUUID().toString(), 
			7227.62
	);
	}

	public static Mono<Transaction> existingOrRandom(TransactionRepository repository) {
	return repository.findAll()
	.next()
	.switchIfEmpty(randomOne(repository));
	}

	public static Mono<Transaction> byId(TransactionRepository repository, String id) {
	return repository.findById(id);
	}

	public static Mono<List<Transaction>> randomMany(TransactionRepository repository, int count) {
	List<Mono<Transaction>> monos = new ArrayList<>();
	for (int i = 0; i < count; i++) {
	monos.add(randomOne(repository));
	}
	return Mono.zip(monos, results -> {
	List<Transaction> list = new ArrayList<>();
	for (Object o : results) {
	list.add((Transaction) o);
	}
	return list;
	});
	}

	public static Mono<Void> deleteAll(TransactionRepository repository) {
		return repository.deleteAll();
	}
}
