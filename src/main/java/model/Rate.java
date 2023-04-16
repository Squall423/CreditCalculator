package model;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class Rate {

     BigDecimal rateNumber;
     TimePoint timePoint;
     RateAmounts rateAmounts;
     MortageResidual mortageResidual;
     MortageReference mortageReference;



}
