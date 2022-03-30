package service;

import model.InputData;
import model.MortageResidual;
import model.Rate;
import model.RateAmounts;

public interface ResidualCalculationService {

    MortageResidual calculate(RateAmounts aRateAmounts, InputData aInputData);

    MortageResidual calculate(RateAmounts aRateAmounts, final InputData inputData, Rate aPreviousRate);
}
