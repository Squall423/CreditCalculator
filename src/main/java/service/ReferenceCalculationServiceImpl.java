package service;

import model.*;
import service.exception.MortageException;

import java.math.BigDecimal;

public class ReferenceCalculationServiceImpl implements ReferenceCalculationService {


    @Override
    public MortageReference calculate(InputData aInputData) {
        return new MortageReference(aInputData.getAmount(), aInputData.getMonthsDuration());
    }

    @Override
    public MortageReference calculate(InputData aInputData, RateAmounts aRateAmounts, Rate aPreviousRate) {
        if (BigDecimal.ZERO.equals(aPreviousRate.getMortageResidual().getAmount())) {
            return new MortageReference(BigDecimal.ZERO, BigDecimal.ZERO);

        }
        switch (aInputData.getOverpaymentReduceWay()) {
            case Overpayment.REDUCE_PERIOD:
                return new MortageReference(aInputData.getAmount(), aInputData.getMonthsDuration());
            case Overpayment.REDUCE_RATE:
                return reduceRateMortageReference(aRateAmounts, aPreviousRate);
            default:
                throw new MortageException();
        }
    }

    private MortageReference reduceRateMortageReference(RateAmounts aRateAmounts, Rate aPreviousRate) {
        if (aRateAmounts.getOverpayment().getAmount().compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal referenceAmount = calculateResidualAmount(aPreviousRate.getMortageResidual().getAmount(),
                    aRateAmounts);
            BigDecimal referenceDuaration = aPreviousRate.getMortageResidual().getDuration().subtract(BigDecimal.ONE);
            return new MortageReference(referenceAmount,referenceDuaration);

        }
        return new MortageReference(aPreviousRate.getMortageReference().getReferenceAmount(),
                aPreviousRate.getMortageReference().getReferenceDuration());
    }

    private BigDecimal calculateResidualAmount(BigDecimal aAmount, RateAmounts aRateAmounts) {
        return aAmount
                .subtract(aRateAmounts.getCapitalAmount()).max(BigDecimal.ONE)
                .subtract(aRateAmounts.getOverpayment().getAmount())
                .max(BigDecimal.ZERO);

    }
}
