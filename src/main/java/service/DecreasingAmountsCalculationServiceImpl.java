package service;

import model.InputData;
import model.Overpayment;
import model.Rate;
import model.RateAmounts;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DecreasingAmountsCalculationServiceImpl implements DecreasingAmountsCalculationService {
    @Override
    public RateAmounts calculate(final InputData aInputData, final Overpayment aOverpayment) {
        BigDecimal interestPercent = aInputData.getInterestPercent();

       final BigDecimal residualAmount = aInputData.getAmount();
       final BigDecimal residualDuration = aInputData.getMonthsDuration();

        BigDecimal interestAmount = AmountsCalculationService.calculateInterestAmount(residualAmount, interestPercent);
        BigDecimal capitalAmount = AmountsCalculationService.compareCapitalWithResidual(
                calculateDecreasingCapitalAmount(residualAmount, residualDuration), residualAmount);
        BigDecimal rateAmount = capitalAmount.add(interestAmount);

        return new RateAmounts(rateAmount, interestAmount, capitalAmount, aOverpayment);
    }

    @Override
    public RateAmounts calculate(final InputData aInputData, final Overpayment aOverpayment, final Rate aPreviousRate) {
        BigDecimal interestPercent = aInputData.getInterestPercent();
        BigDecimal residualAmount = aPreviousRate.getMortageResidual().getResidualAmount();
        BigDecimal referenceAmount = aPreviousRate.getMortageReference().getReferenceAmount();
        BigDecimal referenceDuration = aPreviousRate.getMortageReference().getReferenceDuration();

        BigDecimal interestAmount = AmountsCalculationService.calculateInterestAmount(residualAmount, interestPercent);
        BigDecimal capitalAmount = AmountsCalculationService.compareCapitalWithResidual(
                calculateDecreasingCapitalAmount(referenceAmount, referenceDuration), residualAmount);
        BigDecimal rateAmount = capitalAmount.add(interestAmount);

        return new RateAmounts(rateAmount, interestAmount, capitalAmount, aOverpayment);
    }

    private BigDecimal calculateDecreasingCapitalAmount(final BigDecimal residualAmount, final BigDecimal residualDuration) {
        return residualAmount.divide(residualDuration, 2, RoundingMode.HALF_UP);
    }
}
