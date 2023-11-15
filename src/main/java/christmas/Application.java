package christmas;

import controller.InputController;
import controller.OutputController;
import model.Domain;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InputController inputController = new InputController();
        OutputView outputView = new OutputView();

        inputController.setDate();
        inputController.setMenuOrder();

        Domain.getInstance().setting();
        outputView.printMenu();
        outputView.printTotalOrderAmount();
        outputView.printGiftMenu();
        outputView.printBenefitHistory();
        outputView.printTotalBenefitAmount();
        outputView.printAmountAfterDiscount();
        outputView.printEventBadge();
    }
}
