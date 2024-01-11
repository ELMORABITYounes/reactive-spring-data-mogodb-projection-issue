package com.example.reactivemongoprojectionissue.bmodels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiSubTransaction {
    String checkDate;
    String trackingId; 
}
