package service;

import model.InputData;
import model.MortageResidual;
import model.Rate;
import model.RateAmounts;
import service.exception.MortageException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ResidualCalculationServiceImpl implements ResidualCalculationService {

    @Override
    public MortageResidual calculate(RateAmounts aRateAmounts
            , InputData inputData) {
        if (BigDecimal.ZERO.equals(inputData.getAmount())) {
            return new MortageResidual(BigDecimal.ZERO, BigDecimal.ZERO);
        } else {
            BigDecimal residualAmount = calculateResidualAmount(inputData.getAmount(), aRateAmounts
            );
            BigDecimal residualDuration = calculateResidualDuration(inputData, residualAmount, inputData.getMonthsDuration(), aRateAmounts
            );
            return new MortageResidual(residualAmount, residualDuration);
        }
    }

    @Override
    public MortageResidual calculate(RateAmounts aRateAmounts
            , final InputData inputData, Rate previousRate) {
        BigDecimal previousResidualAmount = previousRate.getMortageResidual().getResidualAmount();
        BigDecimal previousResidualDuration = previousRate.getMortageResidual().getResidualDuration();

        if (BigDecimal.ZERO.equals(previousResidualAmount)) {
            return new MortageResidual(BigDecimal.ZERO, BigDecimal.ZERO);
        } else {
            BigDecimal residualAmount = calculateResidualAmount(previousResidualAmount, aRateAmounts
            );
            BigDecimal residualDuration = calculateResidualDuration(inputData, residualAmount,
                    previousResidualDuration, aRateAmounts
            );
            return new MortageResidual(residualAmount, residualDuration);
        }
    }

    private BigDecimal calculateResidualDuration(
            InputData inputData,
            BigDecimal residualAmount,
            BigDecimal previousResidualDuration,
            RateAmounts rateAmounts

    ) {
        if (rateAmounts
                .getOverpayment().getAmount().compareTo(BigDecimal.ZERO) > 0) {
            switch (inputData.getRateType()) {
                case CONSTANT:
                    return calculateConstantResidualDuration(inputData, residualAmount, rateAmounts
                    );
                case DECREASING:
                    return calculateDecreasingResidualDuration(residualAmount, rateAmounts
                    );
                default:
                    throw new MortageException("Case not handled");
            }
        } else {
            return previousResidualDuration.subtract(BigDecimal.ONE);
        }
    }

    private BigDecimal calculateDecreasingResidualDuration(BigDecimal residualAmount, RateAmounts aRateAmounts) {
        return residualAmount.divide(aRateAmounts.getCapitalAmount(), 0, RoundingMode.CEILING);
    }

    @SuppressWarnings("UnnecessaryLocalVariable")

    private BigDecimal calculateConstantResidualDuration(InputData inputData, BigDecimal residualAmount,
                                                         RateAmounts aRateAmounts
    ) {

        BigDecimal q = AmountsCalculationService.calculateQ(inputData.getInterestPercent());


        BigDecimal xNumerator = aRateAmounts.getRateAmount();
     
        BigDecimal xDenominator =
                aRateAmounts.getRateAmount().subtract(residualAmount.multiply(q.subtract(BigDecimal.ONE)));

        BigDecimal x = xNumerator.divide(xDenominator, 10, RoundingMode.HALF_UP);
        BigDecimal y = q;

        BigDecimal logX = BigDecimal.valueOf(Math.log(x.doubleValue()));
        BigDecimal logY = BigDecimal.valueOf(Math.log(y.doubleValue()));

        return logX.divide(logY, 0, RoundingMode.CEILING);
    }

    private BigDecimal calculateResidualAmount(final BigDecimal residualAmount, final RateAmounts aRateAmounts) {
        return residualAmount
                .subtract(aRateAmounts.getCapitalAmount())
                .subtract(aRateAmounts.getOverpayment().getAmount())
                .max(BigDecimal.ZERO);
    }
}
