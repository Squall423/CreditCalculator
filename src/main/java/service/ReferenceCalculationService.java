package service;

import model.InputData;
import model.MortageReference;
import model.Rate;

public interface ReferenceCalculationService {


    MortageReference calculate(InputData aInputData);

}
