import model.InputData;
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
                new OverpaymentCalculationServiceImpl(),
                new ResidualCalculationServiceImpl(),
                new ReferenceCalculationServiceImpl());

        MortageCalculationService mortageCalculationService = new MortageCalculationServiceImpl(
                printingService,
                rateCalculationService,
                SummaryServiceFactory.create());
        mortageCalculationService.calculate(inputData);


    }
}
