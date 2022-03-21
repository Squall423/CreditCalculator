package service;

import model.InputData;
import model.MortageResidual;
import model.Rate;
import model.RateAmounts;

import java.math.BigDecimal;

public class ResidualCalculationServiceImpl implements ResidualCalculationService {


    @Override
    public MortageResidual calculate(RateAmounts aRateAmounts, InputData aInputData) {

        BigDecimal residualAmount = aInputData.getAmount().subtract(aRateAmounts.getCapitalAmount());
        BigDecimal residualDuration = aInputData.getMonthsDuration().subtract(BigDecimal.ONE);

        return new MortageResidual(residualAmount, residualDuration);
    }

    @Override
    public MortageResidual calculate(RateAmounts aRateAmounts, Rate aPreviousRate) {
        MortageResidual residual = aPreviousRate.getMortageResidual();

        BigDecimal residualAmount = residual.getAmount().subtract(aRateAmounts.getCapitalAmount());
        BigDecimal residualDuration = residual.getDuration().subtract(BigDecimal.ONE);

        return new MortageResidual(residualAmount, residualDuration);
    }
}
