package com.pcoundia.transactions.application.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "TransactionPagedResponse", description = "Paginated response for Transaction results")
public class TransactionPagedResponse implements Serializable {

@Schema(description = "List of paginated Transaction items")
private List<TransactionResponse> content;

	@Schema(description = "Current page number", example = "0")
	private int page;

	@Schema(description = "Page size", example = "10")
	private int size;

	@Schema(description = "Total number of elements", example = "100")
	private long totalElements;

	@Schema(description = "Total number of pages", example = "10")
	private long totalPages;

	public static TransactionPagedResponse from(List<TransactionResponse> content, int page, int size, long totalElements, long totalPages) {
		return TransactionPagedResponse.builder()
		.content(content)
		.page(page)
		.size(size)
		.totalElements(totalElements)
		.totalPages(totalPages)
		.build();
		}
}
