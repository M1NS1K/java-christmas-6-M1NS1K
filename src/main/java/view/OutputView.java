package view;

import controller.OutputController;
import java.util.Map;

public class OutputView {
    private final OutputController outputController = new OutputController();

    public void printMenu() {
        System.out.println("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        System.out.println();
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

    public void printBenefitHistory() {
        Map<String, Integer> benefitHistory = outputController.getBenefitHistory();
        boolean checkSum = true;
        System.out.println("<혜택 내역>");

        for (String benefitName : benefitHistory.keySet()) {
            if(benefitHistory.get(benefitName) != 0) {
                System.out.println(benefitName + ": -"
                        + outputController.getMoneyExpression(benefitHistory.get(benefitName)));
                checkSum = false;
            }
        }

        if(checkSum) {
            System.out.println("없음");
        }

        System.out.println();
    }

    public void printTotalBenefitAmount() {
        System.out.println("<총혜택 금액>");
        if(outputController.getTotalBenefitAmount() > 0) {
            System.out.print("-");
        }

        System.out.println(outputController
                .getMoneyExpression(outputController.getTotalBenefitAmount()));
        System.out.println();
    }

    public void printAmountAfterDiscount() {
        System.out.println("<할인 후 예상 결제 금액>");

        int amountAfterDiscount = outputController.getAmountAfterDiscount();

        System.out.println(outputController.getMoneyExpression(amountAfterDiscount));
        System.out.println();
    }

    public void printEventBadge() {
        System.out.println("<12월 이벤트 배지>");

        System.out.println(outputController.getEventBadge());
        System.out.println();
    }

}
