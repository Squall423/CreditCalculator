package service;

import model.*;
import service.exception.MortageException;

import java.math.BigDecimal;

public class ReferenceCalculationServiceImpl implements ReferenceCalculationService {

    @Override
    public MortageReference calculate(RateAmounts rateAmounts, InputData aInputData) {
        if (BigDecimal.ZERO.equals(aInputData.getAmount())) {
            return new MortageReference(BigDecimal.ZERO, BigDecimal.ZERO);
        }
        return new MortageReference(aInputData.getAmount(), aInputData.getMonthsDuration());
    }

    @Override
    public MortageReference calculate( RateAmounts aRateAmounts, final InputData aInputData, Rate aPreviousRate) {
        if (BigDecimal.ZERO.equals(aPreviousRate.getMortageResidual().getResidualAmount())) {
            return new MortageReference(BigDecimal.ZERO, BigDecimal.ZERO);

        }
        switch (aInputData.getOverpaymentReduceWay()) {
            case Overpayment.REDUCE_RATE:
                return reduceRateMortgageReference(aRateAmounts, aPreviousRate.getMortageResidual());
            case Overpayment.REDUCE_PERIOD:
                return new MortageReference(aInputData.getAmount(), aInputData.getMonthsDuration());
            default:
                throw new MortageException("Case not handled");
        }
    }

    private MortageReference reduceRateMortgageReference(final RateAmounts aRateAmounts,
                                                         final MortageResidual previousResidual) {
        if (aRateAmounts.getOverpayment().getAmount().compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal residualAmount = calculateResidualAmount(previousResidual.getResidualAmount(), aRateAmounts);
            return new MortageReference(residualAmount, previousResidual.getResidualDuration().subtract(BigDecimal.ONE));
        }

        return new MortageReference(previousResidual.getResidualAmount(), previousResidual.getResidualDuration());
    }

    private BigDecimal calculateResidualAmount(final BigDecimal residualAmount,final RateAmounts aRateAmounts) {
        return residualAmount
                .subtract(aRateAmounts.getCapitalAmount())
                .subtract(aRateAmounts.getOverpayment().getAmount())
                .max(BigDecimal.ZERO);

    }
}
