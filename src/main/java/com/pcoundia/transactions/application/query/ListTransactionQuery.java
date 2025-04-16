package com.pcoundia.transactions.application.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ListTransactionQuery {
private int page;
private int limit;
}
