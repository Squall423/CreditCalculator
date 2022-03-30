package service;

import model.InputData;
import model.Overpayment;
import model.Rate;
import model.RateAmounts;

import java.math.BigDecimal;
import java.math.RoundingMode;



public class ConstantAmountsCalculationServiceImpl implements ConstantAmountsCalculationService {
    @Override
    public RateAmounts calculate(final InputData aInputData, final Overpayment aOverpayment) {
        BigDecimal interestPercent = aInputData.getInterestPercent();
        BigDecimal q = AmountsCalculationService.calculateQ(interestPercent);

        BigDecimal residualAmount = aInputData.getAmount();

        BigDecimal interestAmount = AmountsCalculationService.calculateInterestAmount(residualAmount, interestPercent);
        BigDecimal rateAmount = calculateConstantRateAmount(q, interestAmount, residualAmount, aInputData.getAmount(), aInputData.getMonthsDuration());
        BigDecimal capitalAmount = AmountsCalculationService.compareCapitalWithResidual(rateAmount.subtract(interestAmount), residualAmount);


        return new RateAmounts(rateAmount, interestAmount, capitalAmount, aOverpayment);
    }

    @Override
    public RateAmounts calculate(final InputData aInputData,final Overpayment aOverpayment,final Rate aPreviousRate) {
        BigDecimal interestPercent = aInputData.getInterestPercent();
        BigDecimal q = AmountsCalculationService.calculateQ(interestPercent);

        BigDecimal residualAmount = aPreviousRate.getMortageResidual().getResidualAmount();
        BigDecimal referenceAmount = aPreviousRate.getMortageReference().getReferenceAmount();
        BigDecimal referenceDuration = aPreviousRate.getMortageReference().getReferenceDuration();


        BigDecimal interestAmount = AmountsCalculationService.calculateInterestAmount(residualAmount, interestPercent);
        BigDecimal rateAmount = calculateConstantRateAmount(q, interestAmount, residualAmount, referenceAmount, referenceDuration);
        BigDecimal capitalAmount = AmountsCalculationService.compareCapitalWithResidual(rateAmount.subtract(interestAmount), residualAmount);

        return new RateAmounts(rateAmount, interestAmount, capitalAmount, aOverpayment);
    }

    private BigDecimal calculateConstantRateAmount(final BigDecimal q, final BigDecimal interestAmount, final BigDecimal residualAmount, final BigDecimal referenceAmount, final BigDecimal referenceDuration)
    {
        BigDecimal rateAmount = referenceAmount
                .multiply(q.pow(referenceDuration.intValue()))
                .multiply(q.subtract(BigDecimal.ONE))
                .divide(q.pow(referenceDuration.intValue()).subtract(BigDecimal.ONE), 2, RoundingMode.HALF_UP);

        return compareRateWithResidual(rateAmount, interestAmount, residualAmount);
    }

    private BigDecimal compareRateWithResidual(final BigDecimal rateAmount, final BigDecimal interestAmount, final BigDecimal residualAmount) {
        if (rateAmount.subtract(interestAmount).compareTo(residualAmount) >= 0) {
            return residualAmount.add(interestAmount);
        }
        return rateAmount;
    }
}
