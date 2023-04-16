package model;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class Summary {
     BigDecimal interestSum;

     BigDecimal overpaymentProvisionsSum;

     BigDecimal totalLostSum;
     BigDecimal totalCapital;


}

