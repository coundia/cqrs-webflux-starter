package com.pcoundia.transactions.infrastructure.repository;

import com.pcoundia.transactions.infrastructure.entity.Transaction;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TransactionRepository extends ReactiveCrudRepository<Transaction, String> {

@Query("SELECT * FROM transactions LIMIT :limit OFFSET :offset")
Flux<Transaction> findAllByPage(int limit, int offset);

@Query("INSERT INTO transactions ( id ,   reference ,   amount  ) VALUES ( :id ,   :reference ,   :amount  ) RETURNING *")
Mono<Transaction> insert( String id ,   String reference ,   Double amount  );

@Query("UPDATE transactions SET  reference = :reference,   amount = :amount  WHERE id = :id")
Mono<Integer> updateAllById(String id ,  String reference ,  Double amount  );

@Query("SELECT * FROM transactions where id = :id")
Flux<Transaction> getById(String id);


}
