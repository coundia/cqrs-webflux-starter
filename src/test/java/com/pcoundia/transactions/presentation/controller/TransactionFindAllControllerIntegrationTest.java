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

public class TransactionFindAllControllerIntegrationTest extends BaseIntegrationTests {

@Autowired
private TransactionRepository transactionRepository;

@Test
void it_should_be_able_to_get_all_transactions() {
TransactionFixtures.deleteAll(transactionRepository).block();
TransactionFixtures.randomMany(transactionRepository, 15).block();

this.get("/v1/queries/list-transaction?page=0&limit=10")
.expectStatus().isOk()
.expectBody()
.jsonPath("$.data.content").isArray()
.jsonPath("$.data.content.length()").isEqualTo(10)
.jsonPath("$.data.page").isEqualTo(0)
.jsonPath("$.data.size").isEqualTo(10)
.jsonPath("$.data.totalElements").isEqualTo(15);
}
}
