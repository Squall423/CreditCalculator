package model;

public class MortageReference {

    private final Overpayment referenceAmount;

    private final Overpayment referenceDuration;

    MortageReference(Overpayment aReferenceAmount, Overpayment aReferenceDuration) {
        referenceAmount = aReferenceAmount;
        referenceDuration = aReferenceDuration;
    }

    Overpayment getReferenceAmount() {
        return referenceAmount;
    }

    Overpayment getReferenceDuration() {
        return referenceDuration;
    }
}
