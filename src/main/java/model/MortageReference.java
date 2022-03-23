package model;

import java.math.BigDecimal;

public class MortageReference {

    private final BigDecimal referenceAmount;

    private final BigDecimal referenceDuration;

    public MortageReference(BigDecimal aReferenceAmount, BigDecimal aReferenceDuration) {
        referenceAmount = aReferenceAmount;
        referenceDuration = aReferenceDuration;
    }

    BigDecimal getReferenceAmount() {
        return referenceAmount;
    }

    BigDecimal getReferenceDuration() {
        return referenceDuration;
    }
}
