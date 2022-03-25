package service;

import model.InputData;
import model.MortageReference;
import model.Rate;
import model.RateAmounts;

public interface ReferenceCalculationService {


    MortageReference calculate(InputData aInputData);


    MortageReference calculate(InputData aInputData, RateAmounts aRateAmounts, Rate aPreviousRate);
}
