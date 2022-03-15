import model.InputData;
import service.PrintingService;
import service.PrintingServiceImpl;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        InputData inputData = new InputData()
                .withAmount(new BigDecimal("2988008"))
                .withMonthsDuratuon(BigDecimal.valueOf(168));

        PrintingService printingService = new PrintingServiceImpl();
        printingService.printInputDataInfo(inputData);
    }
}
