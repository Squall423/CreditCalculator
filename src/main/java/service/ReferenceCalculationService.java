package service;

import model.MortageReference;
import model.Rate;

public interface ReferenceCalculationService {


    MortageReference calculate(Rate aPreviousRate);

    MortageReference calculate();
}
