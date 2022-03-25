package service;

import model.InputData;
import model.Overpayment;
import model.Rate;
import model.RateAmounts;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static service.TimePointServiceImpl.YEAR;

public class DecreasingAmountsCalculationServiceImpl implements DecreasingAmountsCalculationService {
    @Override
    public RateAmounts calculate(InputData aInputData, Overpayment aOverpayment) {
        BigDecimal interestPercent = aInputData.getInterestPercent();
        BigDecimal residualAmount = aInputData.getAmount();
        BigDecimal referenceAmount = aInputData.getAmount();
        BigDecimal residualDuration = aInputData.getMonthsDuration();

        BigDecimal interestAmount = calculateInterestAmount(residualAmount, interestPercent);
        BigDecimal capitalAmount = calculateCapitalAmount(referenceAmount, residualDuration, residualAmount);
        BigDecimal rateAmount = calculateDecreasingRateAmount(interestAmount, capitalAmount);

        return new RateAmounts(rateAmount, interestAmount, capitalAmount, aOverpayment);
    }

    @Override
    public RateAmounts calculate(InputData aInputData, Overpayment aOverpayment, Rate aPreviousRate) {
        BigDecimal interestPercent = aInputData.getInterestPercent();
        BigDecimal residualAmount = aPreviousRate.getMortageResidual().getAmount();
        BigDecimal referenceAmount = aPreviousRate.getMortageReference().getReferenceAmount();
        BigDecimal referenceDuration = aPreviousRate.getMortageReference().getReferenceDuration();

        BigDecimal interestAmount = calculateInterestAmount(residualAmount, interestPercent);
        BigDecimal capitalAmount = calculateCapitalAmount(referenceAmount, referenceDuration, residualAmount);
        BigDecimal rateAmount = calculateDecreasingRateAmount(interestAmount, capitalAmount);

        return new RateAmounts(rateAmount, interestAmount, capitalAmount, aOverpayment);
    }

    private BigDecimal calculateInterestAmount(BigDecimal aResidualAmount, BigDecimal aInterestPercent) {
        return aResidualAmount.multiply(aInterestPercent).divide(YEAR, 50, RoundingMode.HALF_UP);
    }


    private BigDecimal calculateDecreasingRateAmount(BigDecimal capitalAmount, BigDecimal aInterestAmount) {
        return capitalAmount.add(aInterestAmount);
    }

    private BigDecimal calculateCapitalAmount(BigDecimal aAmount, BigDecimal aMonthsDuration,
                                              BigDecimal aResidualAmount) {
        BigDecimal capitalAmount = aAmount.divide(aMonthsDuration, 50, RoundingMode.HALF_UP);

        if (capitalAmount.compareTo(aResidualAmount) >= 0) {
            return aResidualAmount;
        }
        return capitalAmount;
    }
}
