package service;

import model.InputData;
import model.MortageReference;
import model.Rate;
import model.RateAmounts;

public interface ReferenceCalculationService {

    MortageReference calculate(RateAmounts rateAmounts,InputData aInputData);

    MortageReference calculate( RateAmounts aRateAmounts, final InputData aInputData, Rate aPreviousRate);
}
