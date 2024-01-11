package com.example.reactivemongoprojectionissue;

import com.example.reactivemongoprojectionissue.bmodels.ApiSubTransaction;
import com.example.reactivemongoprojectionissue.bmodels.ApiTransaction;
import com.example.reactivemongoprojectionissue.dao.TransactionRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.test.StepVerifier;

import java.util.UUID;

@DataMongoTest(excludeAutoConfiguration = JmxAutoConfiguration.class)
@Testcontainers
@EnableMongoRepositories("com.example.reactivemongoprojectionissue.dao")
class MongoDbTests {
    @Container
    static MongoDBContainer container = new MongoDBContainer("mongo:4.4");

    @Autowired
    TransactionRepo repo;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", container::getReplicaSetUrl);
    }

    @BeforeEach
    public void dataSetup() {
        repo.deleteAll().block();
    }

    @Test
    void test_recursiveProjection() {

        var obj = new ApiTransaction();
        obj.setCorrelationId("ID");
        UUID id = UUID.randomUUID();
        obj.setId(id);
        obj.setSubTransaction(new ApiSubTransaction(String.format("2023-10-01"), UUID.randomUUID().toString()));

        repo.insert(obj).block();
        StepVerifier.create(repo.getTransactionsProjections(id)).expectNextMatches(
                it -> it.getSubTransaction().getCheckDate().equals("2023-10-01")
        ).verifyComplete();
    }
}
