package com.example.reactivemongoprojectionissue.dao;

import com.example.reactivemongoprojectionissue.bmodels.ApiTransaction;
import com.example.reactivemongoprojectionissue.models.TransactionProjection;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface TransactionRepo extends ReactiveMongoRepository<ApiTransaction, UUID> {

    @Query("{id: ?0}")
    Flux<TransactionProjection> getTransactionsProjections(UUID id);
}
