package com.pcoundia.transactions.presentation.controller;

import com.pcoundia.shared.*;
import com.pcoundia.transactions.application.dto.*;
import com.pcoundia.transactions.infrastructure.entity.*;
import com.pcoundia.transactions.infrastructure.repository.*;
import com.pcoundia.transactions.application.command.*;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;

public class TransactionDeleteControllerIntegrationTest extends BaseIntegrationTests {

@Autowired
private TransactionRepository transactionRepository;

@Test
void it_should_be_able_to_delete_transaction() {
Transaction entity = TransactionFixtures.randomOne(transactionRepository).block();
String existingId = entity.getId();

this.delete("/v1/commands/transaction/" + existingId)
.expectStatus().isOk()
.expectBody()
.jsonPath("$.code").isEqualTo(1);

Transaction found = TransactionFixtures.byId(transactionRepository, existingId).block();
assertThat(found).isNull();
}
}
