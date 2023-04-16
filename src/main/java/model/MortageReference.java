package model;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class MortageReference {

    BigDecimal referenceAmount;
    BigDecimal referenceDuration;

}
