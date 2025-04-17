package com.pcoundia.transactions.presentation.controller;

import com.pcoundia.shared.*;
import com.pcoundia.transactions.application.dto.*;
import com.pcoundia.transactions.infrastructure.entity.*;
import com.pcoundia.transactions.infrastructure.repository.*;
import com.pcoundia.transactions.application.command.*;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TransactionFindByIdControllerIntegrationTest extends BaseIntegrationTests {

@Autowired
private TransactionRepository transactionRepository;

@Test
void it_should_be_able_to_get_transaction_by_id() {
Transaction entity = TransactionFixtures.randomOne(transactionRepository).block();
String existingId = entity.getId();

this.get("/v1/queries/transaction/by-id?id=" + existingId)
.expectStatus().isOk()
.expectBody()
.jsonPath("$.data.id").isEqualTo(existingId);
}
}
