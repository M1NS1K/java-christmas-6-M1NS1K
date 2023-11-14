package christmas;

import controller.InputController;
import model.Domain;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InputController inputController = new InputController();

        inputController.setDate();
        inputController.setMenuOrder();
        Domain.getInstance().showMenuOrder();
    }
}
