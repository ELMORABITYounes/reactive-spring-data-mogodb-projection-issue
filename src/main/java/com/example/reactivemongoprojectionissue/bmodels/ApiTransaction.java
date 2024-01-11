package com.example.reactivemongoprojectionissue.bmodels;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;

@Document("transaction")
@Getter
@Setter
@NoArgsConstructor
public class ApiTransaction{
    private UUID id;    
    private String checkDate;
    private String correlationId;
    private BigDecimal amount;
    private ApiSubTransaction subTransaction;
}
