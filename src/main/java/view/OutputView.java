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
}
