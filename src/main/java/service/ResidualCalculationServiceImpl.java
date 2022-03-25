package service;

import model.InputData;
import model.MortageResidual;
import model.Rate;
import model.RateAmounts;

import java.math.BigDecimal;

public class ResidualCalculationServiceImpl implements ResidualCalculationService {


    @Override
    public MortageResidual calculate(RateAmounts aRateAmounts, InputData aInputData) {

        BigDecimal residualAmount = calculateResidualAmount(aInputData.getAmount(), aRateAmounts);
        BigDecimal residualDuration = aInputData.getMonthsDuration().subtract(BigDecimal.ONE);

        return new MortageResidual(residualAmount, residualDuration);
    }

    @Override
    public MortageResidual calculate(RateAmounts aRateAmounts, Rate aPreviousRate) {
        MortageResidual residual = aPreviousRate.getMortageResidual();

        BigDecimal residualAmount = calculateResidualAmount(residual.getAmount(), aRateAmounts);
        BigDecimal residualDuration = residual.getDuration().subtract(BigDecimal.ONE);

        return new MortageResidual(residualAmount, residualDuration);
    }

    private BigDecimal calculateResidualAmount(BigDecimal aInputData, RateAmounts aRateAmounts) {
        return aInputData.subtract(aRateAmounts.getCapitalAmount()).max(BigDecimal.ONE)

                .subtract(aRateAmounts.getOverpayment().getAmount())
                .max(BigDecimal.ZERO);
    }
}
