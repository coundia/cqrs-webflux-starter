package com.pcoundia.transactions.presentation.controller;
import com.pcoundia.shared.*;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class StatusControllerTest extends BaseIntegrationTests {

@Test
void it_should_status() {
this
.get("/v1/status")
.expectStatus().isOk()
.expectBody()
.jsonPath("$.code").isEqualTo(1);
}
}
