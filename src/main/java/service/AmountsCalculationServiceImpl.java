package service;

import model.InputData;
import model.Rate;
import model.RateAmounts;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AmountsCalculationServiceImpl implements AmountsCalculationService {

    public static final BigDecimal YEAR = BigDecimal.valueOf(12);
    private final String CASE_NOT_HANDLED = "Case not handled";

    @Override
    public RateAmounts calculate(InputData aInputData) {
        switch (aInputData.getRateType()) {
            case CONSTANT:
                return calculateConstantRate(aInputData);
            case DECREASING:
                return calculateDecreasingRate(aInputData);
            default:
                throw new RuntimeException(CASE_NOT_HANDLED);
        }
    }

    @Override
    public RateAmounts calculate(InputData aInputData, Rate aPreviousRate) {
        switch (aInputData.getRateType()) {
            case CONSTANT:
                return calculateConstantRate(aInputData, aPreviousRate);
            case DECREASING:
                return calculateDecreasingRate(aInputData, aPreviousRate);
            default:
                throw new RuntimeException(CASE_NOT_HANDLED);
        }
    }

    private RateAmounts calculateConstantRate(InputData aInputData) {
        BigDecimal interestPercent = aInputData.getInterestPercent();
        BigDecimal residualAmount = aInputData.getAmount();
        BigDecimal q = calculateQ(aInputData.getInterestPercent());

        BigDecimal rateAmount = calculateConstantRateAmount(q, aInputData.getAmount(), aInputData.getMonthsDuration());
        BigDecimal interestAmount = calculateInterestAmount(residualAmount, interestPercent);
        BigDecimal capitalAmount = calculateConstantCapitalAmount(rateAmount, interestAmount);

        return new RateAmounts(rateAmount, interestAmount, capitalAmount);

    }

    private RateAmounts calculateConstantRate(InputData aInputData, Rate aPreviousRate) {
        BigDecimal interestPercent = aInputData.getInterestPercent();
        BigDecimal residualAmount = aPreviousRate.getMortageResidual().getAmount();
        BigDecimal q = calculateQ(interestPercent);


        BigDecimal rateAmount = calculateConstantRateAmount(q, aInputData.getAmount(), aInputData.getMonthsDuration());
        BigDecimal interestAmount = calculateInterestAmount(residualAmount, interestPercent);
        BigDecimal capitalAmount = calculateConstantCapitalAmount(rateAmount, interestAmount);

        return new RateAmounts(rateAmount, interestAmount, capitalAmount);

    }

    private RateAmounts calculateDecreasingRate(InputData aInputData) {
        return null;
    }

    private RateAmounts calculateDecreasingRate(InputData aInputData, Rate aPreviousRate) {
        return null;
    }

    private BigDecimal calculateQ(BigDecimal aInterestPercent) {
        return aInterestPercent.divide(YEAR, 10, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateConstantRateAmount(BigDecimal q, BigDecimal aAmount, BigDecimal aMonthsDuration) {
        return aAmount
                .multiply(q.pow(aMonthsDuration.intValue()))
                .multiply(q.subtract(BigDecimal.ONE))
                .divide(q.pow(aMonthsDuration.intValue())
                        .subtract(BigDecimal.ONE), 2, RoundingMode.UP);
    }

    private BigDecimal calculateConstantCapitalAmount(BigDecimal aRateAmount, BigDecimal aInterestAmount) {
        return aRateAmount.subtract(aInterestAmount);

    }

    private BigDecimal calculateInterestAmount(BigDecimal aResidualAmount, BigDecimal aInterestPercent) {
        return aResidualAmount.multiply(aInterestPercent).divide(YEAR, 2, RoundingMode.HALF_UP);
    }


}
