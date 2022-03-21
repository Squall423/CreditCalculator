import model.InputData;
import model.RateType;
import service.*;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        InputData inputData = new InputData()
                .withAmount(new BigDecimal("298000"))
                .withMonthsDuration(BigDecimal.valueOf(180));


        PrintingService printingService = new PrintingServiceImpl();

        RateCalculationService rateCalculationService = new RateCalculationServiceImpl(
                new TimePointServiceImpl(),
                new AmountsCalculationServiceImpl(),
                new ResidualCalculationServiceImpl());

        MortageCalculationService mortageCalculationService = new MortageCalculationServiceImpl(printingService,
                rateCalculationService);
        mortageCalculationService.calculate(inputData);

    }
}
