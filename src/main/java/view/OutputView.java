package view;

import controller.OutputController;
import java.text.DecimalFormat;

public class OutputView {
    private final OutputController outputController = new OutputController();

    public void printMenu() {
        System.out.println("<주문 메뉴>");
        // ...
        outputController.showOrderMenu();
        System.out.println();
    }

    public void printTotalOrderAmount() {
        System.out.println("<할인 전 총주문 금액>");

        int totalOrderAmount = outputController.getTotalOrderAmount();

        System.out.println(outputController.getMoneyExpression(totalOrderAmount));
    }

    public void printAmountAfterDiscount() {
        System.out.println("<할인 후 예상 결제 금액>");

        int amountAfterDiscount = outputController.getAmountAfterDiscount();

        System.out.println(outputController.getMoneyExpression(amountAfterDiscount));
    }


}
