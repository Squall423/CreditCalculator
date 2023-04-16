package model;

import lombok.Value;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Value
public class RateAmounts {

     BigDecimal rateAmount;
     BigDecimal interestAmount;
     BigDecimal capitalAmount;
     Overpayment overpayment;

}
