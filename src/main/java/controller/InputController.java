package controller;

import model.Domain;
import model.Service;
import view.InputView;

public class InputController {
    private final Domain DOMAIN = Domain.getInstance();
    private final InputView INPUT = new InputView();

    public void setMenuOrder() {
        DOMAIN.setMenuOrder(INPUT.readMenu());
    }

    public void setDate() {
        DOMAIN.setDate(INPUT.readDate());
    }
}
