package com.example.reactivemongoprojectionissue.models;

import java.util.UUID;

public interface TransactionProjection {
    UUID getId();
    SubTransactionProjection getSubTransaction();
}

