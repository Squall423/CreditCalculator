package service;

import model.InputData;
import model.Rate;
import model.RateAmounts;

public interface AmountsCalculationService {
    RateAmounts calculate(InputData aInputData);

    RateAmounts calculate(InputData aInputData, Rate aPreviousRate);
}
