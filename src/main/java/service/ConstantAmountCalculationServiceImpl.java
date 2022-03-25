package service;

import model.InputData;
import model.Overpayment;
import model.Rate;
import model.RateAmounts;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static service.TimePointServiceImpl.YEAR;

public class ConstantAmountCalculationServiceImpl implements ConstantAmountCalculationService {
    @Override
    public RateAmounts calculate(InputData aInputData, Overpayment aOverpayment) {
        BigDecimal interestPercent = aInputData.getInterestPercent();
        BigDecimal q = calculateQ(interestPercent);

        BigDecimal residualAmount = aInputData.getAmount();
        BigDecimal referenceAmount = aInputData.getAmount();
        BigDecimal residualDuration = aInputData.getMonthsDuration();


        BigDecimal interestAmount = calculateInterestAmount(residualAmount, interestPercent);
        BigDecimal rateAmount = calculateConstantRateAmount(q, referenceAmount, residualDuration,
                interestAmount, residualAmount);
        BigDecimal capitalAmount = calculateCapitalAmount(rateAmount, interestAmount, residualAmount);

        return new RateAmounts(rateAmount, interestAmount, capitalAmount, aOverpayment);
    }

    @Override
    public RateAmounts calculate(InputData aInputData, Overpayment aOverpayment, Rate aPreviousRate) {
        BigDecimal interestPercent = aInputData.getInterestPercent();
        BigDecimal q = calculateQ(interestPercent);

        BigDecimal residualAmount = aPreviousRate.getMortageResidual().getAmount();
        BigDecimal referenceAmount = aPreviousRate.getMortageReference().getReferenceAmount();
        BigDecimal referenceDuration = aPreviousRate.getMortageReference().getReferenceDuration();


        BigDecimal interestAmount = calculateInterestAmount(residualAmount, interestPercent);
        BigDecimal rateAmount = calculateConstantRateAmount(q, referenceAmount, referenceDuration,
                interestAmount, residualAmount);
        BigDecimal capitalAmount = calculateCapitalAmount(rateAmount, interestAmount, residualAmount);

        return new RateAmounts(rateAmount, interestAmount, capitalAmount, aOverpayment);
    }

    private BigDecimal calculateQ(BigDecimal aInterestPercent) {
        return aInterestPercent.divide(YEAR, 10, RoundingMode.HALF_UP).add(BigDecimal.ONE);
    }

    private BigDecimal calculateInterestAmount(BigDecimal aResidualAmount, BigDecimal aInterestPercent) {
        return aResidualAmount.multiply(aInterestPercent).divide(YEAR, 50, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateConstantRateAmount(BigDecimal q, BigDecimal aAmount, BigDecimal aMonthsDuration,
                                                   BigDecimal aInterestAmount, BigDecimal aResidualAmount) {
        BigDecimal rateAmount = aAmount
                .multiply(q.pow(aMonthsDuration.intValue()))
                .multiply(q.subtract(BigDecimal.ONE))
                .divide(q.pow(aMonthsDuration.intValue())
                        .subtract(BigDecimal.ONE), 50, RoundingMode.HALF_UP);

        return compareWithResidual(rateAmount, aInterestAmount, aResidualAmount);
    }

    private BigDecimal compareWithResidual(BigDecimal aRateAmount, BigDecimal aInterestAmount,
                                           BigDecimal aResidualAmount) {
        if (aRateAmount.subtract(aInterestAmount).compareTo(aResidualAmount) >= 0) {
            return aResidualAmount.add(aInterestAmount);
        }
        return aRateAmount;
    }

    private BigDecimal calculateCapitalAmount(BigDecimal aRateAmount, BigDecimal aInterestAmount,
                                              BigDecimal aResidualAmount) {
        BigDecimal capitalAmount = aRateAmount.subtract(aInterestAmount);

        if (capitalAmount.compareTo(aResidualAmount) >= 0) {
            return aResidualAmount;
        }
        return capitalAmount;
    }
}
