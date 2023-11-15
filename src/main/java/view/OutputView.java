package view;

import controller.OutputController;

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
        System.out.println();
    }

    public void printGiftMenu() {
        System.out.println("<증정 메뉴>");

        if(outputController.getIsGiftMenu()) {
            System.out.println("샴페인 1개");
        }

        if(!outputController.getIsGiftMenu()) {
            System.out.println("없음");
        }

        System.out.println();
    }

    public void printAmountAfterDiscount() {
        System.out.println("<할인 후 예상 결제 금액>");

        int amountAfterDiscount = outputController.getAmountAfterDiscount();

        System.out.println(outputController.getMoneyExpression(amountAfterDiscount));
        System.out.println();
    }


}
