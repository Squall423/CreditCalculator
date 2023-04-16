package model;

import lombok.Value;

import java.math.BigDecimal;
import java.math.RoundingMode;


@Value
public class MortageResidual {

    BigDecimal residualAmount;
    BigDecimal residualDuration;

}
